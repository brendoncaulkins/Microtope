package microtope.worker;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DebugListener implements MessageListener{
	private static Logger logger = LogManager.getLogger(DebugListener.class);

	public void onMessage(Message message) {
		try {
			if (message instanceof TextMessage) {
	            TextMessage textMessage = (TextMessage) message;
	            logger.info("Received message '" + textMessage.getText() + "'");
	        }
			else logger.warn("recieved Message but it is no TextMessage");
		} catch (JMSException e) {
			logger.error(e);
		}
	}
}
