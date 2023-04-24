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
 *
 * @author Jeks
 */
public class TableModelTrainer extends AbstractTableModel{

    String[] columnNames= new String[]{"Name","Lastname","Firstname","Password","Login info"};
    private Class[] columnClasses = {String.class, String.class, String.class, String.class,String.class};
    private List<Trainer> users;
    

    public TableModelTrainer() throws Exception{
        users= Controller.getInstance().getTrainers();
    }
    @Override
    public int getRowCount() {
        return users == null ? 0 : users.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

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

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

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

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return columnClasses[columnIndex];
        
    }

    
    public void refreshView() throws Exception{
        users= Controller.getInstance().getTrainers();
        fireTableDataChanged();
    }

    public void updateUser(Trainer dbUser, boolean loggedIn) {
      for(int i=0; i<users.size(); i++){
          if(users.get(i).getUsername().equals(dbUser.getUsername())){
              users.get(i).setLoggedIn(loggedIn);
              fireTableDataChanged();
              break;
          }
      }
    }
    
    public Trainer getTrainer(int row){
        return users.get(row);
    }
}
