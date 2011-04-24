package ch.helvetia.jax2011.control;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.jboss.logging.Logger;
import org.jboss.seam.solder.logging.Category;

import ch.helvetia.jax2011.common.stereotypes.Service;
import ch.helvetia.jax2011.db.TodoDb;
import ch.helvetia.jax2011.entity.Todo;
import ch.helvetia.jax2011.error.ValidationException;

/**
 * Service for Todos.
 */
@Service
public class TodoService {

	@Inject
	@TodoDb
	private EntityManager em;

	@Inject
	@Category("info")
	private Logger logger;

	@Inject
	private Validator validator;

	public Todo createNewTodo() {
		Todo result = new Todo();
		// set dueDate to tomorrow...
		result.setDueDate(new Date(System.currentTimeMillis() + 1000 * 60 * 60
				* 24));
		return result;
	}

	public Todo loadTodo(Long id) {
		Todo todo = em.find(Todo.class, id);
		if (todo == null) {
			throw new EntityNotFoundException();
		}
		return todo;
	}

	public void saveTodo(Todo todo) {
		Set<ConstraintViolation<Todo>> violations = validator.validate(todo);
		if (violations.size() > 0) {
			throw new ValidationException("Validation failed");
		}
		em.persist(todo);
	}

	public void updateTodo(Todo todo) {
		// TODO: Use Seam Validation for validation
		Set<ConstraintViolation<Todo>> violations = validator.validate(todo);
		if (violations.size() > 0) {
			throw new ValidationException("Validation failed");
		}
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
		logger.infov("found {0} todos for tag: {1} and date: {2}!",
				result.size(), filterTagId, filterDate);
		return result;
	}
}
