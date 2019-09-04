package microtope.worker;

import static org.junit.jupiter.api.Assertions.*;

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
	void testConstructor_validActiveMQConfig_ButNoConnection_shouldThrowJMSException() {
		String[] testArgs= new String[] {"Adress","1005","Queue","User","Pwd"};
		
		ActiveMQConfig conf = ActiveMQConfig.createActiveMQConfigFromArgs(testArgs);
		
		assertThrows(JMSException.class,
				() -> {new AMQMessageReciever(conf);});
	}

}
