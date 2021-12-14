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
import model.Usuario;
import services.AttractionService;
import services.FilterProductService;
import services.PromocionService;

@WebServlet("/views/userHome/userHome.do")
public class FilterProductsServlet extends HttpServlet implements Servlet {

	private static final long serialVersionUID = -2362639239967422621L;

	private FilterProductService fService;

	@Override
	public void init() throws ServletException {
		super.init();
		this.fService = new FilterProductService();
	
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Usuario user = (Usuario) req.getSession().getAttribute("user");
		
		List<Producto> productos = fService.filtrarOrdenarProductos(user);
		req.setAttribute("productos", productos);
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/userHome/userHome.jsp");
		dispatcher.forward(req, resp);

	}
	
}
