package microtope.pulser;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class WorkerTests {

	@Test
	void testConstructor_withFakeMessanger_shouldBeBuild() {
		MessageSender sender = new FakeMessageSender();
		int team_id=1;
		int player_id=1;
		
		Worker worker = new Worker(player_id,team_id,sender,500);
		
		return;
	}
	
	
}
