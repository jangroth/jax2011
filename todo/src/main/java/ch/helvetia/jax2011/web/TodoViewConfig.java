package ch.helvetia.jax2011.web;

import org.jboss.seam.faces.rewrite.FacesRedirect;
import org.jboss.seam.faces.security.LoginView;
import org.jboss.seam.faces.transaction.SeamManagedTransaction;
import org.jboss.seam.faces.transaction.SeamManagedTransactionType;
import org.jboss.seam.faces.view.config.ViewConfig;
import org.jboss.seam.faces.view.config.ViewPattern;

import ch.helvetia.jax2011.security.LoggedIn;

@ViewConfig
public interface TodoViewConfig {

	static enum Pages {

		@ViewPattern("/home.xhtml")
		@LoggedIn
		HOME,

		@ViewPattern("/*")
		@SeamManagedTransaction(SeamManagedTransactionType.DISABLED)
		@LoginView("/login.xhtml")
		@FacesRedirect
		ALL;

	}

}
