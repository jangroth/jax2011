package ch.helvetia.jax2011.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Named;

/**
 * Bean to support locales and language switch.
 */
@SessionScoped
@Named
public class LocaleHelper implements Serializable {

	private final static String[] supportedLocaleKeys = new String[] { "en_US",
			"de_CH" };
	private final static String defaultLocaleKey = "en_US";

	private Locale currentLocale;

	private List<Locale> availableLocales = new ArrayList<Locale>();

	@PostConstruct
	public void init() {
		for (String localeKey : supportedLocaleKeys) {
			availableLocales.add(getLocaleFromLocaleKey(localeKey));
		}
		currentLocale = getLocaleFromLocaleKey(defaultLocaleKey);
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
		FacesContext.getCurrentInstance().getViewRoot()
				.setLocale(currentLocale);
	}
}
