package br.com.projetomvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import br.com.projetomvc.dao.InterfaceClientProfileDAO;
import br.com.projetomvc.entity.ClientProfile;

@Service
public class ClientProfileService {
	
	@Autowired
	@Qualifier("clientProfileDAO")
	private InterfaceClientProfileDAO<ClientProfile> dao;

	public List<ClientProfile> findAll() {
		return dao.findAll();
	}

	public ClientProfile findById(Integer id) {
		return dao.findById(id);
	}
	
}
