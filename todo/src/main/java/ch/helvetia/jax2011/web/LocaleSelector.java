package ch.helvetia.jax2011.web;

import java.util.Locale;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;

@SessionScoped
public class LocaleSelector {

	private Locale locale;

	public Locale getLocale() {
		if (locale == null) {
			locale = Locale.ENGLISH;
		}
		return locale;
	}

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
