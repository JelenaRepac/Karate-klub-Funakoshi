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
 *
 * @author Jeks
 */
public class CompetitionTableModel extends AbstractTableModel{

    List<Competition> competitions;
    String columnNames[]= new String[]{"Name","Date","Competition hall","City"};
    Object columnClass[] = new Object[]{String.class,String.class,String.class,City.class};
    SimpleDateFormat format= new SimpleDateFormat("yyyy-MM-dd");
    
    public CompetitionTableModel(List<Competition> competitions){
        this.competitions=competitions;
    }
    
    @Override
    public int getRowCount() {
       return competitions.size();
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
        if(columnIndex<0 || columnIndex> columnClass.length) return Object.class;
        return (Class<?>) columnClass[columnIndex];
    }
    
    
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
    
    

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
       return false;
    }
    
    public void addCompetition(Competition c){
        competitions.add(c);
        fireTableRowsInserted(competitions.size()-1, competitions.size()-1);
    }
    
    public List<Competition> getCompetitions(){
        return competitions;
    }
    
    public Competition getCompetition(int row){
        return competitions.get(row);
    }
    
    public void deleteCompetition(int row){
        competitions.remove(row);
        fireTableDataChanged();
    }
    
    public void deleteCompetition(Competition c){
        competitions.remove(c);
        fireTableDataChanged();
    }
    
    public void setCompetitions(List<Competition> competitions){
        this.competitions=competitions;
        fireTableDataChanged();
    }
}
