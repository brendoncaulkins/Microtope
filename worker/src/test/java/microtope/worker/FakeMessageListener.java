package microtope.worker;

import java.util.LinkedList;
import java.util.List;

import javax.jms.Message;
import javax.jms.MessageListener;

public class FakeMessageListener implements MessageListener {

	public boolean touched = false;
	public Message lastSeen;
	
	public List<Message> allSeen = new LinkedList<Message>();
	
	@Override
	public void onMessage(Message message) {
		touched=true;
		lastSeen=message;
		allSeen.add(message);
	}

}
