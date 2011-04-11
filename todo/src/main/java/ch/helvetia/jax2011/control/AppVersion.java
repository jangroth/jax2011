package ch.helvetia.jax2011.control;

import java.util.Locale;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

@ApplicationScoped
@Named
public class AppVersion {

	private String version;

	// Todo use Seam Solder
	@PostConstruct
	public void init() {
		ResourceBundle bundle = ResourceBundle.getBundle("version", new Locale("en"), Thread.currentThread()
				.getContextClassLoader());
		version = bundle.getString("version");
	}

	public String getVersion() {
		return version;
	}

}
