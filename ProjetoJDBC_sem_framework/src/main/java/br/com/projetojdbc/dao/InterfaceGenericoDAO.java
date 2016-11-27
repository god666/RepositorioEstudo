package br.com.projetojdbc.dao;

import java.sql.Connection;
import java.util.List;

public interface InterfaceGenericoDAO <Objeto>{
	
	public void cadastrar(Objeto entrada);
	public void atualizar(Objeto entrada);
	public void salvar(Objeto entrada);
	public Objeto buscarPorId(int id);
	public List<Objeto> buscarTudo();
	public void excluir (int id);

}
