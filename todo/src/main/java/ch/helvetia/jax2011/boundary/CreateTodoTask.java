package ch.helvetia.jax2011.boundary;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.enterprise.context.Conversation;
import javax.inject.Inject;

import ch.helvetia.jax2011.common.stereotypes.UserTask;
import ch.helvetia.jax2011.control.TagService;
import ch.helvetia.jax2011.control.TodoService;
import ch.helvetia.jax2011.entity.Tag;
import ch.helvetia.jax2011.entity.Todo;

/**
 * User-Task to create a new todo.
 */
@UserTask
@Stateful
@TransactionAttribute(TransactionAttributeType.NEVER)
public class CreateTodoTask implements Serializable {

	@Inject
	private Conversation conversation;

	@Inject
	private TodoService todoService;

	@Inject
	private TagService tagService;

	private Todo todo;

	private Set<Tag> tags;

	/**
	 * initialize class-attribute with new todo from service 
	 */
	public void createTodo() {
		todo = todoService.createNewTodo();
		conversation.begin();
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

	public void finish() {
		conversation.end();
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
