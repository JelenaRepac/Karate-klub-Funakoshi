package rs.fon.np.application.kkfunakoshi.so.competition;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import rs.fon.np.application.kkfunakoshi.domain.City;
import rs.fon.np.application.kkfunakoshi.domain.Competition;

public class GetAllCompetitionSOTest {

	private GetAllCompetitionSO getAllCompetitionSO;
	@BeforeEach
	public void setUp() throws Exception {
		getAllCompetitionSO= new GetAllCompetitionSO();
	}

	@AfterEach
	public void tearDown() throws Exception {
		getAllCompetitionSO= null;
	}

	@Test
	public void testExecuteOperation() throws Exception {
		getAllCompetitionSO.executeOperation(null);
		assertNotNull(getAllCompetitionSO.getCompetitions());
		assertTrue(getAllCompetitionSO.getCompetitions().size()>0);
		for(Competition c: getAllCompetitionSO.getCompetitions()) {
			System.out.println(c.toString());
			assertNotNull(c.getId());
			assertNotNull(c.getCity());
			assertNotNull(c.getDate());
			assertNotNull(c.getName());
			assertNotNull(c.getCompetitionHall());
			
			
		}
	}
	
	  

}
