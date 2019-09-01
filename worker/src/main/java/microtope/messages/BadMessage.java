package microtope.messages;

import java.util.Date;

public class BadMessage implements AMQMessage {
	
	@Override
	public boolean equals(Object o) {
		return (o instanceof BadMessage) ? true : false;
	}

	public void setTimeStamp(Date date) {}

	public Date getTimeStamp() {return null;}
}
