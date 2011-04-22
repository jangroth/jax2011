package ch.helvetia.jax2011.web;

import java.util.Set;

import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import org.jboss.logging.Logger;

import ch.helvetia.jax2011.security.Identity;
import ch.helvetia.jax2011.util.BeanManagerUtil;

/**
 * Restrict view access
 */
public class SecurityPhaseListener implements PhaseListener {

	private static final String LOGIN_VIEW_ID = "/login.xhtml";

	private static final String REGISTRATION_VIEW_ID = "/registerUser.xhtml";

	private static final String ERROR_VIEW_ID = "/error.xhtml";

	private static final Logger log = Logger
			.getLogger(SecurityPhaseListener.class);

	@Override
	public void afterPhase(PhaseEvent event) {
		// do nothing
	}

	@Override
	public void beforePhase(PhaseEvent event) {
		PhaseId phaseId = event.getPhaseId();
		if (phaseId.equals(PhaseId.INVOKE_APPLICATION)
				|| phaseId.equals(PhaseId.RENDER_RESPONSE)) {

			FacesContext context = event.getFacesContext();
			String viewId = context.getViewRoot().getViewId();
			Identity identity = getIdentity();
			if (!identity.isLoggedIn() && !viewId.equals(LOGIN_VIEW_ID)
					&& !viewId.equals(ERROR_VIEW_ID)
					&& !viewId.equals(REGISTRATION_VIEW_ID)) {
				log.infof("Redirecting to LoginView %s", LOGIN_VIEW_ID);
				NavigationHandler navHandler = context.getApplication()
						.getNavigationHandler();
				navHandler.handleNavigation(context, "", LOGIN_VIEW_ID + "?"
						+ "faces-redirect=true");
				context.renderResponse();
			}

		}
	}

	private Identity getIdentity() {
		BeanManager manager = BeanManagerUtil.getBeanManager();
		Set<Bean<?>> beans = manager.getBeans(Identity.class);
		if (beans.size() != 1) {
			if (beans.size() == 0) {
				throw new RuntimeException("No beans of class "
						+ Identity.class + " found.");
			} else {
				throw new RuntimeException("Multiple beans of class "
						+ Identity.class + " found: " + beans + ".");
			}
		}
		@SuppressWarnings("unchecked")
		Bean<Identity> myBean = (Bean<Identity>) beans.iterator().next();
		return (Identity) manager.getReference(myBean, Identity.class,
				manager.createCreationalContext(myBean));
	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.ANY_PHASE;
	}

}
