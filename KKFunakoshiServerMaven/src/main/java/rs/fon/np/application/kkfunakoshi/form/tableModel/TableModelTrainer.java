/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.fon.np.application.kkfunakoshi.form.tableModel;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import  rs.fon.np.application.kkfunakoshi.controller.Controller;
import  rs.fon.np.application.kkfunakoshi.domain.Trainer;

/**
 * Predstavlja model za tabelu Trainer.
 * @author Jelena Repac
 */
public class TableModelTrainer extends AbstractTableModel{

	 /**
     * Nazivi kolona u tabeli
     */
    String[] columnNames= new String[]{"Name","Lastname","Firstname","Password","Login info"};
    /**
     * Klase kolona u tabeli
     */
    private Class[] columnClasses = {String.class, String.class, String.class, String.class,String.class};
    /**
	 * Lista trenera za prikaz u tabeli
	 */
    private List<Trainer> users;
    
    /**
     * Konstruktor
     * @throws Exception Ukoliko dodje do greske prilikom vracanja trenera
     */
    public TableModelTrainer() throws Exception{
        users= Controller.getInstance().getTrainers();
    }
    
    /**
     * Vraca broj redova tabele.
     * @return broj redova tabele
     */
    @Override
    public int getRowCount() {
        return users == null ? 0 : users.size();
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
       Trainer user= users.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return user.getFirstname();
            case 1:
                return user.getLastname();
            case 2:
                return user.getUsername();
            case 3:
                return "********";
            case 4:
                return user.isLoggedIn();
            default:
                return "N/A";
        }
    }
    /**
     * Vraca naziv odredjene kolone.
     * @param column kolona ciji naziv se trazi
     * @return naziv kolone
     */
    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    /**
     * Brisanje korsnika iz baze, ukoliko korisnik nije prijavljen.
     * Ukoliko jeste baca se Exception sa porukom o gresci.
     * @param selectedRow red u kojem se nalazi izabrani korisnik
     * @throws Exception Ukoliko dodje do greske prilikom brisanja korisnika
     */
    public void removeUser(int selectedRow) throws Exception {
       Trainer user= users.get(selectedRow);
        try {
            if(user.isLoggedIn()==true){
                throw new Exception("User is logged in onto system, it cant be deleted."); 
            }
            Controller.getInstance().removeTrainer(user);
            users.remove(selectedRow);
            fireTableDataChanged();
        } catch (Exception e) {
            throw e;
        }

    }
    /**
     * Vraca klasu odredjene kolone.
     * @param columnIndex kolona cija klasa se trazi
     * @return klasa odredjene kolone
     */
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return columnClasses[columnIndex];
        
    }

    /**
     * Azurira tabelu povlacenjem trenera iz baze
     * @throws Exception Ukoliko dodje do greske prilikom povlacenja trenera iz baze
     */
    public void refreshView() throws Exception{
        users= Controller.getInstance().getTrainers();
        fireTableDataChanged();
    }

    /**
     *  Azurira korisnika, trenera, odnsono njegov stanja prijavljivanja.
     * @param dbUser korisnik
     * @param loggedIn stanje prijavljivanja
     */
    public void updateUser(Trainer dbUser, boolean loggedIn) {
      for(int i=0; i<users.size(); i++){
          if(users.get(i).getUsername().equals(dbUser.getUsername())){
              users.get(i).setLoggedIn(loggedIn);
              fireTableDataChanged();
              break;
          }
      }
    }
    /**
     * Vraca trenera sa konkretne pozicije
     * @param row red 
     * @return trener sa konkretne pozicije
     */
    public Trainer getTrainer(int row){
        return users.get(row);
    }
}
