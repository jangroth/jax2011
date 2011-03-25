package ch.helvetia.jax2011.web;

import java.io.Serializable;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.inject.Inject;

import ch.helvetia.jax2011.boundary.ListTodosTask;
import ch.helvetia.jax2011.common.stereotypes.Action;
import ch.helvetia.jax2011.entity.Todo;

/**
 * Action to create a new todo-item
 */
@Action
public class ListTodosAction implements Serializable {

	@Inject
	private ListTodosTask task;

	// TODO: introduce seam3 view-action
	public void init() {
		if (!FacesContext.getCurrentInstance().isPostback()) {
			task.findAllTodos();
		}
	}

	public List<Todo> getTodos() {
		return task.getTodos();
	}
}
