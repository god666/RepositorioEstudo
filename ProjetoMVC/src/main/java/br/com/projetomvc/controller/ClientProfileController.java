package br.com.projetomvc.controller;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import br.com.projetomvc.entity.ClientProfile;
import br.com.projetomvc.service.ClientProfileService;

@Controller
public class ClientProfileController {
	
	@Autowired
	private ClientProfileService service;
	
	private List<ClientProfile> profileList;
	
	@PostConstruct
	public void init(){
		profileList = service.findAll();
	}

	/*Gets and sets*/
	public List<ClientProfile> getProfileList() {
		return profileList;
	}

	public void setProfileList(List<ClientProfile> profileList) {
		this.profileList = profileList;
	}
	
}
