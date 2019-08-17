package microtope.messages;

import java.util.Date;

public class LogoutMessage implements PlayerRelatedMessage,AMQMessage {
	
	private final int player;
	private Date timestamp =new Date() ;
	
	public LogoutMessage(int player) {
		this.player=player;
	}
	
	public int getPlayer() {
		return player;
	}
	
	public void setTimeStamp(Date date) {
		this.timestamp=date;
	}

	public Date getTimeStamp() {
		return timestamp;
	}
	
}
