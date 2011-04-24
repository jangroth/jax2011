package ch.helvetia.jax2011.error;

/**
 * Exception for testing the xml-based exception handling in Seam REST
 */
public class RestXmlException extends TodoException {

	public RestXmlException() {
		super();
	}

	public RestXmlException(String message, Throwable cause) {
		super(message, cause);
	}

	public RestXmlException(String message) {
		super(message);
	}

	public RestXmlException(Throwable cause) {
		super(cause);
	}

}
