package microtope.pulser;

import javax.jms.JMSException;

public class FakeMessageSender implements MessageSender {
	
	public boolean sendMessage=false;
	public String lastSent=null;
	
	@Override
	public void sendMessage(String msg) throws JMSException {
		sendMessage=true;
		lastSent=msg;
	}

}
