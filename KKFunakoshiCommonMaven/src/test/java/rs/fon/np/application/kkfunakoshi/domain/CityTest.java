package rs.fon.np.application.kkfunakoshi.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;


class CityTest {

	City c;
	@BeforeEach
	void setUp() throws Exception {
		c= new City();
	}

	@AfterEach
	void tearDown() throws Exception {
		c = null;
	}

	@Test
    void testCityConstructor() {
        Long id = 1L;
        Long ptt = 11000L;
        String name = "Belgrade";
        City city = new City(id, ptt, name);

        assertEquals(id, city.getId());
        assertEquals(ptt, city.getPtt());
        assertEquals(name, city.getName());
    }
	@Test
	void testSetIdNeodgVrednost() {
		assertThrows(Exception.class, 
				()-> c.setId(-1L));
	}
	@Test
	void testSetIdSveOk(){
		c.setId(20L);
		assertEquals(20L, c.getId());
	}

	@Test
    void testSetPttSveOk() {
        c.setPtt(21000L);
        assertEquals(21000L, c.getPtt());
    }
	
	@Test
	void testSetNameNeodgVrednost() {
		assertThrows(IllegalArgumentException.class, 
				()-> c.setName("N") );
	}
	@Test
    void testSetNameSveOk() {
        c.setName("Novi Sad");
        assertEquals("Novi Sad", c.getName());
    }
	
	@Test
	void testToString() {
		c.setId(1L);
		c.setName("Beograd");
		c.setPtt(11000L);
		String str= c.toString();
		
		assertTrue(str.contains("Beograd"));
	}
	
	@ParameterizedTest
	@CsvSource({
		"1, 11000,1, 11000, true",
		"1, 12000,1, 11000, false",
		"2, 11000,1, 11000, false",
		"2, 12000,1, 11000, false",
	})
	void testEquals(Long id1, Long ptt1, Long id2, Long ptt2, Boolean res) {
		c.setId(id1);
		c.setPtt(ptt1);
		
		City city= new City();
		city.setId(id2);
		city.setPtt(ptt2);
		
		assertEquals(res,city.equals(c));
	}
}
