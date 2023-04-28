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
 *	Predstavlja model za tabelu MembershipFee.
 * @author Jelena Repac
 */
public class MembershipFeeTableModel extends AbstractTableModel{
    /**
     * Lista clanarina za prikaz u tabeli
     */
    List<MembershipFee> membershipFees;
    /**
     * Nazivi kolona u tabeli
     */
    String[] columnNames= new String[]{"Date", "Amount"};
    /**
     * Klase kolona u tabeli
     */
    Object[] columnClass= new Object[]{String.class,Double.class};
    /**
     * Formator datuma
     */
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    /**
     * Konstruktor
     * @param membershipFees lista clanarina za prikaz u tabeli
     */
    public MembershipFeeTableModel(List<MembershipFee> membershipFees) {
        this.membershipFees=membershipFees;
    }
    /**
     * Vraca broj redova tabele.
     * @return broj redova tabele
     */
    @Override
    public int getRowCount() {
        return membershipFees.size();
    }
    /**
     * Vraca broj kolona tabele.
     * @return broj kolona
     */
    @Override
    public int getColumnCount() {
        return columnNames.length;
    }
    /**
     * Vraca vrednost koja se nalazi u odredjenom redu i koloni.
     * @param rowIndex indeks reda
     * @param columnIndex indeks kolone
     * @return vrednost u odredjenom redu i koloni
     */
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
    /**
     * Vraca klasu odredjene kolone.
     * Ukoliko je prosledjen broj kolone veci od broja kolona ili ukoliko je prosledjeni broj manji od nule vraca se n/a vrednost.
     * @param columnIndex kolona ciju klasu trazimo
     * @return klasa odredjene kolone
     */
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if(columnIndex<0 || columnIndex>columnClass.length) return Object.class;
        return (Class) columnClass[columnIndex];
    }
    /**
     * Vraca naziv odredjene kolone.
     * Ukoliko je prosledjen broj kolone veci od broja kolona ili ukoliko je prosledjeni broj manji od nule vraca se n/a vrednost.
     * @param column kolona ciji naziv trazimo
     * @return naziv kolone
     */
    @Override
    public String getColumnName(int column) {
        if(column>columnNames.length || column<0) return "n/a";
        return columnNames[column];
    }
    /**
     * Postavlja vrednost na odredjeni red i kolonu.
     * @param rowIndex indeks reda
     * @param columnIndex indeks kolone
     * @param aValue vrednost koja se postavlja
     */
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
    /**
     * Proverava da li celija moze da se menja.
     * @param rowIndex indeks reda
     * @param columnIndex indeks kolone
     * @return false indikator da celije u odredjenom redu i koloni nisu promenljive
     */
    @Override
     public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }
    /**
     * Metoda za dodavanje clanarine u listu clanarina.
     * @param m clanarina
     */
     public void addMembershipFee(MembershipFee m) {
        membershipFees.add(m);
        fireTableRowsInserted(membershipFees.size()-1, membershipFees.size()-1);
     }
	/**
	 *  Vraca listu svih clanarina.
	 * @return List lista clanarina
	 */
    public List<MembershipFee> getMembershipFees() {
        return membershipFees;
    }
    /**
     * Vraca clanarinu sa odredjene pozicije u tabeli
     * @param row red u kojem se nalazi clanarina
     * @return clanarina sa odredjene pozicije
     */
    public MembershipFee getMembershipFee(int row){
        return membershipFees.get(row);
    }
    /**
     * Brise clanarinu sa odredjene pozicije u tabeli.
     * @param row red u kojem se nalazi clanarina
     */
    public void deleteMembershipFee(int row) {
        membershipFees.remove(row);
        fireTableDataChanged();
    }

    /**
     * Provera da li prosledjena clanarina postoji u listi.
     * 
     * @param membershipFee clanarina koja se proverava
     * @return true ukoliko clanarina vec postoji u listi
     */
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

    
    
}
