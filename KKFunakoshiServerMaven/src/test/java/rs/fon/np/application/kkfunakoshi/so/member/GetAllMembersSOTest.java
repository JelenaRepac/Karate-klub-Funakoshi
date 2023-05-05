package rs.fon.np.application.kkfunakoshi.so.member;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import rs.fon.np.application.kkfunakoshi.domain.Competition;
import rs.fon.np.application.kkfunakoshi.domain.Member;
import rs.fon.np.application.kkfunakoshi.so.competition.GetAllCompetitionSO;

class GetAllMembersSOTest {

	private GetAllMembersSO getAllMembersSO;
	@BeforeEach
	public void setUp() throws Exception {
		getAllMembersSO= new GetAllMembersSO();
	}

	@AfterEach
	public void tearDown() throws Exception {
		getAllMembersSO= null;
	}

	@Test
	public void testExecuteOperation() throws Exception {
		getAllMembersSO.executeOperation(null);
		assertNotNull(getAllMembersSO.getMembers());
		assertTrue(getAllMembersSO.getMembers().size()>0);
		for(Member m: getAllMembersSO.getMembers()) {
			assertNotNull(m.getId());
			assertNotNull(m.getCity());
			assertNotNull(m.getBirthday());
			assertNotNull(m.getName());
			assertNotNull(m.getLastname());
			assertNotNull(m.getFathersName());
			assertNotNull(m.getMothersName());
			assertNotNull(m.getDateOfMembership());
			assertNotNull(m.getMembershipFee());
			assertNotNull(m.getBelt());
			assertNotNull(m.getDiscipline());
			assertNotNull(m.getCategory());
			assertNotNull(m.getAdress());
			assertNotNull(m.getGender());
			assertNotNull(m.getMedals());
			assertNotNull(m.getGoldMedals());
			assertNotNull(m.getSilverMedals());
			assertNotNull(m.getBronzeMedals());
			assertNotNull(m.getTotalDebt());
		}
	}
}
