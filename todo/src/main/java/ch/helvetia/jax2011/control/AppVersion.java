package ch.helvetia.jax2011.control;

import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.jboss.seam.solder.resourceLoader.Resource;

@ApplicationScoped
@Named
public class AppVersion {

	@Inject
	@Resource("version.properties")
	private Properties properties;

	private String version;

	@PostConstruct
	public void init() {
		version = properties.getProperty("version");
	}

	//
	// getter & setter
	//

	public String getVersion() {
		return version;
	}

}
