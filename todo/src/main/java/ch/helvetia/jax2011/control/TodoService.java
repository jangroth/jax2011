package ch.helvetia.jax2011.control;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;

import ch.helvetia.jax2011.common.stereotypes.Service;
import ch.helvetia.jax2011.db.TodoDb;
import ch.helvetia.jax2011.entity.Tag;
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
	private Logger logger = Logger.getLogger(TodoService.class);

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

	/**
	 * returns all todos from database.
	 */
	public List<Todo> findAllTodos(Date callDate) {
		TypedQuery<Todo> query = em
				.createNamedQuery("findAllTodos", Todo.class);
		query.setParameter("callDate", callDate);
		List<Todo> result = query.getResultList();
		logger.info("found " + result.size() + " todos for " + callDate);
		return result;
	}

	/**
	 * returns all todos with a specific tag
	 */
	public List<Todo> findAllTodosByTag(Tag tag) {
		TypedQuery<Todo> query = em.createNamedQuery("findAllTodosByTag",
				Todo.class);
		query.setParameter("tag", tag.getId());
		return query.getResultList();
	}

}
