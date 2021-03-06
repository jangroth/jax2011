package ch.helvetia.jax2011.boundary;

import java.io.Serializable;

import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.enterprise.context.Conversation;
import javax.inject.Inject;

import ch.helvetia.jax2011.common.stereotypes.UserTask;
import ch.helvetia.jax2011.control.TodoService;
import ch.helvetia.jax2011.entity.Todo;

/**
 * User-Task to edit an existing todo.
 */
@UserTask
@Stateful
@TransactionAttribute(TransactionAttributeType.NEVER)
public class EditTodoTask implements Serializable {

	@Inject
	private Conversation conversation;

	@Inject
	private TodoService todoService;

	private Todo todo;

	/**
	 * load todo from id
	 */
	public void loadTodo(Long id) {
		conversation.begin();
		todo = todoService.loadTodo(id);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void updateTodo() {
		todoService.updateTodo(todo);
	}

	public void finish() {
		conversation.end();
	}

	//
	// getter & setter
	//

	public Todo getTodo() {
		return todo;
	}

}
