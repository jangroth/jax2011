package ch.helvetia.jax2011.error;

/**
 * Exception for testing the Seam Catch integration in REST
 */
public class RestCatchException extends TodoException {

	public RestCatchException() {
		super();
	}

	public RestCatchException(String message, Throwable cause) {
		super(message, cause);
	}

	public RestCatchException(String message) {
		super(message);
	}

	public RestCatchException(Throwable cause) {
		super(cause);
	}

}
