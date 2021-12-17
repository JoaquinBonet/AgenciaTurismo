package controller.login;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.Servlet;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Usuario;
import services.LoginService;

@WebServlet(urlPatterns = "/login")
public class LoginServlet extends HttpServlet implements Servlet {

	private static final long serialVersionUID = 2394449831904434113L;

	private LoginService loginService;

	@Override
	public void init() throws ServletException {
		super.init();
		loginService = new LoginService();
	}
	
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	String username = req.getParameter("username");
    	Usuario admin = null;
    	Usuario user = null;
    	
    	if (username.equals("admin")) {
    		admin = loginService.login(username);
    	} else {
    		user = loginService.login(username);
    	}
    	
    	if (admin != null) {
    		req.getSession().setAttribute("admin", admin);
    		resp.sendRedirect("views/admin/admin.do");  
    	} else if (!user.isNull()) {
    		
    		req.getSession().setAttribute("user", user);
    		resp.sendRedirect("views/userHome/userHome.do");    		
       	} else {
    		req.setAttribute("flash", "Nombre de usuario incorrecto");
    		
    		RequestDispatcher dispatcher = getServletContext()
      		      .getRequestDispatcher("/login.jsp");
      		    dispatcher.forward(req, resp);
    	}
    }
}
