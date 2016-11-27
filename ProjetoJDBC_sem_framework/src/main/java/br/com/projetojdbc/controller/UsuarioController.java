package br.com.projetojdbc.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.projetojdbc.dao.UsuarioDAO;
import br.com.projetojdbc.entidade.Usuario;

@WebServlet ("/usuariocontroller.do")
public class UsuarioController extends HttpServlet{

	private String cadastroUsuario = "/WEB-INF/cadastro_usuario.jsp";
	private String gerenciarUsuario = "/WEB-INF/gerenciar_usuario.jsp";
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("Entrou No doPOST");
		Usuario usuario = new Usuario();
		usuario.setNome(req.getParameter("nome"));
		usuario.setLogin(req.getParameter("login"));
		usuario.setSenha(req.getParameter("senha"));
		
		UsuarioDAO dao = new UsuarioDAO();
		dao.salvar(usuario);
		
		req.getRequestDispatcher(cadastroUsuario).forward(req, resp);
		
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("Entrou No doGET");
		
		String acao = req.getParameter("acao");
		
		if(acao.equals("cadastrar")){
			req.getRequestDispatcher(cadastroUsuario).forward(req, resp);
		}else if (acao.equals("listar")){
			UsuarioDAO dao = new UsuarioDAO();
			List<Usuario> lista = new ArrayList<Usuario>();
			
			lista = dao.buscarTudo();
			
			req.setAttribute("lista", lista);
			RequestDispatcher dispatcher = req.getRequestDispatcher(gerenciarUsuario);
			dispatcher.forward(req, resp);
			
			//req.getRequestDispatcher(gerenciarUsuario).forward(req, resp);
		} else if (acao.equals("excluir")){
			int id = Integer.parseInt(req.getParameter("id"));
			UsuarioDAO dao = new UsuarioDAO();
			dao.excluir(id);
			
			resp.sendRedirect("usuariocontroller.do?acao=listar");
			//req.getRequestDispatcher(gerenciarUsuario).forward(req, resp);		
			
		} else if (acao.equals("editar")){
			UsuarioDAO dao = new UsuarioDAO();
			int id = Integer.parseInt(req.getParameter("id"));
			Usuario usuario = new Usuario();
			usuario = dao.buscarPorId(id);
			
			req.setAttribute("usuario", usuario);
			//resp.sendRedirect("usuariocontroller.do?acao=cadastrar");
			req.getRequestDispatcher(cadastroUsuario).forward(req, resp);
			
		}
		
	}
	
}
