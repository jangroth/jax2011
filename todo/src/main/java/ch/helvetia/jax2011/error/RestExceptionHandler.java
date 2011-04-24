package ch.helvetia.jax2011.error;

import javax.inject.Inject;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.jboss.seam.exception.control.CaughtException;
import org.jboss.seam.exception.control.Handles;
import org.jboss.seam.exception.control.HandlesExceptions;
import org.jboss.seam.rest.exceptions.RestRequest;
import org.jboss.seam.rest.exceptions.RestResource;

@HandlesExceptions
public class RestExceptionHandler {

	@Inject
	@RestResource
	ResponseBuilder builder;

	public void handleException(@Handles @RestRequest CaughtException<RestCatchException> event) {
		builder.status(500).entity("<error>REST catch exception handling test.</error>");
		event.handled();
	}

}
