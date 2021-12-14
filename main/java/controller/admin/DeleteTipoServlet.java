package controller.admin;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import services.AttractionService;
import services.TipoService;

@WebServlet("/views/admin/tipo/delete.do")
public class DeleteTipoServlet extends HttpServlet {

	
	private static final long serialVersionUID = 3914173317187924365L;
	private TipoService tService;

	@Override
	public void init() throws ServletException {
		super.init();
		this.tService = new TipoService();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		String name = req.getParameter("name");

		tService.delete(name);

		resp.sendRedirect("/AgenciaTurismo/views/admin/admin.do");
	}
}
