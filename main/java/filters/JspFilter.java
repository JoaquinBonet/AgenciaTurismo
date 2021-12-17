/*package filters;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;

@WebFilter("*.jsp")
public class JspFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                          FilterChain chain)  throws IOException, ServletException {
        RequestDispatcher dispatcher = ((HttpServletRequest) request).getRequestDispatcher("/login.jsp");
       dispatcher.forward(request,response);
    }
}
*/
