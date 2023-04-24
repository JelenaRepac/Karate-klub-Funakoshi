/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.fon.np.application.kkfunakoshi.so.result;

import java.util.List;
import rs.fon.np.application.kkfunakoshi.domain.Competition;
import rs.fon.np.application.kkfunakoshi.domain.Member;
import rs.fon.np.application.kkfunakoshi.domain.MembershipFee;
import rs.fon.np.application.kkfunakoshi.domain.Result;
import rs.fon.np.application.kkfunakoshi.domain.Team;


import rs.fon.np.application.kkfunakoshi.so.AbstractSO;

/**
 *
 * @author Jeks
 */
public class GetByQueryResultSO extends AbstractSO {
    
    
    private List<Result> results;
    
    @Override
    protected void precondition(Object param) throws Exception {
        if(param==null || !(param instanceof String))
           throw new Exception("Object is the wrong type!");
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        
        
        String query= (String) param;
        try {
            results= repository.getByQuery(new Result(),query);
            for(Result r: results){
                
                if(r.getMember().getMemberID()!=0){
                    r.setMember(getMember(r));
                }
                else{
                    r.setMember(new Member());
                }
                if(r.getTeam().getId()!=0){
                    Team team= (Team) repository.get(new Team(), " WHERE t.id="+r.getTeam().getId());
                    r.setTeam(team);
                }
                else{
                    r.setTeam(new Team());
                }
                Competition competition= (Competition) repository.get(new Competition(), " WHERE c.id="+r.getCompetition().getId());
                r.setCompetition(competition);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage(), e);
        }
       
    }

    private Member getMember(Result r) throws Exception {
        Member member= (Member) repository.get(new Member(), " WHERE m.id="+r.getMember().getMemberID());
        List<MembershipFee> membershipFees= repository.getByQuery(new MembershipFee(), " WHERE mf.memberId="+member.getMemberID());
        member.setMembershipFee(membershipFees);
        return member;
    }

    public List<Result> getResults() {
        return results;
    }
 
    
    
}
