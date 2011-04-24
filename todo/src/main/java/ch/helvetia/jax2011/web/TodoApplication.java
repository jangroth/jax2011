package ch.helvetia.jax2011.web;

import javax.ws.rs.core.Application;

import org.jboss.seam.rest.exceptions.ExceptionMapping;

import ch.helvetia.jax2011.error.RestAnnotationException;

@ExceptionMapping.List({ @ExceptionMapping(exceptionType = RestAnnotationException.class, status = 500, message = "REST annotation-based exception handling test.") })
public class TodoApplication extends Application {

}
