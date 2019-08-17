package microtope.messages;

public class BadMessage implements AMQMessage {
	
	
	@Override
	public boolean equals(Object obj) {
		return (obj instanceof BadMessage) ? true : false;
	}
}
