package br.com.fabricarevisao.dao;

import br.com.projetojdbc.dao.UsuarioDAO;
import br.com.projetojdbc.entidade.Usuario;

public class TestUsuarioDAO {

	public static void testCadastrar(){
		Usuario u = new Usuario();
		UsuarioDAO dao = new UsuarioDAO();
		
		u.setNome("Revisao Teste criptografao");
		u.setLogin("revisao");
		u.setSenha("123");
		dao.cadastrar(u);
		System.out.println("Rodou");
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		testCadastrar();
	}

}
