package microtope.messages;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class AMQMessageParserTests {

	@Test
	void testTextMessageParser_faultyFormat_shouldGiveBadMessage() {
		String message = "Unknown Message Format";
		
		AMQMessage expected = new BadMessage();
		
		AMQMessage parsed = AMQMessageParser.parseTextMessage(message);
		
		assertEquals(expected,parsed);
	}
	
	@Test
	void testTextMessageParser_MessageStartsWithE_shouldGiveBadMessage() {
		String message = "E: Player 37845 logged out";
		
		AMQMessage expected = new BadMessage();
		
		AMQMessage parsed = AMQMessageParser.parseTextMessage(message);
		
		assertEquals(expected,parsed);
	}
	
	@Test
	void testTextMessageParser_parseCoinsMessage_PlayerNrIsTooShort_shouldGiveBadMessage() {
		String message = "E: Player 3745 collected 1 coins for Team PURPLE";
		
		AMQMessage expected = new BadMessage();
		
		AMQMessage parsed = AMQMessageParser.parseTextMessage(message);
		
		assertEquals(expected,parsed);
	}
	
	@Test
	void testTextMessageParser_parseStepsMessage_PlayerNrIsTooShort_shouldGiveBadMessage() {
		String message = "M: Player 3784 moved 14 steps";
		
		AMQMessage expected = new BadMessage();
		
		AMQMessage parsed = AMQMessageParser.parseTextMessage(message);
		
		assertEquals(expected,parsed);
	}
	
	@Test
	void testTextMessageParser_parseCoinsMessage_EverythingCorrect_shouldBeParsed() {
		String message = "E: Player 37845 collected 1 coins for Team PURPLE";
		
		AMQMessage expected = new CoinMessage(37854,1);
		
		AMQMessage parsed = AMQMessageParser.parseTextMessage(message);
		
		assertEquals(expected,parsed);
	}
	
	@Test
	void testTextMessageParser_parseStepsMessage_EverythingCorrect_shouldBeParsed() {
		String message = "M: Player 37845 moved 14 steps";
		
		AMQMessage expected = new StepMessage(37854,14);
		
		AMQMessage parsed = AMQMessageParser.parseTextMessage(message);
		
		assertEquals(expected,parsed);
	}
}
