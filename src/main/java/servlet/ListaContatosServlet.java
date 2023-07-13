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
 * Servlet implementation class ListaContatosServlet
 */
@WebServlet("/listaContatos")
public class ListaContatosServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ContatosDao dao = new ContatosDao();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<ContatosBean> contatos = dao.listar();
		String acao = request.getParameter("acao");
		String contato = request.getParameter("contato");

		RequestDispatcher rd = request.getRequestDispatcher("/cadastro.jsp");

		if (acao != null && acao.equalsIgnoreCase("editar")) {
			ContatosBean bean = dao.buscar(Long.valueOf(contato));
			request.setAttribute("contato", bean);
			request.setAttribute("ctt", dao.listar());
			request.setAttribute("total", contatos.size());
		} else if (acao != null && acao.equalsIgnoreCase("excluir")) {
			dao.deletar(Long.valueOf(contato));
			request.setAttribute("ctt", dao.listar());
			request.setAttribute("total", contatos.size() - 1);
		}else if(acao != null && acao.equalsIgnoreCase("listar")) {
			request.setAttribute("ctt", dao.listar());
			request.setAttribute("total", contatos.size());
		}

		rd.forward(request, response);
	}

	@SuppressWarnings("unused")
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String nome = request.getParameter("nome");
		String email = request.getParameter("email");
		String numero = request.getParameter("numero");
		String id = request.getParameter("id");

		ContatosBean ct = new ContatosBean();
		ct.setId(id.isEmpty() ? null : Long.valueOf(id));
		ct.setNome(nome);
		ct.setEmail(email);
		ct.setNumero(numero);

		RequestDispatcher rd = request.getRequestDispatcher("/cadastro.jsp");
		List<ContatosBean> contatos = dao.listar();
		
		int cont = 1;

		if (id == null || id.isEmpty() && !dao.validarNumero(numero)) {
			request.setAttribute("error", "Esse numero ja foi cadastrado!");
			request.setAttribute("ctt", dao.listar());
			request.setAttribute("total", contatos.size());
		} else if (id == null || id.isEmpty() && dao.validarNumero(numero)) {
			dao.cadastrar(ct);
			request.setAttribute("msg", "Contato salvo!");
			request.setAttribute("ctt", dao.listar());
			request.setAttribute("total", contatos.size() + 1);
			System.out.println("entrou");
		} else if (id != null || !id.isEmpty()) {
			dao.atualizar(ct);
			request.setAttribute("ctt", dao.listar());
			request.setAttribute("total", contatos.size());
			request.setAttribute("msg", "Contato atualizado!");
		}
		rd.forward(request, response);


	}

}
