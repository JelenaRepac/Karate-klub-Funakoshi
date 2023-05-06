package rs.fon.np.application.kkfunakoshi.so.member;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import rs.fon.np.application.kkfunakoshi.domain.AbstractDO;
import rs.fon.np.application.kkfunakoshi.domain.Belt;
import rs.fon.np.application.kkfunakoshi.domain.Category;
import rs.fon.np.application.kkfunakoshi.domain.City;
import rs.fon.np.application.kkfunakoshi.domain.Competition;
import rs.fon.np.application.kkfunakoshi.domain.Discipline;
import rs.fon.np.application.kkfunakoshi.domain.Gender;
import rs.fon.np.application.kkfunakoshi.domain.Medal;
import rs.fon.np.application.kkfunakoshi.domain.Member;
import rs.fon.np.application.kkfunakoshi.domain.Result;
import rs.fon.np.application.kkfunakoshi.domain.Team;
import rs.fon.np.application.kkfunakoshi.repository.Repository;
import rs.fon.np.application.kkfunakoshi.repository.impl.RepositoryDBGeneric;

class UpdateMemberSOTest {

	private Repository<AbstractDO> repository;
	private UpdateMemberSO updateMemberSO;
	@BeforeEach
	void setUp() throws Exception {
		updateMemberSO= new UpdateMemberSO();
		repository= new RepositoryDBGeneric();
	}
	 @Test
    public void testPreconditionWithNullObject() {
        List<Member> members = null;

        Exception exception = assertThrows(Exception.class, () -> {
        	updateMemberSO.execute(members);
        });

        assertEquals("The object was not sent!", exception.getMessage());
    }
    @Test
    public void testPreconditionWithEmptyResults() {
        List<Member> members = new ArrayList();

        Exception exception = assertThrows(Exception.class, () -> {
        	updateMemberSO.execute(members);
        });

        assertEquals("No required parameters sent!", exception.getMessage());
    }
    
    @Test
    public void testPreconditionWithValidParameters() throws Exception {
        List<Member> members = new ArrayList<>();
        Member oldM= 
    			new Member(1L,"Milica", "Markovic", Gender.FEMALE,  new java.sql.Date(100, 11, 2),
    					"Jadranka", "Nikola", new City(1L,11000L,"Beograd"), 
    					Belt.CRNI, 50L, Discipline.KATE, Category.SENIOR,
    					"Niksicka 10", 10L, 10L, 30L, new ArrayList<>(),
    					new java.sql.Date(123, 4, 2), 3000.00);
        Member newM= 
    			new Member(1L,"Milica", "Markovic", Gender.FEMALE,  new java.sql.Date(100, 11, 2),
    					"Jadranka", "Nikola", new City(1L,11000L,"Beograd"), 
    					Belt.CRVENI, 50L, Discipline.KATE, Category.POLETARAC,
    					"Niksicka 10", 10L, 10L, 30L, new ArrayList<>(),
    					new java.sql.Date(123, 4, 2), 3000.00);
        members.add(oldM);
        members.add(newM);
        updateMemberSO.execute(members);
        assertEquals(1, repository.getAll(new Member()).get(0).getId());
       
    }
}
