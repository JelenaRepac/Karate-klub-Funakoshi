/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.fon.np.application.kkfunakoshi.so.competition;

import java.util.List;
import  rs.fon.np.application.kkfunakoshi.domain.Competition;
import  rs.fon.np.application.kkfunakoshi.so.AbstractSO;

/**
 *
 * @author Jeks
 */
public class AddCompetitionSO extends AbstractSO{

  
    @Override
    protected void precondition(Object param) throws Exception {
         if(param== null || !(param instanceof Competition)){
            throw new Exception("Object is the wrong type!");
        }
        else{
            Competition competition= (Competition)param;
            checkStructuralConstraints(competition);
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        repository.add((Competition) param);
    }
    
    private void checkStructuralConstraints(Competition competition) throws Exception {
       boolean exists= checkIfExists(competition);
       if(exists){
           throw new Exception("Competition already exists. Try again.");
       }
    }

    private boolean checkIfExists(Competition competition) throws Exception {
        List<Competition> commpetitions= repository.getAll(new Competition());
        for(Competition c: commpetitions){
            if(c.getName().equals(competition.getName()) && c.getDate().equals(competition.getDate())){
                return true;
            }
        }
        return false;
        
    }
    
}
