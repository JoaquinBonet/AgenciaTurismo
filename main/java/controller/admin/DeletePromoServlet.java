package controller.admin;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import services.AttractionService;
import services.PromocionService;

@WebServlet("/views/admin/promos/delete.do")
public class DeletePromoServlet extends HttpServlet {

	private static final long serialVersionUID = -4231348476999727068L;
	private PromocionService pService;

	@Override
	public void init() throws ServletException {
		super.init();
		this.pService = new PromocionService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String name = req.getParameter("name");
		pService.delete(name);
		
		resp.sendRedirect("/AgenciaTurismo/views/admin/admin.do");
	}
}
