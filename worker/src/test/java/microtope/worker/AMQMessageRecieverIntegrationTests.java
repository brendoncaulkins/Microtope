package microtope.worker;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class AMQMessageRecieverIntegrationTests {



	@Tag("Integration")
	@Test
	void testVMSettings() throws JMSException {
		// This is a simple test to see if i can open connections, create sessions, create producers ...
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("vm://localhost?broker.persistent=false");

		var connection = connectionFactory.createConnection();

        connection.start();
        
        var session = connection.createSession(false,
                Session.AUTO_ACKNOWLEDGE);
        
        Destination destination = session.createQueue("VMQueue"); 
        var producer = session.createProducer(destination);
        
        var consumer = session.createConsumer(destination);
        
        var fakeListener = new FakeMessageListener();
        consumer.setMessageListener(fakeListener);
        
        producer.send(session.createTextMessage("Hola"));
        
        while(!fakeListener.touched) {
        	// Ooof, this feels hacky!
        }
        connection.close();
        assertTrue(fakeListener.touched);
	}
	

	@Tag("Integration")
	@Test
	void testOpenAndCloseConnection_withVMConnection_shouldWork(){
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("vm://localhost?broker.persistent=false");

			try {
				var reciever = new AMQMessageReciever(MessageRecieverHelpers.validConf());
				
				var vmConnection = connectionFactory.createConnection();
				
				reciever.open(vmConnection);
				
				reciever.close();
				return;
			} catch (JMSException e) {
				fail();
			} catch (IOException e) {
				fail();
			}
	}
	
	@Tag("Integration")
	@Test
	void testOpenAndRegisterFakeListenerAndClose_withVMConnection_shouldWork(){
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("vm://localhost?broker.persistent=false");

			try {
				var reciever = new AMQMessageReciever(MessageRecieverHelpers.validConf());
				
				var vmConnection = connectionFactory.createConnection();
				
				reciever.open(vmConnection);

				var fake = new FakeMessageListener();
				reciever.registerMessageListener(fake);
				
				reciever.close();
				return;
			} catch (JMSException e) {
				fail();
			} catch (IOException e) {
				fail();
			}
	}
	
	@Tag("Integration")
	@Test
	void testFullCycle_sendOneMessage_ListenerShouldBeTouched(){
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("vm://localhost?broker.persistent=false");

			try {
				// Setup Side to test
				var reciever = new AMQMessageReciever(MessageRecieverHelpers.validConf());			
				var vmConnection = connectionFactory.createConnection();
				reciever.open(vmConnection);
				var fake = new FakeMessageListener();
				reciever.registerMessageListener(fake);
				

		        var session = vmConnection.createSession(false,
		                Session.AUTO_ACKNOWLEDGE);
		        
		        Destination destination = session.createQueue(MessageRecieverHelpers.validConf().queue_to_connect); 
		        var producer = session.createProducer(destination);
		        producer.send(session.createTextMessage("Hello World!"));
				
		        while(!fake.touched) {
		        	
		        }
		        
				reciever.close();
				assertTrue(fake.touched);
				
			} catch (JMSException e) {
				fail();
			} catch (IOException e) {
				fail();
			}
	}
	
}
