/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.fon.np.application.kkfunakoshi.so.member;

import java.util.ArrayList;
import java.util.List;
import rs.fon.np.application.kkfunakoshi.domain.Member;
import rs.fon.np.application.kkfunakoshi.domain.MembershipFee;
import rs.fon.np.application.kkfunakoshi.domain.Result;
import rs.fon.np.application.kkfunakoshi.domain.Team;
import rs.fon.np.application.kkfunakoshi.so.AbstractSO;

/**
 * Klasa koja se odnosi na brisanje clana.
 * @author Jelena Repac
 */
public class DeleteMemberSO extends AbstractSO {


    
    @Override
    protected void precondition(Object param) throws Exception {
       if(param==null || !(param instanceof Member)){
         throw new Exception("Object is the wrong type!");
            
        } else {
            Member member = (Member) param;
            try {
                checkStructuralConstraints(member);
              
            } catch (Exception ex) {
                throw new Exception(ex.getMessage());
            }
        }
    
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        Member m=(Member) param;
        repository.delete(m);
        
    }
    
     private void checkStructuralConstraints(Member member) throws Exception {
            checkIfMembershipFeesExist(member);
            checkIfMemberHasPartInTeam(member);
            checkIfMemberHasResults(member);
    }

    private void checkIfMembershipFeesExist(Member member) throws Exception {
        try {
            String query = " WHERE mf.memberId=" + member.getMemberID();
            List<MembershipFee> membershipFees = repository.getByQuery(new MembershipFee(),query);
            for(MembershipFee mf: membershipFees){
                if(mf.getMember().getMemberID().equals(member.getMemberID())){
                    throw new Exception("Member has paid membership fees. It can't be deleted!.");
                }
            }
            
        } catch (Exception ex) {
            throw ex;
        }

    }

    private void checkIfMemberHasPartInTeam(Member member) throws Exception {
        try {
            List<Team> teams =repository.getByQuery(new Team(), "");
            teams=filterTeam(teams);
            for(Team team : teams){
               
                    for(Member m:team.getMembers()){
                        if(m.getMemberID().equals(member.getMemberID())){
                            throw new Exception("Member has part in team. It can't be deleted!");
                        }
                    }
                
            }
           
        } catch (Exception ex) {
            throw ex;
         }
    } 

    private void checkIfMemberHasResults(Member member) throws Exception {
       try {
            String query = " WHERE memberId=" + member.getMemberID()+" ORDER BY medal";
           
            List<Result> results = repository.getByQuery(new Result(), query);
            if (results!=null && results.size() > 0) {
                throw new Exception("Member has results. It can't be deleted!.");
            }
        } catch (Exception ex) {
            throw ex;
         }
    }
    
     private List<Team> filterTeam(List<Team> teams) {
        List<Team> returnTeams=new ArrayList<>();
        for(Team t: teams){
                if(t!=null){
                returnTeams.add(t);
                }
        }
        return returnTeams;
    }
    
}
