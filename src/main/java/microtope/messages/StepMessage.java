package microtope.messages;

import java.util.Date;

public class StepMessage implements PlayerRelatedMessage,AMQMessage {
	private final int steps;
	private final int player;
	private Date timestamp =new Date() ;
	
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
	public boolean equals(Object obj) {
		if (!(obj instanceof StepMessage)) return false;
		var otherParsed = (StepMessage) obj;
		
		return otherParsed.getPlayer() == getPlayer() 
				&& otherParsed.getSteps() == getSteps()
				&& otherParsed.getTimeStamp().equals(getTimeStamp());
	}
}
