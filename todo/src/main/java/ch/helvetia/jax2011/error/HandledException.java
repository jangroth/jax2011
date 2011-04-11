package ch.helvetia.jax2011.error;

/**
 * Marks an exception as handled
 */
public class HandledException extends RuntimeException {

	public HandledException() {
		super();
	}

	public HandledException(String message, Throwable cause) {
		super(message, cause);
	}

	public HandledException(String message) {
		super(message);
	}

	public HandledException(Throwable cause) {
		super(cause);
	}

}
