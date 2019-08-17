package microtope.messages;

import java.util.Date;

public class StepMessage implements PlayerRelatedMessage,AMQMessage {
	private final int steps;
	private final int player;
	private Date timestamp =new Date(0) ;
	
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
	
	public void setTimeStamp(Date date) {
		this.timestamp=date;
	}

	public Date getTimeStamp() {
		return timestamp;
	}
	
	@Override
	public boolean equals(Object o) {
		if (!(o instanceof StepMessage)) 
			return false;
		var otherParsed = (StepMessage) o;
		
		return otherParsed.getPlayer() == getPlayer()
				&& otherParsed.getSteps() == getSteps();
	}
}
