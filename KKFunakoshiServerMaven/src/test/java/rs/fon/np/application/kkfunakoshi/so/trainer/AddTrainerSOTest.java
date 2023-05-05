package rs.fon.np.application.kkfunakoshi.so.trainer;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import rs.fon.np.application.kkfunakoshi.domain.Belt;
import rs.fon.np.application.kkfunakoshi.domain.Category;
import rs.fon.np.application.kkfunakoshi.domain.City;
import rs.fon.np.application.kkfunakoshi.domain.Discipline;
import rs.fon.np.application.kkfunakoshi.domain.Gender;
import rs.fon.np.application.kkfunakoshi.domain.Member;
import rs.fon.np.application.kkfunakoshi.domain.Trainer;
import rs.fon.np.application.kkfunakoshi.repository.Repository;
import rs.fon.np.application.kkfunakoshi.repository.impl.RepositoryDBGeneric;
import rs.fon.np.application.kkfunakoshi.so.member.AddMemberSO;

class AddTrainerSOTest {

	private Repository repository;
    private AddTrainerSO addTrainerSO;

    @BeforeEach
    public void setUp() {
        repository = new RepositoryDBGeneric();
        addTrainerSO = new AddTrainerSO();
    }

    @Test
    public void testAddTrainerSuccess() throws Exception {
    	Trainer trainer= new Trainer(1L, "Vukasin", "Pudaric", "vukasin", "vukasin2000", false);
    	addTrainerSO.execute(trainer);
        List<Trainer> trainers= repository.getAll(new Trainer());
        System.out.println(trainers.toString());
        assertEquals(3, repository.getAll(new Trainer()).size());
        
    }

    @Test
    public void testAddTrainerAlreadyExists() throws Exception {
    	Trainer trainer= new Trainer(1L, "Vukasin", "Pudaric", "vukasin", "vukasin2000", false);
        assertThrows(Exception.class, 
        		() -> addTrainerSO.execute(trainer));
    }

    @Test
    public void testAddTrainerNullInput() {
       assertThrows(Exception.class, 
    		   () -> addTrainerSO.execute(null));
    }
    @Test
    public void testAddTrainerWrongType() {
        assertThrows(Exception.class, 
        		() -> addTrainerSO.execute(new City()));
    }


}
