/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package rs.fon.np.application.kkfunakoshi.db;

import rs.fon.np.application.kkfunakoshi.*;
import rs.fon.np.application.kkfunakoshi.repository.Repository;

/**
 *
 * @author Jeks
 * @param <T>
 */
public interface DbRepository<T> extends Repository<T>{
    
     default public void connect() throws Exception{
        DbConnectionFactory.getInstance().getConnection();
    }
    
    default public void disconnect() throws Exception{
        DbConnectionFactory.getInstance().getConnection().close();
    }
    
    default public void commit() throws Exception{
        DbConnectionFactory.getInstance().getConnection().commit();
    }
    
    default public void rollback() throws Exception{
        DbConnectionFactory.getInstance().getConnection().rollback();
    }

}
