package rs.fon.np.application.kkfunakoshi.so.team;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
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
import rs.fon.np.application.kkfunakoshi.domain.Member;
import rs.fon.np.application.kkfunakoshi.domain.Team;
import rs.fon.np.application.kkfunakoshi.repository.Repository;
import rs.fon.np.application.kkfunakoshi.repository.impl.RepositoryDBGeneric;

class AddTeamSOTest {

	private Repository repository;
	private AddTeamSO addTeamSO;
	
	@BeforeEach
	void setUp() throws Exception {
		repository= new RepositoryDBGeneric();
		addTeamSO= new AddTeamSO();
	}

	@Test
    public void testAddTeamSuccess() throws Exception {
		Member member1 =  
    			new Member(1L,"Milica", "Markovic", Gender.FEMALE,  new java.sql.Date(100, 11, 2),
    					"Jadranka", "Nikola", new City(1L,11000L,"Beograd"), 
    					Belt.CRNI, 50L, Discipline.KATE, Category.SENIOR,
    					"Niksicka 10", 10L, 10L, 30L, new ArrayList<>(),
    					new java.sql.Date(123, 4, 2), 3000.00);
    	Member member2 =  
    			new Member(2L,"Tamara", "Pekec", Gender.FEMALE, new java.sql.Date(101, 11, 2),
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
    	
    	ArrayList<Member> meambers= new ArrayList<Member>();
    	meambers.add(member3);
    	meambers.add(member2);
    	meambers.add(member1);
		Team team= new Team(1L,"POLETARKE", meambers, Category.POLETARAC);
		addTeamSO.execute(team);
        List<Team> teams= repository.getByQuery(new Team(),"");
       
        assertEquals(3, repository.getByQuery(new Team(),"").size());
    }
	 @Test
	    public void testAddCompetitionAlreadyExists() throws Exception {
		 Member member1 =  
	    			new Member(1L,"Milica", "Markovic", Gender.FEMALE,  new java.sql.Date(100, 11, 2),
	    					"Jadranka", "Nikola", new City(1L,11000L,"Beograd"), 
	    					Belt.CRNI, 50L, Discipline.KATE, Category.SENIOR,
	    					"Niksicka 10", 10L, 10L, 30L, new ArrayList<>(),
	    					new java.sql.Date(123, 4, 2), 3000.00);
	    	Member member2 =  
	    			new Member(2L,"Tamara", "Pekec", Gender.FEMALE, new java.sql.Date(101, 11, 2),
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
	    	
	    	ArrayList<Member> meambers= new ArrayList<Member>();
	    	meambers.add(member3);
	    	meambers.add(member2);
	    	meambers.add(member1);
			Team team= new Team(1L,"POLETARKE", meambers, Category.POLETARAC);
		 
	        assertThrows(Exception.class, 
	        		() -> addTeamSO.execute(team));
	    }

	    @Test
	    public void testAddTeamNullInput() {
	       assertThrows(Exception.class, 
	    		   () -> addTeamSO.execute(null));
	    }
	    @Test
	    public void testAddTeamWrongType() {
	        assertThrows(Exception.class, 
	        		() -> addTeamSO.execute(new City()));
	    }
}
