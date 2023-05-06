/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.fon.np.application.kkfunakoshi.domain;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

/**
 * Predstavlja grad iz kojeg je clan kluba. Klasa koja sadrzi informacije kao sto su id grada, ptt, kao i ime grada.
 * Implementira interfejs Serializable, sto dopusta da instance klase budu serijalizovane i deserijalizovane.
 * Nasledjuje abstraktnu klasu AbstractDO.
 * @author Jelena Repac
 */
public class City extends AbstractDO implements Serializable {
   
	/**
	 * Id grada.
	 */
    private Long id;
    /**
     * Ptt, postanski broj grada.
     */
    private Long ptt;
    /**
     * Ime grada.
     */
    private String name;

    /**
     * Konstruktor koji kao parametar prima sva polja klase.
     * @param id id grada 
     * @param ptt Postanski broj grada
     * @param name Ime grada kao String
     */
    public City(Long id, Long ptt, String name) {
        this.id = id;
        this.ptt = ptt;
        this.name = name;
    }

    /**
     * Bezparametarski konstruktor.
     */
    public City() {
       
    }
    
    /**
     * Vraca ID grada.
     * @return id grada
     */
    public Long getId() {
        return id;
    }
    /**
     * Postavlja ID grada, na onu vrednost koju prosledimo kao parametar
     * @param id grada 
     * @throws IllegalArgumentException Ukoliko je id negativan broj
     */
    public void setId(Long id){
    	if(id < 0)
             throw new IllegalArgumentException("ID must be 0 or larger number");
    	this.id = id;
    }
    /**
     * Vraca postanski broj grada.
     * @return postanski broj grada
     */
    public Long getPtt() {
        return ptt;
    }
    /**
     * Postavlja vrednost postanskog broja grada.
     * @param ptt nova vrednost postanskog broja grada
     */
    public void setPtt(Long ptt) {
        this.ptt = ptt;
    }
    /**
     * Vraca ime grada.
     * @return ime grada
     */
    public String getName() {
        return name;
    }
    /**
     * Postavlja vrednost imena grada na onu vrednost koju prosledimo kao parametar.
     * @param name ime grada 
     * @throws IllegalArgumentException ukoliko je prosledjeno ime krace od 2 karaktera.
     */
    public void setName(String name) {
    	if(name.length() < 2)
            throw new IllegalArgumentException("City name must have at least 2 characters");     
        this.name = name;
    }

    
    @Override
	public int hashCode() {
		return Objects.hash(id, ptt);
	}
    
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		City other = (City) obj;
		return Objects.equals(id, other.id) && Objects.equals(ptt, other.ptt);
	}

	@Override
    public String toString() {
        return name;
    }

    @Override
    public String getAttributeList() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getClassName() {
    	return "city";
    }

    @Override
    public String getAttributeValues() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getQueryCondition() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int getNumberOfBountObject() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Object getBoundObject(int numberOfObjects, int numberOfAttributesBoundObjects) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int getNumberOfAttributesBoundObjects() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String setAttributeValues() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setForeignId(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public AbstractDO getEntityFromResultSet(ResultSet resultSet) throws SQLException {
        return new City(resultSet.getLong("id"), resultSet.getLong("ptt"), resultSet.getString("name"));
    }

    @Override
    public String getStatementSelectAllQuery() {
       return "SELECT id,name,ptt FROM city";
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
