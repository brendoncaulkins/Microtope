package microtope.messages;

import java.util.Date;

public class AMQBaseMessage implements AMQMessage {

	protected Date timestamp = new Date(0);

	public void setTimeStamp(Date date) {
		this.timestamp=date;
	}

	public Date getTimeStamp() {
		return timestamp;
	}
}
