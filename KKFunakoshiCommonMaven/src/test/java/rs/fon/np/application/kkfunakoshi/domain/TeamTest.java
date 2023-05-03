package rs.fon.np.application.kkfunakoshi.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TeamTest {

	Team t;
	@BeforeEach
	void setUp() throws Exception {
		t= new Team();
	}

	@AfterEach
	void tearDown() throws Exception {
		t= null;
	}

	@Test
    void testSetIdNeodgVrednost() {
        Long id = -10L;
        Throwable exception = assertThrows(
                Exception.class, () -> {
                    t.setId(id);
                }
        );
        assertEquals("ID must be 0 or larger number", exception.getMessage());
    }

    @Test
    void testSetIdSveOk() throws Exception {
        Long id2 = 5L;
        t.setId(id2);
        assertEquals(id2, t.getId());
    }
    
    
    @Test
    void testSetNameNeodgVrednost() {
        String name = "Tr";
        Throwable exception = assertThrows(
                Exception.class, () -> {
                    t.setName(name);
                }
        );
        assertEquals("Team name must have at least 5 characters", exception.getMessage());
    }
    
    
    @Test
    void testSetNameSveOK() throws Exception {
        String name2 = "TrainingName";
        t.setName(name2);
        assertEquals(name2, t.getName());
    }
    
    @Test
    void testSetCategoryNullVrednost() {
    	assertThrows(NullPointerException.class
				, ()-> t.setName(null));
    }
    @Test
    void testSetCategorySveOk() {
    	t.setCategory(Category.JUNIOR);
    	assertEquals(Category.JUNIOR, t.getCategory());
    }

    @Test
    void testSetClanoviNull() {
    	assertThrows(NullPointerException.class
				, ()-> t.setMembers(null));
    }
    @Test
	void testToString() {
		t.setName("SENIORI-ZENSKI TIM");
		String str= t.toString();
		
		assertTrue(str.contains("SENIORI-ZENSKI TIM"));
	}
    
}
