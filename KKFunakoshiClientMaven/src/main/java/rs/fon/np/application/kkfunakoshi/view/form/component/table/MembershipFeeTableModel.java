/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.fon.np.application.kkfunakoshi.view.form.component.table;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;
import rs.fon.np.application.kkfunakoshi.domain.MembershipFee;

/**
 *
 * @author Jeks
 */
public class MembershipFeeTableModel extends AbstractTableModel{
    
    List<MembershipFee> membershipFees;
    String[] columnNames= new String[]{"Date", "Amount"};
    Object[] columnClass= new Object[]{String.class,Double.class};
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    
    public MembershipFeeTableModel(List<MembershipFee> membershipFees) {
        this.membershipFees=membershipFees;
    }

    @Override
    public int getRowCount() {
        return membershipFees.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        MembershipFee mf= membershipFees.get(rowIndex);
       switch(columnIndex){
           
           case 0 :
                return (mf.getDate()!=null)?format.format(mf.getDate()):null;
            case 1 :
                return mf.getAmount();
            default:
                return "n/a";
            
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if(columnIndex<0 || columnIndex>columnClass.length) return Object.class;
        return (Class) columnClass[columnIndex];
    }

    @Override
    public String getColumnName(int column) {
        if(column>columnNames.length || column<0) return "n/a";
        return columnNames[column];
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        MembershipFee m= membershipFees.get(rowIndex);
        switch (columnIndex) {
           
           
            case 0: {
                try {
                    m.setDate(format.parse((String) aValue));
                } catch (ParseException ex) {
                    Logger.getLogger(MembershipFeeTableModel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            break;

            case 1: m.setAmount((Double) aValue); break;
         
            default:
                break;
            }
    }

    @Override
     public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }
    
     public void addMembershipFee(MembershipFee m) {
        membershipFees.add(m);
        fireTableRowsInserted(membershipFees.size()-1, membershipFees.size()-1);
     }

    public List<MembershipFee> getMembershipFees() {
        return membershipFees;
    }

    public MembershipFee getMembershipFee(int row){
        return membershipFees.get(row);
    }
    
    public void deleteMembershipFee(int row) {
        membershipFees.remove(row);
        fireTableDataChanged();
    }

    public boolean exists(MembershipFee membershipFee) {
        
        String[] date= format.format(membershipFee.getDate()).split("-");
        System.out.println(membershipFee.getDate().toString());
        int monthOfPaying= Integer.parseInt(date[1]);
        for(MembershipFee mf: membershipFees){
            String[] d=format.format(mf.getDate()).split("-");
            int month=Integer.parseInt(d[1]);
            if(mf.getDate().equals(membershipFee.getDate()) || month==monthOfPaying ){
                return true;
            }
        }
        return false;
    }

    public boolean contains(MembershipFee membershipFee) {
        for(MembershipFee m: membershipFees){
            if(m.getDate().equals(membershipFee.getDate())){
                return true;
            }
        }
        return false;
    }
    
}
