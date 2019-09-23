package microtope.worker;

import java.io.Closeable;
import javax.jms.MessageListener;

public interface MessageReciever extends Closeable {

	public void registerMessageListener(MessageListener msglst);
	
}