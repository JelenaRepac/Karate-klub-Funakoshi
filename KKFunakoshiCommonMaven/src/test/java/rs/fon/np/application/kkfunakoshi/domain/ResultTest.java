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

class ResultTest {

	Result r;
	Competition example1;
	Competition example2;
	Member m1;
	Member m2;
	Team t1;
	Team t2;
	
	@BeforeEach
	void setUp() throws Exception {
		r= new Result();
		Calendar calendar=Calendar.getInstance();
		example1= new Competition(1L,"Kup Beograda",calendar.getTime() , "Ranko Zeravica", new City(1L, 11000L,"Beograd"));			
		example2= new Competition(1L,"Kup Beograda", null, "Ranko Zeravica", new City(1L, 11000L,"Beograd"));			
		Calendar dateOfBirth= Calendar.getInstance();
		calendar.set(2001, 03, 02);
		
		m1= new Member(1L,"Jelena","Repac",Gender.FEMALE,calendar.getTime(),"Jadranka","Nikola",new City(1L, 11000L,"Beograd"),Belt.CRNI,50L,Discipline.KATE,Category.SENIOR,"Niksicka 10",10L,10L,30L, new ArrayList<MembershipFee>(),calendar.getTime(),3000.0);
		m2= new Member(null,"Jelena","Repac",Gender.FEMALE,calendar.getTime(),"Jadranka","Nikola",new City(1L, 11000L,"Beograd"),Belt.CRNI,50L,Discipline.KATE,Category.SENIOR,"Niksicka 10",10L,10L,30L, new ArrayList<MembershipFee>(),calendar.getTime(),3000.0);
		
		ArrayList<Member> members= new ArrayList<Member>();
		members.add(m1);
		t1= new Team(1L, "SENIORI- devojke", members, Category.SENIOR);
		t2= new Team(2L, "SENIORI- devojke", null, Category.SENIOR);
		}

	@AfterEach
	void tearDown() throws Exception {
		r=null;
	}

	@Test
    public void testSetIdNeodgVrednost() {
        Long id = -10L;
        Throwable exception = assertThrows(
                Exception.class, () -> {
                    r.setId(id);
                }
        );
        assertEquals("Id ne sme biti negativan broj ili jednak nuli", exception.getMessage());
    }

    @Test
    public void testSetIdSveOk() throws Exception {
        Long id2 = 5L;
        r.setId(id2);
        assertEquals(id2, r.getId());
    }
    
    @Test
    void testSetTakmicenjeNeodgVrednost() {
    	assertThrows(IllegalArgumentException.class, 
    			()-> r.setCompetition(example2));
    }
    @Test
    void testSetTakmicenjeSveOK() {
    	r.setCompetition(example1);
    	assertEquals(example1, r.getCompetition());
    }
    @Test
    void testSetClanNeodgVrednost() {
    	assertThrows(IllegalArgumentException.class, 
    			()-> r.setMember(m2));
    }
    @Test
    void testSetClanSveOK() {
    	r.setMember(m1);
    	assertEquals(m1, r.getMember());
    }
    @Test
    void testSetMedaljuNull() {
    	assertThrows(NullPointerException.class, 
    			()-> r.setMedals(null));
    }
    @Test
    void testSetTimNeodgVrednost() {
    	System.out.println(t2);
    	assertThrows(IllegalArgumentException.class, 
    			()-> r.setTeam(t2));
    }
    @Test
    void testSetTimSveOK() {
    	r.setTeam(t1);
    	assertEquals(t1, r.getTeam());
    }
    @Test
    void testSetKategorijaNull() {
    	assertThrows(NullPointerException.class, 
    			()-> r.setCategory(null));
    }
    @Test
    void testSetKategorijaSveOK() {
    	r.setCategory(Category.JUNIOR);
    	assertEquals(Category.JUNIOR, r.getCategory());
    }
    @Test
    public void testToString() {
        Team team = new Team();
        ArrayList<Member> members= new ArrayList<Member>();
		members.add(m1);
        team.setId(1L);
        team.setName("Team1");
        team.setMembers(members);
        team.setCategory(Category.JUNIOR);
        Result result = new Result(example1, m1, team, Medal.GOLD,Category.JUNIOR);
        String expectedOutput = "Result{" + "competition=" + example1 + ", member=" + m1 + ", team=" + team + ", category='JUNIOR', medals=" + "GOLD" + '}';
        assertEquals(expectedOutput, result.toString());
    }
   
}
