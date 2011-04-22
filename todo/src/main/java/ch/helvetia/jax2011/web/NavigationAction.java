package ch.helvetia.jax2011.web;

import java.io.Serializable;

import javax.enterprise.context.Conversation;
import javax.inject.Inject;

import ch.helvetia.jax2011.common.stereotypes.Action;
import ch.helvetia.jax2011.security.Identity;

/**
 * Action for navigation - end current conversation and redirect to new view
 */
@Action
public class NavigationAction implements Serializable {

	@Inject
	private Conversation conversation;

	@Inject
	private Identity identity;

	public String goToHome() {
		endConversation();
		return "home.xhtml?faces-redirect=true";
	}

	public String goToCreateTodo() {
		endConversation();
		return "createTodo.xhtml?faces-redirect=true";
	}

	public String goToEditTodo() {
		endConversation();
		return "editTodo.xhtml?faces-redirect=true&IncludeViewParams=true";
	}

	public String goToCreateTag() {
		endConversation();
		return "createTag.xhtml?faces-redirect=true";
	}

	public String goToRegisterUser() {
		endConversation();
		return "registerUser.xhtml?faces-redirect=true";
	}

	public void logout() {
		endConversation();
		identity.logout();
	}

	private void endConversation() {
		if (!conversation.isTransient()) {
			conversation.end();
		}
	}
}
