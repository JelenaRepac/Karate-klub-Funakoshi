/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.fon.np.application.kkfunakoshi.domain;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Predstavlja rezultat ostvaren na takmicenju. 
 * Sadrzi id, takmicenje na kome je ostvaren rezultat, clana i tim koji su ostvarili rezultat i kategoriju u kojoj su se takmicili kao i osvojenu medalju. 
 * Implementira interfejs Serializable, sto dopusta da instance klase budu serijalizovane i deserijalizovane.
 * Nasledjuje abstraktnu klasu AbstractDO.
 * @author Jelena Repac
 */
public class Result extends AbstractDO implements Serializable {
    
	/**
	 * Id rezultat
	 */
    private Long id;
    /**
     * Takmicenje na kome je rezultat ostvaren
     */
    private Competition competition;
    /**
     * Clan koji je rezultat ostvario
     */
    private Member member;
    /**
     * Tim koji je ostvario rezultat
     */
    private Team team;
    /**
     * Osvojena medalja
     */
    private Medal medals;
    /**
     * Kategorija u kojoj je takmicar ostvario rezultat
     */
    private Category category;

    /**
     * Bezparametarski konstruktor
     */
    public Result() {
    }
	/**
	 * Konstruktor
	 * @param id id rezultat
	 * @param competition takmicenje
	 * @param member clan
	 * @param team tim
	 * @param medals medalja
	 * @param category kategorija
	 */
    public Result(Long id, Competition competition, Member member, Team team, Medal medals, Category category) {
        this.id = id;
        this.competition = competition;
        this.member = member;
        this.team = team;
        this.medals = medals;
        this.category = category;
    }

    /**
	 * Konstruktor
	 * @param competition takmicenje
	 * @param member clan
	 * @param team tim
	 * @param medals medalja
	 * @param category kategorija
	 */
    public Result(Competition competition, Member member, Team team, Medal medals, Category category) {
        
        this.competition = competition;
        this.member = member;
        this.team = team;
        this.medals = medals;
        this.category = category;
    }
    /**
     * Vraca ID rezultata takmicenja
	 * @return ID  rezultata takmicenja
     */
    public Long getId() {
        return id;
    }
    /**
     * Postavlja ID rezultata takmicenja
     * @param id ID rezultata takmicenja
     * @throws IllegalArgumentException Ukoliko je id negativan broj ili jednak nuli
     */
    public void setId(Long id) {
    	if(id<0 || id==0)
    		throw new IllegalArgumentException("Id ne sme biti negativan broj ili jednak nuli");
        this.id = id;
    }
    /**
     * Vraca takmicenje na kome je ostvaren rezultat
     * @return  takmicenje na kome je ostvaren rezultat
     */
    public Competition getCompetition() {
        return competition;
    }
    /**
     * Postavlja takmicenje na kome je ostvaren rezultat
     * @param competition takmicenje na kome je ostvaren rezultat
     * @throws IllegalArgumentException Ukoliko takmicenje nije u potpunosti inicijalizovano
     */
    public void setCompetition(Competition competition) {
    	if(competition==null || competition.getId()==null || competition.getCity()==null || competition.getName().equals("") || competition.getCompetitionHall().equals("") || competition.getDate()==null )
    		throw new IllegalArgumentException("Takmicenje mora biti u potpunosti inicijalizovano");
    	this.competition = competition;
    }
    /**
     * Vraca clana koji je ostvario rezultat
     * @return clan koji je ostvario rezultat
     */
    public Member getMember() {
        return member;
    }
    /**
     * Postavlja clana koji je ostvario rezultat
     * @param member clan koji je ostvario rezultat
     * @throws IllegalArgumentException Ukoliko clan nije u potpunosti inicijalizovan
     */
    public void setMember(Member member) {
    	if(member== null || member.getId()==null ||
    			member.getName().equals("") || member.getLastname().equals("") ||
    			member.getAdress().equals("") || member.getBelt()==null ||
    			member.getCategory()==null || member.getCity()==null || 
    			member.getDiscipline()==null || member.getTotalDebt()==null ||
    			member.getMothersName().equals("") || member.getFathersName().equals("") ||
    			member.getDateOfMembership()==null || member.getGender()==null)
    		throw new IllegalArgumentException("Clan mora biti u potpunosti inicijalizovan");
        this.member = member;
    }
    /**
     * Vraca medalju koja je osvojena na takmicenju
     * @return medalja ostvarena na takmicenju
     */
    public Medal getMedals() {
        return medals;
    }
    /**
     * Postavlja medalju osvojenu na takmicenju
     * @param medals osvojena medalja
     * @throws NullPointerException Ukoliko je medalja null
     */
    public void setMedals(Medal medals) {
    	if(medals==null)
    		throw new NullPointerException("Prosledjena medalja ne sme biti null");
        this.medals = medals;
    }
    /**
     * Vraca tim koji je osvojio medalju na takmicenju
     * @return tim 
     */
    public Team getTeam() {
        return team;
    }
    /**
     * Postavlja tim koji je osvojio medalju na takmicenju
     * @param team tim koji je osvojio medalju
     * @throws IllegalArgumentException Ukoliko prosledjeni tim nije u potpunosti inicijalizovan
     */
    public void setTeam(Team team) {
    	if(team== null || team.getId()==null || team.getName().equals("")
    			|| team.getMembers()==null || team.getCategory()==null) 
    		throw new IllegalArgumentException("Prosledjeni tim nije u potpunosti inicijalizovan");
        this.team = team;
    }
    /**
     * Vraca kategoriju u kojoj se clan kluba takmicio i osvojio medalju
     * @return kategorija clana
     */
    public Category getCategory() {
        return category;
    }
    /**
     * Postavlja kategoriju u kojoj se clan takmicio i osvojio medalju
     * @param category kategorija u kojoj se clan takmicio i osvojio medalju
     * @throws NullPointerException Ukoliko je kategorija null
     */
    public void setCategory(Category category) {
    	if(category==null)
    		throw new NullPointerException("Kategorija ne sme biti null");
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
        try {
			c.setId(rs.getLong("competitionId"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
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
