package ch.helvetia.jax2011.web;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import ch.helvetia.jax2011.common.stereotypes.Action;
import ch.helvetia.jax2011.security.Credentials;
import ch.helvetia.jax2011.security.Identity;

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
			facesContext.addMessage(null, new FacesMessage(
					FacesMessage.SEVERITY_INFO, "login successful", "welcome "
							+ credentials.getUsername()));
			facesContext.getExternalContext().getFlash().setKeepMessages(true);
			return "home.xhtml?faces-redirect=true";
		} else {
			facesContext.addMessage(null, new FacesMessage(
					FacesMessage.SEVERITY_ERROR, "Login failed",
					"login failed for username '" + credentials.getUsername()
							+ "'."));
			return null;
		}
	}

}
