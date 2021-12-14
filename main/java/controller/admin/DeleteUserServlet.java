package controller.admin;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import services.TipoService;
import services.UserService;

@WebServlet("/views/admin/users/delete.do")
public class DeleteUserServlet extends HttpServlet {

	private static final long serialVersionUID = -903930329058233805L;
	private UserService uService;

	@Override
	public void init() throws ServletException {
		super.init();
		this.uService = new UserService();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		String name = req.getParameter("name");

		uService.delete(name);

		resp.sendRedirect("/AgenciaTurismo/views/admin/admin.do");
	}
}
