/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.fon.np.application.kkfunakoshi.repository.impl;


import rs.fon.np.application.kkfunakoshi.db.DbConnectionFactory;
import rs.fon.np.application.kkfunakoshi.domain.Trainer;

import java.sql.*;

/**
 * Klasa koja sluzi za obradu zahteva vezanih za korisnicki nalog.
 * @author Jelena Repac
 */
public class RepositoryDBTrainer {

	/**
	 * Referenca na repozitorijum zaduzen za implementaciju metoda
	 */
    private RepositoryDBGeneric repositoryDBGeneric;
    /**
     * Konstruktor
     */
    public RepositoryDBTrainer() {
        repositoryDBGeneric= new RepositoryDBGeneric();
    }
 
    /**
     * Postavlja status korisnika na prijavljen.
     * @param currentUser trenutni korisnik
     * @throws Exception Ukoliko dodje do greske prilikom komunikacije sa bazom
     */
    public void setUserIsLoggedIn(Trainer currentUser) throws Exception{
        String query="UPDATE user SET loggedIn=1 WHERE id="+currentUser.getId();
        Statement statement= DbConnectionFactory.getInstance().getConnection().createStatement();
        statement.executeUpdate(query);
        repositoryDBGeneric.commit();
        statement.close();
        
    }
    /**
     * Postavlja status korisnika na odjavljen.
     * @param currentUser trenutni korisnik
     * @throws Exception Ukoliko dodje do greske prilikom komunikacije sa bazom
     */
    public void setUserIsLoggedOut(Trainer currentUser) throws Exception{
        String query="UPDATE user SET loggedIn=0 WHERE id="+currentUser.getId();
        Statement statement= DbConnectionFactory.getInstance().getConnection().createStatement();
        statement.executeUpdate(query);
        repositoryDBGeneric.commit();
        statement.close();
    }

   

    
}
