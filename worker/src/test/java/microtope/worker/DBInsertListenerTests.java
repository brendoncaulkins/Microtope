package microtope.worker;

import static org.junit.jupiter.api.Assertions.*;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

import org.apache.activemq.command.ActiveMQBytesMessage;
import org.apache.activemq.command.ActiveMQTextMessage;
import org.junit.jupiter.api.Test;

class DBInsertListenerTests {

	@Test
	public void testConstructor_FakeDBWriter_shouldBeBuild() {
		FakeDBWriter mock = new FakeDBWriter();
		
		DatabaseInsertListener testObject = new DatabaseInsertListener(mock);
		
		return;
	}
	
	@Test
	public void testConstructor_NullDBWriter_shouldThrowError() {
		assertThrows(IllegalArgumentException.class, 
				() -> {new DatabaseInsertListener(null);}
		);		
	}
	
	@Test
	public void testOnMessage_MessageIsCoins_ShouldWriteCoins() {
		FakeDBWriter mock = new FakeDBWriter();	
		DatabaseInsertListener testObject = new DatabaseInsertListener(mock);
		
		TextMessage message = new ActiveMQTextMessage( );
		try {
			message.setText("E: Player 374 collected 2 coins for Team 1");
		} catch (JMSException e) {fail();}
		
		
		testObject.onMessage(message);
		
		assertTrue(mock.wroteCoins);
	}
	
	@Test
	public void testOnMessage_MessageIsSteps_ShouldWriteSteps() {
		FakeDBWriter mock = new FakeDBWriter();	
		DatabaseInsertListener testObject = new DatabaseInsertListener(mock);
		
		TextMessage message = new ActiveMQTextMessage( );
		try {
			message.setText("M: Player 374 moved 14 steps");
		} catch (JMSException e) {fail();}
		
		
		testObject.onMessage(message);
		
		assertTrue(mock.wroteSteps);
	}
	
	@Test
	public void testOnMessage_MessageIsLogin_ShouldWriteLogin() {
		FakeDBWriter mock = new FakeDBWriter();	
		DatabaseInsertListener testObject = new DatabaseInsertListener(mock);
		
		TextMessage message = new ActiveMQTextMessage( );
		try {
			message.setText("E: Player 50043 logged in for team 2051");
		} catch (JMSException e) {fail();}
		
		
		testObject.onMessage(message);
		
		assertTrue(mock.wroteLogin);
	}
	
	@Test
	public void testOnMessage_MessageIsLogout_ShouldWriteLogout() {
		FakeDBWriter mock = new FakeDBWriter();	
		DatabaseInsertListener testObject = new DatabaseInsertListener(mock);
		
		TextMessage message = new ActiveMQTextMessage( );
		try {
			message.setText("E: Player 37845 logged out");
		} catch (JMSException e) {fail();}
		
		
		testObject.onMessage(message);
		
		assertTrue(mock.wroteLogout);
	}
	
	@Test
	public void testOnMessage_MessageIsNotTypeOfTextMessage_ShouldNotTouchWriter() {
		FakeDBWriter mock = new FakeDBWriter();	
		DatabaseInsertListener testObject = new DatabaseInsertListener(mock);
		
		Message message = new ActiveMQBytesMessage( );
		
		testObject.onMessage(message);
		
		assertFalse(mock.isTouched);
		
	}
	
	@Test
	public void testOnMessage_MessageIsBadMessage_ShouldNotTouchWriter() {
		FakeDBWriter mock = new FakeDBWriter();	
		DatabaseInsertListener testObject = new DatabaseInsertListener(mock);
		
		TextMessage message = new ActiveMQTextMessage( );
		try {
			message.setText("Invalid Format");
		} catch (JMSException e) {fail();}
		
		
		testObject.onMessage(message);
		
		assertFalse(mock.isTouched);
	}
}
