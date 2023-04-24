/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package rs.fon.np.application.kkfunakoshi.domain;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Jeks
 */
public class Member extends AbstractDO implements Serializable{
    
    private Long memberID;
    private String name;
    private String lastname;
    private Gender gender;
    private Date birthday;
    private String mothersName;
    private String fathersName;
    private City city;
    private Belt belt;
    private Long medals;
    private Discipline discipline;
    private Category category;
    private String adress;
    private Long goldMedals;
    private Long silverMedals;
    private Long bronzeMedals;
    List<MembershipFee> membershipFee;
    private Date dateOfMembership;
    private Double totalDebt;
    public Member() {
        this.membershipFee= new ArrayList<>();
    }
    public Member(Long memberID, String name, String lastname, Gender gender, Date birthday, String mothersName,
            String fathersName, City city, Belt belt, Long medals, Discipline discipline, Category category,
            String adress, Long goldMedals, Long silverMedals, Long bronzeMedals, List<MembershipFee> membershipFee, Date dateOfMembership, Double totalDebt) {
        this.memberID = memberID;
        this.name = name;
        this.lastname = lastname;
        this.gender = gender;
        this.birthday = birthday;
        this.mothersName = mothersName;
        this.fathersName = fathersName;
        this.city = city;
        this.belt = belt;
        this.medals = medals;
        this.discipline = discipline;
        this.category = category;
        this.adress = adress;
        this.goldMedals = goldMedals;
        this.silverMedals = silverMedals;
        this.bronzeMedals = bronzeMedals;
        this.membershipFee = membershipFee;
        this.dateOfMembership= dateOfMembership;
        this.totalDebt=totalDebt;
    }

   
     public Member(String name, String lastname, Gender gender, Date birthday, String mothersName, 
             String fathersName, City city, Belt belt, Long medals, Discipline discipline, Category category, 
             String adress, Long goldMedals, Long silverMedals, Long bronzeMedals, List<MembershipFee> membershipFee, Date dateOfMembership, Double totalDebt) {
        
        this.name = name;
        this.lastname = lastname;
        this.gender = gender;
        this.birthday = birthday;
        this.mothersName = mothersName;
        this.fathersName = fathersName;
        this.city = city;
        this.belt = belt;
        this.medals = medals;
        this.discipline = discipline;
        this.category = category;
        this.adress = adress;
        this.goldMedals = goldMedals;
        this.silverMedals = silverMedals;
        this.bronzeMedals = bronzeMedals;
        this.membershipFee = membershipFee;
        this.dateOfMembership=dateOfMembership;
        this.totalDebt=totalDebt;
    }


   
    public Long getMemberID() {
        return memberID;
    }

    public void setMemberID(Long memberID) {
        this.memberID = memberID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getMothersName() {
        return mothersName;
    }

    public void setMothersName(String mothersName) {
        this.mothersName = mothersName;
    }

    public String getFathersName() {
        return fathersName;
    }

    public void setFathersName(String fathersName) {
        this.fathersName = fathersName;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Belt getBelt() {
        return belt;
    }

    public void setBelt(Belt belt) {
        this.belt = belt;
    }

    public Long getMedals() {
        return medals;
    }

    public void setMedals(Long medals) {
        this.medals = medals;
    }

    public Long getGoldMedals() {
        return goldMedals;
    }

    public void setGoldMedals(Long goldMedals) {
        this.goldMedals = goldMedals;
    }

    public Long getSilverMedals() {
        return silverMedals;
    }

    public void setSilverMedals(Long silverMedals) {
        this.silverMedals = silverMedals;
    }

    public Long getBronzeMedals() {
        return bronzeMedals;
    }

    public void setBronzeMedals(Long bronzeMedals) {
        this.bronzeMedals = bronzeMedals;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Discipline getDiscipline() {
        return discipline;
    }

    public void setDiscipline(Discipline discipline) {
        this.discipline = discipline;
    }

    public List<MembershipFee> getMembershipFee() {
        return membershipFee;
    }

    public void setMembershipFee(List<MembershipFee> membershipFee) {
        this.membershipFee = membershipFee;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Date getDateOfMembership() {
        return dateOfMembership;
    }

    public void setDateOfMembership(Date dateOfMembership) {
        this.dateOfMembership = dateOfMembership;
    }

    public Double getTotalDebt() {
        return totalDebt;
    }

    public void setTotalDebt(Double totalDebt) {
        this.totalDebt = totalDebt;
    }

    
    @Override
    public String toString() {
        return ((name!=null)?name:"")+" "+((lastname!=null)?lastname:"");
    }

    @Override
    public String getAttributeList() {
        return "name,lastname,gender,dateOfBirth,mothersName,fathersName,"
                + "adress,belt,dateOfMembership,medals,goldMedals,silverMedals,bronzeMedals,"
                + "discipline,category,cityId,totalDebt";
    }

    @Override
    public String getClassName() {
      return "member";
    }

    @Override
    public String getAttributeValues() {
       return "'"+getName()+"', '"+getLastname()+"', '"+getGender()+"', '"
               + new java.sql.Date(getBirthday().getTime())+"','"+getMothersName()+"', '"
               + getFathersName()+"', '"+getAdress()+"',  '"+getBelt().toString()+"', '"+new java.sql.Date(getDateOfMembership().getTime())+"', "+
               getMedals()+","+getGoldMedals()+","+getSilverMedals()+","+getBronzeMedals()+",'"+getDiscipline().toString()+"', '"+
               getCategory().toString()+"',"+getCity().getId()+","+getTotalDebt();
    }

    @Override
    public String getQueryCondition() {
        return "id="+getMemberID();
    }

    @Override
    public void setId(Long id) {
        this.memberID=id;
    }


    @Override
    //vrati broj vezanih objekata
    //lista clanarina
    public int getNumberOfBountObject() {
        return getMembershipFee().size();
    }

    @Override
    public Object getBoundObject(int numberOfObjects,int numberOfBoundAttributes) {
        return getMembershipFee().get(numberOfObjects);
    }

    @Override
    public int getNumberOfAttributesBoundObjects() {
        return 1;
    }

    @Override
    public void setForeignId(Long id) {
        setMemberID(id);
    }

    @Override
    public String setAttributeValues() {
        return "id="+getMemberID()+","
                +"name='"+getName()+"',"+
                "lastname='"+getLastname()+"',"+
                "gender='"+getGender().toString()+"',"+
                "dateOfBirth='"+new java.sql.Date(getBirthday().getTime())+"',"+
                "mothersName='"+getMothersName()+"',"+
                "fathersName='"+getFathersName()+"',"+
                "adress='"+getAdress()+"',"+
                "belt='"+getBelt().toString()+"',"+
                "discipline='"+getDiscipline().toString()+"',"+
                "category='"+getCategory().toString()+"',"+
                "dateOfMembership='"+new java.sql.Date(getDateOfMembership().getTime())+"',"+
                "medals="+getMedals()+","+
                "goldMedals="+getGoldMedals()+","+
                "silverMedals="+getSilverMedals()+","+
                "bronzeMedals="+getBronzeMedals()+","+
                "cityId="+getCity().getId()+","+
                "totalDebt="+getTotalDebt();
    }

    @Override
    public AbstractDO getEntityFromResultSet(ResultSet rs) throws SQLException {
        City c= new City(rs.getLong("cityId"), rs.getLong("ptt"), rs.getString("cityName"));
        return new Member(rs.getLong("memberId"), rs.getString("name"), rs.getString("lastname"),Gender.valueOf(rs.getString("gender")), 
                rs.getDate("birthday"), rs.getString("mother"), rs.getString("father"), c,Belt.valueOf(rs.getString("belt")), 
                rs.getLong("medals"), Discipline.valueOf(rs.getString("discipline")), Category.valueOf(rs.getString("category")), rs.getString("adress"), 
                rs.getLong("gold"), rs.getLong("silver"), rs.getLong("bronze"), 
                new ArrayList<MembershipFee>(), rs.getDate("dateOfMembership"), rs.getDouble("totalDebt"));
    }
    @Override
    public String getStatementSelectAllQuery() {
        return "SELECT m.id as memberId, m.name as name, m.lastname as lastname,"
                    + "m.gender as gender,m.dateOfBirth as birthday,m.mothersName as mother,"
                    + "m.fathersName as father, m.adress as adress, m.belt as belt,"
                    + "m.discipline as discipline, m.category as category,"
                    + "m.dateOfMembership as dateOfMembership,"
                    + "m.medals as medals,m.cityId as cityId,m.goldMedals as gold, m.silverMedals as silver, m.bronzeMedals as bronze,"
                    + "m.totalDebt as totalDebt, c.name as cityName,"
                    + "c.ptt as ptt FROM member m INNER JOIN city c ON (m.cityId=c.id)";
        
    }

    @Override
    public Long getId() {
        return memberID;
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
