package rs.fon.np.application.kkfunakoshi.so.result;

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
import rs.fon.np.application.kkfunakoshi.domain.Medal;
import rs.fon.np.application.kkfunakoshi.domain.Member;
import rs.fon.np.application.kkfunakoshi.domain.Result;
import rs.fon.np.application.kkfunakoshi.domain.Team;
import rs.fon.np.application.kkfunakoshi.repository.Repository;
import rs.fon.np.application.kkfunakoshi.repository.impl.RepositoryDBGeneric;
import rs.fon.np.application.kkfunakoshi.so.competition.GetCompetitionsByQuerySO;

class GetByQueryResultSOTest {

	private Repository<AbstractDO> repository;
    private GetByQueryResultSO getByQueryResultSO;

    @BeforeEach
    public void setUp() throws Exception {
        repository = new RepositoryDBGeneric();
        getByQueryResultSO = new GetByQueryResultSO();
    }

    @Test
    public void testExecuteOperation() throws Exception {
    	Calendar calendar = Calendar.getInstance();
        calendar.set(2000, 3,2);
        Calendar dateOfMembership = Calendar.getInstance();
        dateOfMembership.set(2022, 11,2);
        Calendar competitionDate = Calendar.getInstance();
        competitionDate.set(2023, 05,05);
        Result result= new Result(new Competition(219L,"Zlatno zvono",new Date(), "Ranko Zeravica", new City(1L, 11000L, "Beograd")),
        		new Member(92L,"Marija", "Repac", Gender.FEMALE, calendar.getTime(),
    					"Jadranka", "Nikola", new City(1L,11000L,"Beograd"), 
    					Belt.CRNI, 50L, Discipline.KATE, Category.SENIOR,
    					"Niksicka 10", 10L, 10L, 30L, new ArrayList<>(),
    					dateOfMembership.getTime(), 3000.00), new Team(),Medal.BRONZE,Category.SENIOR);
        Result result1= new Result(new Competition(220L,"Kup Beograda", new Date(), "Ranko Zeravica", new City(1L, 11000L, "Beograd")),
        		new Member(92L,"Marija", "Repac", Gender.FEMALE, calendar.getTime(),
    					"Jadranka", "Nikola", new City(1L,11000L,"Beograd"), 
    					Belt.CRNI, 50L, Discipline.KATE, Category.SENIOR,
    					"Niksicka 10", 10L, 10L, 30L, new ArrayList<>(),
    					dateOfMembership.getTime(), 3000.00), new Team(),Medal.BRONZE,Category.SENIOR);
        
    	
        

        List<Result> expectedResults = new ArrayList<Result>();
        expectedResults.add(result);
        expectedResults.add(result1);
        String str= " WHERE category = 'SENIOR'";
        
        getByQueryResultSO.execute(str);
        repository.add(result);
        repository.add(result1);

        List<Result> actualResults = getByQueryResultSO.getResults();
        
        assertEquals(expectedResults, actualResults);
    }

    @Test
    public void testPreconditionNullParam() {
        Exception exception = assertThrows(Exception.class, () -> getByQueryResultSO.execute(null));
        assertEquals("Object is the wrong type!", exception.getMessage());
    }

    @Test
    public void testPreconditionWrongParamType() {
        Exception exception = assertThrows(Exception.class, () -> getByQueryResultSO.execute(new City()));
        assertEquals("Object is the wrong type!", exception.getMessage());
    }
}
