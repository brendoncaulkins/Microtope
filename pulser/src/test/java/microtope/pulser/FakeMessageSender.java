package microtope.pulser;

import javax.jms.JMSException;

public class FakeMessageSender implements MessageSender {
	
	public boolean sendMessage=false;
	public String lastSent=null;
	
	public boolean lastMessageWasSteps=false;
	public boolean lastMessageWasCoins=false;
	
	@Override
	public void sendMessage(String msg) throws JMSException {
		sendMessage=true;
		lastSent=msg;
		
		if(msg.contains("coins")) {
			lastMessageWasSteps=false;
			lastMessageWasCoins=true;	
		}
		if(msg.contains("steps")){
			lastMessageWasSteps=true;
			lastMessageWasCoins=false;	
		}
	}

}
