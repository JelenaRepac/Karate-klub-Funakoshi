package rs.fon.np.application.kkfunakoshi.so.competition;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Date;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import rs.fon.np.application.kkfunakoshi.domain.City;
import rs.fon.np.application.kkfunakoshi.domain.Competition;
import rs.fon.np.application.kkfunakoshi.repository.Repository;
import rs.fon.np.application.kkfunakoshi.repository.impl.RepositoryDBGeneric;

public class AddCompetitionSOTest {

	  	private Repository repository;
	    private AddCompetitionSO addCompetitionSO;

	    @BeforeEach
	    public void setUp() {
	        repository = new RepositoryDBGeneric();
	        addCompetitionSO = new AddCompetitionSO();
	    }

	    @Test
	    public void testAddCompetitionSuccess() throws Exception {
	        Competition competition =new Competition(10L,"Zlatno zvono", new Date(), "Ranko Zeravica", new City(1L, 11000L, "Beograd"));
	        addCompetitionSO.execute(competition);
	        List<Competition> competitions= repository.getAll(new Competition());
	       
	        assertEquals(2, repository.getAll(new Competition()).size());
	    }

	    @Test
	    public void testAddCompetitionAlreadyExists() throws Exception {
	        Competition competition = new Competition(10L,"Kup Beograda", new Date(), "Ranko Zeravica", new City(1L, 11000L, "Beograd"));
	        addCompetitionSO.execute(competition);
	        assertThrows(Exception.class, 
	        		() -> addCompetitionSO.execute(competition));
	    }

	    @Test
	    public void testAddCompetitionNullInput() {
	       assertThrows(Exception.class, 
	    		   () -> addCompetitionSO.execute(null));
	    }
	    @Test
	    public void testAddCompetitionWrongType() {
	        assertThrows(Exception.class, 
	        		() -> addCompetitionSO.execute(new City()));
	    }

}
