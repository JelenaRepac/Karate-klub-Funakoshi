/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.fon.np.application.kkfunakoshi.so.trainer;

import rs.fon.np.application.kkfunakoshi.domain.Trainer;
import rs.fon.np.application.kkfunakoshi.so.AbstractSO;

/**
 *
 * @author Jeks
 */
public class RemoveTrainerSO extends AbstractSO {

    
    @Override
    protected void precondition(Object param) throws Exception {
         if(param==null || !(param instanceof Trainer)){
             throw new Exception("The object is the wrong type!");
         }
         else{
             Trainer u=(Trainer) param;
             checkValueConstraints(u);
         }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
       repository.delete((Trainer)param);
       
    }

    private void checkValueConstraints(Trainer u) throws Exception {
       if(u.getId().equals(null)){
           throw new Exception("Trainer ID is not sent!");
       }
    }
    
}
