package microtope.messages;

public class LogoutMessage implements PlayerRelatedMessage {
	
	private final int player;
		
	public LogoutMessage(int player) {
		this.player=player;
	}
	
	public int getPlayer() {
		return player;
	}
	
}
