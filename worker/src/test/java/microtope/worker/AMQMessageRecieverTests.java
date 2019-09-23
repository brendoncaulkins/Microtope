package microtope.worker;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import javax.jms.JMSException;

import org.junit.jupiter.api.Test;

import microtope.config.ActiveMqConfiguration;

class AMQMessageRecieverTests {

	@Test
	void testConstructor_EmptyAMQConfig_ShouldThrowIllegalArgumentException() {
		ActiveMqConfiguration conf = ActiveMqConfiguration.emptyConfig();
		
		assertThrows(IllegalArgumentException.class,
				() -> {new ActiveMqMessageReciever(conf);});
	}
	
	@Test
	void testConstructor_validActiveMQConfig_ButNoConnection_shouldBeBuild() {
		var testReciever = new ActiveMqMessageReciever(MessageRecieverHelpers.validConf());
		return;
	}
	
	@Test
	void testCreateConnectionFromConfig_validActiveMQConfig_ButNoConnection_shouldThrowJMSException() {
		var testReciever = new ActiveMqMessageReciever(MessageRecieverHelpers.validConf());

		assertThrows(JMSException.class,
				() -> testReciever.createConnectionFromConfig());
	}
	
	@Test
	void testOpen_validActiveMQConfig_ButNoConnection_shouldThrowJMSException() {
		var testReciever = new ActiveMqMessageReciever(MessageRecieverHelpers.validConf());

		assertThrows(JMSException.class,
				() -> testReciever.open(testReciever.createConnectionFromConfig()));
	}
	
	@Test
	void testClose_WasNeverOpen_ShouldNotThrowException() {
		try {
			var testReciever = new ActiveMqMessageReciever(MessageRecieverHelpers.validConf());
			testReciever.close();
			return;
		}
		catch (IOException e) {
			fail();
		}
	}
	

	@Test
	void testRegisterMessageListener_WasNeverOpen_ShouldNotThrowException() {
		var testReciever = new ActiveMqMessageReciever(MessageRecieverHelpers.validConf());
		
		var fakeListener = new FakeMessageListener();
		testReciever.registerMessageListener(fakeListener);
		
		return;
	}
	
}
