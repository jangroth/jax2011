package ch.helvetia.jax2011.web;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.primefaces.event.DateSelectEvent;

import ch.helvetia.jax2011.boundary.ListTodosTask;
import ch.helvetia.jax2011.common.stereotypes.Action;
import ch.helvetia.jax2011.entity.Todo;

/**
 * Action to create a new todo-item.
 */
@Action
public class ListTodosAction implements Serializable {

	@Inject
	private ListTodosTask task;

	private Date callDate = new Date();

	// TODO: introduce seam3 view-action
	public void init() {
		if (!FacesContext.getCurrentInstance().isPostback()) {
			task.findAllTodos(callDate);
		}
	}

	public void dateChanged(DateSelectEvent e) {
		callDate = e.getDate();
		task.findAllTodos(callDate);
	}

	//
	// getter & setter
	//

	public Date getCallDate() {
		return callDate;
	}

	public void setCallDate(Date callDate) {
		this.callDate = callDate;
	}

	public List<Todo> getTodos() {
		return task.getTodos();
	}

}
