package br.com.projetojdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.projetojdbc.entidade.Usuario;

public class UsuarioDAO implements InterfaceGenericoDAO<Usuario>{

	private Connection connection = ConnectionFactory.getConnection();
	private String criptografia = "md5";
	private String nomeTabela = "Usuario";
	
	@Override
	public void cadastrar(Usuario usuario) {
		String sql = "insert into Usuario (nome, login, senha) values (?,?,?)";
		
		try (PreparedStatement preparador = connection.prepareStatement(sql)){
			preparador.setString(1, usuario.getNome());
			preparador.setString(2, usuario.getLogin());
			//preparador.setString(3, criptografia);
			preparador.setString(3, usuario.getSenha());
			
			preparador.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	@Override
	public void atualizar(Usuario usuario) {
		String sql = "update Usuario set nome=?, login=?, senha=? where id=?";
		
		try (PreparedStatement preparador = connection.prepareStatement(sql)){
			preparador.setString(1, usuario.getNome());
			preparador.setString(2, usuario.getLogin());
			//preparador.setString(3, criptografia);
			preparador.setString(3, usuario.getSenha());
			preparador.setInt(4, usuario.getId());
			
			preparador.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void salvar(Usuario usuario) {
		if(usuario.getId() !=0){
			atualizar(usuario);
		} else{
			cadastrar(usuario);
		}
	}

	@Override
	public Usuario buscarPorId(int id) {
		
		String sql = "select * from Usuario where id="+id;
		Usuario usuario = new Usuario();
		
		try (PreparedStatement preparador = connection.prepareStatement(sql)){
			ResultSet resultado = preparador.executeQuery();
			
			if(resultado.next()){
				usuario.setId(id);
				usuario.setNome(resultado.getString("nome"));
				usuario.setLogin(resultado.getString("login"));
				usuario.setSenha(resultado.getString("senha"));
				
				return usuario;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Se não for encontrado nenhum usuário, retornará o valor de usuario = null
		return null;
	}

	@Override
	public List<Usuario> buscarTudo() {

		String sql = "select * from Usuario";
		List<Usuario> lista = new ArrayList<Usuario>();  //Null por default?
		
		try (PreparedStatement preparador = connection.prepareStatement(sql)){
			ResultSet resultado = preparador.executeQuery();
			
			while(resultado.next()){
				Usuario usuario = new Usuario();
				usuario.setId(resultado.getInt("id"));
				usuario.setNome(resultado.getString("nome"));
				usuario.setLogin(resultado.getString("login"));
				usuario.setSenha(resultado.getString("senha"));
				
				lista.add(usuario);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(lista.isEmpty()){
			return null;
		} else {
			return lista;
		}
		
		
	}

	@Override
	public void excluir(int id) {
		
		String sql = "delete from "+nomeTabela+" where id="+id;
		
		try (PreparedStatement preparador = connection.prepareStatement(sql)){
			preparador.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public Usuario buscarPorLoginSenha(String login, String senha){
		String sql = "select * from Usuario where login=? and senha=?";
		Usuario usuario = new Usuario();
		
		try (PreparedStatement preparador = connection.prepareStatement(sql)){
			preparador.setString(1, login);
			preparador.setString(2, senha);
			
			ResultSet resultado = preparador.executeQuery();
			
			if(resultado.next()){
				usuario.setId(resultado.getInt("id"));
				usuario.setNome(resultado.getString("nome"));
				usuario.setLogin(resultado.getString("login"));
				usuario.setSenha(resultado.getString("senha"));
				return usuario;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
}
