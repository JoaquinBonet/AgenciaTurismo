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

@WebServlet("/views/admin/attractions/edit.do")
public class EditAttractionServlet extends HttpServlet {

	private static final long serialVersionUID = 7598291131560345626L;
	private AttractionService attractionService;

	@Override
	public void init() throws ServletException {
		super.init();
		this.attractionService = new AttractionService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String nombre = req.getParameter("name");

		Atraccion atraccion = (Atraccion) attractionService.find(nombre);
		req.setAttribute("attraction", atraccion);

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/admin/attractions/edit.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("name");
		Integer cost = Integer.parseInt(req.getParameter("cost"));
		Double duration = Double.parseDouble(req.getParameter("duration"));
		Integer capacity = Integer.parseInt(req.getParameter("capacity"));
		String info = req.getParameter("info");
		String tipo = req.getParameter("tipo");
		String img_url = req.getParameter("img");
		
		Atraccion attraction = attractionService.update(name, capacity, cost, duration, info, tipo, img_url);

		if (attraction.isValid()) {
			resp.sendRedirect("/AgenciaTurismo/views/admin/admin.do");
		} else {
			req.setAttribute("attraction", attraction);

			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/admin/attractions/edit.jsp");
			dispatcher.forward(req, resp);
		}
	}
}
