package rs.fon.np.application.kkfunakoshi.so.result;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
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
import rs.fon.np.application.kkfunakoshi.domain.Medal;
import rs.fon.np.application.kkfunakoshi.domain.Member;
import rs.fon.np.application.kkfunakoshi.domain.Result;
import rs.fon.np.application.kkfunakoshi.domain.Team;
import rs.fon.np.application.kkfunakoshi.repository.Repository;
import rs.fon.np.application.kkfunakoshi.repository.impl.RepositoryDBGeneric;

class UpdateResultSOTest {
     private Repository<AbstractDO> repository;
	 private UpdateResultSO updateResultSO;

    @BeforeEach
    public void setUp() {
    	repository= new RepositoryDBGeneric();
        updateResultSO = new UpdateResultSO();
    }

    @Test
    public void testPreconditionWithNullObject() {
        List<Result> results = null;

        Exception exception = assertThrows(Exception.class, () -> {
            updateResultSO.precondition(results);
        });

        assertEquals("The object was not sent!", exception.getMessage());
    }
    @Test
    public void testPreconditionWithEmptyResults() {
        List<Result> results = new ArrayList();

        Exception exception = assertThrows(Exception.class, () -> {
            updateResultSO.precondition(results);
        });

        assertEquals("No required parameters sent!", exception.getMessage());
    }
    @Test
    public void testPreconditionWithValidParameters() throws Exception {
        List<Result> results = new ArrayList<>();
        results.add(createResult());
        Result result=new Result(new Competition(3L, "Zlatno zvono", new Date(), "Ranko Zeravica", new City(1L, 11000L, "Beograd")),
        		new Member(1L,"Milica", "Markovic", Gender.FEMALE,  new java.sql.Date(100, 11, 2),
    					"Jadranka", "Nikola", new City(1L,11000L,"Beograd"), 
    					Belt.CRNI, 50L, Discipline.KATE, Category.SENIOR,
    					"Niksicka 10", 10L, 10L, 30L, new ArrayList<>(),
    					new java.sql.Date(123, 4, 2), 3000.00), new Team(), Medal.GOLD, Category.SENIOR);
        results.add(result);
        
        updateResultSO.execute(results);
        assertEquals(1, repository.getAll(new Result()).get(0).getId());
       
    }
    private Result createResult() throws Exception {
    	Competition competition = new Competition(3L, "Zlatno zvono", new Date(), "Ranko Zeravica", new City(1L, 11000L, "Beograd"));
    	Member member =  
    			new Member(1L,"Milica", "Markovic", Gender.FEMALE,  new java.sql.Date(100, 11, 2),
    					"Jadranka", "Nikola", new City(1L,11000L,"Beograd"), 
    					Belt.CRNI, 50L, Discipline.KATE, Category.SENIOR,
    					"Niksicka 10", 10L, 10L, 30L, new ArrayList<>(),
    					new java.sql.Date(123, 4, 2), 3000.00);

        Result result = new Result();
        result.setCompetition(competition);
        result.setMember(member);
        result.setTeam(new Team());
        result.setMedals(Medal.BRONZE);
        result.setCategory(Category.SENIOR);
        System.out.println(result.toString());
        repository.add(result);

        return result;
    }


}
