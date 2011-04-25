package ch.helvetia.jax2011.security;

import javax.inject.Inject;

import org.jboss.seam.security.Authenticator;
import org.jboss.seam.security.BaseAuthenticator;
import org.jboss.seam.security.UserImpl;
import org.picketlink.idm.impl.api.PasswordCredential;

import ch.helvetia.jax2011.control.UserService;
import ch.helvetia.jax2011.entity.User;

/**
 * Simple user authenticator
 */
public class TodoAuthenticator extends BaseAuthenticator implements Authenticator {

	@Inject
	org.jboss.seam.security.Credentials credentials;

	@Inject
	private UserService userService;

	@Inject
	org.jboss.seam.security.Identity identity;

	@Override
	public void authenticate() {
		if (credentials.getCredential() instanceof PasswordCredential) {
			String username = credentials.getUsername();
			String password = ((PasswordCredential) credentials.getCredential()).getValue();

			User newUser = userService.authenticate(username,
					password);
			if (newUser != null) {
				setUser(new UserImpl(newUser.getName()));
				//identity.addRole("admin", "USERS", "GROUP");
				setStatus(AuthenticationStatus.SUCCESS);
				return;
			}
		}
		setStatus(AuthenticationStatus.FAILURE);
	}
}
