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
 *
 * @author Jeks
 */
public class TeamTableModel extends AbstractTableModel{

    List<Team> teams;
    String[] columnNames= new String[]{"Name","Category","Members"};
    Object[] columnClass= new Object[]{String.class,Category.class,List.class};

    public TeamTableModel(List<Team> teams) {
        this.teams=teams;
    }
    
    
    @Override
    public int getRowCount() {
        return teams.size();
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
    
    @Override
     public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }
    
   
    public Team getTeam(int row){
        return teams.get(row);
    }
}
