package ch.helvetia.jax2011.util;

import java.io.Serializable;
import java.util.List;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Event;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject;
import javax.inject.Named;

import org.jboss.seam.international.Alter;
import org.jboss.seam.solder.core.Client;

/**
 * Bean to support locales and language switch.
 */
@SessionScoped
@Named
public class LocaleHelper implements Serializable {

	private final static String defaultLocaleKey = "en_US";

	@Inject
	private List<java.util.Locale> availableLocales;

	@Inject
	private Locale defaultLocale;

	@Inject
	@Alter
	@Client
	private Event<java.util.Locale> localeEvent;

	private Locale currentLocale;

	@PostConstruct
	public void init() {
		currentLocale = defaultLocale;
		FacesContext.getCurrentInstance().getViewRoot()
				.setLocale(currentLocale);
	}

	public void processValueChanged(ValueChangeEvent e) {
		setLanguage((String) e.getNewValue());
	}

	private Locale getLocaleFromLocaleKey(String localeKey) {
		String language = localeKey.substring(0, 2);
		String country = localeKey.substring(3, 5);
		return new Locale(language, country);
	}

	//
	// getter & setter
	//

	public Locale getLocale() {
		return currentLocale;
	}

	public List<Locale> getAvailabeLocales() {
		return availableLocales;
	}

	public String getLanguage() {
		return currentLocale.toString();
	}

	public void setLanguage(String isoCode) {
		currentLocale = getLocaleFromLocaleKey(isoCode);
		localeEvent.fire(currentLocale);
		FacesContext.getCurrentInstance().getViewRoot()
				.setLocale(currentLocale);
	}
}