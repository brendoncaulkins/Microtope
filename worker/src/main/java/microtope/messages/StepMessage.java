package microtope.messages;

public class StepMessage extends AMQBaseMessage  implements PlayerRelatedMessage {
	private final int steps;
	private final int player;
	
	public StepMessage(int player, int steps) {
		this.player=player;
		this.steps=steps;
	}

	public int getSteps() {
		return steps;
	}

	public int getPlayer_Id() {
		return player;
	}
	
	@Override
	public boolean equals(Object o) {
		if (!(o instanceof StepMessage)) 
			return false;
		var otherParsed = (StepMessage) o;
		
		return otherParsed.getPlayer_Id() == getPlayer_Id()
				&& otherParsed.getSteps() == getSteps();
	}
}
