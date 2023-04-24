/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.fon.np.application.kkfunakoshi.repository.impl;


import rs.fon.np.application.kkfunakoshi.db.DbConnectionFactory;
import rs.fon.np.application.kkfunakoshi.domain.Trainer;

import java.sql.*;

/**
 *
 * @author Jeks
 */
public class RepositoryDBTrainer {

    private RepositoryDBGeneric repositoryDBGeneric;
    
    public RepositoryDBTrainer() {
        repositoryDBGeneric= new RepositoryDBGeneric();
    }
 
    
    public void setUserIsLoggedIn(Trainer currentUser) throws Exception{
        String query="UPDATE user SET loggedIn=1 WHERE id="+currentUser.getId();
        Statement statement= DbConnectionFactory.getInstance().getConnection().createStatement();
        statement.executeUpdate(query);
        repositoryDBGeneric.commit();
        statement.close();
        
    }
    
    public void setUserIsLoggedOut(Trainer currentUser) throws Exception{
        String query="UPDATE user SET loggedIn=0 WHERE id="+currentUser.getId();
        Statement statement= DbConnectionFactory.getInstance().getConnection().createStatement();
        statement.executeUpdate(query);
        repositoryDBGeneric.commit();
        statement.close();
    }

   

    
}
