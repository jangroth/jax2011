package ch.helvetia.jax2011.error;

import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerFactory;

import org.jboss.logging.Logger;

import ch.helvetia.jax2011.util.BeanManagerUtil;

/**
 * Factory for the TodoExceptionHandler
 * REPLACED with ch.helvetia.jax2011.error.JsfExceptionHandler
 */
public class TodoExceptionHandlerFactory extends ExceptionHandlerFactory {

	private transient final Logger log = Logger.getLogger(TodoExceptionHandlerFactory.class);

	private ExceptionHandlerFactory parent;

	private transient boolean exceptionHandlerUnavailable = false;

	public TodoExceptionHandlerFactory(ExceptionHandlerFactory parent) {
		super();
		this.parent = parent;
	}

	@Override
	public ExceptionHandler getExceptionHandler() {
		log.trace("Creating the JSF exception handler");
		if (this.exceptionHandlerUnavailable) {
			log.trace("Exception handling previously disabled");
			return parent.getExceptionHandler();
		}

		try {
			BeanManagerUtil.getBeanManager();
		} catch (Exception e) {
			log.info("Could not location BeanManager, exception handling disabled");
			this.exceptionHandlerUnavailable = true;
			return parent.getExceptionHandler();
		}

		return new TodoExceptionHandler(parent.getExceptionHandler());
	}

}
