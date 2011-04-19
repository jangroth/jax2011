package ch.helvetia.jax2011.security;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ch.helvetia.jax2011.error.TodoSecurityException;

/**
 * Bean for authentication and authorization
 */
@SessionScoped
@Named
public class Identity implements Serializable {

	@Inject
	private Credentials credentials;

	private Set<String> activeRoles = new HashSet<String>();

	private User user;

	public void login() {
		if ("test".equals(credentials.getUsername())
				&& "test".equals(credentials.getPassword())) {
			user = new User("test");
			activeRoles.add("admin");
		}
	}

	public void logout() {
		user = null;
		activeRoles.clear();
	}

	public boolean isLoggedIn() {
		return user != null;
	}

	public boolean hasRole(String checkRole) {
		for (String role : activeRoles) {
			if (role.equals(checkRole)) {
				return true;
			}
		}
		return false;
	}

	public void checkRole(String checkRole) {
		if (!hasRole(checkRole)) {
			throw new TodoSecurityException(
					"Authorization check failed for role " + checkRole);
		}
	}

	public User getUser() {
		return user;
	}

}
