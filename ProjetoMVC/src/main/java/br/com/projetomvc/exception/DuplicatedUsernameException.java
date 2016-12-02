package br.com.projetomvc.exception;

public class DuplicatedUsernameException extends Exception {

	public DuplicatedUsernameException(Exception e){
		super(e);
	}
	
	public DuplicatedUsernameException(String msg){
		super(msg);
	}
}
