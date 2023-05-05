package rs.fon.np.application.kkfunakoshi.so.trainer;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import rs.fon.np.application.kkfunakoshi.domain.City;
import rs.fon.np.application.kkfunakoshi.domain.Trainer;
import rs.fon.np.application.kkfunakoshi.repository.Repository;
import rs.fon.np.application.kkfunakoshi.repository.impl.RepositoryDBGeneric;

class RemoveTrainerSOTest {

	private Repository repository;
	private RemoveTrainerSO removeTrainerSO;
	@BeforeEach
	void setUp() throws Exception {
		removeTrainerSO= new RemoveTrainerSO();
		repository= new RepositoryDBGeneric();
	}

	@AfterEach
	void tearDown() throws Exception {
		removeTrainerSO= null;
	}

	@Test
	void testPreconditionNullParam() {
		 Exception exception = assertThrows(Exception.class, () -> 
	            removeTrainerSO.execute(null)
	        );
	        
	        String expectedMessage = "The object cant be null!";
	        String actualMessage = exception.getMessage();
	        assertTrue(actualMessage.contains(expectedMessage));
		
	}
	@Test
	void testPreconditionWithWrongTypeParam() {
		 Exception exception = assertThrows(Exception.class, () -> 
         removeTrainerSO.execute(new City())
     );
     
     String expectedMessage = "The object is the wrong type!";
     String actualMessage = exception.getMessage();
     assertTrue(actualMessage.contains(expectedMessage));
	}
	
	@Test
	void testPreconditionWithObjectWithoutCredentials() {
		Trainer t= new Trainer();
		NullPointerException exception = assertThrows(NullPointerException.class, () -> 
         removeTrainerSO.execute(t)
     );
     
     String expectedMessage = "Trainer ID is not sent!";
     String actualMessage = exception.getMessage();
     assertTrue(actualMessage.contains(expectedMessage));
	}
	
	@Test
	void testExecuteOperation() throws Exception {
		Trainer user = new Trainer(20L,"Jelena","Repac","jeca", "jelena2001",true);
    	removeTrainerSO.execute(user);
    	assertEquals(2, repository.getAll(new Trainer()).size());
	}
}
