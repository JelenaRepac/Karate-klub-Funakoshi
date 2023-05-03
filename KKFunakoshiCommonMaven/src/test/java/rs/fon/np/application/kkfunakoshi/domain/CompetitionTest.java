package rs.fon.np.application.kkfunakoshi.domain;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Calendar;
import java.util.Date;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 * 
 * @author Jelena Repac
 *
 */
class CompetitionTest {

	Competition c;
	City cityExample1;
	City cityExample2;
	@BeforeEach
	void setUp() throws Exception {
		c= new Competition();
		cityExample1= new City(1L, 11000L,"");
		cityExample2= new City(1L, 11000L,"Beograd");
	}

	@AfterEach
	void tearDown() throws Exception {
		c= null;
	}

	@Test
	void testSetIdNeodgVrednost() {
		assertThrows(IllegalArgumentException.class, 
				() -> c.setId(-1L));
	}
	@Test
	void testSetIdSveOK() {
		c.setId(10L);
		assertEquals(10L, c.getId());
	}
	
	@Test
	void testSetNazivNeodgVrednost() {
		String name= "Kup";
		Throwable exception= assertThrows(IllegalArgumentException.class, 
				()-> c.setName(name));
		assertEquals("Naziv takmicenja mora imati minimum 5 karaktera niti biti prazan string", exception.getMessage());
	}
	@Test
	void testSetNazivNull() {
		assertThrows(NullPointerException.class
				, ()-> c.setName(null));
	}
	@Test
	void testSetNazivSveOK() {
		c.setName("Kup Beograda");
		assertEquals("Kup Beograda", c.getName());
	}
	
	@Test
	void testSetDatumNeodgVrednost() {
        
		Calendar calendar = Calendar.getInstance();
		calendar.set(2022, 10, 10);
	    Date currentDate = calendar.getTime();
	    System.out.println(currentDate);
        Throwable exception = assertThrows(Exception.class, () ->
            c.setDate(currentDate));
        
        assertEquals("Datum ne sme biti pre danasnjeg!", exception.getMessage());
        
	}
	@Test 
	void testSetDatumSveOK() {
		 Calendar calendar = Calendar.getInstance();
	        
        calendar.add(Calendar.DAY_OF_MONTH, 7);
        Date futureDate = calendar.getTime();
		c.setDate(futureDate);
		assertEquals(futureDate, c.getDate());
	}
	@Test
	void testSetNazivHaleNeodgVrednost() {
		String name= "Hala";
		Throwable exception= assertThrows(IllegalArgumentException.class, 
				()-> c.setCompetitionHall(name));
		assertEquals("Naziv hale mora imati minimum 5 karaktera", exception.getMessage());
	}
	@Test
	void testSetNazivHaleNull() {
		assertThrows(NullPointerException.class
				, ()-> c.setCompetitionHall(null));
	}
	@Test
	void testSetNazivHaleSveOK() {
		c.setCompetitionHall("Ranko Zeravica");
		assertEquals("Ranko Zeravica", c.getCompetitionHall());
	}
	@Test
	void testSetGradNeodgVrednost() {
		assertThrows(IllegalArgumentException.class, 
				()-> c.setCity(cityExample1));
	}

	@Test
	void testSetGradSveOK() {
		c.setCity(cityExample2);
		assertEquals(cityExample2, c.getCity());
	}
	@Test
	void testToString() {
		Calendar calendar = Calendar.getInstance();
        
        calendar.add(Calendar.DAY_OF_MONTH, 7);
        Date futureDate = calendar.getTime();
		c.setCity(new City(2L, 11000L, "Beograd"));
		c.setCompetitionHall("Ranko Zeravica");
		c.setDate(futureDate);
		c.setId(1L);
		c.setName("Kup Beograda");
		String str= c.toString();
		System.out.println(str);
		assertTrue(str.contains("Ranko Zeravica"));
		assertTrue(str.contains(futureDate.toString()));
		assertTrue(str.contains("1"));
		assertTrue(str.contains("Beograd"));
		assertTrue(str.contains("Kup Beograda"));
		
	}
	@ParameterizedTest
	@CsvSource({
		"1, Kup Beograda,1, Kup Beograda, true",
		"1, Kup Srbije,1, Kup Beograda, false",
		"2, Kup Beograda,1, Kup Beograda, false",
		"2, Kup Srbije,1, Kup Beograda, false",
	})
	void testEquals(Long id1, String name1, Long id2, String name2, Boolean res) {
		c.setId(id1);
		c.setName(name1);
		Competition competition= new Competition();
		competition.setId(id2);
		competition.setName(name2);
		
		assertEquals(res,competition.equals(c));
	}
	

}
