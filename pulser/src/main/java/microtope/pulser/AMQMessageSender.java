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
	ActiveMQConfig amqConfig;
	
	private static Logger logger = LogManager.getLogger(AMQMessageSender.class);

	
	public AMQMessageSender(ActiveMQConfig amqConfig) throws JMSException {
		if(amqConfig.isEmpty()) {
			logger.error( "Sender Constructor recieved bad AMQConfig" );
			throw new IllegalArgumentException( "AMQConfig was empty" );
		}
		this.amqConfig=amqConfig;
    }
	
	public void open(Connection connection) throws IOException, JMSException{
		this.connection=connection;
        connection.start();
        
        //Creating a non transactional session to send/receive JMS message.
        session = connection.createSession(false,
                Session.AUTO_ACKNOWLEDGE);
        
        //Destination represents here our queue on the AMQ-Server. 
        //The queue will be created automatically on the server.
        Destination destination = session.createQueue(amqConfig.queue_to_connect); 
        
        // MessageProducer is used for sending messages to the queue.
        producer = session.createProducer(destination);
                
        logger.info( "Opened the sender successfully - connection, session and producer are running" );       
	}
	
	public Connection createConnectionFromConfig() throws JMSException {
		var url = String.format( "tcp://%s:%s" , amqConfig.adress_to_connect, amqConfig.port_to_connect);
		logger.info( "Sender connecting to " + url );
		
        // Getting JMS connection from the server
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
        
        return connectionFactory.createConnection(amqConfig.user_to_connect,amqConfig.pwd_to_connect);
	}
	
	@Override
	public void sendMessage(String msg) throws JMSException {
		if(connection!=null || session!=null || producer!=null) {
			TextMessage message = session
					.createTextMessage(msg);
	        producer.send(message);	
		}
    }

	@Override
	public void close() throws IOException {
        try {
			connection.close();
			logger.info( "Closed Sender-Connection" );
		} 
        catch (NullPointerException ne) {
        	logger.warn("Tried closing Connection to AMQ - was never opened!");
        }
        catch (JMSException e) {
			logger.warn(e);
		}
	}
	
}
