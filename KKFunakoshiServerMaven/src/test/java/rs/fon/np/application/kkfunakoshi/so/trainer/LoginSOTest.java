package rs.fon.np.application.kkfunakoshi.so.trainer;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import rs.fon.np.application.kkfunakoshi.domain.City;
import rs.fon.np.application.kkfunakoshi.domain.Trainer;

class LoginSOTest {

private LoginSO loginSO;
    
    @BeforeEach
    void setUp() throws Exception {
        loginSO = new LoginSO();
    }

    @Test
    void testPreconditionWithNullParam() {
        assertThrows(Exception.class, () -> loginSO.execute(null));
    }
    
    @Test
    void testPreconditionWithWrongTypeParam() {
        assertThrows(Exception.class, () -> loginSO.execute(new City()));
    }
    
   

    @Test
    void testExecuteOperationWithCorrectCredentials() throws Exception {
        Trainer user = new Trainer(1L,"Jelena","Repac","jeca", "jelena2001",false);
        loginSO.execute(user);
        Trainer currentUser = loginSO.getCurrentUser();
        assertNotNull(currentUser);
        assertEquals(user.getUsername(), currentUser.getUsername());
        assertEquals(user.getPassword(), currentUser.getPassword());
    }
    
  

    @Test
    void testExecuteOperationWithAlreadyLoggedInUser() throws Exception {
        Trainer user =new Trainer(1L,"Danijela","Pudaric","daca", "dacika2000",false);
        loginSO.execute(user);
        assertThrows(Exception.class, () -> loginSO.executeOperation(user));
    }

}
