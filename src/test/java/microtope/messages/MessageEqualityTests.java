package microtope.messages;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

public class MessageEqualityTests {
	
	@Test
	void testCoinMessage_sameValues_shouldBeTheSame() {
		AMQMessage first = new CoinMessage(30705,1);
		
		AMQMessage other = new CoinMessage(30705,1);
		
		assertEquals(first,other);
	}
	
	@Test
	void testCoinMessage_differentPlayer_shouldNotBeTheSame() {
		AMQMessage first = new CoinMessage(30705,1);
		
		AMQMessage other = new CoinMessage(30702,1);
		
		assertNotEquals(first,other);
	}
	
	@Test
	void testCoinMessage_differentCoins_shouldNotBeTheSame() {
		AMQMessage first = new CoinMessage(30705,1);
		
		AMQMessage other = new CoinMessage(30705,2);
		
		assertNotEquals(first,other);
	}
	
	void testCoinMessage_sameMessage_shouldBeTheSame() {
		AMQMessage one = new CoinMessage(30705,10);
		
		assertEquals(one,one);
	}
	
	@Test
	void testStepMessage_sameValues_shouldBeTheSame() {
		AMQMessage first = new StepMessage(30705,10);
		
		AMQMessage other = new StepMessage(30705,10);
		
		assertEquals(first,other);
	}
	@Test
	
	void testStepMessage_sameMessage_shouldBeTheSame() {
		AMQMessage one = new StepMessage(30705,10);
		
		assertEquals(one,one);
	}
	
	@Test
	void testStepMessage_differentPlayer_shouldNotBeTheSame() {
		AMQMessage first = new StepMessage(30705,1);
		
		AMQMessage other = new StepMessage(30702,1);
		
		assertNotEquals(first,other);
	}
	
	@Test
	void testStepMessage_differentSteps_shouldNotBeTheSame() {
		AMQMessage first = new StepMessage(30705,10);
		
		AMQMessage other = new StepMessage(30705,22);
		
		assertNotEquals(first,other);
	}
	
	@Test
	void testEquals_twodifferntCoinTypes_shouldNotBeTheSame() {
		AMQMessage first = new StepMessage(30705,10);
		
		AMQMessage other = new CoinMessage(30705,10);
		
		assertNotEquals(first,other);	
	}
	
}
