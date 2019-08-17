package microtope.messages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Date;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

public abstract class AMQMessageParser {
	private static Logger logger = LogManager.getLogger(AMQMessageParser.class);
	
	public static AMQMessage parseJMSMessage(Message message) {
		try {
			if (message instanceof TextMessage) {
	            TextMessage textMessage = (TextMessage) message;
	            AMQMessage toReturn = parseTextMessage(textMessage.getText());
	            toReturn.setTimeStamp(new Date(message.getJMSTimestamp()));
	            return toReturn;
	        }
			else {
				logger.warn("recieved Message but it is no TextMessage");
				return new BadMessage();
			}
		} catch (JMSException e) {
			logger.error(e);
			return new BadMessage();
		}
	}
	
	public static AMQMessage parseTextMessage(String msg) {
		logger.trace("parsing message " + msg);
		
		if(!msg.startsWith("M: ") && !msg.startsWith("E: ")) {
			logger.debug("Recieved a Message with strange format");
			return new BadMessage();
		}
		else if (msg.startsWith("M: Player ") || msg.startsWith("E: Player")) {
			try {
				String noPrefix = msg.substring(10);
				String playerNrStr = noPrefix.substring(0, 5);
				int player = Integer.parseInt(playerNrStr);
				
				String noPlayerStr = noPrefix.substring(6);
				
				if(noPlayerStr.startsWith("moved")) {
					var cleaned = noPlayerStr.substring(6);
					cleaned = cleaned.trim();
					var stepsStr = cleaned.substring(0,2);
					int steps = Integer.parseInt(stepsStr);
					
					return new StepMessage(player,steps);
				}
				else if (noPlayerStr.startsWith("collected")){
					var cleaned = noPlayerStr.substring(9);
					cleaned = cleaned.trim();
					var stepsStr = cleaned.substring(0,1);
					int steps = Integer.parseInt(stepsStr);
					
					return new CoinMessage(player,steps);
				}
				else
				{
					logger.warn("Parser recieved unknown Player-Message!");
					return new BadMessage();
				}
			} catch (NumberFormatException e) {
				logger.debug("Bad Integer Format in Message - Saving you the exception");
				return new BadMessage();
			}
			
		}
		
		return new BadMessage();
	}
	
}
