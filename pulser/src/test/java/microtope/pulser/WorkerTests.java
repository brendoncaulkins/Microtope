package microtope.pulser;

import static org.junit.jupiter.api.Assertions.*;

import javax.jms.JMSException;

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
	
	@Test
	void testRandomizedWorked_withFakeMessenger_shouldBeBuild() {
		MessageSender sender = new FakeMessageSender();
		
		Worker worker = Worker.randomWorker(sender, 100);
		
		return;
	}
	
	@Test
	void testWork_singleMessageWithFakeMessenger_shouldBeSend() {
		MessageSender sender = new FakeMessageSender();
		Worker worker = Worker.randomWorker(sender, 1);
		 
		try {
			worker.work();
			
			assertTrue(((FakeMessageSender) sender).sendMessage);
		} catch (JMSException e) {
			fail();
		} catch (InterruptedException e) {
			fail();
		}
			
	}
	
	@Test
	void testSendRandomizedMessage_test10000Messages_shouldSendCoinsAndSteps() {
		FakeMessageSender sender = new FakeMessageSender();
		Worker worker = Worker.randomWorker(sender, 1);
		
		
		boolean sendCoins= false;
		boolean sendSteps=false;
		
		for (int i = 0; i<10000; i++) {
			worker.sendRandomizedMessage();
			sendCoins = sendCoins || sender.lastMessageWasCoins;
			sendSteps = sendSteps || sender.lastMessageWasSteps;
		}
		
		assertTrue(sendCoins && sendSteps);
			
	}
}
