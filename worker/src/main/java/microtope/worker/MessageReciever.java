package microtope.worker;

import java.io.Closeable;
import javax.jms.MessageListener;

public interface MessageReciever extends Closeable {

	void registerMessageListener(MessageListener msglst);
	
}