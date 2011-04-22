package ch.helvetia.jax2011.boundary;

import java.io.Serializable;

import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.enterprise.context.Conversation;
import javax.inject.Inject;

import ch.helvetia.jax2011.common.stereotypes.UserTask;
import ch.helvetia.jax2011.control.UserService;
import ch.helvetia.jax2011.entity.User;

/**
 * Task to create a new User.
 */
@UserTask
@Stateful
@TransactionAttribute(TransactionAttributeType.NEVER)
public class RegisterUserTask implements Serializable {

	@Inject
	private UserService userService;

	@Inject
	private Conversation conversation;

	private User user;

	public void createUser() {
		user = userService.createNewUser();
		conversation.begin();
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void saveUser() {
		userService.saveUser(user);
	}

	public void finish() {
		conversation.end();
	}

	//
	// getter & setter
	//

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
