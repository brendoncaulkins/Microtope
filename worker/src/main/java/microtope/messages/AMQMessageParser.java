package microtope.messages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Date;
import java.util.regex.Pattern;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

public abstract class AMQMessageParser {
	private static Logger logger = LogManager.getLogger(AMQMessageParser.class);
	
	private static final Pattern loginPattern = Pattern.compile("E: Player (\\d+) logged in for team (\\d+)");
	private static final Pattern logoutPattern = Pattern.compile("E: Player (\\d+) logged out");
	private static final Pattern coinPattern = Pattern.compile("E: Player (\\d+) collected (\\d+) coins for Team (\\d+)");
	private static final Pattern stepPattern = Pattern.compile("M: Player (\\d+) moved (\\d+) steps");
	
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
	
		var loginMatcher = loginPattern.matcher(msg);
		if(loginMatcher.matches()) {
			int player_id = Integer.parseInt(loginMatcher.group(1));
			int team_id = Integer.parseInt(loginMatcher.group(2));
			return new LoginMessage(player_id,team_id);
		}
		
		var logoutMatcher = logoutPattern.matcher(msg);
		if(logoutMatcher.matches()) {
			int player_id = Integer.parseInt(logoutMatcher.group(1));
			return new LogoutMessage(player_id);
		}
		
		var stepMatcher = stepPattern.matcher(msg);
		if(stepMatcher.matches()) {
			int player_id = Integer.parseInt(stepMatcher.group(1));
			int steps = Integer.parseInt(stepMatcher.group(2));
			return new StepMessage(player_id,steps);
		}
		
		var coinMatcher = coinPattern.matcher(msg);
		if(coinMatcher.matches()) {
			int player_id = Integer.parseInt(coinMatcher.group(1));
			int coins = Integer.parseInt(coinMatcher.group(2));
			return new CoinMessage(player_id,coins);
		}
		
		return new BadMessage();
	}
		
}
