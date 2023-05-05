package rs.fon.np.application.kkfunakoshi.so.trainer;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import rs.fon.np.application.kkfunakoshi.domain.City;
import rs.fon.np.application.kkfunakoshi.domain.Trainer;
import rs.fon.np.application.kkfunakoshi.repository.Repository;
import rs.fon.np.application.kkfunakoshi.repository.impl.RepositoryDBGeneric;
import rs.fon.np.application.kkfunakoshi.repository.impl.RepositoryDBTrainer;



public class LogOutSOTest {
    
	private LogOutSO logOutSO;
	private Repository repositoryUser;
	 @BeforeEach
	    void setUp() throws Exception {
		 	logOutSO = new LogOutSO();
		 	repositoryUser= new RepositoryDBGeneric();
	    }

	 @Test
    void testPreconditionWithNullParam() {
        assertThrows(Exception.class, () -> logOutSO.execute(null));
    }
	    
    @Test
    void testPreconditionWithWrongTypeParam() {
        assertThrows(Exception.class, () -> logOutSO.execute(new City()));
    }
    @Test
    void testPreconditionWithObjectWithoutCredentials() {
        assertThrows(Exception.class, () -> logOutSO.execute(new Trainer()));
    }
    @Test
    void testExecuteOperation() throws Exception {
    	Trainer user = new Trainer(20L,"Jelena","Repac","jeca", "jelena2001",true);
    	logOutSO.execute(user);
    	Trainer tr= (Trainer) repositoryUser.get(new Trainer(), " WHERE id="+user.getId());
    	assertEquals(false, tr.isLoggedIn());
    }
    
}