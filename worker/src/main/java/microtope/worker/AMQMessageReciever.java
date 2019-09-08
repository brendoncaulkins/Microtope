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

import microtope.config.ActiveMQConfig;
 
public class AMQMessageReciever implements Closeable, MessageReciever{
	
	private static Logger logger = LogManager.getLogger(AMQMessageReciever.class);

	Connection connection; 
	Session session;
	
	private ActiveMQConfig amqConfig;
	private MessageConsumer consumer;

    public AMQMessageReciever (ActiveMQConfig amqconf) throws JMSException {
        if(amqconf.isEmpty())
        	throw new IllegalArgumentException("AMQConf is Empty!");
        amqConfig=amqconf;        
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
        
        // MessageConsumer is used for receiving (consuming) messages
        consumer = session.createConsumer(destination);
        
        logger.debug("Created fully working AMQ MessageReciever");
                
        logger.info( "Opened the sender successfully - connection, session and producer are running" );       
	}
	
	@Override
	public void close() throws IOException {
		try {
			session.close();
			connection.close();
			logger.debug("Closing AMQMessageReciever");
		} catch (JMSException e) {
			logger.error(e);
		} catch (NullPointerException ne) {
			logger.warn("Tryed closing MessasgeReceiver - but was never opened!");
		}
	}

	
	public Connection createConnectionFromConfig() throws JMSException {
		var url = String.format( "tcp://%s:%s" , this.amqConfig.adress_to_connect, amqConfig.port_to_connect);
		logger.info( "Sender connecting to " + url );
		
        // Getting JMS connection from the server
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
        
        return connectionFactory.createConnection(amqConfig.user_to_connect,amqConfig.pwd_to_connect);
	}
	
	@Override
	public void registerMessageListener(MessageListener msglst) {
		if(consumer!=null)
			try {
				consumer.setMessageListener(msglst);
				logger.info("registered new MessageListener of type " + msglst.getClass().getSimpleName());
			} catch (JMSException e) {
				logger.error(e);
			}
	}   
    
}
