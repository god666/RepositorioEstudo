package br.com.projetomvc.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.projetomvc.entity.ClientProfile;
import br.com.projetomvc.exception.DAOException;

@Repository
public class ClientProfileDAO extends GenericDAO<ClientProfile> implements InterfaceClientProfileDAO<ClientProfile>{
	
}
