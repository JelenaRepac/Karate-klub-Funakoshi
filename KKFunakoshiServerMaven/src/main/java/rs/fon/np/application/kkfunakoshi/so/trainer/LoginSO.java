/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.fon.np.application.kkfunakoshi.so.trainer;

import java.util.List;
import rs.fon.np.application.kkfunakoshi.domain.Trainer;
import rs.fon.np.application.kkfunakoshi.repository.impl.RepositoryDBTrainer;
import rs.fon.np.application.kkfunakoshi.so.AbstractSO;

/**
 * Klasa koja se odnosi na prijavljivanje korisnika na sistem
 * @author Jelena Repac
 */
public class LoginSO extends AbstractSO {

    private Trainer currentUser;
    private RepositoryDBTrainer repositoryTrainer;
    /**
     * Konstruktor
     */
    public LoginSO() {
        repositoryTrainer= new RepositoryDBTrainer();
    }
    
    
    @Override
    protected void precondition(Object param) throws Exception {
        if(param==null || !(param instanceof Trainer)){
            throw new Exception("The object is the wrong type!");
        }
        if(((Trainer) param).getUsername().equals(null) || ((Trainer)param).getPassword().equals(null)){
            throw new Exception("No credentials sent!");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        List<Trainer> trainers;
       
        Trainer user= (Trainer) param;
        try {
            trainers= (List<Trainer>) repository.getAll(new Trainer());
            for(Trainer t: trainers){
                if(t.getUsername().equals(user.getUsername()) && t.getPassword().equals(user.getPassword())){
                    currentUser=t;
                    if(currentUser.isLoggedIn()==true){
                        throw new Exception("Trainer is already logged in.");
                    }
                    
                    repositoryTrainer.setUserIsLoggedIn(currentUser);
                    
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
       
    }

    /**
     * Vraca trenutno prijavljenog korisnika
     * @return trener
     */
    public Trainer getCurrentUser() {
        return currentUser;
    }
    
    
    
}
