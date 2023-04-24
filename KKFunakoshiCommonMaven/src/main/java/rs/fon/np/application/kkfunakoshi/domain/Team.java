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
 *
 * @author Jeks
 */
public class Team extends AbstractDO implements Serializable {
    
    private Long id;
    private String name;
    private List<Member> members;
    private Category category;
    
    private List<Member> teamMembers= new ArrayList<>();

    public Team() {
    }

    public Team(Long id, String name, List<Member> teamMembers, Category category) {
        this.id = id;
        this.name = name;
        this.members = members;
        this.category = category;
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

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> teamMembers) {
        this.members = teamMembers;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
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
