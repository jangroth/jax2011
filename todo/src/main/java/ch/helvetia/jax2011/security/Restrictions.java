package ch.helvetia.jax2011.security;

import org.jboss.seam.security.annotations.Secures;

/**
 * Authorizer methods
 */
public class Restrictions {

	@Secures
	@Admin
	public boolean isAdmin(org.jboss.seam.security.Identity identity) {
		return identity.hasRole("admin", "USERS", "GROUP");
	}

	@Secures
	@LoggedIn
	public boolean isLoggedIn(org.jboss.seam.security.Identity identity) {
		return identity.isLoggedIn();
	}

}
