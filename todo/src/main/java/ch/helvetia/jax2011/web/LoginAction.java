package ch.helvetia.jax2011.web;

import java.io.Serializable;

import javax.enterprise.event.Observes;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.jboss.seam.security.Credentials;
import org.jboss.seam.security.Identity;
import org.jboss.seam.security.events.LoggedInEvent;
import org.jboss.seam.security.events.LoginFailedEvent;
import org.jboss.seam.servlet.event.Initialized;
import org.jboss.seam.servlet.http.HttpServletRequestContext;

import ch.helvetia.jax2011.common.stereotypes.Action;
import ch.helvetia.jax2011.util.MessageHelper;

/**
 * Action to login
 */
@Action
public class LoginAction implements Serializable {

	@Inject
	private Credentials credentials;

	@Inject
	private Identity identity;

	// TODO Use Seam 3
	public String login() {
		identity.login();
		FacesContext facesContext = FacesContext.getCurrentInstance();
		if (identity.isLoggedIn()) {
			facesContext.addMessage(null, MessageHelper.createMessageFromKey(
					FacesMessage.SEVERITY_INFO, "loginWelcome",
					credentials.getUsername()));
			facesContext.getExternalContext().getFlash().setKeepMessages(true);
			return "home.xhtml?faces-redirect=true";
		} else {
			facesContext.addMessage(null, MessageHelper.createMessageFromKey(
					FacesMessage.SEVERITY_ERROR, "loginFailed",
					credentials.getUsername()));
			return null;
		}
	}

}
