package ch.helvetia.jax2011.web;

import java.io.Serializable;

import javax.enterprise.context.Conversation;
import javax.inject.Inject;

import ch.helvetia.jax2011.common.stereotypes.Action;

/**
 * Action for navigation - end current conversation and redirect to new view
 */
@Action
public class NavigationAction implements Serializable {

	@Inject
	private Conversation conversation;

	public String goToHome() {
		conversation.end();
		return "home.xhtml?faces-redirect=true";
	}

	public String goToCreateTodo() {
		conversation.end();
		return "createTodo.xhtml?faces-redirect=true";
	}

	public String goToCreateTag() {
		conversation.end();
		return "createTag.xhtml?faces-redirect=true";
	}

}
