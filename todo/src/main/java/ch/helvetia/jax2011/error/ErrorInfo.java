package ch.helvetia.jax2011.error;

import java.io.Serializable;
import java.util.Date;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 * Holds information about the handled exception for the error page
 * TODO Use RenderScope from the Seam faces module
 */
@SessionScoped
@Named
public class ErrorInfo implements Serializable {

	private Throwable handledException;

	private String stacktrace;

	private String user;

	private Date date;

	public Throwable getHandledException() {
		return handledException;
	}

	public void setHandledException(Throwable handledException) {
		this.handledException = handledException;
	}

	public String getStacktrace() {
		return stacktrace;
	}

	public void setStacktrace(String stacktrace) {
		this.stacktrace = stacktrace;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
