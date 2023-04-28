/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.fon.np.application.kkfunakoshi.view.form.component.table;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import rs.fon.np.application.kkfunakoshi.domain.Category;
import rs.fon.np.application.kkfunakoshi.domain.Member;
import rs.fon.np.application.kkfunakoshi.domain.Team;

/**
 * Predstavlja model za tabelu Team.
 * @author Jelena Repac
 */
public class TeamTableModel extends AbstractTableModel{

	/**
	 * Lista timova za prikaz u tabeli
	 */
    List<Team> teams;
    /**
     * Nazivi kolona u tabeli
     */
    String[] columnNames= new String[]{"Name","Category","Members"};
    /**
     * Klase kolona u tabeli
     */
    Object[] columnClass= new Object[]{String.class,Category.class,List.class};
    /**
     * Konstruktor
     * @param teams timovi za prikaz u tabeli
     */
    public TeamTableModel(List<Team> teams) {
        this.teams=teams;
    }
    
    /**
     * Vraca broj redova tabele.
     * @return broj redova tabele
     */
    @Override
    public int getRowCount() {
        return teams.size();
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
       Team t= teams.get(rowIndex);
       if (columnIndex == 0) {
    	    return t.getName();
    	} else if (columnIndex == 1) {
    	    return t.getCategory();
    	} else if (columnIndex == 2) {
    	    return t.getMembers();
    	} else {
    	    return "n/a";
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
     * Vraca tim sa odredjene pozicije u tabeli.
     * @param row red u kojem se nalazi odredjeni tim
     * @return Team tim sa odredjene pozicije
     */
    public Team getTeam(int row){
        return teams.get(row);
    }
}
