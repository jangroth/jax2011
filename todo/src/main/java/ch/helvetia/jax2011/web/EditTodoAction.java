package ch.helvetia.jax2011.web;

import java.io.Serializable;

import javax.enterprise.context.Conversation;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import ch.helvetia.jax2011.boundary.EditTodoTask;
import ch.helvetia.jax2011.common.stereotypes.Action;
import ch.helvetia.jax2011.entity.Todo;
import ch.helvetia.jax2011.util.MessageHelper;

/**
 * Action to edit an existing todo-item.
 */
@Action
public class EditTodoAction implements Serializable {

	@Inject
	private Conversation conversation;

	@Inject
	private EditTodoTask task;

	private Long todoId;

	// TODO: introduce seam3 view-action
	public void init() {
		if (!FacesContext.getCurrentInstance().isPostback()
				&& conversation.isTransient()) {
			task.loadTodo(todoId);
		}
	}

	public String updateTodo() {
		task.updateTodo();
		FacesContext facesContext = FacesContext.getCurrentInstance();
		facesContext.addMessage(null, MessageHelper.createMessageFromKey(
				FacesMessage.SEVERITY_INFO, "todoEdited", task.getTodo()
						.getName()));
		facesContext.getExternalContext().getFlash().setKeepMessages(true);
		task.finish();
		return "/home.xhtml?faces-redirect=true";
	}

	public String cancel() {
		task.finish();
		return "/home.xhtml?faces-redirect=true";
	}

	//
	// getter & setter
	//

	public Todo getTodo() {
		return task.getTodo();
	}

	public Long getTodoId() {
		return todoId;
	}

	public void setTodoId(Long todoId) {
		this.todoId = todoId;
	}

}
