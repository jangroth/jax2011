package ch.helvetia.jax2011.util;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Produces;

import org.jboss.seam.international.locale.DefaultLocale;
import org.jboss.seam.international.locale.LocaleConfiguration;

public class JaxLocaleConfiguration extends LocaleConfiguration {

	@Produces
	@DefaultLocale
	private String defaultLocaleKey = "en_US";

	@PostConstruct
	public void setup() {
		addSupportedLocaleKey("en_US");
		addSupportedLocaleKey("de_CH");
	}

}
