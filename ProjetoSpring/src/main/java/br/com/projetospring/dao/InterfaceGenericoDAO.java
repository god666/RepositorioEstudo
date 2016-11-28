package br.com.projetospring.dao;

import java.util.List;

public interface InterfaceGenericoDAO <Objeto>{
	
	public Objeto salvar(Objeto entrada);
	public void excluir(Objeto entrada);
	public Objeto buscarPorId(int id);
	public List<Objeto> buscarTodos();
	
}
