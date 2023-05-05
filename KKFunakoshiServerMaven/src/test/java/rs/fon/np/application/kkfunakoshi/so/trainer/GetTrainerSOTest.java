package rs.fon.np.application.kkfunakoshi.so.trainer;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import rs.fon.np.application.kkfunakoshi.domain.City;
import rs.fon.np.application.kkfunakoshi.domain.Competition;
import rs.fon.np.application.kkfunakoshi.domain.Trainer;

public class GetTrainerSOTest {

	private GetTrainerSO getTrainerSO;
	
	@BeforeEach
	public void setUp()  {
		getTrainerSO= new GetTrainerSO();
	}

	@AfterEach
	public void tearDown(){
		getTrainerSO = null;
	}

	@Test
	public void testExecuteOperation() throws Exception {
		getTrainerSO.executeOperation(null);
		assertNotNull(getTrainerSO.getTrainers());
		assertTrue(getTrainerSO.getTrainers().size()>0);
		for(Trainer trainer: getTrainerSO.getTrainers()) {
			System.out.println(trainer.toString());
			assertNotNull(trainer.getId());
			assertNotNull(trainer.getUsername());
			assertNotNull(trainer.getPassword());
			assertNotNull(trainer.getFirstname());
			assertNotNull(trainer.getLastname());
		}
	}
	
	 
}
