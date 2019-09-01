package microtope.messages;

import java.util.Date;

public class LogoutMessage implements PlayerRelatedMessage,AMQMessage {
	
	private final int player_id;
	private Date timestamp =new Date(0) ;
	
	public LogoutMessage(int player_id) {
		this.player_id=player_id;
	}
	
	public int getPlayer_Id() {
		return player_id;
	}
	
	public void setTimeStamp(Date date) {
		this.timestamp=date;
	}

	public Date getTimeStamp() {
		return timestamp;
	}
	
	@Override
	public boolean equals(Object o) {
		if (!(o instanceof LogoutMessage)) return false;
		var otherParsed = (LogoutMessage) o;
		
		return otherParsed.getPlayer_Id() == getPlayer_Id();
	}
	
}
