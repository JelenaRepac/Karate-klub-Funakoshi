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
 * Klasa koja se odnosi na azuriranje rezultata.
 * @author Jelena Repac
 */
public class UpdateResultSO extends AbstractSO{
    
    
   
     
    @Override
    protected void precondition(Object param) throws Exception {
        if(param==null){
            throw new Exception("The object was not sent!");
        }
        List<Result> results= (List<Result>) param;
        
        if(results.size()<2 || results.get(0).equals(null) || results.get(1).equals(null)){
             throw new Exception("No required parameters sent!");
        }
        if(!(results.get(0) instanceof Result) || !(results.get(1) instanceof Result)){
             throw new Exception("Object is the wrong type!!");
        } else{
            Result newResult= ((List<Result>) param).get(1);
            checkStructuralConstraints(newResult);
        }

    }

    @Override
    protected void executeOperation(Object param) throws Exception {
       List<Result> resultsForUpdate= (List<Result>) param;
       Result oldResult= resultsForUpdate.get(0);
       Result newResult= resultsForUpdate.get(1);
       
        try {
            repository.edit(oldResult, newResult);
            
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error while trying to change result info.", e);
        }
    }

    private void checkStructuralConstraints(Result result) throws Exception {
        boolean exists= ckeckIfExcists(result);
        if(exists){
            throw new Exception("Result exists.");
        }
    }

    private boolean ckeckIfExcists(Result result) throws Exception {
        
        List<Result> results= getResults();
        for(Result r: results){
            if(r.getMember()!=null){
                if(r.getCompetition().getName().equals(result.getCompetition().getName())
                        && r.getMember().getName().equals(result.getMember().getName())
                        && r.getMember().getLastname().equals(result.getMember().getLastname())
                        && r.getMedals().equals(result.getMedals())
                        && r.getCategory().equals(r.getCategory())){
                    return true;
                }
                break;
            }
            if(r.getTeam()!=null){
                if(r.getCompetition().getName().equals(result.getCompetition().getName())
                    && r.getTeam().getName().equals(result.getTeam().getName()) 
                    && r.getMedals().equals(result.getMedals())
                    && r.getCategory().equals(r.getCategory())){
                    return true;
                }
                break;
            }
        }
        return false;
    }
    
    
    

    private List<Result> getResults() throws Exception {
       List<Result> results=repository.getByQuery(new Result(), "");
        
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
            return results;
    }
    
     private Member getMember(Result r) throws Exception {
        Member member= (Member) repository.get(new Member(), " WHERE m.id="+r.getMember().getMemberID());
        List<MembershipFee> membershipFees= repository.getByQuery(new MembershipFee(), " WHERE mf.memberId="+member.getMemberID());
        member.setMembershipFee(membershipFees);
        return member;
    }
}
