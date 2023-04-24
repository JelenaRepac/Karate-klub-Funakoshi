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
 *
 * @author Jeks
 */
public class ResultTableModel extends AbstractTableModel {

    List<Result> results;
    String[] columnNames= new String[]{"Competition","Member","Team","Category","Medal"};
    Object[] columnClass= new Object[]{Competition.class,Member.class,Team.class,String.class,String.class};

    public ResultTableModel(List<Result> results) {
        this.results = results;
    }
    
    @Override
    public int getRowCount() {
       return results.size();
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
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }
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
    
    public void addResult(Result r){
        results.add(r);
        fireTableRowsInserted(results.size()-1, results.size()-1);
    }

    public void delete(int selectedRow) {
        results.remove(selectedRow);
        fireTableDataChanged();
    }
    
    public List<Result> getResults(){
        return results;
    }
    
    public Long getGoldMedals(){
        Long gold=0L;
        for(Result r:results){
            if(r.getMedals().equals(Medal.GOLD)){
                gold++;
            }
        }
        return gold;
    }
     public Long getSilverMedals(){
        Long silver=0L;
        for(Result r:results){
            if(r.getMedals().equals(Medal.SILVER)){
                silver++;
            }
        }
        return silver;
    }
    public Long getBronzeMedals(){
        Long bronze=0L;
        for(Result r:results){
            if(r.getMedals().equals(Medal.BRONZE)){
                bronze++;
            }
        }
        return bronze;
    }

    public Result getResult(int selectedRow) {
        return results.get(selectedRow);
    }

    public boolean containsMember(Result result) {
       for(Result r: results){
            if(r.getMember()!=null){
                if(r.getMember().getName().equals(result.getMember().getName()) && r.getMedals().equals(result.getMedals()) && r.getCategory().equals(result.getCategory()))
                    return true;
            }
       }
       return false;
    }

    public boolean containsTeam(Result result) {
        for(Result r: results){
            if(r.getTeam()!=null){
                if(r.getTeam().getName().equals(result.getTeam().getName()) && r.getMedals().equals(result.getMedals()))
                    return true;
            }
       }
       return false;
    }
    
    public void refreshView() throws Exception{
        results=ControllerUI.getInstance().getByQueryResults(" ORDER BY medal");
        fireTableDataChanged();
        
    }
}
