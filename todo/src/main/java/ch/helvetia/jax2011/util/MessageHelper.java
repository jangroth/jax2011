package ch.helvetia.jax2011.util;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class MessageHelper {

	private MessageHelper() {
		// dont you ever..
	}

	/**
	 * Gets the {@link ResourceBundle} of the current JSF context.
	 * 
	 * @return {@link ResourceBundle} of the current context
	 */
	public static ResourceBundle getResourceBundle() {
		return getResourceBundle(FacesContext.getCurrentInstance());
	}

	/**
	 * Gets the {@link ResourceBundle} from a given JSF context.
	 * 
	 * @param ctx
	 *            {@link FacesContext} of the current application
	 * @return {@link ResourceBundle} of the current application
	 */
	public static ResourceBundle getResourceBundle(FacesContext ctx) {
		// Get the current JSF application
		Application application = ctx.getApplication();

		// Get the name of the message bundle used.
		String messageBundleName = application.getMessageBundle();

		// Get the locale of the current view
		Locale locale = ctx.getViewRoot().getLocale();

		// Load the resource bundle
		return ResourceBundle.getBundle(messageBundleName, locale);
	}

	/**
	 * Generates a {@link FacesMessage} from an entry in the
	 * {@link ResourceBundle} of the Faces application.
	 * 
	 * @param severity
	 *            Severity of the message
	 * @param messageKey
	 *            Key of the message in the {@link ResourceBundle}
	 * @param param
	 *            Parameters to go into the message
	 * @return {@link FacesMessage} initialised with the given information
	 */
	public static FacesMessage createMessage(FacesMessage.Severity severity,
			String messageKey, Object... params) {
		FacesContext ctx = FacesContext.getCurrentInstance();
		ResourceBundle bundle = getResourceBundle(ctx);
		String msgPattern = bundle.getString(messageKey);
		String msg;

		if (params.length > 0) {
			msg = MessageFormat.format(msgPattern, params);
		} else {
			msg = msgPattern;
		}

		FacesMessage facesMsg = new FacesMessage();
		facesMsg.setSeverity(severity);
		facesMsg.setSummary(msg);

		return facesMsg;
	}

}
