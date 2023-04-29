/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.fon.np.application.kkfunakoshi.so.member;

import java.util.List;
import rs.fon.np.application.kkfunakoshi.domain.Member;
import rs.fon.np.application.kkfunakoshi.so.AbstractSO;

/**
 * Klasa koja se odnosi na azuriranje clana u bazi.
 * @author Jelena Repac
 */
public class UpdateMemberSO extends AbstractSO{

    @Override
    protected void precondition(Object param) throws Exception {
         if(param==null){
            throw new Exception("The object was not sent!");
        }
        List<Member> members= (List<Member>) param;
        
        if(members.size()<2 || members.get(0).equals(null) || members.get(1).equals(null)){
             throw new Exception("No required parameters sent!");
        }
        if(!(members.get(0) instanceof Member) || !(members.get(1) instanceof Member)){
             throw new Exception("Object is the wrong type!");
        } 

    }

    @Override
    protected void executeOperation(Object param) throws Exception {
       List<Member> membersForUpdate= (List<Member>) param;
       Member oldMember= membersForUpdate.get(0);
       Member newMember= membersForUpdate.get(1);

        try {
            repository.editComplex(oldMember,newMember);
            repository.addBoundObjects(newMember);
            
            
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage(), e);
    
        }
    }
   

}
