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
 * User-Task to create a new todo.
 */
@UserTask
@Stateful
@TransactionAttribute(TransactionAttributeType.NEVER)
public class CreateTodoTask implements Serializable {

	@Inject
	private Conversation conversation;

	@Inject
	private TodoService todoService;

	private Todo todo;

	public void createTodo() {
		todo = todoService.createNewTodo();
		conversation.begin();
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void saveTodo() {
		todoService.saveTodo(todo);
		conversation.end();
	}

	public Todo getTodo() {
		return todo;
	}

}
