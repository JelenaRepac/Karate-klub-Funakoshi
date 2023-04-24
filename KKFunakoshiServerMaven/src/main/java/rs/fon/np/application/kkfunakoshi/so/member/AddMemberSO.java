/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.fon.np.application.kkfunakoshi.so.member;

import java.util.List;
import  rs.fon.np.application.kkfunakoshi.domain.Member;
import  rs.fon.np.application.kkfunakoshi.so.AbstractSO;

/**
 *
 * @author Jeks
 */
public class AddMemberSO extends AbstractSO {
    
    
    @Override
    protected void precondition(Object param) throws Exception {
        if(param== null || !(param instanceof Member)){
            throw new Exception("The object is the wrong type!");
        }
        else{
            Member member=(Member) param;
            checkStructuralConstraints(member);
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        repository.addComplex((Member) param);
        
    }

    private void checkStructuralConstraints(Member member) throws Exception {
       boolean exists= checkIfExists(member);
       if(exists){
           throw new Exception("The member already exists. Try again.");
       }
    }

    private boolean checkIfExists(Member member) throws Exception {
        List<Member> members= repository.getAll(new Member());
        for(Member m: members){
            if(m.getName().equals(member.getName()) && m.getLastname().equals(member.getLastname()) 
                    && m.getBirthday().equals(member.getBirthday())){
                return true;
            }
        }
        return false;
    }
}
