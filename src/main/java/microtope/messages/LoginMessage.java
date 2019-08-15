package microtope.messages;

public class LoginMessage implements PlayerRelatedMessage {
	private final int player;
	
	public LoginMessage(int player) {
		this.player=player;
	}
	
	public int getPlayer() {
		return player;
	}
}
