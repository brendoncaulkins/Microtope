package microtope.worker;
import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;
 
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
 
public class MessageReceiver {
	
	private static Logger logger = LogManager.getLogger(MessageReceiver.class);

	Connection connection; 
	Session session;
	
    public MessageReceiver (String adress, String port, String queue, String user_to_connect, String pwd_to_connect) throws JMSException {
        
    	if(user_to_connect == null || user_to_connect.isEmpty())
    		logger.warn("recieved empty user for MessageReciever! Using anonymus user");
    	
    	if((pwd_to_connect == null || pwd_to_connect.isEmpty()) && !user_to_connect.isEmpty())
    		logger.warn("Not using anonymus user - but not having pwd!");
    	
    	var connectionURL = String.format( "tcp://%s:%s" , adress, port);
		
    	logger.info("Creating new MessageReciever with URL:" + connectionURL);
    	// Getting JMS connection from the server
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(connectionURL);
        logger.debug("Connecting as " + user_to_connect + " with pwd [REDACTED]");
        
        connection =  connectionFactory.createConnection(user_to_connect,pwd_to_connect);
        
        connection.start();
        logger.debug( "Connection opened" ); 
        
        logger.debug("Opened Connection" );
        
        Session session = connection.createSession(false,
                Session.AUTO_ACKNOWLEDGE);
 
        Destination destination = session.createQueue(queue);
        logger.debug("Opened Session and found Queue");
        // MessageConsumer is used for receiving (consuming) messages
        MessageConsumer consumer = session.createConsumer(destination);
        
        // Here we receive the message.
        Message message = consumer.receive();
        
        if (message instanceof TextMessage) {
            TextMessage textMessage = (TextMessage) message;
            logger.info("Received message '" + textMessage.getText() + "'");
        }
        connection.close();
        logger.info("closing message reciever connection");
    }
    
}
