package microtope.messages;

import java.util.Date;

public interface AmqMessage {
	
	public void setTimeStamp(Date date);
	
	public Date getTimeStamp();

}
