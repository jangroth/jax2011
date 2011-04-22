package ch.helvetia.jax2011.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Represents an User.
 */
@NamedQueries(value = { @NamedQuery(name = "authenticateUser", query = "SELECT u FROM User u WHERE u.name = :userName AND u.password = :userPassword") })
@Entity
public class User {

	@GeneratedValue
	@Id
	private Long id;

	@NotEmpty
	private String name;

	@NotEmpty
	private String password;

	//
	// getters & setters
	//

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	/**
	 * trim & set password
	 */
	public void setPassword(String password) {
		this.password = password.trim();
	}

}
