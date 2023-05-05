package rs.fon.np.application.kkfunakoshi.so.competition;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import rs.fon.np.application.kkfunakoshi.domain.AbstractDO;
import rs.fon.np.application.kkfunakoshi.domain.City;
import rs.fon.np.application.kkfunakoshi.domain.Competition;
import rs.fon.np.application.kkfunakoshi.repository.Repository;
import rs.fon.np.application.kkfunakoshi.repository.impl.RepositoryDBGeneric;

public class GetCompetitionsByQuerySOTest {


	private Repository<AbstractDO> repository;
    private GetCompetitionsByQuerySO getCompetitionsByQuerySO;

    @BeforeEach
    public void setUp() throws Exception {
        repository = new RepositoryDBGeneric();
        getCompetitionsByQuerySO = new GetCompetitionsByQuerySO();
    }

    @Test
    public void testExecuteOperation() throws Exception {
        Competition c1 = new Competition(1L, "Zlatno zvono", new Date(), "Ranko Zeravica", new City(1L, 11000L, "Beograd"));
        Competition c2 = new Competition(2L, "Kup Beograda", new Date(), "Ranko Zeravica", new City(1L, 11000L, "Beograd"));

        repository.add(c1);
        repository.add(c2);

        List<Competition> expectedCompetitions = new ArrayList<Competition>();
        expectedCompetitions.add(c1);
        expectedCompetitions.add(c2);
        String str= "WHERE competitionHall = 'Ranko Zeravica'";
        
        getCompetitionsByQuerySO.execute(str);
        

        List<Competition> actualCompetitions = getCompetitionsByQuerySO.getCompetitions();
        
        assertEquals(expectedCompetitions, actualCompetitions);
    }

    @Test
    public void testPreconditionNullParam() {
        Exception exception = assertThrows(Exception.class, () -> getCompetitionsByQuerySO.execute(null));
        assertEquals("Object is the wrong type!", exception.getMessage());
    }

    @Test
    public void testPreconditionWrongParamType() {
        Exception exception = assertThrows(Exception.class, () -> getCompetitionsByQuerySO.execute(new City()));
        assertEquals("Object is the wrong type!", exception.getMessage());
    }

}
