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
import rs.fon.np.application.kkfunakoshi.domain.Belt;
import rs.fon.np.application.kkfunakoshi.domain.Category;
import rs.fon.np.application.kkfunakoshi.domain.City;
import rs.fon.np.application.kkfunakoshi.domain.Discipline;
import rs.fon.np.application.kkfunakoshi.domain.Gender;
import rs.fon.np.application.kkfunakoshi.domain.Member;

/**
 * Predstavlja model za tabelu Member
 * @author Jelena Repac
 */
public class MemberTableModel extends AbstractTableModel{
	/**
	 * Lista clanova za prikaz u tabeli
	 */
    List<Member> members;
    /**
     * Nazivi kolona u tabeli
     */
    String[] columnNames= new String[]{"Name","Lastname","Gender","Date of birth","Mothers name",
        "Fathers name","City","Adress","Belt", "Discipline","Category","Medals","Gold","Silver","Bronze","Debt"};
    /**
     * Klase kolona u tabeli
     */
    Object[] columnClass= new Object[]{String.class, String.class,String.class, String.class, String.class,String.class, City.class, 
        String.class, Belt.class, Discipline.class, Category.class,String.class,String.class,String.class,String.class,String.class}; 
    /**
     * Formator datuma
     */
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * Konstruktor
     * @param members clanovi za prikaz u tabeli
     */
    public MemberTableModel(List<Member> members) {
        this.members=members;
    }
    /**
     * Vraca broj redova tabele.
     * @return broj redova tabele
     */
    @Override
    public int getRowCount() {
      return members.size();
    }
    /**
     * Vraca broj kolona tabele.
     * @return broj kolona
     */
    @Override
    public int getColumnCount() {
            return columnClass.length;
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
     * Vraca vrednost koja se nalazi u odredjenom redu i koloni.
     * @param rowIndex indeks reda
     * @param columnIndex indeks kolone
     * @return vrednost u odredjenom redu i koloni
     */
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Member m= members.get(rowIndex);
        switch(columnIndex){
        case 0:
            return m.getName();
        case 1:
            return m.getLastname();
        case 2:
            return m.getGender();
        case 3:
            return (m.getBirthday() != null) ? format.format(m.getBirthday()) : null;
        case 4:
            return m.getMothersName();
        case 5:
            return m.getFathersName();
        case 6:
            return m.getCity();
        case 7:
            return m.getAdress();
        case 8:
            return m.getBelt();
        case 9:
            return m.getCategory();
        case 10:
            return m.getDiscipline();
        case 11:
            return m.getMedals();
        case 12:
            return m.getGoldMedals();
        case 13:
            return m.getSilverMedals();
        case 14:
            return m.getBronzeMedals();
        case 15:
            return m.getTotalDebt();
        default:
            return "n/a";
        }
    }
    /**
     * Postavlja vrednost na odredjeni red i kolonu.
     * @param rowIndex indeks reda
     * @param columnIndex indeks kolone
     * @param aValue vrednost koja se postavlja
     */
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
            case 4: m.setMothersName((String)aValue);break;
            case 5: m.setFathersName((String) aValue);break;
            case 6: m.setCity((City) aValue);break;
            case 7: m.setAdress((String)aValue);break;
            case 8: m.setBelt((Belt) aValue);break;
            case 9: m.setCategory((Category) aValue); break;
            case 10: m.setDiscipline((Discipline)aValue); break;
            case 11: m.setMedals((Long) aValue);break;
            case 12: m.setGoldMedals((Long)aValue); break;
            case 13: m.setSilverMedals((Long) aValue); break;
            case 14: m.setBronzeMedals((Long) aValue); break;
            case 15: m.setTotalDebt((Double) aValue); break;
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
        return false;
    }
    /**
     * Metoda za dodavanje clana u listu clanova.
     * @param m clan
     */
     public void addMember(Member m){
         members.add(m);
         fireTableRowsInserted(members.size()-1, members.size()-1);
     }
     /**
      * Vraca listu svih clanova.
      * @return List lista clanova
      */
    public List<Member> getMembers() {
        return members;
    }
    /**
     * Vraca clana sa odredjene pozicije u tabeli.
     * @param row red u kojem se nalazi clan
     * @return clan clan sa odredjene pozicije
     */
    public Member getMember(int row){
        return members.get(row);
    }
    /**
     * Brise clana sa odredjene pozicije u tabeli.
     * @param row red u kojem se nalazi clan
     */
    public void deleteMember(int row) {
        members.remove(row);
        fireTableDataChanged();
    }
    /**
     * Brise prosledjenog clana iz tabele.
     * @param member clan kojeg brisemo iz tabele
     */
    public void deleteMember(Member member){
        members.remove(member);
        fireTableDataChanged();
    }
    /**
     * Menja trenutnu listu sa novom listom clanova.
     * @param members lista svih clanova iz tabele
     */
    public void setMembers(List<Member> members){
        this.members=members;
        fireTableDataChanged();
    }
    /**
     * Azurira tabelu clanova sa podacima iz baze.
     * @throws Exception ukoliko dodje do greske prilikom preuzimanja podataka iz baze
     */
    public void refreshView() throws Exception{
        members= ControllerUI.getInstance().getByQuery("");
        fireTableDataChanged();
    }
    /**
     * Provera da li prosledjen clan postoji u listi.
     * 
     * @param m clan koji se proverava
     * @return true ukoliko clan vec postoji u listi
     */
    public boolean contains(Member m) {
       for(Member member: members){
           if(member.equals(m))
               return true;
       }
       return false;
    }
    
}
