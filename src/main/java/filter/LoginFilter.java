package filter;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebFilter(urlPatterns="/*")
public class LoginFilter implements Filter {
	public void doFilter(
		ServletRequest request,
		ServletResponse response,
		FilterChain chain
	) throws ServletException, IOException {
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse)response;
		HttpSession session = req.getSession();
		
		String path = req.getRequestURI().substring(req.getContextPath().length());
		tool.Logger.debug(path);
		
		if (path.contains("css") || path.contains("/LoginExecute") || path.contains("login.jsp")) {
	        chain.doFilter(req, res);
	        return;
	    }
		
		if (session.getAttribute("loginUser") == null) {
			req.getRequestDispatcher("login.jsp").forward(req, res);
			return;
		} else {
			chain.doFilter(req, res);
		}
	}
}