package br.com.projetomvc.exception;

public class DAOException extends Exception {

	public DAOException(Exception e){
		super(e);
	}
	public DAOException(String msg, Exception e){
		super(msg, e);
	}
}
