package microtope.pulser;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import javax.jms.JMSException;

import org.junit.jupiter.api.Test;


import microtope.config.ActiveMqConfiguration;

class AMQMessageSenderTests {

	@Test
	void testConstructor_withEmptyAMQConfig_shouldThrowError() {
		ActiveMqConfiguration empty = ActiveMqConfiguration.emptyConfig();
		
		assertThrows(IllegalArgumentException.class,
				() -> new ActiveMqMessageSender(empty));
	}

	@Test
	void testConstructor_ConfigIsOk_shouldBeBuild() {
		String[] testArgs= new String[] {"Adress","1005","Queue","User","Pwd"};
		var testConf = ActiveMqConfiguration.createActiveMqConfigFromArgs(testArgs);
		
		try {
			ActiveMqMessageSender sender= new ActiveMqMessageSender(testConf);
			return;
		} catch (JMSException e) {
			fail();
		}
	}

	@Test
	void testOpen_ConfigIsOk_ButSenderIsOffline_shouldThrowJMSException() {
		try {
			ActiveMqMessageSender sender= new ActiveMqMessageSender(AMQHelpers.validConf());
			
			assertThrows(JMSException.class, () -> sender.open(sender.createConnectionFromConfig()));
		} catch (JMSException e) {
			fail();
		}
	}
	
	@Test
	void testClose_WasNeverOpened_shouldNotThrowAnyExceptions() {
		try {
			ActiveMqMessageSender sender= new ActiveMqMessageSender(AMQHelpers.validConf());
			sender.close();
			
			return;
		} catch (JMSException e) {
			fail();
		} catch (IOException e) {
			fail();
		}
	}
	
	@Test
	void testSendMessage_WasNeverOpened_shouldNotThrowAnyExceptions() {
		try {
			ActiveMqMessageSender sender= new ActiveMqMessageSender(AMQHelpers.validConf());
			sender.sendMessage("Hello World");
			
			return;
		} catch (JMSException e) {
			fail();
		}
	}
	
	
	
}
