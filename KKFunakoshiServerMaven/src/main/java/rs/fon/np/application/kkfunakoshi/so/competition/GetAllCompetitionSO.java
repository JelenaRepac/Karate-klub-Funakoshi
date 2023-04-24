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
public class GetAllCompetitionSO extends AbstractSO{

   
    private List<Competition> competitions;
    
    
    @Override
    protected void precondition(Object param) throws Exception {
        //no recondition to check
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        
        try {
            competitions= repository.getAll(new Competition());
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error while loading the competitions", e);
        }
       
    }

    public List<Competition> getCompetitions() {
        return competitions;
    }
    
    
    
}
