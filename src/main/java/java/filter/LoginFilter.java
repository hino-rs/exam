//package filter;
//
//import java.io.IOException;
//
//import jakarta.servlet.Filter;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.FilterConfig;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.ServletRequest;
//import jakarta.servlet.ServletResponse;
//import jakarta.servlet.annotation.WebFilter;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import jakarta.servlet.http.HttpSession;
//
//@WebFilter(urlPatterns={"/*"})
//public class LoginFilter implements Filter {
//
//	public void doFilter(
//		ServletRequest request, ServletResponse response,
//		FilterChain chain
//	) throws IOException, ServletException {
//		System.out.println("hello LoginFilter");
//		
//		HttpServletRequest httpReq = (HttpServletRequest) request;
//		HttpServletResponse httpRes = (HttpServletResponse) response;
//		
//		String path = httpReq.getRequestURI();
//		if (path.startsWith("/exam/LoginAction.action") || path.startsWith("/exam/css/")) {
//			return;
//		}
//		
//		HttpSession session = httpReq.getSession();
//		
//		Object isLogin = session.getAttribute("isLogin");
//		
//		if (isLogin == null) {
//			System.out.println("to login.jsp");
//			httpReq.getRequestDispatcher("login.jsp").forward(httpReq, httpRes);
//		}
//	}
//
//	public void init(FilterConfig filterConfig) {}
//	public void destroy() {}
//}
