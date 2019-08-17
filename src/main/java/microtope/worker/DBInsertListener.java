package microtope.worker;

import javax.jms.Message;
import javax.jms.MessageListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import microtope.messages.AMQMessage;
import microtope.messages.AMQMessageParser;
import microtope.messages.BadMessage;
import microtope.messages.CoinMessage;
import microtope.messages.StepMessage;

public class DBInsertListener implements MessageListener {

	private static Logger logger = LogManager.getLogger(DBInsertListener.class);
	
	DBWriter writer;
	
	public DBInsertListener(DBWriter writer) {
		this.writer = writer;
	}
	
	@Override
	public void onMessage(Message message) {
		AMQMessage msg = AMQMessageParser.parseJMSMessage(message);
		
		if(msg instanceof BadMessage) {
			logger.debug("Recieved bad Message - not doing anything");
		}
		else if (msg instanceof StepMessage) {
			StepMessage msgParsed = (StepMessage) msg;
			writer.writeSteps(msgParsed);
		}
		else if (msg instanceof CoinMessage) {
			CoinMessage msgParsed = (CoinMessage) msg;
			writer.writeCoins(msgParsed);
		}
		else {
			logger.debug("Recieved Unhandeled Message Class!");
		}
		
	}

}
