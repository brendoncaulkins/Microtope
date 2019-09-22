package microtope.messages;

import java.util.Date;

public interface AMQMessage {
	
	public void setTimeStamp(Date date);
	
	public Date getTimeStamp();

}
