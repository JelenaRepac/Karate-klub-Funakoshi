package rs.fon.np.application.kkfunakoshi.so.member;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import rs.fon.np.application.kkfunakoshi.domain.Belt;
import rs.fon.np.application.kkfunakoshi.domain.Category;
import rs.fon.np.application.kkfunakoshi.domain.City;
import rs.fon.np.application.kkfunakoshi.domain.Competition;
import rs.fon.np.application.kkfunakoshi.domain.Discipline;
import rs.fon.np.application.kkfunakoshi.domain.Gender;
import rs.fon.np.application.kkfunakoshi.domain.Medal;
import rs.fon.np.application.kkfunakoshi.domain.Member;
import rs.fon.np.application.kkfunakoshi.domain.Result;
import rs.fon.np.application.kkfunakoshi.domain.Team;
import rs.fon.np.application.kkfunakoshi.domain.Trainer;
import rs.fon.np.application.kkfunakoshi.repository.Repository;
import rs.fon.np.application.kkfunakoshi.repository.impl.RepositoryDBGeneric;
import rs.fon.np.application.kkfunakoshi.so.trainer.LogOutSO;

class DeleteMemberSOTest {

	private DeleteMemberSO deleteMemberSO;
	private Repository repository;
	
	Calendar calendar = Calendar.getInstance();
    Calendar dateOfMembership = Calendar.getInstance();
	 @BeforeEach
	    void setUp() throws Exception {
		 deleteMemberSO = new DeleteMemberSO();
		 repository= new RepositoryDBGeneric();
	    }

	 @Test
    void testPreconditionWithNullParam() {
        assertThrows(Exception.class, 
        		() -> deleteMemberSO.execute(null));
    }
	    
    @Test
    void testPreconditionWithWrongTypeParam() {
        assertThrows(Exception.class, 
        		() -> deleteMemberSO.execute(new City()));
    }
   
    @Test
    void testExecuteOperation() throws Exception {
        calendar.set(2000, 3,2);
        dateOfMembership.set(2022, 11,2);
    	Member member =  
    			new Member(103L,"Marija", "Repac", Gender.FEMALE, calendar.getTime(),
    					"Jadranka", "Nikola", new City(1L,11000L,"Beograd"), 
    					Belt.CRNI, 50L, Discipline.KATE, Category.SENIOR,
    					"Niksicka 10", 10L, 10L, 30L, new ArrayList<>(),
    					dateOfMembership.getTime(), 3000.00);
    	deleteMemberSO.execute(member);
    	List<Member> members=repository.getByQuery(new Member(), " WHERE m.id="+member.getId());
    	assertEquals(0,members.size());
    	assertEquals(11, repository.getAll(new Member()).size());
    }
    @Test
    void testMemberHasResult() throws Exception {
        calendar.set(2000, 3,2);
        dateOfMembership.set(2022, 11,2);
    	Member member =  
    			new Member(103L,"Marija", "Repac", Gender.FEMALE, calendar.getTime(),
    					"Jadranka", "Nikola", new City(1L,11000L,"Beograd"), 
    					Belt.CRNI, 50L, Discipline.KATE, Category.SENIOR,
    					"Niksicka 10", 10L, 10L, 30L, new ArrayList<>(),
    					dateOfMembership.getTime(), 3000.00);
    	repository.add(member);
    	Result result= new Result(new Competition(223L,"Zlatno zvono", new Date(), "Ranko Zeravica", new City(1L, 11000L, "Beograd")),
    			member,new Team(),Medal.BRONZE,Category.SENIOR);
    	repository.add(result);
    	Exception exception= assertThrows(Exception.class, 
    			()-> deleteMemberSO.execute(member));
    	String expectedMessage= "Member has results. It can't be deleted!";
    	String actualMessage= exception.getMessage();
    	assertTrue(actualMessage.contains(expectedMessage));
        		
    }
}
