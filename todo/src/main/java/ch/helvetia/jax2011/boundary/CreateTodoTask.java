package ch.helvetia.jax2011.boundary;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Set;

import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.enterprise.context.Conversation;
import javax.inject.Inject;

import org.jboss.seam.faces.context.conversation.Begin;
import org.jboss.seam.faces.context.conversation.End;
import org.jboss.seam.transaction.Transactional;

import ch.helvetia.jax2011.common.stereotypes.UserTask;
import ch.helvetia.jax2011.control.TagService;
import ch.helvetia.jax2011.control.TodoService;
import ch.helvetia.jax2011.entity.Tag;
import ch.helvetia.jax2011.entity.Todo;
import ch.helvetia.jax2011.security.Admin;
import ch.helvetia.jax2011.security.Identity;

/**
 * User-Task to create a new todo.
 */
@UserTask
// @Stateful
@TransactionAttribute(TransactionAttributeType.NEVER)
// @Transactional
public class CreateTodoTask implements Serializable {

	// @Inject
	// private Conversation conversation;

	@Inject
	private Identity identity;

	@Inject
	private TodoService todoService;

	@Inject
	private TagService tagService;

	private Todo todo;

	private Set<Tag> tags;

	/**
	 * initialize class-attribute with new todo from service
	 */
	@Admin
	@Begin
	public void createTodo() {
		todo = todoService.createNewTodo();
		// conversation.begin();
	}

	/**
	 * retrieve all tags from service
	 */
	public void findAllTags() {
		tags = tagService.findAllTags();
	}

	/**
	 * adds array of selected tags to todo
	 */
	public void addTags(Tag[] selectedTags) {
		todo.getTags().addAll(Arrays.asList(selectedTags));
	}

	/**
	 * calls service method in transactional context
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void saveTodo() {
		todoService.saveTodo(todo);
	}

	@End
	public void finish() {
		// conversation.end();
	}

	//
	// getter & setter
	//

	public Todo getTodo() {
		return todo;
	}

	public Set<Tag> getTags() {
		return tags;
	}

}
