package ch.helvetia.jax2011.error;

/**
 * Exception for testing the annotation-based exception handling in Seam REST
 */
public class RestAnnotationException extends TodoException {

	public RestAnnotationException() {
		super();
	}

	public RestAnnotationException(String message, Throwable cause) {
		super(message, cause);
	}

	public RestAnnotationException(String message) {
		super(message);
	}

	public RestAnnotationException(Throwable cause) {
		super(cause);
	}

}
