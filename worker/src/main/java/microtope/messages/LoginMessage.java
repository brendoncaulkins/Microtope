package microtope.messages;

import java.util.Date;

public class LoginMessage implements PlayerRelatedMessage,AMQMessage {
	private final int player_id;
	private final int team_id;

	private Date timestamp = new Date(0);
	
	public LoginMessage(int player,int team) {
		this.player_id=player;
		this.team_id=team;
	}
	
	public int getPlayer_Id() {
		return player_id;
	}
	public int getTeam_Id() {
		return team_id;
	}
	
	public void setTimeStamp(Date date) {
		this.timestamp=date;
	}

	public Date getTimeStamp() {
		return timestamp;
	}
	
	@Override
	public boolean equals(Object o) {
		if (!(o instanceof LoginMessage)) return false;
		var otherParsed = (LoginMessage) o;
		
		return otherParsed.getPlayer_Id() == getPlayer_Id() 
				&& otherParsed.getTeam_Id() == getTeam_Id();
	}
}
