package ch.helvetia.jax2011.web;

import java.io.Serializable;

import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.jboss.seam.international.status.Messages;
import org.jboss.seam.international.status.builder.BundleKey;
import org.jboss.seam.security.Credentials;
import org.jboss.seam.security.Identity;

import ch.helvetia.jax2011.common.stereotypes.Action;

/**
 * Action to login
 */
@Action
public class LoginAction implements Serializable {

	@Inject
	private Credentials credentials;

	@Inject
	private Identity identity;

	@Inject
	private Messages messages;

	public String login() {
		identity.login();
		FacesContext facesContext = FacesContext.getCurrentInstance();
		if (identity.isLoggedIn()) {
			messages.info(new BundleKey("messages", "loginWelcome"),
					credentials.getUsername());
			// facesContext.addMessage(null, MessageHelper.createMessageFromKey(
			// FacesMessage.SEVERITY_INFO, "loginWelcome",
			// credentials.getUsername()));
			facesContext.getExternalContext().getFlash().setKeepMessages(true);
			return "home.xhtml?faces-redirect=true";
		} else {
			messages.warn(new BundleKey("messages", "loginFailed"),
					credentials.getUsername());
			return null;
		}
	}

}
