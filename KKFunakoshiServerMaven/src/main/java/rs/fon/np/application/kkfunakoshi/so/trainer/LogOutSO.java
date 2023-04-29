/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.fon.np.application.kkfunakoshi.so.trainer;

import rs.fon.np.application.kkfunakoshi.domain.Trainer;
import rs.fon.np.application.kkfunakoshi.repository.impl.RepositoryDBTrainer;
import rs.fon.np.application.kkfunakoshi.so.AbstractSO;

/**
 * Klasa koja se odnosi na odjavljivanje korisnika sa sistema
 * @author Jelena Repac
 */
public class LogOutSO extends AbstractSO{

    private RepositoryDBTrainer repositoryUser;
    /**
     * Konstruktor
     */
    public LogOutSO() {
        repositoryUser=new RepositoryDBTrainer();
    }
     
     
    @Override
    protected void precondition(Object param) throws Exception {
       if(param==null || !(param instanceof Trainer)){
           throw new Exception("Object is the wrong type!");
       }
       if(((Trainer) param).getUsername().equals(null) || (((Trainer)param).getPassword().equals(null))){
           throw new Exception("No credentials sent!");
       }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
       Trainer u= (Trainer) param;
       repositoryUser.setUserIsLoggedOut(u);
       
    }
    
}
