package microtope.pulser;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class MessageSenderIntegrationTests {

	ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("vm://localhost?broker.persistent=false");

	@Tag("Integration")
	@Test
	void testVMSettings() throws JMSException {
		// This is a simple test to see if i can open connections, create sessions, create producers ...
		var connection = connectionFactory.createConnection();

        connection.start();
        
        var session = connection.createSession(false,
                Session.AUTO_ACKNOWLEDGE);
        
        Destination destination = session.createQueue("VMQUeue"); 
        var producer = session.createProducer(destination);
        
        producer.send(session.createTextMessage("Hola"));
        
        connection.close();
	}

	
	@Tag("Integration")
	@Test
	void testOpen_withVMConnection_shouldWork(){
			try {
				AMQMessageSender sender= new AMQMessageSender(AMQHelpers.validConf());
				
				var vmConnection = connectionFactory.createConnection();
				
				sender.open(vmConnection);
				
				return;
			} catch (JMSException e) {
				fail();
			} catch (IOException e) {
				fail();
			}
	}
	

	@Tag("Integration")
	@Test
	void testOpenAndCloseConnection_withVMConnection_shouldWork(){
			try {
				AMQMessageSender sender= new AMQMessageSender(AMQHelpers.validConf());
				
				var vmConnection = connectionFactory.createConnection();
				
				sender.open(vmConnection);
				
				sender.close();
				return;
			} catch (JMSException e) {
				fail();
			} catch (IOException e) {
				fail();
			}
	}
	


	@Tag("Integration")
	@Test
	void testOpenAndSendMessage_withVMConnection_shouldWork(){
			try {
				AMQMessageSender sender= new AMQMessageSender(AMQHelpers.validConf());
				
				var vmConnection = connectionFactory.createConnection();
				
				sender.open(vmConnection);
				
				sender.sendMessage("Hello World!");
				return;
			} catch (JMSException e) {
				fail();
			} catch (IOException e) {
				fail();
			}
	}

	@Tag("Integration")
	@Test
	void testOpenAndSendMessageAndClose_withVMConnection_shouldWork(){
			try {
				AMQMessageSender sender= new AMQMessageSender(AMQHelpers.validConf());
				
				var vmConnection = connectionFactory.createConnection();
				
				sender.open(vmConnection);
				
				sender.sendMessage("Hello World!");

				sender.close();
				
				return;
			} catch (JMSException e) {
				fail();
			} catch (IOException e) {
				fail();
			}
	}
	
}
