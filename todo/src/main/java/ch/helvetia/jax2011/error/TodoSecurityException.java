package ch.helvetia.jax2011.error;

/**
 * Todo App Security Exception
 */
public class TodoSecurityException extends RuntimeException {

	public TodoSecurityException() {
		super();
	}

	public TodoSecurityException(String message, Throwable cause) {
		super(message, cause);
	}

	public TodoSecurityException(String message) {
		super(message);
	}

	public TodoSecurityException(Throwable cause) {
		super(cause);
	}

}
