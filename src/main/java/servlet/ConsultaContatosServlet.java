package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ContatosDao;
import entidades.ContatosBean;

/**
 * Servlet implementation class ConsultaContatosServlet
 */
@WebServlet("/consultaContatos")
public class ConsultaContatosServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ContatosDao dao = new ContatosDao();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String selecao = request.getParameter("selecao");
		String consulta = request.getParameter("consulta");
		
		List<ContatosBean> contatos = dao.listar();
		
		if(consulta != null && !consulta.trim().isEmpty()) {
			List<ContatosBean> lista = dao.consulta(selecao, consulta);
			RequestDispatcher rd = request.getRequestDispatcher("/cadastro.jsp");
			request.setAttribute("ctt", lista);
			request.setAttribute("total", contatos.size());
			rd.forward(request, response);	
		}else {
			RequestDispatcher rd = request.getRequestDispatcher("/cadastro.jsp");
			request.setAttribute("ctt", dao.listar());
			request.setAttribute("total", contatos.size());
			rd.forward(request, response);	
		}
		
		
		
	}

}
