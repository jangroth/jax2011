package ch.helvetia.jax2011.control;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.jboss.logging.Logger;

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
		// TODO: Use Seam REST for validation
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<Todo>> violations = validator.validate(todo);
		if (violations.size() > 0) {
			throw new ValidationException("Validation failed");
		}
		em.persist(todo);
	}

	public void updateTodo(Todo todo) {
		// TODO: Use Seam REST for validation
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
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
		logger.info("found " + result.size() + " todos for tag: " + filterTagId
				+ " and date: " + filterDate);
		return result;
	}

}
