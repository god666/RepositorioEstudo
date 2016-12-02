package br.com.projetomvc.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class MessageUtil {

	public static final String SAVE_SUCESS = "Salvo com Sucesso!";
	public static final String SAVE_ERROR = "Não foi possível Salvar!";
	public static final String DELETE_SUCESS = "O Cliente foi excluído com sucesso!";
	public static final String DELETE_ERROR = "Não foi possível excluir o Cliente";
	
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
