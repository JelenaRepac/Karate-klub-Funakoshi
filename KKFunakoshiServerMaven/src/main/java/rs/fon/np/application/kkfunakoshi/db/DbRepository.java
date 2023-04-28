/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package rs.fon.np.application.kkfunakoshi.db;

import rs.fon.np.application.kkfunakoshi.*;
import rs.fon.np.application.kkfunakoshi.repository.Repository;

/**
 * Interfejs koji nasledjuje interfejs Repository.
 * @author Jelena Repac
 * @param <T> tip objekta
 */
public interface DbRepository<T> extends Repository<T>{
    /**
     * Za konektovanje sa bazom.
     * @throws Exception Ukoliko dodje do greske prilikom konektovanja sa bazom.
     */
     default public void connect() throws Exception{
        DbConnectionFactory.getInstance().getConnection();
    }
    /**
     * Za raskidanje konekcije sa bazom.
     * @throws Exception Ukoliko dodje do greske prilikom raskidanja konekcije
     */
    default public void disconnect() throws Exception{
        DbConnectionFactory.getInstance().getConnection().close();
    }
    /**
     * Potvrditi transakciju
     * @throws Exception Ukoliko dodje do greske prilikom potvrdjivanaj transakcije
     */
    default public void commit() throws Exception{
        DbConnectionFactory.getInstance().getConnection().commit();
    }
    /**
     * Opozvati transakciju
     * @throws Exception Ukoliko dodje do greske prilikom opoziva transakcije
     */
    default public void rollback() throws Exception{
        DbConnectionFactory.getInstance().getConnection().rollback();
    }

}
