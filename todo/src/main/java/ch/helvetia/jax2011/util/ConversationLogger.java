package ch.helvetia.jax2011.util;

import org.jboss.seam.solder.logging.Log;
import org.jboss.seam.solder.logging.MessageLogger;
import org.jboss.seam.solder.messages.Message;

@MessageLogger
public interface ConversationLogger {

	@Log
	@Message("Conversation %s started ")
	void started(String cid);

	@Log
	@Message("Conversation stopped")
	void stopped();

}