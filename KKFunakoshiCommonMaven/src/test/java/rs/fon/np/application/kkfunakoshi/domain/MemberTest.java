package rs.fon.np.application.kkfunakoshi.domain;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class MemberTest {

	Member m;
	City cityExample1;
	City cityExample2;
	
	@BeforeEach
	void setUp() throws Exception {
		m= new Member();
		cityExample1= new City(1L, 11000L,"");
		cityExample2= new City(1L, 11000L,"Beograd");
	}

	@AfterEach
	void tearDown() throws Exception {
		m= null;
	}

	@Test
	void testSetIdNeodgVrednost() {
		assertThrows(IllegalArgumentException.class, 
				() -> m.setMemberID(-1L));
	}
	@Test
	void testSetIdSveOK() {
		m.setMemberID(10L);
		assertEquals(10L, m.getMemberID());
	}
	@Test
	void testSetImeNeodgVrednost() {
		String name= "Je";
		Throwable exception= assertThrows(IllegalArgumentException.class, 
				()-> m.setName(name));
		assertEquals("Ime clana mora imati minimum 3 karaktera niti biti prazan string", exception.getMessage());
	}
	@Test
	void testSetImeNull() {
		assertThrows(NullPointerException.class
				, ()-> m.setName(null));
	}
	@Test
	void testSetImeSveOK() {
		m.setName("Jelena");
		assertEquals("Jelena", m.getName());
	}
	@Test
	void testSetPrezimeNeodgVrednost() {
		String lastname= "Re";
		Throwable exception= assertThrows(IllegalArgumentException.class, 
				()-> m.setLastname(lastname));
		assertEquals("Prezime clana mora imati minimum 3 karaktera niti biti prazan string", exception.getMessage());
	}
	@Test
	void testSetPrezimeNull() {
		assertThrows(NullPointerException.class
				, ()-> m.setLastname(null));
	}
	@Test
	void testSetPrezimeSveOK() {
		m.setLastname("Repac");
		assertEquals("Repac", m.getLastname());
	}
	
	@Test
	void testSetDatumRodjenjaNull() {
		assertThrows(NullPointerException.class, 
				()-> m.setBirthday(null));
	}
	@Test
	void testSetDatumRodjenjaNeodgVrednost() {
        
		Calendar calendar = Calendar.getInstance();
		calendar.set(2023, 10, 10);
        Throwable exception = assertThrows(Exception.class, () ->
           m.setBirthday(calendar.getTime()));
        
        assertEquals("Datum rodjenja ne sme biti nakon danasnjeg!", exception.getMessage());
        
	}
	@Test 
	void testSetDatumRodjenjaSveOK() {
		Calendar calendar = Calendar.getInstance();
	        
       calendar.set(2000, 3,2);
		m.setBirthday(calendar.getTime());
		assertEquals(calendar.getTime(), m.getBirthday());
	}
	
	@Test
	void testSetImeMajkeNeodgVrednost() {
		String mothersName= "Je";
		Throwable exception= assertThrows(IllegalArgumentException.class, 
				()-> m.setMothersName(mothersName));
		assertEquals("Ime majke clana mora imati minimum 3 karaktera niti biti prazan string", exception.getMessage());
	}
	@Test
	void testSetImeMajkeNull() {
		assertThrows(NullPointerException.class
				, ()-> m.setMothersName(null));
	}
	@Test
	void testSetImeMajkeSveOK() {
		m.setMothersName("Jadranka");
		assertEquals("Jadranka", m.getMothersName());
	}
	
	@Test
	void testSetImeOcaNeodgVrednost() {
		String fathersName= "Ni";
		Throwable exception= assertThrows(IllegalArgumentException.class, 
				()-> m.setFathersName(fathersName));
		assertEquals("Ime oca clana mora imati minimum 3 karaktera niti biti prazan string", exception.getMessage());
	}
	@Test
	void testSetImeOcaNull() {
		assertThrows(NullPointerException.class
				, ()-> m.setFathersName(null));
	}
	@Test
	void testSetImeOcaSveOK() {
		m.setFathersName("Jadranka");
		assertEquals("Jadranka", m.getFathersName());
	}
	@Test
	void testSetGradNeodgVrednost() {
		assertThrows(IllegalArgumentException.class, 
				()-> m.setCity(cityExample1));
	}

	@Test
	void testSetGradSveOK() {
		m.setCity(cityExample2);
		assertEquals(cityExample2, m.getCity());
	}
	
	@Test
	void testSetPojasNull() {
		assertThrows(NullPointerException.class, 
				()-> m.setBelt(null));
	}
	@Test
	void testSetPojasSveOK() {
		m.setBelt(Belt.BELI);
		assertEquals(Belt.BELI, m.getBelt());
	}
	
	@ParameterizedTest
	@CsvSource({
		"-1",
		"0"
	})
	void testSetMedaljeNeodgVrednost(Long medalje) {
		assertThrows(IllegalArgumentException.class, 
				()-> m.setMedals(medalje));
	}
	@Test
	void testSetMedaljeSveOK() {
		m.setMedals(50L);
		assertEquals(50L, m.getMedals());
	}
	
	@ParameterizedTest
	@CsvSource({
		"-1",
		"0"
	})
	void testSetZlatneMedaljeNeodgVrednost(Long medalje) {
		assertThrows(IllegalArgumentException.class, 
				()-> m.setGoldMedals(medalje));
	}
	@Test
	void testSetZlatneMedaljeSveOK() {
		m.setGoldMedals(50L);
		assertEquals(50L, m.getGoldMedals());
	}
	@ParameterizedTest
	@CsvSource({
		"-1",
		"0"
	})
	void testSetSrebrneMedaljeNeodgVrednost(Long medalje) {
		assertThrows(IllegalArgumentException.class, 
				()-> m.setSilverMedals(medalje));
	}
	@Test
	void testSetSrebrneMedaljeSveOK() {
		m.setSilverMedals(50L);
		assertEquals(50L, m.getSilverMedals());
	}
	@ParameterizedTest
	@CsvSource({
		"-1",
		"0"
	})
	void testSetBronzaneMedaljeNeodgVrednost(Long medalje) {
		assertThrows(IllegalArgumentException.class, 
				()-> m.setBronzeMedals(medalje));
	}
	@Test
	void testSetBronzaneMedaljeSveOK() {
		m.setBronzeMedals(50L);
		assertEquals(50L, m.getBronzeMedals());
	}
	@Test
    void testSetKategorijaNull() {
    	assertThrows(NullPointerException.class, 
    			()-> m.setCategory(null));
    }
    @Test
    void testSetKategorijaSveOK() {
    	m.setCategory(Category.JUNIOR);
    	assertEquals(Category.JUNIOR, m.getCategory());
    }
    @Test
    void testSetDisciplinaNull() {
    	assertThrows(NullPointerException.class, 
    			()-> m.setDiscipline(null));
    }
    @Test
    void testSetDisciplinaSveOK() {
    	m.setDiscipline(Discipline.BORBE);
    	assertEquals(Discipline.BORBE, m.getDiscipline());
    }
    @Test
    void testSetClanarineNull() {
    	assertThrows(NullPointerException.class, 
    			()-> m.setMembershipFee(null));
    }
    @Test
    void testSetClanarinaSveOK() {
    	MembershipFee mf= new MembershipFee();
    	Calendar calendar=Calendar.getInstance();
    	Member member= new Member(1L,"Jelena","Repac",Gender.FEMALE,calendar.getTime(),"Jadranka","Nikola",new City(1L, 11000L,"Beograd"),Belt.CRNI,50L,Discipline.KATE,Category.SENIOR,"Niksicka 10",10L,10L,30L, new ArrayList<MembershipFee>(),calendar.getTime(),3000.0);
		
		calendar.set(2023, 05, 03);
    	mf.setAmount(3000);
    	mf.setDate(calendar.getTime());
    	mf.setMember(member);
    	mf.setId(1L);
    	
    	List<MembershipFee> membershipFees= new ArrayList<>();
    	membershipFees.add(mf);
    	member.setMembershipFee(membershipFees);
    	assertEquals(1,member.getMembershipFee().size());
    	assertTrue(membershipFees.contains(mf));
    	assertEquals(membershipFees, member.getMembershipFee());
    }
    @Test
    void testSetAdresaNull() {
    	assertThrows(NullPointerException.class, 
    			()-> m.setAdress(null));
    }
    @Test
    void testSetAdresaSveOk() {
    	m.setAdress("Niksicka 10");
    	assertEquals("Niksicka 10", m.getAdress());
    }
    @Test
    void testSetPolNull() {
    	assertThrows(NullPointerException.class, 
    			()-> m.setGender(null));
    }
    @Test
    void testSetPolSveOk() {
    	m.setGender(Gender.FEMALE);
    	assertEquals(Gender.FEMALE, m.getGender());
    }
    
    @Test
	void testSetDatumUclanjenjaNull() {
		assertThrows(NullPointerException.class, 
				()-> m.setDateOfMembership(null));
	}
	@Test
	void testSetDatumUclanjenjaNeodgVrednost() {
        
		Calendar calendar = Calendar.getInstance();
		calendar.set(2023, 10, 10);
        Throwable exception = assertThrows(Exception.class, () ->
           m.setDateOfMembership(calendar.getTime()));
        
        assertEquals("Datum uclanjenja ne sme biti nakon danasnjeg!", exception.getMessage());
        
	}
	@Test 
	void testSetDatumUclanjenjaSveOK() {
		Calendar calendar = Calendar.getInstance();
	        
       calendar.set(2000, 3,2);
		m.setDateOfMembership(calendar.getTime());
		assertEquals(calendar.getTime(), m.getDateOfMembership());
	}
	@Test
	void testSetDugNull() {
		assertThrows(NullPointerException.class, 
				()-> m.setTotalDebt(-1000.00));
	}
	@Test
	void testSetDugSveOK() {
		m.setTotalDebt(3000.0);
		assertEquals(3000.0, m.getTotalDebt());
	}
	
	@Test
    public void testToString() {
		Calendar calendar = Calendar.getInstance();
        calendar.set(2000, 3,2);
        Calendar dateOfMembership = Calendar.getInstance();
        dateOfMembership.set(2022, 11,2);
		m.setName("Jelena");
		m.setLastname("Repac");
		m.setBirthday(calendar.getTime());
		m.setDiscipline(Discipline.KATE);
		m.setCategory(Category.SENIOR);
		m.setAdress("Niksicka 10");
		m.setDateOfMembership(dateOfMembership.getTime());
		String str= m.toString();
		assertTrue(str.contains("Jelena"));
		assertTrue(str.contains("Repac"));
		assertTrue(str.contains(Discipline.KATE.toString()));
		assertTrue(str.contains(Category.SENIOR.toString()));
		assertTrue(str.contains(dateOfMembership.getTime().toString()));
		assertTrue(str.contains(calendar.getTime().toString()));
		assertTrue(str.contains("Niksicka 10"));
    }
}
