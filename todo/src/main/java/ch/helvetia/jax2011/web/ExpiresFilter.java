package ch.helvetia.jax2011.web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This filter controls the setting of the Expires HTTP header and the max-age directive of the Cache-Control HTTP
 * header in server responses.
 * TODO Use Seam Servlet
 */
public class ExpiresFilter implements Filter {

	private static final String PARAM_EXPIRES = "expires";

	private long expires = 604800000L; // 604800000 ms = 7 day

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		String expiresParam = filterConfig.getInitParameter(PARAM_EXPIRES);
		if (expiresParam != null) {
			this.expires = Long.valueOf(expiresParam);
		}
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
			ServletException {
		this.addExpiresHeader((HttpServletRequest) request, (HttpServletResponse) response);
		chain.doFilter(request, response);
	}

	private void addExpiresHeader(HttpServletRequest request, HttpServletResponse response) {
		response.setDateHeader("Expires", System.currentTimeMillis() + this.expires);
		response.setHeader("Pragma", "public");
		response.setHeader("Cache-Control", "public, max-age=" + this.expires / 1000);
	}

	@Override
	public void destroy() {
		// do nothing
	}

}
