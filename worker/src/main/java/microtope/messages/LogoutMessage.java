package microtope.messages;

public class LogoutMessage extends AMQBaseMessage implements PlayerRelatedMessage {
	
	private final int player_id;
	
	public LogoutMessage(int player_id) {
		this.player_id=player_id;
	}
	
	public int getPlayer_Id() {
		return player_id;
	}
	
	@Override
	public boolean equals(Object o) {
		if (!(o instanceof LogoutMessage)) return false;
		var otherParsed = (LogoutMessage) o;
		
		return otherParsed.getPlayer_Id() == getPlayer_Id();
	}
	
}
