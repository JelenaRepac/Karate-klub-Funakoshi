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
import rs.fon.np.application.kkfunakoshi.domain.City;
import rs.fon.np.application.kkfunakoshi.domain.Competition;

/**
 * Predstavlja model za tabelu Competition.
 * 
 * @author Jelena Repac
 */
public class CompetitionTableModel extends AbstractTableModel{

	/**
	 * Lista takmicenja za prikaz u tabeli
	 */
    List<Competition> competitions;
    /**
     * Nazivi kolona u tabeli
     */
    String columnNames[]= new String[]{"Name","Date","Competition hall","City"};
    /**
     * Klase kolona u tabeli
     */
    Object columnClass[] = new Object[]{String.class,String.class,String.class,City.class};
    /**
     * Formator datuma
     */
    SimpleDateFormat format= new SimpleDateFormat("yyyy-MM-dd");
    /**
     * Konstruktor
     * @param competitions takmicenja za prikaz u tabeli
     */
    public CompetitionTableModel(List<Competition> competitions){
        this.competitions=competitions;
    }
    /**
     * Vraca broj redova tabele.
     * @return broj redova tabele
     */
    @Override
    public int getRowCount() {
       return competitions.size();
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
     * @param column kolona ciji naziv se trazi
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
     * @param columnIndex kolona cija klasa se trazi
     * @return klasa odredjene kolone
     */
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if(columnIndex<0 || columnIndex> columnClass.length) return Object.class;
        return (Class<?>) columnClass[columnIndex];
    }
    
    /**
     * Vraca vrednost koja se nalazi u odredjenom redu i koloni.
     * @param rowIndex indeks reda
     * @param columnIndex indeks kolone
     * @return vrednost u odredjenom redu i koloni
     */
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
       Competition competition= competitions.get(rowIndex);
       switch (columnIndex) {
       case 0:
           return competition.getName();
       case 1:
           return (null != competition.getDate()) ? format.format(competition.getDate()) : null;
       case 2:
           return competition.getCompetitionHall();
       case 3:
           return competition.getCity();
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
       Competition c= competitions.get(rowIndex);
       switch(columnIndex){
           case 0: c.setName((String)aValue); break;
           case 1: {
               try {
                   c.setDate(format.parse((String)aValue));
               } catch (ParseException ex) {
                   Logger.getLogger(CompetitionTableModel.class.getName()).log(Level.SEVERE, null, ex);
               }
           }
           case 2: c.setCompetitionHall((String) aValue); break;
           case 3: c.setCity((City) aValue); break;
           default: break;
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
     * Metoda za dodavanje takmiceja u listu takmicenja.
     * @param c takmicenje
     */
    public void addCompetition(Competition c){
        competitions.add(c);
        fireTableRowsInserted(competitions.size()-1, competitions.size()-1);
    }
    /**
     * Vraca listu svih takmicenja.
     * @return lista takmicenja
     */
    public List<Competition> getCompetitions(){
        return competitions;
    }
    /**
     * Vraca takmicenje sa odredjene pozicije u tabeli.
     * @param row red u kojem se nalazi takmicenje
     * @return takmicenje sa odredjene pozicije
     */
    public Competition getCompetition(int row){
        return competitions.get(row);
    }
    /**
     * Brise takmicenje sa odredjene pozicije u tabeli.
     * @param row red u kojem se nalazi takmicenje
     */ 
    public void deleteCompetition(int row){
        competitions.remove(row);
        fireTableDataChanged();
    }
    /**
     * Brise prosledjeno takmicenje iz tabele.
     * @param c takmicenje koje se brise
     */
    public void deleteCompetition(Competition c){
        competitions.remove(c);
        fireTableDataChanged();
    }
    /**
     * Menja trenutnu listu sa novom listom takmicenja.
     * @param competitions takmicenja koja se postavljaju u listu 
     */
    public void setCompetitions(List<Competition> competitions){
        this.competitions=competitions;
        fireTableDataChanged();
    }
}
