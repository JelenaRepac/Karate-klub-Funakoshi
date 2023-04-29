/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.fon.np.application.kkfunakoshi.so.trainer;

import java.util.List;
import rs.fon.np.application.kkfunakoshi.domain.Trainer;
import rs.fon.np.application.kkfunakoshi.so.AbstractSO;

/**
 * Klasa koja se odnosi na vracanje trenera
 * @author Jelena Repac
 */
public class GetTrainerSO extends AbstractSO {

   
    
    private List<Trainer> trainers;
    @Override
    protected void precondition(Object param) throws Exception {
        //no recondition to check
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
       trainers= repository.getAll(new Trainer());
    }

    /**
     * Vraca sve trenere iz baze
     * @return lista trenera
     */
    public List<Trainer> getTrainers() {
        return trainers;
    }
 
    
}
