package microtope.pulser;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import microtope.config.ActiveMQConfig;

class AMQMessageSenderTests {

	@Test
	void testConstructor_withEmptyAMQConfig_shouldThrowError() {
		ActiveMQConfig empty = ActiveMQConfig.emptyConfig();
		
		assertThrows(IllegalArgumentException.class,
				() -> new AMQMessageSender(empty));
	}

}
