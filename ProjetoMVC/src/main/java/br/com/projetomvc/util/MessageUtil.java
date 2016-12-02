package br.com.projetomvc.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class MessageUtil {

	public static final String SAVE_SUCESS = "Salvo com Sucesso!";
	public static final String SAVE_ERROR = "N�o foi poss�vel Salvar!";
	public static final String DELETE_SUCESS = "O Cliente foi exclu�do com sucesso!";
	public static final String DELETE_ERROR = "N�o foi poss�vel excluir o Cliente";
	
	public static void sucessMessage(String msg){
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, 
				msg, null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public static void errorMessage(String msg){
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, 
				msg, null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
	
	public static void warinngMessage(String msg){
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, 
				msg, null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
	
}
