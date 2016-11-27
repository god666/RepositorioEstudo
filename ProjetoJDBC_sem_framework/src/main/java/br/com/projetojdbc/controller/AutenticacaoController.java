package br.com.projetojdbc.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.projetojdbc.dao.UsuarioDAO;
import br.com.projetojdbc.entidade.Usuario;

@WebServlet("/autenticacao.do")
public class AutenticacaoController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String login = req.getParameter("login");
		String senha = req.getParameter("senha");
		
		Usuario usuario = new Usuario();
		UsuarioDAO dao = new UsuarioDAO();
		usuario = dao.buscarPorLoginSenha(login, senha);
		
		if(usuario!=null){
			HttpSession sessao = req.getSession();
			sessao.setAttribute("usuario", usuario);
			System.out.println("AUTENTICOU");
			req.getRequestDispatcher("WEB-INF/home.jsp").forward(req, resp);
			
			//resp.sendRedirect("WEB-INF/home.jsp");
		} else{
			resp.sendRedirect("login.html");
		}
		
	}
	
}
