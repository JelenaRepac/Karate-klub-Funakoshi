package rs.fon.np.application.kkfunakoshi.so.city;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import rs.fon.np.application.kkfunakoshi.domain.City;
import rs.fon.np.application.kkfunakoshi.domain.Member;

public class GetAllCitiesSOTest {

	 private GetAllCitiesSO getAllCitiesSO;
	    
    @BeforeEach
    public void setUp() {
        getAllCitiesSO = new GetAllCitiesSO();
    }
    @AfterEach
    public void tearDown() {
    	getAllCitiesSO=null;
    }
   
    @Test
    public void testExecuteOperation() throws Exception {
        getAllCitiesSO.executeOperation(null);
        assertNotNull(getAllCitiesSO.getCities());
        assertTrue(getAllCitiesSO.getCities().size() > 0);
        for (City city : getAllCitiesSO.getCities()) {
        	System.out.println(city.toString());
            assertNotNull(city.getId());
            assertNotNull(city.getName());
        }
    }

    	
}
