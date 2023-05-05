package rs.fon.np.application.kkfunakoshi.so.member;

import static org.junit.Assert.assertEquals;
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
import rs.fon.np.application.kkfunakoshi.domain.Member;
import rs.fon.np.application.kkfunakoshi.repository.Repository;
import rs.fon.np.application.kkfunakoshi.repository.impl.RepositoryDBGeneric;
import rs.fon.np.application.kkfunakoshi.so.competition.AddCompetitionSO;

class AddMemberSOTest {

	private Repository repository;
    private AddMemberSO addMemberSO;

    @BeforeEach
    public void setUp() {
        repository = new RepositoryDBGeneric();
        addMemberSO = new AddMemberSO();
    }

    @Test
    public void testAddMemberSuccess() throws Exception {
    	Calendar calendar = Calendar.getInstance();
        calendar.set(2000, 3,2);
        Calendar dateOfMembership = Calendar.getInstance();
        dateOfMembership.set(2022, 11,2);
    	Member member =  
    			new Member("Marija", "Repac", Gender.FEMALE, calendar.getTime(),
    					"Jadranka", "Nikola", new City(1L,11000L,"Beograd"), 
    					Belt.CRNI, 50L, Discipline.KATE, Category.SENIOR,
    					"Niksicka 10", 10L, 10L, 30L, new ArrayList<>(),
    					dateOfMembership.getTime(), 3000.00);
    	System.out.println(member.toString());
    	addMemberSO.execute(member);
        List<Member> members= repository.getAll(new Member());
        System.out.println(members.toString());
        assertEquals(11, repository.getAll(new Member()).size());
    }

    @Test
    public void testAddMemberAlreadyExists() throws Exception {
    	Calendar calendar = Calendar.getInstance();
        calendar.set(2000, 3,2);
        Calendar dateOfMembership = Calendar.getInstance();
        dateOfMembership.set(2022, 11,2);
    	Member member =  
    			new Member("Jelena", "Repac", Gender.FEMALE, calendar.getTime(),
    					"Jadranka", "Nikola", new City(1L,11000L,"Beograd"), 
    					Belt.CRNI, 50L, Discipline.KATE, Category.SENIOR,
    					"Niksicka 10", 10L, 10L, 30L, new ArrayList<>(),
    					dateOfMembership.getTime(), 3000.00);
    	addMemberSO.execute(member);
        assertThrows(Exception.class, 
        		() -> addMemberSO.execute(member));
    }

    @Test
    public void testAddMemberNullInput() {
       assertThrows(Exception.class, 
    		   () -> addMemberSO.execute(null));
    }
    @Test
    public void testAddMemberWrongType() {
        assertThrows(Exception.class, 
        		() -> addMemberSO.execute(new City()));
    }

}
