/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.fon.np.application.kkfunakoshi.so.trainer;

import java.util.List;
import rs.fon.np.application.kkfunakoshi.domain.Trainer;
import rs.fon.np.application.kkfunakoshi.so.AbstractSO;

/**
 *
 * @author Jeks
 */
public class AddTrainerSO extends AbstractSO{

   
    
    @Override
    protected void precondition(Object param) throws Exception {
        if(param== null || !(param instanceof Trainer)){
            throw new Exception("The object is the wrong type!");
        }
        else{
            Trainer trainer = (Trainer) param;
            checkStructuralConstraints(trainer);
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        
        repository.add((Trainer) param);
    }

       private void checkStructuralConstraints(Trainer trainer) throws Exception {
       boolean exists= checkIfExists(trainer);
       if(exists){
            throw new Exception("Trainer with that username already exists. Try again.");
       }
    }

    private boolean checkIfExists(Trainer trainer) throws Exception {
        try {
            List<Trainer> trainers= repository.getAll(new Trainer());
            for(Trainer t : trainers){
                if(t.getUsername().equals(trainer.getUsername())){
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
             e.printStackTrace();
            throw new Exception("Error while checking if trainer exist in database.", e);
     
        }
    }
    
}
