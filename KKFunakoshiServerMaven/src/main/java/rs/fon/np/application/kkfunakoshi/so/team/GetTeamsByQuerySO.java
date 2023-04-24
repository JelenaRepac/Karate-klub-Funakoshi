/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.fon.np.application.kkfunakoshi.so.team;

import java.util.ArrayList;
import java.util.List;
import rs.fon.np.application.kkfunakoshi.domain.Team;
import rs.fon.np.application.kkfunakoshi.so.AbstractSO;

/**
 *
 * @author Jeks
 */
public class GetTeamsByQuerySO extends AbstractSO {

    
    private List<Team> teams;
    
    @Override
    protected void precondition(Object param) throws Exception {
        if(param==null || !(param instanceof String))
           throw new Exception("Object is the wrong type!");
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
      
        String query=(String)param;
        try {
            
            teams= repository.getByQuery(new Team(),query);
            teams=filterTeam(teams);
          
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error while loading the teams", e);
        }
    }

    public List<Team> getTeams() {
        return teams;
    }

    private List<Team> filterTeam(List<Team> teams) {
        List<Team> returnTeams=new ArrayList<>();
        for(Team t: teams){
                if(t!=null){
                returnTeams.add(t);
                }
        }
        return returnTeams;
    }
    
    
}