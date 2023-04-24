/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.fon.np.application.kkfunakoshi.domain;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Jeks
 */
public class MembershipFee extends AbstractDO implements Serializable{
    private Long id;
    private Member member;
    private Date date;
    private double Amount;

    public MembershipFee() {
    }

    public MembershipFee(Long id, Member member, Date date, double Amount) {
        this.id = id;
        this.member = member;
        this.date = date;
        this.Amount = Amount;
    }

    public MembershipFee(Member member, Date date, double Amount) {
        this.member = member;
        this.date = date;
        this.Amount = Amount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getAmount() {
        return Amount;
    }

    public void setAmount(double Amount) {
        this.Amount = Amount;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    @Override
    public String toString() {
        return "MembershipFee{" + "date=" + new java.sql.Date(getDate().getTime()) + ", Amount=" + Amount + ",Member="+getMember()+'}';
    }

    @Override
    public String getAttributeList() {
       return "date,amount,memberId";
    }

    @Override
    public String getClassName() {
       return "membershipfee";
    }

    @Override
    public String getAttributeValues() {
       return "'"+new java.sql.Date(getDate().getTime())+"',"+getAmount()+","+getMember().getMemberID();
    }

    @Override
    public String getQueryCondition() {
       return "id="+id;
    }

    @Override
    public int getNumberOfBountObject() {
        return 1;
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
        getMember().setMemberID(id);
    }

    @Override
    public String setAttributeValues() {
        return "date='"+new java.sql.Date(getDate().getTime())+"',"+
                "amount="+getAmount()+","+
                "memberId="+getMember().getMemberID();
    }

    @Override
    public AbstractDO getEntityFromResultSet(ResultSet rs) throws SQLException {
        Member member= new Member(rs.getLong("m.id"), rs.getString("m.name"), rs.getString("m.lastname"), 
                Gender.valueOf(rs.getString("m.gender")), rs.getDate("m.dateOfBirth"), rs.getString("m.mothersName"), rs.getString("m.fathersName"), 
                new City(rs.getLong("m.cityId"), rs.getLong("c.ptt"), rs.getString("c.name")),
                Belt.valueOf(rs.getString("m.belt")), rs.getLong("m.medals"), Discipline.valueOf(rs.getString("m.discipline")),
                Category.valueOf(rs.getString("m.category")), rs.getString("m.adress"), rs.getLong("m.goldMedals"), rs.getLong("m.silverMedals"),
                rs.getLong("m.bronzeMedals"), new ArrayList<MembershipFee>(), rs.getDate("m.dateOfMembership"), rs.getDouble("m.totalDebt"));
        MembershipFee membershipFee= new MembershipFee(rs.getLong("mf.id"), member, rs.getDate("mf.date"),rs.getDouble("mf.amount"));
        return membershipFee;
    }

    @Override
    public String getStatementSelectAllQuery() {
        return "SELECT mf.id,mf.date,mf.amount,mf.memberId, "
                    + "m.id,m.name,m.lastname,m.gender,m.dateOfBirth, m.mothersName,m.fathersName,m.adress,"
                    + "m.belt,m.medals,m.discipline,m.category,m.goldMedals,m.silverMedals,m.bronzeMedals,m.dateOfMembership,m.totalDebt,"
                    + "m.cityId,c.id,c.ptt,c.name FROM membershipfee mf JOIN member m ON (mf.memberId=m.id) JOIN city c ON (c.id=m.cityId)";
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