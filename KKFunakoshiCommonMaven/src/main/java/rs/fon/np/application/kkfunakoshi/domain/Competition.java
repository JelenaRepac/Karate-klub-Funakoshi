/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.fon.np.application.kkfunakoshi.domain;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Jeks
 */
public class Competition extends AbstractDO implements Serializable {
    
    private Long id;
    private String name;
    private Date date;
    private String competitionHall;
    private City city;
    
    SimpleDateFormat format= new SimpleDateFormat("yyyy-MM-dd");
    public Competition() {
    }

    public Competition(Long id, String name, Date date, String competitionHall, City city) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.competitionHall = competitionHall;
        this.city = city;
    }

   
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getCompetitionHall() {
        return competitionHall;
    }

    public void setCompetitionHall(String competitionHall) {
        this.competitionHall = competitionHall;
    }

    @Override
    public String toString() {
        return  name;
    }

    @Override
    public String getAttributeList() {
       return "name, date, competitionHall, cityId";
    }

    @Override
    public String getClassName() {
       return "competition";
    }

    @Override
    public String getAttributeValues() {
       return "'"+getName()+"', '"+new java.sql.Date(getDate().getTime())+"', '"+getCompetitionHall()+"', "+getCity().getId();
    }

    @Override
    public String getQueryCondition() {
        return "id="+getId();
    }

    

    @Override
    public int getNumberOfBountObject() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    @Override
    public Object getBoundObject(int numberOfObjects,int numberOfBoundAttributes) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int getNumberOfAttributesBoundObjects() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setForeignId(Long id) {
        setId(id);
        
    }

    @Override
    public String setAttributeValues() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public AbstractDO getEntityFromResultSet(ResultSet rs) throws SQLException {
        City c= new City(rs.getLong("c.cityId"), rs.getLong("ci.ptt"), rs.getString("ci.name"));
        Competition competition= new Competition(rs.getLong("c.id"), rs.getString("c.name"), rs.getDate("c.date")
                , rs.getString("c.competitionHall"), c);
        return competition;
    }

    @Override
    public String getStatementSelectAllQuery() {
        return "SELECT c.id,c.name,c.date,c.competitionHall,c.cityId,ci.name,ci.ptt FROM competition c JOIN city ci ON (c.cityId=ci.id)";
    }

    @Override
    public String getBoundObjectClassName() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getBoundObjectAttributeList() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getBoundObjectAttributeValues(int attributeNumber) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

   
    
}
