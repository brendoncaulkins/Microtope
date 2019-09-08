package microtope.worker;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import javax.jms.JMSException;

import org.junit.jupiter.api.Test;

import microtope.config.ActiveMQConfig;

class AMQMessageRecieverTests {

	@Test
	void testConstructor_EmptyAMQConfig_ShouldThrowIllegalArgumentException() {
		ActiveMQConfig conf = ActiveMQConfig.emptyConfig();
		
		assertThrows(IllegalArgumentException.class,
				() -> {new AMQMessageReciever(conf);});
	}
	
	@Test
	void testConstructor_validActiveMQConfig_ButNoConnection_shouldBeBuild() {
		try {
			var testRec = new AMQMessageReciever(MessageRecieverHelpers.validConf());
			return;
		} catch (JMSException e) {
			fail();
		}
	}
	
	@Test
	void testCreateConnectionFromConfig_validActiveMQConfig_ButNoConnection_shouldThrowJMSException() {
		try {
			var testRec = new AMQMessageReciever(MessageRecieverHelpers.validConf());

			assertThrows(JMSException.class,
					() -> testRec.createConnectionFromConfig());
		} catch (JMSException e) {
			fail();
		}
	}
	
	@Test
	void testOpen_validActiveMQConfig_ButNoConnection_shouldThrowJMSException() {
		try {
			var testRec = new AMQMessageReciever(MessageRecieverHelpers.validConf());

			assertThrows(JMSException.class,
					() -> testRec.open(testRec.createConnectionFromConfig()));
		} catch (JMSException e) {
			fail();
		}
	}
	
	@Test
	void testClose_WasNeverOpen_ShouldNotThrowException() {
		try {
			var testRec = new AMQMessageReciever(MessageRecieverHelpers.validConf());
			testRec.close();
			return;
		} catch (JMSException e) {
			fail();
		} catch (IOException e) {
			fail();
		}
	}
	

	@Test
	void testRegisterMessageListener_WasNeverOpen_ShouldNotThrowException() {
		try {
			var testRec = new AMQMessageReciever(MessageRecieverHelpers.validConf());
			
			var fakeListener = new FakeMessageListener();
			testRec.registerMessageListener(fakeListener);
			
			return;
		} catch (JMSException e) {
			fail();
		}
	}
	
}
