package controller.admin;

import java.io.IOException;
import java.util.ArrayList;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Atraccion;
import model.Promocion;
import services.AttractionService;
import services.PromocionService;

@WebServlet("/views/admin/promos/edit.do")
public class EditPromoServlet extends HttpServlet {


	private static final long serialVersionUID = 3436509470164063032L;
	private PromocionService pService;
	private AttractionService aService;

	@Override
	public void init() throws ServletException {
		super.init();
		this.pService = new PromocionService();
		this.aService = new AttractionService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String nombre = req.getParameter("name");

		Promocion p = (Promocion) pService.find(nombre);
		req.setAttribute("promo", p);

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/admin/promos/edit.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("name");
		String info = req.getParameter("info");
		String img = req.getParameter("img");
		String atraccionesString = req.getParameter("atracciones");
		ArrayList<Atraccion> atracciones = new ArrayList();
		Integer costo = 0;
		Integer porcentaje = 0;
		if (req.getParameter("cost") != null) {
			costo = Integer.parseInt(req.getParameter("cost"));
		}
		if (req.getParameter("porcentaje") != null) {
			porcentaje = Integer.parseInt(req.getParameter("porcentaje"));
		}
	
		
		for (String cs : atraccionesString.split(", ")) {
			Atraccion a = null;
			if (aService.find(cs) != null) {
				a = (Atraccion) aService.find(cs);
			}
			atracciones.add(a);
		}
		
		Promocion p = pService.update(atracciones, name, info, img, costo, porcentaje );

		if (p.isValid()) {
			resp.sendRedirect("/AgenciaTurismo/views/admin/admin.do");
		} else {
			req.setAttribute("promo", p);

			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/admin/promos/edit.jsp");
			dispatcher.forward(req, resp);
		}
	}
}
