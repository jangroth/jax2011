package ch.helvetia.jax2011.web;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;

import org.jboss.seam.faces.validation.InputField;

@FacesValidator("passwordValidator")
public class PasswordValidator implements Validator {

	@Inject
	@InputField
	private String password;

	@Inject
	@InputField
	private String passwordRepeat;

	@Override
	public void validate(FacesContext arg0, UIComponent arg1, Object arg2)
			throws ValidatorException {

		if (!password.trim().equals(passwordRepeat.trim())) {
			throw new ValidatorException(new FacesMessage("Error",
					"Passwords dont match"));
		}
	}

}
