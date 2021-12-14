package controller.admin;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Atraccion;
import services.AttractionService;
import services.TipoService;

@WebServlet("/views/admin/tipo/create.do")
public class CreateTipoServlet extends HttpServlet {

	private static final long serialVersionUID = 5011091601935076309L;
	private TipoService tService;

	@Override
	public void init() throws ServletException {
		super.init();
		this.tService = new TipoService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/admin/tipo/create.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("name");

		String tipo = tService.create(name);
		//req.setAttribute("tipo", tipo);
		
		resp.sendRedirect("/AgenciaTurismo/views/admin/admin.do");

		

	}
}
