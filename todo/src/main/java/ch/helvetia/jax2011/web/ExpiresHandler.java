package ch.helvetia.jax2011.web;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jboss.seam.servlet.event.Initialized;
import org.jboss.seam.servlet.http.HttpServletRequestContext;

/**
 * This handler controls the setting of the Expires HTTP header and the max-age directive of the Cache-Control HTTP
 * header in server responses.
 * Configuration in seam-beans.xml
 */
@ApplicationScoped
public class ExpiresHandler {

	private long expires = 604800000L; // 604800000 ms = 7 day
	private String path = "/javaxx.faces.resource";

	public void addExpiresHeader(@Observes @Initialized HttpServletRequestContext ctx)
			throws Exception {
		HttpServletRequest request = ctx.getRequest();
		HttpServletResponse response = ctx.getResponse();
		if (request.getServletPath().startsWith(path)) {
			response.setDateHeader("Expires", System.currentTimeMillis() + this.expires);
			response.setHeader("Pragma", "public");
			response.setHeader("Cache-Control", "public, max-age=" + this.expires / 1000);
		}
	}

}
