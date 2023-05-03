package rs.fon.np.application.kkfunakoshi.domain;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MembershipFeeTest {

	MembershipFee mf;
	Member example1;
	Member example2;
	@BeforeEach
	void setUp() throws Exception {
		mf= new MembershipFee();
		Calendar calendar=Calendar.getInstance();
		calendar.set(2001, 03, 02);
		example1= new Member(1L,"Jelena","Repac",Gender.FEMALE,calendar.getTime(),"Jadranka","Nikola",new City(1L, 11000L,"Beograd"),Belt.CRNI,50L,Discipline.KATE,Category.SENIOR,"Niksicka 10",10L,10L,30L, new ArrayList<MembershipFee>(),calendar.getTime(),3000.0);
		example2= new Member(null,"Jelena","Repac",Gender.FEMALE,calendar.getTime(),"Jadranka","Nikola",new City(1L, 11000L,"Beograd"),Belt.CRNI,50L,Discipline.KATE,Category.SENIOR,"Niksicka 10",10L,10L,30L, new ArrayList<MembershipFee>(),calendar.getTime(),3000.0);
		
	}

	@AfterEach
	void tearDown() throws Exception {
		mf= null;
	}

	@Test
	void testSetIdNeodgVrednost() {
		assertThrows(IllegalArgumentException.class, 
				() -> mf.setId(-1L));
	}
	@Test
	void testSetIdSveOK() {
		mf.setId(10L);
		assertEquals(10L, mf.getId());
	}

	@Test
	void testSetDatumNeodgVrednost() {
        
		Calendar calendar = Calendar.getInstance();
		calendar.set(2022, 10, 10);
	    Date currentDate = calendar.getTime();
	    System.out.println(currentDate);
        Throwable exception = assertThrows(Exception.class, () ->
            mf.setDate(currentDate));
        
        assertEquals("Datum ne sme biti pre danasnjeg!", exception.getMessage());
        
	}
	@Test 
	void testSetDatumSveOK() {
		 Calendar calendar = Calendar.getInstance();
	        
        calendar.add(Calendar.DAY_OF_MONTH, 7);
        Date futureDate = calendar.getTime();
		mf.setDate(futureDate);
		assertEquals(futureDate, mf.getDate());
	}
	
	@Test
	void testSetIznosNeodgVrednost() {
		assertThrows(IllegalArgumentException.class, 
				()-> mf.setAmount(-10));
	}
	@Test
	void testSetIznosSveOK() {
		mf.setAmount(1000);
		assertEquals(1000, mf.getAmount());
	}
	
	@Test
    void testSetClanNeodgVrednost() {
    	assertThrows(IllegalArgumentException.class, 
    			()-> mf.setMember(example2));
    }
    @Test
    void testSetClanSveOK() {
    	mf.setMember(example1);
    	assertEquals(example1, mf.getMember());
    }
    
    @Test
    void testToString() {
    	Calendar calendar=Calendar.getInstance();
		calendar.set(2023, 05, 03);
    	mf.setAmount(3000);
    	mf.setDate(calendar.getTime());
    	mf.setMember(example1);
    	mf.setId(1L);
    	String str= mf.toString();
    	assertTrue(str.contains("3000"));
    	assertTrue(str.contains(example1.toString()));
    	assertTrue(str.contains("1"));
    	assertTrue(str.contains(calendar.getTime().toString()));
    }
}
