package microtope.worker;

import java.io.Closeable;
import java.util.Optional;

import javax.jms.TextMessage;

public interface MessageReciever extends Closeable{

	Optional<TextMessage> readMessage();

}