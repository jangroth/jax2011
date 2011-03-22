package ch.helvetia.jax2011.control;

import java.util.Date;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import ch.helvetia.jax2011.common.stereotypes.Service;
import ch.helvetia.jax2011.db.TodoDb;
import ch.helvetia.jax2011.entity.Todo;

/**
 * Service for Todos.
 */
@Service
public class TodoService {

	@Inject
	@TodoDb
	private EntityManager entityManager;

	public Todo createNewTodo() {
		Todo result = new Todo();
		result.setDueDate(new Date());
		return result;
	}

	public void saveTodo(Todo todo) {
		entityManager.persist(todo);
	}

	public void updateTodo(Todo todo) {
		entityManager.merge(todo);
	}

}
