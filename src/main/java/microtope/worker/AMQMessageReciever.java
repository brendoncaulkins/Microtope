package microtope.worker;
import java.io.Closeable;
import java.io.IOException;
import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.MessageListener;
 
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
 
public class AMQMessageReciever implements Closeable, MessageReciever{
	
	private static Logger logger = LogManager.getLogger(AMQMessageReciever.class);

	Connection connection; 
	Session session;
	
	private Destination destination;
	private MessageConsumer consumer;
	
    public AMQMessageReciever (String adress, String port, String queue, String user_to_connect, String pwd_to_connect) throws JMSException {
        
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
        logger.debug( "AMQ Connection opened" ); 
        
        Session session = connection.createSession(false,
                Session.AUTO_ACKNOWLEDGE);
 
        destination = session.createQueue(queue);
        logger.debug("Opened Session and found Queue");
        
        // MessageConsumer is used for receiving (consuming) messages
        consumer = session.createConsumer(destination);
        
        logger.debug("Created fully working AMQ MessageReciever");
        
    }    
   
	@Override
	public void close() throws IOException {
		try {
			session.close();
			connection.close();
			logger.debug("Closing AMQMessageReciever");
		} catch (JMSException e) {
			logger.error(e);
		}
	}

	@Override
	public void registerMessageListener(MessageListener msglst) {
		if(consumer!=null)
			try {
				consumer.setMessageListener(msglst);
				logger.info("registered new MessageListener of type " + msglst.getClass().toString());
			} catch (JMSException e) {
				logger.error(e);
			}
		logger.error("Empty Consumer!");
	}   
    
}
