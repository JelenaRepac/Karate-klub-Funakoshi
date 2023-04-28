/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.fon.np.application.kkfunakoshi.domain;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 * Predstavlja abstraktni domenski objekat. Koristi se kao bazna klasa za sve domenske objekte.
 * Sadrzi abstraktne metode cija implementacija zavisi od domenske klase.
 * @author Jelena Repac
 */
public abstract class AbstractDO {
    
	/**
	 *  Vraca listu atributa objekta.
	 * @return lista atributa objekta
	 */
    public abstract String getAttributeList();
    /**
     * Vraca ime klase objekta.
     * @return ime klase objekta
     */
    public abstract String getClassName();
    /**
     * Vraca vrednosti atributa objekta.
     * @return vrednosti atributa objekta
     */
    public abstract String getAttributeValues();
    /**
     * Vraca upit za povlacenje objekta iz baze.
     * @return upit 
     */
    public abstract String getQueryCondition();
    /**
     * Vraca broj objekata koji su povezani sa ovim objektom.
     * @return broj povezanih objekata
     */
    public abstract int getNumberOfBountObject();
    /**
     * Vraca slog povezanog objekta na osnovu broja objekta i broja atributa povezanog objekta.
     * @param numberOfObjects broj objekata
     * @param numberOfAttributesBoundObjects broj atributa povezanog objekta
     * @return slog povezanog objekta
     */
    public abstract Object getBoundObject(int numberOfObjects, int numberOfAttributesBoundObjects);
    
    /**
     * Vraca broj slogova povezanog objekta.
     * @return broj slogova povezanog objekta
     */
    public abstract int getNumberOfAttributesBoundObjects();
    /**
     * Postavlja vrednost atributa id.
     * @param id nova vrednost atributa id
     * @throws Exception ukoliko dodje do greske
     */
    public abstract void setId(Long id) throws Exception;
    /**
     * Vraca vrednost atributa id.
     * @return vrednost atributa id
     */
    public abstract Long getId();
    /**
     * Postavlja vrednosti atributa objekta.
     * @return SQL upit za azuriranje objekta
     */
    public abstract String setAttributeValues();
    /**
     * Postavlja vrednost spoljnog kljuca.
     * @param id nova vrednost spoljnog kljuca
     */
    public abstract void setForeignId(Long id);
    /**
     * Vraća objekat na osnovu rezultata upita nad bazom podataka.
     * @param resultSet rezultat upita nad bazom podataka
     * @return objekat domena
     * @throws SQLException ukoliko je doslo do greske prilikom citanja iz rezultata upita
     */
    public abstract AbstractDO getEntityFromResultSet(ResultSet resultSet) throws SQLException;
    /**
     * Vraća SQL upit za izbor svih slogova za trenutnu klasu.
     * 
     * @return SQL upit za izbor svih slogova za trenutnu klasu
     */
    public abstract String getStatementSelectAllQuery();
    /**
     * Vraća naziv klase vezanog objekta.
     * 
     * @return Naziv klase vezanog objekta
     */
    public abstract String getBoundObjectClassName();
    /**
     * Vraća listu atributa vezanog objekta u obliku SQL upita.
     * 
     * @return Lista atributa vezanog objekta u obliku SQL upita
     */
    public abstract String getBoundObjectAttributeList();
	/**
	 * Vraća vrednost atributa vezanog objekta za dati broj atributa.
	 * 
	 * @param attributeNumber Broj atributa
	 * @return Vrednost atributa vezanog objekta za dati broj atributa
	 */
    public abstract String getBoundObjectAttributeValues(int attributeNumber);

      

}

   
    
