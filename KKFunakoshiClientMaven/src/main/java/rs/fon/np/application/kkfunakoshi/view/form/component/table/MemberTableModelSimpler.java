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

import rs.fon.np.application.kkfunakoshi.controller.ControllerUI;
import rs.fon.np.application.kkfunakoshi.domain.Member;

import rs.fon.np.application.kkfunakoshi.domain.Belt;
import rs.fon.np.application.kkfunakoshi.domain.Category;
import rs.fon.np.application.kkfunakoshi.domain.Discipline;
import rs.fon.np.application.kkfunakoshi.domain.Gender;

/**
 *
 * @author Jeks
 */
public class MemberTableModelSimpler extends AbstractTableModel{
    
     List<Member> members;
    String[] columnNames= new String[]{"Name","Lastname","Gender","Date of birth",
        "Belt", "Discipline","Category","Medals","Gold","Silver","Bronze"};
    Object[] columnClass= new Object[]{String.class, String.class,String.class, String.class, Belt.class,Discipline.class, Category.class,
        String.class,String.class,String.class,String.class}; 
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    public MemberTableModelSimpler(List<Member> members) {
        this.members=members;
    }

    @Override
    public int getRowCount() {
      return members.size();
    }

    @Override
    public int getColumnCount() {
            return columnClass.length;
       }
    @Override
    public String getColumnName(int column) {
        if(column>columnNames.length || column<0) return "n/a";
        return columnNames[column];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if(columnIndex<0 || columnIndex>columnClass.length) return Object.class;
        return (Class) columnClass[columnIndex];
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Member m= members.get(rowIndex);
        switch (columnIndex) {
        case 0:
            return m.getName();
        case 1:
            return m.getLastname();
        case 2:
            return m.getGender();
        case 3:
            return (m.getBirthday() != null) ? format.format(m.getBirthday()) : null;
        case 4:
            return m.getBelt();
        case 5:
            return m.getCategory();
        case 6:
            return m.getDiscipline();
        case 7:
            return m.getMedals();
        case 8:
            return m.getGoldMedals();
        case 9:
            return m.getSilverMedals();
        case 10:
            return m.getBronzeMedals();
        default:
            return "n/a";
    }



    }
     @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Member m = members.get(rowIndex);
        switch (columnIndex) {
           
            case 0: m.setName((String) aValue); break;
            case 1: m.setLastname((String) aValue);break;
            case 2: m.setGender((Gender) aValue); break;
            case 3: {
                try {
                    m.setBirthday(format.parse((String)aValue));
                    break;
                } catch (ParseException ex) {
                    Logger.getLogger(MemberTableModel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
           
            case 4: m.setBelt((Belt) aValue);break;
            case 5: m.setCategory((Category) aValue); break;
            case 6: m.setDiscipline((Discipline)aValue); break;
            case 7: m.setMedals((Long) aValue);break;
            case 8: m.setGoldMedals((Long)aValue); break;
            case 9: m.setSilverMedals((Long) aValue); break;
            case 10: m.setBronzeMedals((Long) aValue); break;
            default:
                break;
            }
        }

    @Override
     public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }
    
     public void addMember(Member m){
         members.add(m);
         fireTableRowsInserted(members.size()-1, members.size()-1);
     }

    public List<Member> getMembers() {
        return members;
    }

    public Member getMember(int row){
        return members.get(row);
    }
    
    public void deleteMember(int row) {
        members.remove(row);
        fireTableDataChanged();
    }
    public void deleteMember(Member member){
        members.remove(member);
        fireTableDataChanged();
    }
     
    public void setMembers(List<Member> members){
        this.members=members;
        fireTableDataChanged();
    }
    
    public void refreshView() throws Exception{
        members= ControllerUI.getInstance().getByQuery("");
        fireTableDataChanged();
    }

    public boolean contains(Member m) {
         
        for(Member member: members){
            if(member.equals(m))
                return true;
        }
        return false;
    }
    
}
