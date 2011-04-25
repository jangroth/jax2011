package ch.helvetia.jax2011.boundary;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.jboss.seam.conversation.spi.SeamConversationContext;
import org.jboss.seam.rest.validation.ValidateRequest;

import ch.helvetia.jax2011.control.TodoService;
import ch.helvetia.jax2011.entity.Todo;
import ch.helvetia.jax2011.error.RestAnnotationException;
import ch.helvetia.jax2011.error.RestCatchException;
import ch.helvetia.jax2011.error.RestXmlException;

/**
 * REST service to work with todos
 * 
 * Because the conversation context is not active during REST calls, it's not
 * possible to use the TodoService here TODO use the Seam 3 Conversation module
 * to manage a temporary conversation context
 */
@Path("/rest")
@Produces("application/xml")
@Stateless
public class TodoRestFacade {

	@Inject
	private TodoService todoService;

	@PersistenceUnit(unitName = "todoApp")
	private EntityManagerFactory emf;

	@Inject
	HttpServletRequest request;

	@Inject
	SeamConversationContext<HttpServletRequest> conversationContext;

	@XmlRootElement
	private static class ListResponse {
		private List<Todo> todos;

		@XmlElement(name = "todo")
		public List<Todo> getTodos() {
			return todos;
		}

		public void setTodos(List<Todo> todos) {
			this.todos = todos;
		}

	}

	@GET
	@Path("listTodos")
	public ListResponse listTodos() {
		// EntityManager em = emf.createEntityManager();
		// TypedQuery<Todo> query = em.createNamedQuery("findTodosByDate",
		// Todo.class);
		// query.setParameter("filterDate", new Date());
		// List<Todo> todos = query.getResultList();

		conversationContext.associate(request).activate(null);

		List<Todo> todos = todoService.findTodos(new Date(), null);
		ListResponse response = new ListResponse();
		response.setTodos(todos);

		conversationContext.invalidate().deactivate().dissociate(request);

		return response;
	}

	@POST
	@Path("newTodo")
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@ValidateRequest
	public void newTodo(Todo todo) {
		conversationContext.associate(request).activate(null);

		// TODO: Use Seam REST for validation
		// ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		// Validator validator = factory.getValidator();
		// Set<ConstraintViolation<Todo>> violations = validator.validate(todo);
		// if (violations.size() > 0) {
		// throw new ValidationException("Validation failed");
		// }

		// EntityManager em = emf.createEntityManager();
		// em.persist(todo);
		todoService.saveTodo(todo);

		conversationContext.invalidate().deactivate().dissociate(request);
	}

	@GET
	@Path("testCatchException")
	public String testCatchException() {
		throw new RestCatchException();
	}

	@GET
	@Path("testAnnotationException")
	public String testAnnotationException() {
		throw new RestAnnotationException();
	}

	@GET
	@Path("testXmlException")
	public String testXmlException() {
		throw new RestXmlException();
	}

}
