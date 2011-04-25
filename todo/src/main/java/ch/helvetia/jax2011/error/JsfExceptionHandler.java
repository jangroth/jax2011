package ch.helvetia.jax2011.error;

import java.util.Date;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.jboss.seam.conversation.spi.SeamConversationContext;
import org.jboss.seam.exception.control.CaughtException;
import org.jboss.seam.exception.control.Handles;
import org.jboss.seam.exception.control.HandlesExceptions;
import org.jboss.seam.exception.filter.ExceptionStackOutput;
import org.jboss.seam.faces.qualifier.Faces;
import org.jboss.seam.security.Identity;

@HandlesExceptions
public class JsfExceptionHandler {

	private static final String ERROR_PAGE = "/error.xhtml";

	@Inject
	private NavigationHandler navigationHandler;

	@Inject
	FacesContext facesContext;

	@Inject
	private Flash flash;

	@Inject
	HttpServletRequest request;

	@Inject
	SeamConversationContext<HttpServletRequest> conversationContext;

	@Inject
	private Identity identity;

	public void handleException(@Handles @Faces CaughtException<Throwable> event) {
		conversationContext.associate(request).activate(null);

		ExceptionStackOutput<Throwable> stackOutput = new ExceptionStackOutput<Throwable>(event.getException());
		flash.put("handledException", event.getException());
		flash.put("stackTrace", stackOutput.printTrace());
		flash.put("user", identity.getUser().getId());
		flash.put("date", new Date());

		navigationHandler.handleNavigation(facesContext, null, ERROR_PAGE + "?" + "faces-redirect=true");
		event.handled();
	}

}
