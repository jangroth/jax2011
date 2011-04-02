package ch.helvetia.jax2011.boundary;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import ch.helvetia.jax2011.control.TodoService;
import ch.helvetia.jax2011.entity.Todo;

/**
 * REST service to work with todos
 */
@Path("/rest")
@Produces("application/json")
public class TodoRestService {

	@Inject
	private TodoService todoService;

	@PersistenceUnit(unitName = "todoApp")
	private EntityManagerFactory emf;

	@GET
	@Path("listTodos")
	// Because the conversation context is not active during REST calls,
	// it's not possible to use the TodoService here
	// TODO use the Seam 3 Conversation module to manage a temporary conversation context
	public List<Todo> listTodos() {
		EntityManager em = emf.createEntityManager();
		List<Todo> todos = em.createNamedQuery("findAllTodos", Todo.class).getResultList();
		return todos;
	}

}
