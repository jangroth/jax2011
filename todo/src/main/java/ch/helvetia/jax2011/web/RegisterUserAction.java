package ch.helvetia.jax2011.web;

import java.io.Serializable;

import javax.enterprise.context.Conversation;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.jboss.seam.security.Credentials;
import org.jboss.seam.security.Identity;
import org.picketlink.idm.impl.api.PasswordCredential;

import ch.helvetia.jax2011.boundary.RegisterUserTask;
import ch.helvetia.jax2011.common.stereotypes.Action;
import ch.helvetia.jax2011.entity.User;
import ch.helvetia.jax2011.util.MessageHelper;

/**
 * Action to regiser a new user.
 */
@Action
public class RegisterUserAction implements Serializable {

	@Inject
	private Conversation conversation;

	@Inject
	private Credentials credentials;

	@Inject
	private Identity identity;

	@Inject
	private RegisterUserTask task;

	private String passwordRepeat;

	// TODO: introduce seam3 view-action
	public void init() {
		if (conversation.isTransient()) {
			task.createUser();
		}
	}

	public String registerUser() {
		String result = null;
		FacesContext facesContext = FacesContext.getCurrentInstance();
		if (!passwordsEqual()) {
			facesContext.addMessage(null, MessageHelper.createMessageFromKey(
					FacesMessage.SEVERITY_WARN, "passwordsDontMatch"));
		} else {
			task.saveUser();
			credentials.setUsername(task.getUser().getName());
			credentials.setCredential(new PasswordCredential(task.getUser().getPassword()));
			identity.quietLogin();
			facesContext.addMessage(null, MessageHelper.createMessageFromKey(
					FacesMessage.SEVERITY_INFO, "loginWelcome", task.getUser()
							.getName()));
			facesContext.getExternalContext().getFlash().setKeepMessages(true);
			result = "/home.xhtml?faces-redirect=true";
		}
		return result;
	}

	public String cancelRegistration() {
		task.finish();
		return "/home.xhtml?faces-redirect=true";
	}

	private boolean passwordsEqual() {
		return task.getUser().getPassword().equals(passwordRepeat.trim());
	}

	//
	// getter & setter
	//

	public String getPasswordRepeat() {
		return passwordRepeat;
	}

	public void setPasswordRepeat(String passwordRepeat) {
		this.passwordRepeat = passwordRepeat;
	}

	public User getUser() {
		return task.getUser();
	}

}
