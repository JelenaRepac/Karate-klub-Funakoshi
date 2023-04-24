/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.fon.np.application.kkfunakoshi.domain;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Jeks
 */
public class Result extends AbstractDO implements Serializable {
    
    private Long id;
    private Competition competition;
    private Member member;
    private Team team;
    private Medal medals;
    private Category category;

    public Result() {
    }

    public Result(Long id, Competition competition, Member member, Team team, Medal medals, Category category) {
        this.id = id;
        this.competition = competition;
        this.member = member;
        this.team = team;
        this.medals = medals;
        this.category = category;
    }

    public Result(Competition competition, Member member, Team team, Medal medals, Category category) {
        
        this.competition = competition;
        this.member = member;
        this.team = team;
        this.medals = medals;
        this.category = category;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Competition getCompetition() {
        return competition;
    }

    public void setCompetition(Competition competition) {
        this.competition = competition;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Medal getMedals() {
        return medals;
    }

    public void setMedals(Medal medals) {
        this.medals = medals;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    
    @Override
    public String toString() {
        return "Result{" + "competition=" + competition + ", member=" + member + ", team=" + team + ", category='"+category+"', medals=" + medals + '}';
    }

    
    @Override
    public String getAttributeList() {
        return "competitionId,memberId,teamId,category,medal";
    }

    @Override
    public String getClassName() {
        return "result";
    }

    @Override
    public String getAttributeValues() {
       return getCompetition().getId()+","+((getMember()!=null)?getMember().getMemberID():null)+","+((getTeam()!=null)?getTeam().getId():null)+",'"+
               getCategory().toString()+"','"+getMedals().toString()+"'";
    }

    @Override
    public String getQueryCondition() {
        return "id="+getId();
    }

    @Override
    public int getNumberOfBountObject() {
       return 2;
    }

    @Override
    public Object getBoundObject(int numberOfObjects,int numberOfBoundAttributes) {
        if(numberOfObjects==0){
            return getCompetition();
        }
        if(numberOfObjects==1){
            return getMember();
        }
        else
            return null;
        
    }

    @Override
    public int getNumberOfAttributesBoundObjects() {
        //return 1;
        return 0;
    }

    @Override
    public void setForeignId(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String setAttributeValues() {
        return "competitionId="+getCompetition().getId()+","+
                "memberId="+getMember().getMemberID()+","+
                "teamId="+getTeam().getId()+","+
                "category='"+getCategory().toString()+"',"+
                "medal='"+getMedals().toString()+"'";
    }

    @Override
    public AbstractDO getEntityFromResultSet(ResultSet rs) throws SQLException {
        Member m= new Member();
        m.setMemberID(rs.getLong("memberId"));
        
        Team t= new Team();
        t.setId(rs.getLong("teamId"));
        
        Competition c= new Competition();
        c.setId(rs.getLong("competitionId"));
        
        return new Result(rs.getLong("id"), c, m, t, Medal.valueOf(rs.getString("medal")), Category.valueOf(rs.getString("category")));
    }
    
    @Override
    public String getStatementSelectAllQuery() {
        return "SELECT id,competitionId,memberId,teamId,category,medal FROM result";
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
