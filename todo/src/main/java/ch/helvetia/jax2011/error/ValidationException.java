package ch.helvetia.jax2011.error;

/**
 * Todo App Validation Exception
 */
public class ValidationException extends TodoException {

	public ValidationException() {
		super();
	}

	public ValidationException(String message, Throwable cause) {
		super(message, cause);
	}

	public ValidationException(String message) {
		super(message);
	}

	public ValidationException(Throwable cause) {
		super(cause);
	}

}
