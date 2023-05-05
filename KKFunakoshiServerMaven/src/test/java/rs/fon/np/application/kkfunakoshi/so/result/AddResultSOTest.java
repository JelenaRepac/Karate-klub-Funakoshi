package rs.fon.np.application.kkfunakoshi.so.result;

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
import rs.fon.np.application.kkfunakoshi.domain.Medal;
import rs.fon.np.application.kkfunakoshi.domain.Member;
import rs.fon.np.application.kkfunakoshi.domain.Result;
import rs.fon.np.application.kkfunakoshi.domain.Team;
import rs.fon.np.application.kkfunakoshi.repository.Repository;
import rs.fon.np.application.kkfunakoshi.repository.impl.RepositoryDBGeneric;
import rs.fon.np.application.kkfunakoshi.so.competition.AddCompetitionSO;

class AddResultSOTest {

	private Repository repository;
    private AddResultSO addResultSO;

    @BeforeEach
    public void setUp() {
        repository = new RepositoryDBGeneric();
        addResultSO = new AddResultSO();
    }

    @Test
    public void testAddResultSuccess() throws Exception {
    	Calendar calendar = Calendar.getInstance();
        calendar.set(2000, 3,2);
        Calendar dateOfMembership = Calendar.getInstance();
        dateOfMembership.set(2022, 11,2);
        Result result= new Result(new Competition(204L,"Zlatno zvono", new Date(), "Ranko Zeravica", new City(1L, 11000L, "Beograd")),
        		new Member(88L,"Marija", "Repac", Gender.FEMALE, calendar.getTime(),
    					"Jadranka", "Nikola", new City(1L,11000L,"Beograd"), 
    					Belt.CRNI, 50L, Discipline.KATE, Category.SENIOR,
    					"Niksicka 10", 10L, 10L, 30L, new ArrayList<>(),
    					dateOfMembership.getTime(), 3000.00), new Team(),Medal.BRONZE,Category.SENIOR);
        
    	addResultSO.execute(result);
        List<Result> results= repository.getAll(new Result());
       
        assertEquals(1, repository.getAll(new Result()).size());
    }

    @Test
    public void testAddResultAlreadyExists() throws Exception {
    	Calendar calendar = Calendar.getInstance();
        calendar.set(2000, 3,2);
        Calendar dateOfMembership = Calendar.getInstance();
        dateOfMembership.set(2022, 11,2);
    	 Result result= new Result(new Competition(204L,"Zlatno zvono", new Date(), "Ranko Zeravica", new City(1L, 11000L, "Beograd")),
         		new Member(88L,"Marija", "Repac", Gender.FEMALE, calendar.getTime(),
     					"Jadranka", "Nikola", new City(1L,11000L,"Beograd"), 
     					Belt.CRNI, 50L, Discipline.KATE, Category.SENIOR,
     					"Niksicka 10", 10L, 10L, 30L, new ArrayList<>(),
     					dateOfMembership.getTime(), 3000.00), new Team(),Medal.BRONZE,Category.SENIOR);
        assertThrows(Exception.class, 
        		() -> addResultSO.execute(result));
    }

    @Test
    public void testAddResultNullInput() {
       assertThrows(Exception.class, 
    		   () -> addResultSO.execute(null));
    }
    @Test
    public void testAddResultWrongType() {
        assertThrows(Exception.class, 
        		() -> addResultSO.execute(new City()));
    }

}
