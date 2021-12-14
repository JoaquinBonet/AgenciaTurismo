package controller.products;

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
import model.Producto;
import model.Promocion;
import services.AttractionService;
import services.PromocionService;

@WebServlet("/views/totalProducts/totalProducts.do")
public class ListProductsServlet extends HttpServlet implements Servlet {


	private static final long serialVersionUID = -30424774915059548L;
	private AttractionService attractionService;
	private PromocionService promocionService;

	@Override
	public void init() throws ServletException {
		super.init();
		this.attractionService = new AttractionService();
		this.promocionService = new PromocionService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Atraccion> atracciones = attractionService.list();
		List<Promocion> promos = promocionService.list();
		

		req.setAttribute("atracciones", atracciones);
		req.setAttribute("promos", promos);
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/totalProducts/totalProducts.jsp");
		dispatcher.forward(req, resp);

	}
	
	

}
