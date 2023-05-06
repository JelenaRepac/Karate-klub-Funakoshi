package rs.fon.np.application.kkfunakoshi.so.member;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import rs.fon.np.application.kkfunakoshi.domain.AbstractDO;
import rs.fon.np.application.kkfunakoshi.domain.Belt;
import rs.fon.np.application.kkfunakoshi.domain.Category;
import rs.fon.np.application.kkfunakoshi.domain.City;
import rs.fon.np.application.kkfunakoshi.domain.Competition;
import rs.fon.np.application.kkfunakoshi.domain.Discipline;
import rs.fon.np.application.kkfunakoshi.domain.Gender;
import rs.fon.np.application.kkfunakoshi.domain.Member;
import rs.fon.np.application.kkfunakoshi.repository.Repository;
import rs.fon.np.application.kkfunakoshi.repository.impl.RepositoryDBGeneric;

class GetMembersByQuerySOTest {

	private Repository<AbstractDO> repository;
	private GetMembersByQuerySO getMembersByQuerySO;
	
	@BeforeEach
	void setUp() throws Exception {
		repository= new RepositoryDBGeneric();
		getMembersByQuerySO= new GetMembersByQuerySO();
	}


	 @Test
    public void testPreconditionNullParam() {
        Exception exception = assertThrows(Exception.class, () -> getMembersByQuerySO.execute(null));
        assertEquals("Object is the wrong type!", exception.getMessage());
    }

    @Test
    public void testPreconditionWrongParamType() {
        Exception exception = assertThrows(Exception.class, () -> getMembersByQuerySO.execute(new City()));
        assertEquals("Object is the wrong type!", exception.getMessage());
    }
	@Test
	void testExecuteOperation() throws Exception {
    	Member member1 =  
    			new Member("Milica", "Markovic", Gender.FEMALE,  new java.sql.Date(100, 11, 2),
    					"Jadranka", "Nikola", new City(1L,11000L,"Beograd"), 
    					Belt.CRNI, 50L, Discipline.KATE, Category.SENIOR,
    					"Niksicka 10", 10L, 10L, 30L, new ArrayList<>(),
    					new java.sql.Date(123, 4, 2), 3000.00);
    	Member member2 =  
    			new Member("Tamara", "Pekec", Gender.FEMALE, new java.sql.Date(101, 11, 2),
    					"Jadranka", "Nikola", new City(1L,11000L,"Beograd"), 
    					Belt.CRNI, 50L, Discipline.KATE, Category.SENIOR,
    					"Niksicka 10", 10L, 10L, 30L, new ArrayList<>(),
    					new java.sql.Date(123, 4, 2), 3000.00);
    	Member member3 =  
    			new Member(3L,"Stasa", "Orozovic", Gender.FEMALE, new java.sql.Date(101, 11, 2),
    					"Jadranka", "Nikola", new City(1L,11000L,"Beograd"), 
    					Belt.CRNI, 50L, Discipline.KATE, Category.SENIOR,
    					"Niksicka 10", 10L, 10L, 30L, new ArrayList<>(),
    					new java.sql.Date(123, 4, 2), 3000.00);
    	repository.add(member1);
    	repository.add(member2);
    	repository.add(member3);
    	List<Member> expectedMembers= new ArrayList<Member>();
    	expectedMembers.add(member1);
    	expectedMembers.add(member2);
    	expectedMembers.add(member3);
    	System.out.println(expectedMembers.toString());
    	String str= " WHERE gender='FEMALE'";
    	getMembersByQuerySO.execute(str);
    	
    	List<Member> actualMembers= getMembersByQuerySO.getMembers();
    	System.out.println(actualMembers.toString());
		assertTrue(!actualMembers.isEmpty());
		assertEquals(expectedMembers.size(),actualMembers.size());
    	
	}

}
