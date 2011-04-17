package ch.helvetia.jax2011.security;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * Bean for authentication and authorization
 */
@SessionScoped
@Named
public class Identity implements Serializable {

	@Inject
	Credentials credentials;

	private User user;

	public void login() {
		if ("test".equals(credentials.getUsername()) &&
				"test".equals(credentials.getPassword())) {
			user = new User("test");
		}
	}

	public void logout() {
		user = null;
	}

	public boolean isLoggedIn() {
		return user != null;
	}

	public User getUser() {
		return user;
	}

}
