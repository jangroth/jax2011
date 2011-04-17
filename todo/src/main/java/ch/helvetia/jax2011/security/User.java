package ch.helvetia.jax2011.security;

/**
 * Represents an User. User has unique id.
 */
public class User {

	String id;

	public User(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
