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
 * Predstavlja trenera karate kluba. 
 * Dodatno je definisan id-om, imenom, prezimenom, korisnickim imenom kao i lozinkom.
 * Implementira interfejs Serializable, sto dopusta da instance klase budu serijalizovane i deserijalizovane.
 * Nasledjuje abstraktnu klasu AbstractDO.
 * @author Jelena Repac
 */
public class Trainer extends AbstractDO implements Serializable{
    
	/**
	 * Id trenera
	 */
    private Long id;
    /**
     * Ime trenera
     */
    private String firstname;
    /**
     * Prezime trenera
     */
    private String lastname;
    /**
     * Korisnicko ime trenera
     */
    private String username;
    /**
     * Lozinka trenera
     */
    private String password;
    /**
     * Status prijavljivanja 
     */
    private boolean loggedIn;

    /**
     * Bezparametarski konstruktor
     */
    public Trainer() {
    }
    /**
     * Konstruktor
     * @param id id trenere
     * @param firstname ime trenera
     * @param lastname prezime trenera
     * @param username korisnicko ime
     * @param password lozinka
     * @param loggedIn status prijavljivanja
     */
    public Trainer(Long id, String firstname, String lastname, String username, String password, boolean loggedIn) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.password = password;
        this.loggedIn = loggedIn;
    }

    /**
     * Vraca status prijavljivanja trenera
     * @return status prijavljivanja trenera
     */
    public boolean isLoggedIn() {
        return loggedIn;
    }
    /**
     * Postavlja status prijavljivanja trenera
     * @param loggedIn status prijavljivanja
     */
    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }
    /**
     * Vraca id trenera
     * @return id trenera
     */
    public Long getId() {
        return id;
    }
    /**
     * Postavlja id trenera.
     * @param id trenera
     */
    @Override
    public void setId(Long id) {
    	if(id < 0)
            throw new IllegalArgumentException("ID must be 0 or larger number");
        this.id = id;
    }
    /**
     * Vraca ime trenera
     * @return ime trenera
     */
    public String getFirstname() {
        return firstname;
    }
    /**
     * Postavlja ime trenera
     * @param firstname ime trenera
     */
    public void setFirstname(String firstname) {
    	if(firstname.length() < 5)
            throw new IllegalArgumentException("Trainer name must have at least 5 characters");     
        this.firstname = firstname;
    }
    /**
     * Vraca prezime trenera
     * @return prezime trenera
     */
    public String getLastname() {
        return lastname;
    }
    /**
     * Postavlja prezime trenera
     * @param lastname prezime trenera
     */
    public void setLastname(String lastname) {
    	if(lastname.length() < 5)
            throw new IllegalArgumentException("Trainer lastname must have at least 5 characters");     
        this.lastname = lastname;
    }
    /**
     * Vraca korisnicko ime trenera
     * @return korisnicko ime trenera
     */
    public String getUsername() {
        return username;
    }
    /**
     * Postavlja korisnicko ime trenera
     * @param username korisnicko ime
     */
    public void setUsername(String username) {
        if(username.length() < 4)
            throw new IllegalArgumentException("Username must have at least 4 characters");
        this.username = username;
    }
    /**
     * Vraca lozinku trenera
     * @return lozinku trenera
     */
    public String getPassword() {
        return password;
    }
    
    /**
     * Postavlja lozinku trenera
     * @param password lozinka
     */
    public void setPassword(String password) {
        if(password.length() < 8)
            throw new IllegalArgumentException("Password must have at least 8 characters");
        this.password = password;
    }

    @Override
    public String toString() {
        return firstname+" "+lastname;

    }

    
    @Override
	public int hashCode() {
		return Objects.hash(id, username);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Trainer other = (Trainer) obj;
		return Objects.equals(id, other.id) && Objects.equals(username, other.username);
	}
	@Override
    public String getAttributeList() {
        return "firstname,lastname,username,password,loggedIn";
    }

    @Override
    public String getClassName() {
        return "user";
    }

    @Override
    public String getAttributeValues() {
       return "'"+firstname+"', '"+lastname+"', '"+username+"', '"+password+"',"+loggedIn;
    }

    @Override
    public String getQueryCondition() {
      return "id="+id;
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
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String setAttributeValues() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public AbstractDO getEntityFromResultSet(ResultSet resultSet) throws SQLException{
        
        Trainer trainer= new Trainer(resultSet.getLong("id"), resultSet.getString("firstname"),
                    resultSet.getString("lastname"), resultSet.getString("username"), resultSet.getString("password"),
                    resultSet.getBoolean("loggedIn"));
       
        return trainer;
    }

    @Override
    public String getStatementSelectAllQuery() {
       return "SELECT * FROM user";
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
