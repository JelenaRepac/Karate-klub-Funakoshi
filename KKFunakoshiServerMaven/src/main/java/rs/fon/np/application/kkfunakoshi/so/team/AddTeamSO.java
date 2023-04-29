/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.fon.np.application.kkfunakoshi.so.team;

import java.util.List;
import rs.fon.np.application.kkfunakoshi.domain.Team;
import rs.fon.np.application.kkfunakoshi.so.AbstractSO;

/**
 * Klasa koja se odnosi na dodavanje tima.
 * @author Jelena Repac
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

    /**
     * Provera da li tim vec postoji u bazi.
     * @param team tim
     * @return 
     * <ul>
        * 		<li>true - ukoliko tim vec postoji u bazi</li>
        * 		<li>false - ukoliko tim ne postoji u bazi</li>
        * 	</ul>
     * @throws Exception Ukoliko dodje do greske u radu sa bazom
     */
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
