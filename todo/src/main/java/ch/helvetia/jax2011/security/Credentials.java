package ch.helvetia.jax2011.security;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Represents the credentials the current user will use to authenticate
 */
@Named
@RequestScoped
public class Credentials {

	@NotEmpty
	private String username;

	@NotEmpty
	private String password;

	//
	// getter & setter
	//

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
