package controller.admin;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Atraccion;
import model.Usuario;
import services.AttractionService;
import services.UserService;

@WebServlet("/views/admin/users/edit.do")
public class EditUserServlet extends HttpServlet {

	private static final long serialVersionUID = -4690781050819594641L;
	private UserService uService;

	@Override
	public void init() throws ServletException {
		super.init();
		this.uService = new UserService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String nombre = req.getParameter("name");

		Usuario user = uService.find(nombre);
		req.setAttribute("user", user);

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/admin/users/edit.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("name");
		Integer money = Integer.parseInt(req.getParameter("coins"));
		Double time = Double.parseDouble(req.getParameter("time"));
		String tipo = req.getParameter("tipo");
	
		
		Usuario user = uService.update(name, tipo, time, money);

		if (user.isValid()) {
			resp.sendRedirect("/AgenciaTurismo/views/admin/admin.do");
		} else {
			req.setAttribute("user", user);

			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/admin/users/edit.jsp");
			dispatcher.forward(req, resp);
		}
	}
}
