package ch.helvetia.jax2011.control;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.jboss.logging.Logger;

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

	// todo: use Solder for logging
	private final Logger logger = Logger.getLogger(TodoService.class);

	public Todo createNewTodo() {
		Todo result = new Todo();
		// set dueDate to tomorrow...
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

	/**
	 * returns todos from database.
	 */
	public List<Todo> findTodos(Date filterDate, Long filterTagId) {
		TypedQuery<Todo> query;
		if (filterTagId != null) {
			query = em.createNamedQuery("findTodosByDateAndTag", Todo.class);
			query.setParameter("filterDate", filterDate);
			query.setParameter("filterTagId", filterTagId);
		} else {
			query = em.createNamedQuery("findTodosByDate", Todo.class);
			query.setParameter("filterDate", filterDate);
		}
		List<Todo> result = query.getResultList();
		logger.info("found " + result.size() + " todos for tag: " + filterTagId
				+ " and date: " + filterDate);
		return result;
	}

}
