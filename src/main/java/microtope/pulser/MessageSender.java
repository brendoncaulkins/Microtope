package microtope.pulser;

import javax.jms.JMSException;

public interface MessageSender {

	void sendMessage(String msg) throws JMSException;

}