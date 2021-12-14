package controller.products;

import java.io.IOException;
import java.util.Map;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import model.Usuario;
import persistence.commons.DAOFactory;

import services.BuyProductService;

@WebServlet("/views/itemDetail/itemDetail/buy.do")
public class BuyProductServlet extends HttpServlet {

	private static final long serialVersionUID = 3455721046062278592L;
	private BuyProductService buyProductService;

	@Override
	public void init() throws ServletException {
		super.init();
		this.buyProductService = new BuyProductService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String nombreProducto = req.getParameter("nombre");
		Usuario user = (Usuario) req.getSession().getAttribute("user");
		Map<String, String> errors = buyProductService.buy(user.getNombre(), nombreProducto);
		
		Usuario user2 = DAOFactory.getUsuariosDao().findByUsername(user.getNombre());
		req.getSession().setAttribute("user", user2);
		req.setAttribute("producto", nombreProducto);
		
		if (errors.isEmpty()) {
			req.setAttribute("flash", "¡Gracias por comprar!");
		} else {
			req.setAttribute("errors", errors);
			req.setAttribute("flash", "No ha podido realizarse la compra");
		}
		
		RequestDispatcher dispatcher = getServletContext()
				.getRequestDispatcher("/views/itemDetail/itemDetail.jsp?producto=" + nombreProducto);
		
		dispatcher.forward(req, resp);
	}
}
