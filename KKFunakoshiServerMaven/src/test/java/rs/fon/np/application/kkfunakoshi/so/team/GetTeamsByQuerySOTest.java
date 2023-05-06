package rs.fon.np.application.kkfunakoshi.so.team;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import rs.fon.np.application.kkfunakoshi.domain.Belt;
import rs.fon.np.application.kkfunakoshi.domain.Category;
import rs.fon.np.application.kkfunakoshi.domain.City;
import rs.fon.np.application.kkfunakoshi.domain.Discipline;
import rs.fon.np.application.kkfunakoshi.domain.Gender;
import rs.fon.np.application.kkfunakoshi.domain.Member;
import rs.fon.np.application.kkfunakoshi.domain.Team;
import rs.fon.np.application.kkfunakoshi.repository.Repository;
import rs.fon.np.application.kkfunakoshi.repository.impl.RepositoryDBGeneric;

class GetTeamsByQuerySOTest {

	private Repository repository;
	private GetTeamsByQuerySO getTeamsByQuerySO;
	
	@BeforeEach
	void setUp() throws Exception {
		repository= new RepositoryDBGeneric();
		getTeamsByQuerySO= new GetTeamsByQuerySO();
	}


	@Test
    public void testPreconditionNullParam() {
        Exception exception = assertThrows(Exception.class, 
        		() -> getTeamsByQuerySO.execute(null));
        assertEquals("Object is the wrong type!", exception.getMessage());
    }

    @Test
    public void testPreconditionWrongParamType() {
        Exception exception = assertThrows(Exception.class, 
        		() -> getTeamsByQuerySO.execute(new City()));
        assertEquals("Object is the wrong type!", exception.getMessage());
    }
    
    @Test
    void testExecuteOpeartion() throws Exception {
    	Member member1 =  
    			new Member(4L,"Jadranka", "Repac", Gender.FEMALE,  new java.sql.Date(100, 11, 2),
    					"Jadranka", "Nikola", new City(1L,11000L,"Beograd"), 
    					Belt.CRNI, 50L, Discipline.KATE, Category.SENIOR,
    					"Niksicka 10", 10L, 10L, 30L, new ArrayList<>(),
    					new java.sql.Date(123, 4, 2), 3000.00);
    	Member member2 =  
    			new Member(5L,"Jelena", "Repac", Gender.FEMALE, new java.sql.Date(101, 11, 2),
    					"Jadranka", "Nikola", new City(1L,11000L,"Beograd"), 
    					Belt.CRNI, 50L, Discipline.KATE, Category.SENIOR,
    					"Niksicka 10", 10L, 10L, 30L, new ArrayList<>(),
    					new java.sql.Date(123, 4, 2), 3000.00);
    	Member member3 =  
    			new Member(6L,"Danijela", "Pudaric", Gender.FEMALE, new java.sql.Date(101, 11, 2),
    					"Jadranka", "Nikola", new City(1L,11000L,"Beograd"), 
    					Belt.CRNI, 50L, Discipline.KATE, Category.SENIOR,
    					"Niksicka 10", 10L, 10L, 30L, new ArrayList<>(),
    					new java.sql.Date(123, 4, 2), 3000.00);
    	ArrayList<Member> members= new ArrayList<Member>();
    	members.add(member1);
    	members.add(member2);
    	members.add(member3);
    	
    	repository.add(member1);
    	repository.add(member2);
    	repository.add(member3);
    	ArrayList<Team> expectedTeams= new ArrayList<Team>();
		Team team= new Team(2L,"SENIORKE", members, Category.SENIOR);
		expectedTeams.add(team);

    	repository.addComplexObject(team);
		String str= " WHERE t.category='SENIOR'";
    	getTeamsByQuerySO.execute(str);
    	
    	List<Team> actualTeams= getTeamsByQuerySO.getTeams();
		assertTrue(!actualTeams.isEmpty());
		assertEquals(expectedTeams.size(),actualTeams.size());
		
    }

}
