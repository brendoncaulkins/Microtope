package microtope.messages;

public class LoginMessage extends AMQBaseMessage implements PlayerRelatedMessage {
	private final int player_id;
	private final int team_id;

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
	
	@Override
	public boolean equals(Object o) {
		if (!(o instanceof LoginMessage)) return false;
		var otherParsed = (LoginMessage) o;
		
		return otherParsed.getPlayer_Id() == getPlayer_Id() 
				&& otherParsed.getTeam_Id() == getTeam_Id();
	}
}
