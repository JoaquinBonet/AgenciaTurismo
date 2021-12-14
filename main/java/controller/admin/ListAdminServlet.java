package controller.admin;

import java.io.IOException;
import java.util.List;


import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.Servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Atraccion;
import model.Promocion;
import model.Usuario;
import services.AttractionService;
import services.PromocionService;
import services.TipoService;
import services.UserService;

@WebServlet("/views/admin/admin.do")
public class ListAdminServlet extends HttpServlet implements Servlet {


	private static final long serialVersionUID = -30424774915059548L;
	private AttractionService attractionService;
	private PromocionService promocionService;
	private TipoService tipoService;
	private UserService userService;

	@Override
	public void init() throws ServletException {
		super.init();
		this.attractionService = new AttractionService();
		this.promocionService = new PromocionService();
		this.tipoService = new TipoService();
		this.userService = new UserService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Atraccion> atracciones = attractionService.list();
		List<Promocion> promos = promocionService.list();
		List<String> tipos = tipoService.list();
		List<Usuario> users = userService.list();
		
		req.setAttribute("users", users);
		req.setAttribute("attractions", atracciones);
		req.setAttribute("promos", promos);
		req.setAttribute("tipos", tipos);
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/admin/admin.jsp");
		dispatcher.forward(req, resp);

	}
	
	

}
