package microtope.pulser;

import java.io.Closeable;
import java.io.IOException;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import microtope.config.ActiveMQConfig;

public class AMQMessageSender implements Closeable, MessageSender {
	
	Connection connection;
	Session session;
	MessageProducer producer;
	
	private static Logger logger = LogManager.getLogger(AMQMessageSender.class);

	
	public AMQMessageSender(ActiveMQConfig amqConfig) throws JMSException {
		if(amqConfig.isEmpty()) {
			logger.error( "Sender Constructor recieved bad AMQConfig" );
			throw new IllegalArgumentException( "AMQConfig was empty" );
		}
		
		var url = String.format( "tcp://%s:%s" , amqConfig.adress_to_connect, amqConfig.port_to_connect);
		logger.info( "Sender connecting to " + url );
		
        // Getting JMS connection from the server and starting it
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
        
        connection =  connectionFactory.createConnection(amqConfig.user_to_connect,amqConfig.pwd_to_connect);
        
        connection.start();
        logger.debug( "Connection opened" ); 
        
        //Creating a non transactional session to send/receive JMS message.
        session = connection.createSession(false,
                Session.AUTO_ACKNOWLEDGE);
        
        
        logger.debug( "Created Session" ); 
         
        //Destination represents here our queue on the AMQ-Server. 
        //The queue will be created automatically on the server.
        Destination destination = session.createQueue(amqConfig.queue_to_connect); 
        
        logger.debug( "Created Queue" ); 
        
        
        // MessageProducer is used for sending messages to the queue.
        producer = session.createProducer(destination);
        
        logger.debug( "Created Producer" ); 
        
        logger.info( "Build a new Sender" );       
    }
	
	
	@Override
	public void sendMessage(String msg) throws JMSException {
		 
        // We will send a small text message saying 'Hello World!!!' 
        TextMessage message = session
                .createTextMessage(msg);
         
        // Here we are sending our message!
        producer.send(message);
        
        logger.debug( "Send a new message!" );
	}

	@Override
	public void close() throws IOException {
        try {
        	logger.debug( "closing Sender-Connection ..." );
			connection.close();
			logger.info( "Closed Sender-Connection" );
		} catch (JMSException e) {
			logger.debug(e);
		}
	}
	
}
