/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.fon.np.application.kkfunakoshi.domain;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Predstavlja tim koga cine 3 clana kluba istog pola i istog godista.
 * Sadrzi id, naziv, clanove koji cine tim kao i kategoriju u kojoj se tim takmici. 
 * Implementira interfejs Serializable, sto dopusta da instance klase budu serijalizovane i deserijalizovane.
 * Nasledjuje abstraktnu klasu AbstractDO.
 * @author Jelena Repac
 */
public class Team extends AbstractDO implements Serializable {
    /**
     * Id tima
     */
    private Long id;
    /**
     * Naziv tima
     */
    private String name;
    /**
     * Lista clanova tima
     */
    private ArrayList<Member> members;
    /**
     * Kategorija u kojoj se tim takmici
     */
    private Category category;
    /**
     * Lista clanova tima
     */
    private transient ArrayList<Member> teamMembers= new ArrayList<>();
    /**
     * Bezparametarski konstruktor
     */
    public Team() {
    }

    /**
     * Konstruktor
     * @param id id tima
     * @param name naziv tima
     * @param teamMembers clanovi tima
     * @param category kategorija u kojoj se tim takmici
     */
    public Team(Long id, String name, ArrayList<Member> teamMembers, Category category) {
        this.id = id;
        this.name = name;
        this.members = teamMembers;
        this.category = category;
    }

  
    /**
     * Vraca vrednost id-a tima
     * @return id tima
     */
    public Long getId() {
        return id;
    }
    /**
     * Postavlja id tima
     * @param id tima
     */
    public void setId(Long id) {
    	if(id < 0)
            throw new IllegalArgumentException("ID must be 0 or larger number");
        this.id = id;
    }
    /**
     * Vraca naziv tima
     * @return naziv tima
     */
    public String getName() {
        return name;
    }
	/**
	 * Postavlja ime tima
	 * @param name naziv tima
	 */
    public void setName(String name) {
    	if(name.length() < 5)
            throw new IllegalArgumentException("Team name must have at least 5 characters");     
        this.name = name;
    }
    /**
     * Vraca listu clanova tima
     * @return lista clanova tima
     */
    public ArrayList<Member> getMembers() {
        return members;
    }
    /**
     * Postavlja clanove tima
     * @param teamMembers clanovi tima
     */
    public void setMembers(ArrayList<Member> teamMembers) {
    	if(teamMembers==null || teamMembers.isEmpty())
    		throw new NullPointerException("List of team members cant be null or empty");
        this.members = teamMembers;
    }
    /**
     * Vraca kategoriju u kojoj se tim takmicio
     * @return kategorija u kojoj se tim takmicio
     */
    public Category getCategory() {
        return category;
    }
    /**
     * Postavlja kategoriju u kojoj se tim takmicio
     * @param category kategorija u kojoj se tim takmicio
     */
    public void setCategory(Category category) {
    	if(category==null)
    		throw new NullPointerException("Category cant be null");
        this.category = category;
    }

    @Override
    public String toString() {
        return name;
    }

    
    @Override
    public String getAttributeList() {
        return "name,category";
    }

    @Override
    public String getClassName() {
       return "team";
    }

    @Override
    public String getAttributeValues() {
       return "'"+getName()+"','"+getCategory()+"'";
    }

    @Override
    public String getQueryCondition() {
        return "id="+getId();
    }

  
    @Override
    public int getNumberOfBountObject() {
        return members.size();
    }


    @Override
    public Object getBoundObject(int numberOfObjects,int numberOfBoundAttributes) {
        return members.get(numberOfObjects);
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
       
        City c= new City(rs.getLong("c.id"), rs.getLong("c.ptt"), rs.getString("c.name"));
        Member m =new Member(rs.getLong("m.memberId"), rs.getString("m.name"), rs.getString("m.lastname"),Gender.valueOf(rs.getString("m.gender")), 
                rs.getDate("m.birthday"), rs.getString("m.mother"), rs.getString("m.father"), c,
                Belt.valueOf(rs.getString("m.belt")), 
                rs.getLong("m.medals"), Discipline.valueOf(rs.getString("m.discipline")), Category.valueOf(rs.getString("m.category")), rs.getString("m.adress"), 
                rs.getLong("m.gold"), rs.getLong("m.silver"), rs.getLong("m.bronze"), 
                new ArrayList<MembershipFee>(), rs.getDate("m.dateOfMembership"), rs.getDouble("m.totalDebt"));
               
        Team team= new Team();
        //Member m= new Member();
        m.setMemberID(rs.getLong("tm.idMember"));
        teamMembers.add(m);
       
        team.setId(rs.getLong("t.id"));
        team.setCategory(Category.valueOf(rs.getString("t.category")));
        team.setName(rs.getString("t.name"));
        team.setMembers(teamMembers);
        
        if(teamMembers.size()==3){
            teamMembers= new ArrayList<>();
            return team;
            
        }
         else{
             return null;
         }
    }

    @Override
    public String getStatementSelectAllQuery() {
    	 return "SELECT t.id,t.name,t.category,tm.idTeam,tm.idMember,\n" +
    	           "m.id as memberId, m.name as name, m.lastname as lastname,\n" +
    	           "m.gender as gender,m.dateOfBirth as birthday,m.mothersName as mother,\n" +
    	           "m.fathersName as father, m.adress as adress, m.belt as belt,\n" +
    	           "m.discipline as discipline, m.category as category,\n" +
    	           "m.dateOfMembership as dateOfMembership,\n" +
    	           "m.medals as medals,m.cityId as cityId,m.goldMedals as gold, m.silverMedals as silver, m.bronzeMedals as bronze,\n" +
    	           "m.totalDebt as totalDebt, c.name,c.id,c.ptt\n" +
    	           "FROM team t JOIN teammembers tm ON (t.id=tm.idTeam) JOIN member M ON (tm.idMember=m.id)\n" +
    	           "JOIN city c ON (c.id=m.cityId)";
    }

    @Override
    public String getBoundObjectClassName() {
        return "teammembers";
    }

    @Override
    public String getBoundObjectAttributeList() {
       return "idTeam,idMember";
    }


    @Override
    public String getBoundObjectAttributeValues(int attributeNumber) {
        return id+","+members.get(attributeNumber).getMemberID();
    }

   
    
    
    
    
}
