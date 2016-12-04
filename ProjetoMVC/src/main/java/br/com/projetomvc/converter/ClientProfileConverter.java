package br.com.projetomvc.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.projetomvc.entity.ClientProfile;
import br.com.projetomvc.service.ClientProfileService;

@Component
public class ClientProfileConverter implements Converter {
	
	@Autowired
	ClientProfileService service;
	
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String id) {
		try{
			return service.findById(Integer.parseInt(id));
		}catch (Exception e) {
			e.getStackTrace();
			return null;
		}	
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object clientProfile) {
		if(clientProfile instanceof ClientProfile){
			ClientProfile profile = (ClientProfile) clientProfile;
			return profile.getId().toString();
		} else {
			return null;
		}
		
	}

}
