/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.fon.np.application.kkfunakoshi.repository.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import rs.fon.np.application.kkfunakoshi.*;
import rs.fon.np.application.kkfunakoshi.db.DbConnectionFactory;
import rs.fon.np.application.kkfunakoshi.db.DbRepository;
import rs.fon.np.application.kkfunakoshi.domain.AbstractDO;

/**
 *
 * @author Jeks
 */
public class RepositoryDBGeneric  implements DbRepository<AbstractDO>  {

     @Override
    public List<AbstractDO> getAll(AbstractDO t) throws Exception {
            List<AbstractDO> abstractDOs= new ArrayList<>();
            Connection connection= DbConnectionFactory.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(t.getStatementSelectAllQuery());
            while(resultSet.next()){
                abstractDOs.add(t.getEntityFromResultSet(resultSet));
            }
        
            return abstractDOs;
    }
    
     
    

    @Override
    public void add(AbstractDO t) throws Exception {
        try {
            Connection connection = DbConnectionFactory.getInstance().getConnection();
            StringBuilder sb = new StringBuilder();
            sb.append("INSERT INTO ")
                    .append(t.getClassName())
                    .append(" (")
                    .append(t.getAttributeList())
                    .append(")")
                    .append(" VALUES (")
                    .append(t.getAttributeValues())
                    .append(")");
            String query = sb.toString();
            System.out.println(query);
            Statement statement = connection.createStatement();
            statement.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
            ResultSet rsKey = statement.getGeneratedKeys();
            if (rsKey.next()) {
                Long id = rsKey.getLong(1);
                t.setId(id);
            }
            statement.close();
            rsKey.close();
        } catch (SQLException ex) {
            throw ex;
        }
    }

    @Override
    public void editComplex(AbstractDO oldT, AbstractDO newT) throws Exception {
       try {
            Connection connection = DbConnectionFactory.getInstance().getConnection();
            StringBuilder sb = new StringBuilder();
            sb.append("UPDATE ")
                    .append(oldT.getClassName())
                    .append(" SET ")
                    .append(newT.setAttributeValues())
                    .append(" WHERE ")
                    .append(oldT.getQueryCondition());
            String query = sb.toString();
            Statement statement = connection.createStatement();
            statement.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
            ResultSet rsKey = statement.getGeneratedKeys();
            if (rsKey.next()) {
                Long id = rsKey.getLong(1);
                newT.setId(id);
                
                for(int j=0; j<newT.getNumberOfBountObject(); j++){
                AbstractDO abstractDO;
                for(int i=0; i<newT.getNumberOfAttributesBoundObjects(); i++){
                    abstractDO=(AbstractDO) newT.getBoundObject(j,i);
                    System.out.println(abstractDO.toString());
                    abstractDO.setForeignId(id);
                    StringBuilder sbBoundObject = new StringBuilder();
                    sbBoundObject.append("UPDATE ")
                            .append(abstractDO.getClassName())
                            .append(" SET ")
                            .append(abstractDO.setAttributeValues())
                            .append(" WHERE ")
                            .append(abstractDO.getQueryCondition());
                    String queryBound = sbBoundObject.toString();
                    System.out.println(queryBound);
                    statement.executeUpdate(queryBound);
                    
                }
                }
            }
            
            statement.close();
        } catch (SQLException ex) {
            throw ex;
        }
    }

    @Override
    public void delete(AbstractDO t) throws Exception {
            try {
            Connection connection = DbConnectionFactory.getInstance().getConnection();
            StringBuilder sb = new StringBuilder();
            sb.append("DELETE FROM ")
                    .append(t.getClassName())
                    .append(" WHERE ")
                    .append(t.getQueryCondition());
            String query = sb.toString();
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            statement.close();
        } catch (SQLException ex) {
            throw ex;
        }
    }

    @Override
    public List<AbstractDO> getByQuery(AbstractDO t,String query) throws Exception {
            List<AbstractDO> abstractDOs= new ArrayList<>();
            Connection connection= DbConnectionFactory.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(t.getStatementSelectAllQuery()+query);
            while(resultSet.next()){
                
                abstractDOs.add(t.getEntityFromResultSet(resultSet));
            }
        
            return abstractDOs;
    }

    @Override
    public AbstractDO get(AbstractDO t, String query) throws Exception {
            AbstractDO abstractDO=null;
            Connection connection= DbConnectionFactory.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(t.getStatementSelectAllQuery()+query);
            while(resultSet.next()){
                abstractDO=t.getEntityFromResultSet(resultSet);
            }
        
            return abstractDO;
    }
  
    @Override
    public void addComplex(AbstractDO t) throws Exception {
        try {
            Connection connection = DbConnectionFactory.getInstance().getConnection();
            StringBuilder sb = new StringBuilder();
            sb.append("INSERT INTO ")
                    .append(t.getClassName())
                    .append(" (").append(t.getAttributeList()).append(")")
                    .append(" VALUES (")
                    .append(t.getAttributeValues())
                    .append(")");
            String query = sb.toString();
          
            Statement statement = connection.createStatement();
            statement.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
            
            ResultSet rsKey = statement.getGeneratedKeys();
            if (rsKey.next()) {
                Long id = rsKey.getLong(1);
                t.setId(id);
                
                for(int j=0; j<t.getNumberOfBountObject(); j++){
                AbstractDO abstractDO;
                for(int i=0; i<t.getNumberOfAttributesBoundObjects(); i++){
                    abstractDO=(AbstractDO) t.getBoundObject(j,i);
                    System.out.println(abstractDO.toString());
                    abstractDO.setForeignId(id);
                    StringBuilder sbBoundObject = new StringBuilder();
                    sbBoundObject.append("INSERT INTO ")
                            .append(abstractDO.getClassName())
                            .append(" (").append(abstractDO.getAttributeList()).append(")")
                            .append(" VALUES (")
                            .append(abstractDO.getAttributeValues())
                            .append(")");
                    String queryBound = sbBoundObject.toString();
                    System.out.println(queryBound);
                    statement.executeUpdate(queryBound);
                }
                
                 }
            }

            
        } catch (SQLException ex) {
            throw ex;
        }
    }
    @Override
    public void edit(AbstractDO oldT, AbstractDO newT) throws Exception {
       
            Connection connection = DbConnectionFactory.getInstance().getConnection();
            StringBuilder sb = new StringBuilder();
            sb.append("UPDATE ")
                    .append(oldT.getClassName())
                    .append(" SET ")
                    .append(newT.setAttributeValues())
                    .append(" WHERE ")
                    .append(oldT.getQueryCondition());
            
            String query = sb.toString();
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            statement.close();
    }

   
    @Override
    public void addComplexObject(AbstractDO t) throws Exception {
      Connection connection = DbConnectionFactory.getInstance().getConnection();
            StringBuilder sb = new StringBuilder();
            sb.append("INSERT INTO ")
                    .append(t.getClassName())
                    .append(" (").append(t.getAttributeList()).append(")")
                    .append(" VALUES (")
                    .append(t.getAttributeValues())
                    .append(")");
            String query = sb.toString();
          
            Statement statement = connection.createStatement();
            statement.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
            
            ResultSet rsKey = statement.getGeneratedKeys();
            if (rsKey.next()) {
                Long id = rsKey.getLong(1);
                t.setId(id);
                
                for(int j=0; j<t.getNumberOfBountObject(); j++){
                AbstractDO abstractDO;
                
                    abstractDO=(AbstractDO) t.getBoundObject(j,0);
                    System.out.println(abstractDO.toString());
                   
                    StringBuilder sbBoundObject = new StringBuilder();
                    sbBoundObject.append("INSERT INTO ")
                            .append(t.getBoundObjectClassName())
                            .append(" (").append(t.getBoundObjectAttributeList()).append(")")
                            .append(" VALUES (")
                            .append(t.getBoundObjectAttributeValues(j))
                            .append(")");
                    String queryBound = sbBoundObject.toString();
                    System.out.println(queryBound);
                    statement.executeUpdate(queryBound);
                 }
            }
    }

    @Override
    public void addBoundObjects(AbstractDO t) {
        try {
            Connection connection= DbConnectionFactory.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(t.getStatementSelectAllQuery());
            
            
            if (resultSet.next()) {
              
                for(int j=0; j<t.getNumberOfBountObject(); j++){
                AbstractDO abstractDO;
                for(int i=0; i<t.getNumberOfAttributesBoundObjects(); i++){
                    abstractDO=(AbstractDO) t.getBoundObject(j,i);
                    System.out.println(abstractDO.toString());
                    abstractDO.setForeignId(t.getId());
                    StringBuilder sbBoundObject = new StringBuilder();
                    sbBoundObject.append("INSERT INTO ")
                            .append(abstractDO.getClassName())
                            .append(" (").append(abstractDO.getAttributeList()).append(")")
                            .append(" VALUES (")
                            .append(abstractDO.getAttributeValues())
                            .append(")");
                    String queryBound = sbBoundObject.toString();
                    statement.executeUpdate(queryBound);
                }
                
                 }
            }
        }    catch (Exception ex) {
             Logger.getLogger(RepositoryDBGeneric.class.getName()).log(Level.SEVERE, null, ex);
         }
    }

  
    
  
}

    