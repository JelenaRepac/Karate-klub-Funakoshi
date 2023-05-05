/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.fon.np.application.kkfunakoshi.view.form.component.table;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import rs.fon.np.application.kkfunakoshi.controller.ControllerUI;
import rs.fon.np.application.kkfunakoshi.domain.Category;
import rs.fon.np.application.kkfunakoshi.domain.Competition;
import rs.fon.np.application.kkfunakoshi.domain.Medal;
import rs.fon.np.application.kkfunakoshi.domain.Member;
import rs.fon.np.application.kkfunakoshi.domain.Result;
import rs.fon.np.application.kkfunakoshi.domain.Team;

/**
 * Predstavlja model za tabelu Result.
 * @author Jelena Repac
 */
public class ResultTableModel extends AbstractTableModel {

	/**
	 * Lista rezultata za prikaz u tabeli
	 */
    List<Result> results;
    /**
     * Nazivi kolona u tabeli
     */
    String[] columnNames= new String[]{"Competition","Member","Team","Category","Medal"};
    /**
     * Klase kolona u tabeli
     */
    Object[] columnClass= new Object[]{Competition.class,Member.class,Team.class,String.class,String.class};
    /**
     * Konstruktor
     * @param results lista rezultata za prikaz u tabeli
     */
    public ResultTableModel(List<Result> results) {
        this.results = results;
    }
    /**
     * Vraca broj redova tabele.
     * @return broj redova tabele
     */
    @Override
    public int getRowCount() {
       return results.size();
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
        return (Class<?>) columnClass[columnIndex];
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
     * Vraca vrednost koja se nalazi u odredjenom redu i koloni.
     * @param rowIndex indeks reda
     * @param columnIndex indeks kolone
     * @return vrednost u odredjenom redu i koloni
     */
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Result r= results.get(rowIndex);
        switch (columnIndex) {
        case 0:
            return r.getCompetition();
        case 1:
            return r.getMember();
        case 2:
            return r.getTeam();
        case 3:
            return r.getCategory();
        case 4:
            return r.getMedals();
        default:
            return "n/a";
        }
    }
    /**
     * Metoda za dodavanje rezultata u listu rezultata.
     * @param r rezultat
     */
    public void addResult(Result r){
        results.add(r);
        fireTableRowsInserted(results.size()-1, results.size()-1);
    }
    /**
     * Brise rezultat sa odredjene pozicije u tabeli.
     * @param selectedRow red u kojem se nalazi rezultat
     */
    public void delete(int selectedRow) {
        results.remove(selectedRow);
        fireTableDataChanged();
    }
    /**
     * Vraca listu svih rezultata.
     * @return lista rezultata
     */
    public List<Result> getResults(){
        return results;
    }
    /**
     * Vraca ukupan broj zlatnih medalja.
     * @return broj zlatnih medalja
     */
    public Long getGoldMedals(){
        Long gold=0L;
        for(Result r:results){
            if(r.getMedals().equals(Medal.GOLD)){
                gold++;
            }
        }
        return gold;
    }
    /**
     * Vraca ukupan broj srebrnih medalja.
     * @return broj srebrnih medalja
     */
     public Long getSilverMedals(){
        Long silver=0L;
        for(Result r:results){
            if(r.getMedals().equals(Medal.SILVER)){
                silver++;
            }
        }
        return silver;
    }
     /**
      * Vraca ukupan broj bronzanih medalja.
      * @return broj bronzanih medalja
      */
    public Long getBronzeMedals(){
        Long bronze=0L;
        for(Result r:results){
            if(r.getMedals().equals(Medal.BRONZE)){
                bronze++;
            }
        }
        return bronze;
    }
    /**
     * Vraca rezultat sa odredjene pozicije u tabeli.
     * @param selectedRow red u kojem se nalazi rezultat
     * @return Result rezultat sa odredjene pozicije
     */
    public Result getResult(int selectedRow) {
        return results.get(selectedRow);
    }
    /**
     * Provera da li rezultat sadrzi prosledjenog clana.
     * 
     * @param result rezultat za proveru
     * @return true clan vec postoji u listi
     */
    public boolean containsMember(Result result) {
       for(Result r: results){
            if(r.getMember()!=null){
                if(r.getMember().getName().equals(result.getMember().getName()) && r.getMedals().equals(result.getMedals()) && r.getCategory().equals(result.getCategory()))
                    return true;
            }
       }
       return false;
    }
    /**
     * Provera da li rezultat sadrzi prosledjeni tim.
     * 
     * @param result rezultat za proveru
     * @return true tim vec postoji u listi
     */
    public boolean containsTeam(Result result) {
        for(Result r: results){
            if(r.getTeam()!=null){
                if(r.getTeam().getName().equals(result.getTeam().getName()) && r.getMedals().equals(result.getMedals()))
                    return true;
            }
       }
       return false;
    }
    /**
     * Azurira tabelu rezultata sa podacima iz baze.
     * @throws Exception ukoliko dodje do greske prilikom preuzimanja podataka iz baze
     */
    public void refreshView() throws Exception{
        results=ControllerUI.getInstance().getByQueryResults(" ORDER BY medal");
        fireTableDataChanged();
        
    }
}
