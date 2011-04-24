package ch.helvetia.jax2011.web;

import org.jboss.seam.faces.transaction.SeamManagedTransaction;
import org.jboss.seam.faces.transaction.SeamManagedTransactionType;
import org.jboss.seam.faces.view.config.ViewConfig;
import org.jboss.seam.faces.view.config.ViewPattern;

@ViewConfig
public interface TodoViewConfig {

	static enum Pages {

		@ViewPattern("/*")
		@SeamManagedTransaction(SeamManagedTransactionType.DISABLED)
		ALL;

	}

}
