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
import java.util.Objects;

/**
 * Predstavlja karate takmicenje. Sadrzi informacije kao sto su id takmicenja, naziv istog, kao i grad, datum i mesto odrzavanja takmicenja.
 * Implementira interfejs Serializable, sto dopusta da instance klase budu serijalizovane i deserijalizovane.
 * Nasledjuje abstraktnu klasu AbstractDO.
 * @author Jelena Repac
 */
public class Competition extends AbstractDO implements Serializable {
    
	/**
	 * Id takmicenja.
	 */
    private Long id;
    /**
     * Naziv takmicenja.
     */
    private String name;
    /**
     * Datum odrzavanja takmicenja.
     */
    private Date date;
    /**
     * Hala u kojoj se odrzava takmicenje.
     */
    private String competitionHall;
    /**
     * Grad u kojem se odrzava takmicenje.
     */
    private City city;
    /**
     * Formator datuma
     */
    SimpleDateFormat format= new SimpleDateFormat("yyyy-MM-dd");
    /**
     * Bezparametarski konstruktor
     */
    public Competition() {
    }

    /**
     * Konstruktor koji kao parametar prima sva polja klase.
     * @param id id takmicenja 
     * @param name naziv takmicenja
     * @param date datum odrzavanja
     * @param competitionHall hala u kojoj se odrzava
     * @param city grad u kojem se odrzava 
     */
    public Competition(Long id, String name, Date date, String competitionHall, City city) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.competitionHall = competitionHall;
        this.city = city;
    }

   /**
    * Vraca vrednost id-a.
    * @return id id takmicenja
    */
    public Long getId() {
        return id;
    }
    
    /**
     * Postavlja vrednost id-a.
     * @param id Id takmicenja
     * @throws IllegalArgumentException Ukoliko je id negativan broj
     */
    public void setId(Long id){
    	if(id < 0)
            throw new IllegalArgumentException("Id takmicenja ne sme biti negativan broj");
        this.id = id;
    }

    /**
     * Vraca naziv takmicenja.
     * @return naziv takmicenja
     */
    public String getName() {
        return name;
    }
    /**
     * Postavlja naziv takmicenja.
     * @param name naziv takmicenja
     * @throws IllegalArgumentException Stvara se izuzetak ukoliko je prosledjeni naziv kraci od 5 karaktera ili prazan string
     * @throws NullPointerException Ukoliko je takmicenje null
     */
    public void setName(String name) {
    	if(name == null )
    		throw new NullPointerException("Naziv takmicenja ne sme biti null");
    	if(name.length() < 5 || name.isEmpty())
            throw new IllegalArgumentException("Naziv takmicenja mora imati minimum 5 karaktera niti biti prazan string");
        this.name = name;
    }
    /**
     * Vraca datum odrzavanja.
     * @return datum odrzavanja takmicenja
     */
    public Date getDate() {
        return date;
    }
    
    /**
     * Postavlja datum odrzavanja takmicenja
     * @param date datum odrzavanja
     * @throws IllegalArgumentException Ukoliko je datum pre danasnjeg
     */
    public void setDate(Date date) {
    	Date currentDate = new Date();
    	System.out.println(currentDate);
        if (date.before(currentDate)) 
            throw new IllegalArgumentException("Datum ne sme biti pre danasnjeg!");
        this.date = date;
    }
    
    /**
     * Vraca grad u kojem se odrzava takmicenje
     * @return grad u kojem se odrzava takmicenje
     */
    public City getCity() {
        return city;
    }
    /**
     * Postavlja grad u kojem se odrzava takmmicenje
     * @param city grad u kojem se odrzava takmicenje
     * @throws IllegalArgumentException Stvara se izuzetak ukoliko prosledjeni grad nije potpuno inicijalizovan
     */
    public void setCity(City city) {
    	if(city == null || city.getId() ==0 || city.getName().equals("") || city.getPtt()==0 )
    		throw new IllegalArgumentException("Grad mora biti u potpunosti inicijalizovan");
        this.city = city;
    }
    /**
     * Vraca halu u kojoj se odrzava takmicenje
     * @return naziv hale u kojoj se takmicenje odrzava
     */
    public String getCompetitionHall() {
        return competitionHall;
    }
    /**
     * Postavlja halu u kojoj se odrzava takmicenje
     * @param competitionHall hala odrzavanja
     * @throws IllegalArgumentException Stvara se izuzetak ukoliko je prosledjeni naziv kraci od 5 karaktera 
     * @throws NullPointerException Ukoliko je naziv hale null
     */
    public void setCompetitionHall(String competitionHall) {
    	if(competitionHall==null)
    		throw new NullPointerException("Naziv hale ne sme biti null");
    	if(competitionHall.length() < 5)
            throw new IllegalArgumentException("Naziv hale mora imati minimum 5 karaktera");
        this.competitionHall = competitionHall;
    }

    
    @Override
	public String toString() {
		return "Competition [id=" + id + ", name=" + name + ", date=" + date + ", competitionHall=" + competitionHall
				+ ", city=" + city + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(competitionHall, date, id, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Competition other = (Competition) obj;
		return  Objects.equals(id, other.id) && Objects.equals(name, other.name);
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
        try {
			setId(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
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
