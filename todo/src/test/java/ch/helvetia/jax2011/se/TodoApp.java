package ch.helvetia.jax2011.se;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.jboss.seam.conversation.spi.SeamConversationContext;
import org.jboss.weld.context.bound.BoundRequest;
import org.jboss.weld.context.bound.MutableBoundRequest;
import org.jboss.weld.environment.se.bindings.Parameters;
import org.jboss.weld.environment.se.events.ContainerInitialized;

import ch.helvetia.jax2011.control.TodoService;
import ch.helvetia.jax2011.entity.Todo;

@ApplicationScoped
public class TodoApp {

	private ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();

	private Validator validator = validatorFactory.getValidator();

	@Inject
	SeamConversationContext<BoundRequest> conversationContext;

	@Inject
	TodoService todoService;

	@Produces
	public Validator getValidator() {
		return validator;
	}

	public void listTodos(@Observes ContainerInitialized event, @Parameters List<String> parameters) {
		MutableBoundRequest boundRequest = new MutableBoundRequest(new HashMap<String, Object>(),
				new HashMap<String, Object>());
		conversationContext.associate(boundRequest).activate(null);

		List<Todo> todos = todoService.findTodos(new Date(), null);
		System.out.println(todos);

		conversationContext.invalidate().deactivate().dissociate(boundRequest);
	}
}
