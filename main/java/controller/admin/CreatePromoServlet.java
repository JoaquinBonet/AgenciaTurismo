package controller.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Atraccion;
import model.Promocion;
import model.PromocionAbsoluta;
import model.PromocionAxB;
import model.PromocionPorcentual;
import services.AttractionService;
import services.PromocionService;

@WebServlet("/views/admin/promos/create.do")
public class CreatePromoServlet extends HttpServlet {

	private static final long serialVersionUID = -8446020399836998781L;
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

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/admin/promos/create.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("name");
		String tipo = req.getParameter("tipo");
		String info = req.getParameter("info");
		String img = req.getParameter("img");
		String atraccionesString = req.getParameter("atracciones");
		ArrayList<Atraccion> atracciones = new ArrayList();
		
	
		for (String cs : atraccionesString.split(", ")) {
			Atraccion a = null;
			if (aService.find(cs) != null) {
				a = (Atraccion) aService.find(cs);
			}
			atracciones.add(a);
			System.out.println(atracciones);
		}

		Promocion promo = null;
		if (tipo.toLowerCase().equals("promocion absoluta") || tipo.toLowerCase().equals("absoluta")) {

			Integer costo = Integer.parseInt(req.getParameter("cost"));
			promo = new PromocionAbsoluta(atracciones, name, info, costo, img);
		}

		if (tipo.toLowerCase().equals("promocion porcentual") || tipo.toLowerCase().equals("porcentual")) {

			Integer porcentaje = Integer.parseInt(req.getParameter("porcentaje"));
			 promo = new PromocionPorcentual(atracciones, name, info, porcentaje, img);
		}

		if (tipo.toLowerCase().equals("promocion axb") || tipo.toLowerCase().equals("axb")) {

			promo = new PromocionAxB(atracciones, name, info, img);
		}
		
		if (promo.isValid()) {
			pService.create(promo);
			resp.sendRedirect("/AgenciaTurismo/views/admin/admin.do");
		}else {
			req.setAttribute("promo", promo);

			RequestDispatcher dispatcher = getServletContext()
					.getRequestDispatcher("/views/admin/promos/create.jsp");
			dispatcher.forward(req, resp);
		}

	}
}
