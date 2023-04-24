/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.fon.np.application.kkfunakoshi.so.member;

import java.util.List;
import rs.fon.np.application.kkfunakoshi.domain.Member;
import rs.fon.np.application.kkfunakoshi.domain.MembershipFee;
import rs.fon.np.application.kkfunakoshi.so.AbstractSO;

/**
 *
 * @author Jeks
 */
public class GetAllMembersSO extends AbstractSO{

   
    private List<Member> members;
    
    @Override
    protected void precondition(Object param) throws Exception {
        //no recondition to check
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        
        try {
            
            members= repository.getAll(new Member());
            for(Member m: members){
                List<MembershipFee> membershipFees= repository.getByQuery(new MembershipFee(), " WHERE memberId="+m.getMemberID());
                m.setMembershipFee(membershipFees);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error while loading the members", e);
        }
       
    }

    public List<Member> getMembers() {
        return members;
    }
    
    
    
}