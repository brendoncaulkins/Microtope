package microtope.messages;

import java.util.Date;

public class LoginMessage implements PlayerRelatedMessage,AMQMessage {
	private final int player;
	
	public LoginMessage(int player) {
		this.player=player;
	}
	
	public int getPlayer() {
		return player;
	}
	
	private Date timestamp = new Date(0);
		
	public void setTimeStamp(Date date) {
		this.timestamp=date;
	}

	public Date getTimeStamp() {
		return timestamp;
	}
		
}
