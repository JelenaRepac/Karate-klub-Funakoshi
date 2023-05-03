package rs.fon.np.application.kkfunakoshi.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CurrentUserTest {

	CurrentUser cr;
	@BeforeEach
	void setUp() throws Exception {
		cr= new CurrentUser();
	}

	@AfterEach
	void tearDown() throws Exception {
		cr = null;
	}


    @Test
    public void testSetCurrentUser() {
        Trainer trainer = new Trainer(1L,"Jelena", "Repac","jelena","jelena2001",false);
        CurrentUser.setCurrentUser(trainer);
        assertEquals(trainer, CurrentUser.getCurrentUser());
    }

}
