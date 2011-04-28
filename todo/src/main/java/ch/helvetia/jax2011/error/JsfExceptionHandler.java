package ch.helvetia.jax2011.error;

import java.util.Date;

import javax.faces.application.NavigationHandler;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.jboss.seam.conversation.spi.SeamConversationContext;
import org.jboss.seam.exception.control.CaughtException;
import org.jboss.seam.exception.control.Handles;
import org.jboss.seam.exception.control.HandlesExceptions;
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
	ExternalContext externalContext;

	@Inject
	HttpServletRequest request;

	@Inject
	SeamConversationContext<HttpServletRequest> conversationContext;

	@Inject
	ErrorInfo errorInfo;

	@Inject
	private Identity identity;

	public void handleException(@Handles @Faces CaughtException<Throwable> event) {
		conversationContext.associate(request).activate(null);

		errorInfo.setHandledException(new HandledException(event.getException()));
		errorInfo.setStacktrace(printHtmlStackTrace(event.getException()));
		errorInfo.setUser(identity.getUser().getId());
		errorInfo.setDate(new Date());

		navigationHandler.handleNavigation(facesContext, null, ERROR_PAGE + "?faces-redirect=true");
		facesContext.renderResponse();

		event.handled();
	}

	// TODO Security risk! Use HTML escaping.
	private String printHtmlStackTrace(Throwable t) {
		StringBuilder stackTrace = new StringBuilder();
		stackTrace.append("<span>");
		stackTrace.append(t);
		stackTrace.append("</span>");
		for (StackTraceElement element : t.getStackTrace()) {
			stackTrace.append("<br/>");
			stackTrace.append("<span style=\"padding-left: 20px;\">");
			stackTrace.append("at " + element);
			stackTrace.append("</span>");
		}
		for (Throwable cause = t.getCause(); cause != null; cause = cause.getCause()) {
			stackTrace.append("<br/>");
			stackTrace.append("<span>");
			stackTrace.append("Caused by: " + cause);
			stackTrace.append("</span>");
			for (StackTraceElement element : cause.getStackTrace()) {
				stackTrace.append("<br/>");
				stackTrace.append("<span style=\"padding-left: 20px;\">");
				stackTrace.append("at " + element);
				stackTrace.append("</span>");
			}
		}
		return stackTrace.toString();
	}

}
