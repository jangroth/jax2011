package ch.helvetia.jax2011.web;


import java.io.Serializable;
import java.util.Date;

import ch.helvetia.jax2011.common.stereotypes.Action;

/**
 * Action to support the home page.
 */
@Action
public class HomeAction implements Serializable {
	
	private Date callDate;

	public Date getCallDate() {
		return callDate;
	}

	public void setCallDate(Date callDate) {
		this.callDate = callDate;
	}

}
