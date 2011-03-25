package ch.helvetia.jax2011.boundary;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.enterprise.context.Conversation;
import javax.inject.Inject;

import ch.helvetia.jax2011.common.stereotypes.UserTask;
import ch.helvetia.jax2011.control.TodoService;
import ch.helvetia.jax2011.entity.Todo;

/**
 * User-Task to list todos.
 */
@UserTask
@Stateful
@TransactionAttribute(TransactionAttributeType.NEVER)
public class ListTodosTask implements Serializable {

	@Inject
	private Conversation conversation;

	@Inject
	private TodoService todoService;

	private List<Todo> todos;

	public void findAllTodos() {
		todos = todoService.findAllTodos();
	}

	public List<Todo> getTodos() {
		return todos;
	}

}
