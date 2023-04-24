/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.fon.np.application.kkfunakoshi.so.team;

import java.util.List;
import rs.fon.np.application.kkfunakoshi.domain.Team;
import rs.fon.np.application.kkfunakoshi.so.AbstractSO;

/**
 *
 * @author Jeks
 */
public class AddTeamSO extends AbstractSO {

   
    
    @Override
    protected void precondition(Object param) throws Exception {
      if(param==null || !(param instanceof Team)){
           throw new Exception("Object is the wrong type!");
        }
        else{
           Team team= (Team)param;
           checkStructuralConstraints(team);
        }
      
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
      repository.addComplexObject((Team) param);
    
    }

    private void checkStructuralConstraints(Team team) throws Exception {
       boolean exists= checkIfExists(team);
       if(exists){
           throw new Exception("Team already exists. Try again.");
       }
    }

    private boolean checkIfExists(Team team) throws Exception {
        
        List<Team> teams= repository.getByQuery(new Team(), "");
        
        for(Team t: teams){
            if(t!=null){
                if(t.getName().equals(team.getName())){
                    return true;
                }
            }
        } 
        return false;
    }

   
    
}
