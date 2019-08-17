package microtope.messages;

public abstract class AMQMessageParser {
	
	public static AMQMessage parseMessage(String msg) {
		
		return new BadMessage();
	}
	
}
