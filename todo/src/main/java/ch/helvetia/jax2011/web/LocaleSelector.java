package ch.helvetia.jax2011.web;

import java.io.Serializable;
import java.util.Locale;

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

	public Locale getLocale() {
		if (locale == null) {
			locale = Locale.ENGLISH;
		}
		return locale;
	}

	public void processValueChanged(ValueChangeEvent e) {
		setLanguage((String) e.getNewValue());
	}

	//
	// getter & setter
	//

	public void setLocale(Locale locale) {
		this.locale = locale;
		FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
	}

	public String getLanguage() {
		return getLocale().getLanguage();
	}

	public void setLanguage(String language) {
		setLocale(new Locale(language));
	}

}
