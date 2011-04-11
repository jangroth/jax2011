package ch.helvetia.jax2011.error;

/**
 * Todo App Exception
 */
public class TodoException extends RuntimeException {

	public TodoException() {
		super();
	}

	public TodoException(String message, Throwable cause) {
		super(message, cause);
	}

	public TodoException(String message) {
		super(message);
	}

	public TodoException(Throwable cause) {
		super(cause);
	}

}
