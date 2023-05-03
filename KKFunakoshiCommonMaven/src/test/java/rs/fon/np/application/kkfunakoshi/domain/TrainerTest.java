package rs.fon.np.application.kkfunakoshi.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class TrainerTest {

	Trainer t;
	@BeforeEach
	void setUp() throws Exception {
		t=new Trainer();
	}

	@AfterEach
	void tearDown() throws Exception {
		t=null;
	}

	@Test
    public void testSetIdNeodgVrednost() {
        Long id = -10L;
        Throwable exception = assertThrows(
                Exception.class, () -> {
                    t.setId(id);
                }
        );
        assertEquals("ID must be 0 or larger number", exception.getMessage());
    }

    @Test
    public void testSetIdSveOk() throws Exception {
        Long id2 = 5L;
        t.setId(id2);
        assertEquals(id2, t.getId());
    }
    
    
    @Test
    public void testSetNameNeodgVrednost() {
        String name = "Tr";
        Throwable exception = assertThrows(
                Exception.class, () -> {
                    t.setFirstname(name);
                }
        );
        assertEquals("Trainer name must have at least 5 characters", exception.getMessage());
    }
    
    
    @Test
    public void testSetNameSveOK() throws Exception {
        String name2 = "TrainingName";
        t.setFirstname(name2);
        assertEquals(name2, t.getFirstname());
    }
    
    @Test
    public void testSetLastNameNeodgVrednost() {
        String lastname = "Tr";
        Throwable exception = assertThrows(
                Exception.class, () -> {
                    t.setLastname(lastname);
                }
        );
        assertEquals("Trainer lastname must have at least 5 characters", exception.getMessage());
    }
    
    
    @Test
    public void testSetLastNameSveOK() throws Exception {
        String lastname2 = "TrainingLastname";
        t.setLastname(lastname2);
        assertEquals(lastname2, t.getLastname());
    }
    @Test
    public void testSetUsernameNeodgVrednost() {
        String username = "is";
        Throwable exception = assertThrows(
                Exception.class, () -> {
                    t.setUsername(username);
                }
        );
        assertEquals("Username must have at least 4 characters", exception.getMessage());
    }

    @Test
    public void testSetUsernameSveOk() throws Exception {
        String username2 = "jelenarepac";
        t.setUsername(username2);
        assertEquals(username2, t.getUsername());
    }
    
    @Test
    public void testSetPasswordNeodgVrednost() {
        String password = "sifra";
        Throwable exception = assertThrows(
                Exception.class, () -> {
                    t.setPassword(password);
                }
        );
        assertEquals("Password must have at least 8 characters", exception.getMessage());
    }

    @Test
    public void testSetPasswordSveOK() throws Exception {
        String password2 = "jelena2001";
        t.setPassword(password2);
        assertEquals(password2, t.getPassword());
    }
    
    @Test
	void testToString() {
		t.setFirstname("Jelena");
		t.setLastname("Repac");
		String str= t.toString();
		
		assertTrue(str.contains("Jelena"));
		assertTrue(str.contains("Repac"));
	}
	
	@ParameterizedTest
	@CsvSource({
		"1, jelenarepac,1, jelenarepac, true",
		"1, danijelapudaric,1, jelenarepac, false",
		"2, jelenarepac,1, jelenarepac, false",
		"2, danijelapudaric, 1, jelenarepac, false",
	})
	void testEquals(Long id1, String user1, Long id2, String user2, Boolean res) {
		t.setId(id1);
		t.setUsername(user1);
		
		Trainer trainer= new Trainer();
		trainer.setUsername(user2);
		trainer.setId(id2);
		assertEquals(res,trainer.equals(t));
	}
}
