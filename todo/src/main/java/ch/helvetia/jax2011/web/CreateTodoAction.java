package ch.helvetia.jax2011.web;

import java.io.Serializable;

import javax.enterprise.context.Conversation;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import ch.helvetia.jax2011.boundary.CreateTodoTask;
import ch.helvetia.jax2011.common.stereotypes.Action;
import ch.helvetia.jax2011.entity.Todo;
import ch.helvetia.jax2011.util.MessageHelper;

/**
 * Action to create a new todo-item
 */
@Action
public class CreateTodoAction implements Serializable {

	@Inject
	private Conversation conversation;

	@Inject
	private CreateTodoTask task;

	// TODO: introduce seam3 view-action
	public void init() {
		if (!FacesContext.getCurrentInstance().isPostback()
				&& conversation.isTransient()) {
			task.createTodo();
		}
	}

	public String createTodo() {
		task.saveTodo();
		FacesContext facesContext = FacesContext.getCurrentInstance();
		facesContext.addMessage(null, MessageHelper.createMessageFromKey(
				FacesMessage.SEVERITY_INFO, "newTodoCreated", task.getTodo()
						.getName()));
		facesContext.getExternalContext().getFlash().setKeepMessages(true);
		// TODO: investigate if this can be handled in a seam 3 way
		return "/attachTags.xhtml?faces-redirect=true";
	}

	//
	// getter & setter
	//

	public Todo getTodo() {
		return task.getTodo();
	}

}
