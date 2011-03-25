package ch.helvetia.jax2011.control;

import java.util.Date;
import java.util.List;

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
	private EntityManager em;

	public Todo createNewTodo() {
		Todo result = new Todo();
		result.setDueDate(new Date(System.currentTimeMillis() + 1000 * 60 * 60
				* 24));
		return result;
	}

	public void saveTodo(Todo todo) {
		em.persist(todo);
	}

	public void updateTodo(Todo todo) {
		em.merge(todo);
	}

	public List<Todo> findAllTodos() {
		List<Todo> todos = em.createNamedQuery("findAllTodos", Todo.class)
				.getResultList();
		return todos;
	}

}
