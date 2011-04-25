package ch.helvetia.jax2011.web;

import java.io.Serializable;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Named;

/**
 * Bean to support language switch.
 */
@SessionScoped
@Named
public class LocaleSelector implements Serializable {

	private Locale locale;

	private Locale[] allLocales;

	@PostConstruct
	public void init() {
		allLocales = new Locale[] { new Locale("en", "US"),
				new Locale("de", "CH") };
		locale = allLocales[0];
		FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
	}

	public void processValueChanged(ValueChangeEvent e) {
		setLanguage((String) e.getNewValue());
	}

	private Locale getLocaleFromIsoCode(String isoCode) {
		String language = isoCode.substring(0, 2);
		String country = isoCode.substring(3, 5);
		return new Locale(language, country);
	}

	//
	// getter & setter
	//

	public Locale getLocale() {
		return locale;
	}

	public Locale[] getAllLocales() {
		return allLocales;
	}

	public String getLanguage() {
		return locale.toString();
	}

	public void setLanguage(String isoCode) {
		locale = getLocaleFromIsoCode(isoCode);
		FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
	}
}
