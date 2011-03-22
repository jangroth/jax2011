package ch.helvetia.jax2011.web;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import ch.helvetia.jax2011.boundary.CreateTodoTask;
import ch.helvetia.jax2011.common.stereotypes.Action;
import ch.helvetia.jax2011.entity.Todo;

/**
 * Action to create a new todo-item
 */
@Action
public class CreateTodoAction implements Serializable {

	@Inject
	private CreateTodoTask task;

	// TODO: introduce seam3 view-action
	public void init() {
		if (!FacesContext.getCurrentInstance().isPostback()) {
			task.createTodo();
		}
	}

	public String save() {
		task.saveTodo();
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Todo created",
						"Todo sucessfully created"));
		return "/home.xhtml?faces-redirect=true";
	}

	public Todo getTodo() {
		return task.getTodo();
	}
}
