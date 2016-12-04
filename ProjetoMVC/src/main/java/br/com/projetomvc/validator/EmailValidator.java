package br.com.projetomvc.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator(value="emailValidator")
public class EmailValidator implements Validator{

	@Override
	public void validate(FacesContext arg0, UIComponent arg1, Object arg2) throws ValidatorException {
		String email = (String) arg2;
		if (email.indexOf("@")==-1){
			FacesMessage msg = new FacesMessage("Email inválido");
			throw new ValidatorException(msg);
		}
		
	}

}
