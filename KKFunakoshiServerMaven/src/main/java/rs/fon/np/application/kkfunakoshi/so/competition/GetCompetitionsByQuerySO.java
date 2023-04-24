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
public class GetCompetitionsByQuerySO extends AbstractSO{

   
    private List<Competition> competitions;
    @Override
    protected void precondition(Object param) throws Exception {
        if(param==null || !(param instanceof String))
           throw new Exception("Object is the wrong type!");
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        
        String query=(String)param;
        try {
            competitions= repository.getByQuery(new Competition(),query);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error while loading the competitions", e);
        }
      
    }

    public List<Competition> getCompetitions() {
        return competitions;
    }
    
    
}
