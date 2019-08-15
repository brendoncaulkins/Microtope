package microtope.messages;

public class StepMessage implements PlayerRelatedMessage {
	private final int steps;
	private final int player;
	
	public StepMessage(int player, int steps) {
		this.player=player;
		this.steps=steps;
	}

	public int getSteps() {
		return steps;
	}

	public int getPlayer() {
		return player;
	}
}
