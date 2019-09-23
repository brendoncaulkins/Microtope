package microtope.messages;

import static org.junit.jupiter.api.Assertions.*;

import org.apache.activemq.command.ActiveMQBytesMessage;
import org.apache.activemq.command.ActiveMQTextMessage;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

class AMQMessageParserTests {

	@Test
	void testTextMessageParser_faultyFormat_shouldGiveBadMessage() {
		String message = "Unknown Message Format";
		
		AmqMessage expected = new BadMessage();
		
		AmqMessage parsed = AmqMessageParser.parseTextMessage(message);
		
		assertEquals(expected,parsed);
	}
	
	@Test
	void testTextMessageParser_MessageIsValidLoginMessage_shouldBeParsed() {
		String message = "E: Player 50043 logged in for team 2";
		
		AmqMessage expected = new LoginMessage(50043,2);
		
		AmqMessage parsed = AmqMessageParser.parseTextMessage(message);
		
		assertEquals(expected,parsed);
	}
	
	@Test
	void testTextMessageParser_MessageHasVeryLongTeamID_shouldBeParsed() {
		String message = "E: Player 50043 logged in for team 2051";
		
		AmqMessage expected = new LoginMessage(50043,2051);
		
		AmqMessage parsed = AmqMessageParser.parseTextMessage(message);
		
		assertEquals(expected,parsed);
	}
	
	@Test
	void testTextMessageParser_MessageHasVeryLongPlayerID_shouldBeParsed() {
		String message = "E: Player 50043001 logged in for team 2";
		
		AmqMessage expected = new LoginMessage(50043001,2);
		
		AmqMessage parsed = AmqMessageParser.parseTextMessage(message);
		
		assertEquals(expected,parsed);
	}
	
	@Test
	void testTextMessageParser_LoginMessageHasTeamAsString_shouldGiveBadMessage() {
		String message = "E: Player 50043 logged in for team BLUE";
		
		AmqMessage expected = new BadMessage();
		
		AmqMessage parsed = AmqMessageParser.parseTextMessage(message);
		
		assertEquals(expected,parsed);
	}
	
	@Test
	void testTextMessageParser_LoginMessageHasNoTeam_shouldGiveBadMessage() {
		String message = "E: Player 50043 logged in";
		
		AmqMessage expected = new BadMessage();
		
		AmqMessage parsed = AmqMessageParser.parseTextMessage(message);
		
		assertEquals(expected,parsed);
	}
	
	@Test
	void testTextMessageParser_MessageIsValidLogoutMessage_shouldBeParsed() {
		String message = "E: Player 37845 logged out";
		
		AmqMessage expected = new LogoutMessage(37845);
		
		AmqMessage parsed = AmqMessageParser.parseTextMessage(message);
		
		assertEquals(expected,parsed);
	}
	
	@Test
	void testTextMessageParser_parseCoinsMessage_PlayerNrIsShort_shouldGiveCoinMessage() {
		String message = "E: Player 374 collected 2 coins for Team 1";

		AmqMessage expected = new CoinMessage(374,2);
		
		AmqMessage parsed = AmqMessageParser.parseTextMessage(message);
		
		assertEquals(expected,parsed);
	}
	
	@Test
	void testTextMessageParser_parseCoinsMessage_TeamIsStringNotNumber_shouldGiveBadMessage() {
		String message = "E: Player 3745 collected 1 coins for Team PURPLE";
		
		AmqMessage expected = new BadMessage();
		
		AmqMessage parsed = AmqMessageParser.parseTextMessage(message);
		
		assertEquals(expected,parsed);
	}
	
	@Test
	void testTextMessageParser_parseStepsMessage_PlayerNrIsShort_shouldGiveStepMessage() {
		String message = "M: Player 374 moved 14 steps";
		
		AmqMessage expected = new StepMessage(374,14);
		
		AmqMessage parsed = AmqMessageParser.parseTextMessage(message);
		
		assertEquals(expected,parsed);
	}
	
	@Test
	void testTextMessageParser_parseCoinsMessage_EverythingCorrect_shouldBeParsed() {
		String message = "E: Player 37845 collected 1 coins for Team 2";
		
		AmqMessage expected = new CoinMessage(37845,1);
		
		AmqMessage parsed = AmqMessageParser.parseTextMessage(message);
		
		boolean equal = expected.equals(parsed);
		
		assertTrue(equal);
	}
	
	@Test
	void testTextMessageParser_parseStepsMessage_EverythingCorrect_shouldBeParsed() {
		String message = "M: Player 37845 moved 14 steps";
		
		AmqMessage expected = new StepMessage(37845,14);
		
		AmqMessage parsed = AmqMessageParser.parseTextMessage(message);
		
		assertEquals(expected,parsed);
	}
	
	@Test
	void testMessageParser_JMSTextMessage_shouldBeParsed() {
		
		TextMessage text = new ActiveMQTextMessage( );
		try {
			text.setText("M: Player 37845 moved 14 steps");
		} catch (JMSException e) {
			fail();
		}
		
		AmqMessage expected = new StepMessage(37845,14);
		
		AmqMessage parsed = AmqMessageParser.parseJmsMessage(text);
		
		assertEquals(expected,parsed);
	}
	
	@Test
	void testMessageParser_JMSTextMessage_BadFormat_shouldBeBadMessage() {
		
		TextMessage text = new ActiveMQTextMessage( );
		try {
			text.setText("Invalid Format");
		} catch (JMSException e) {
			fail();
		}

		AmqMessage expected = new BadMessage();
		
		AmqMessage parsed = AmqMessageParser.parseJmsMessage(text);
		
		assertEquals(expected,parsed);
	}
	@Test
	void testMessageParser_JMSOtherMessageType_shouldBeBadMessage() {

		Message text = new ActiveMQBytesMessage( );
		
		AmqMessage expected = new BadMessage();
		
		AmqMessage parsed = AmqMessageParser.parseJmsMessage(text);
		
		assertEquals(expected,parsed);
	}
	
	@Test
	@Disabled
	void testMessageParser_MessageThrowsJMSException_shouldReturnBadMessage() {
		// This does not produce an JMS Exception
		// If i could get this exception anyhow in my message, i would have 100% coverage of parser
		TextMessage faulty = new ActiveMQTextMessage( );
		try {
			faulty.setText("M: Player 37845 moved 14 steps");
			faulty.setJMSCorrelationID("Kuchenbär");
			faulty.setJMSTimestamp(0);
		} catch (JMSException e) {
			fail();
		}

		AmqMessage expected = new BadMessage();
		
		AmqMessage parsed = AmqMessageParser.parseJmsMessage(faulty);
		
		assertEquals(expected,parsed);
	}
}
