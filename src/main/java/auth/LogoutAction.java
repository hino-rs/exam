package auth;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class LogoutAction extends Action {
	@Override
	public void execute(
		HttpServletRequest request, HttpServletResponse response
	) throws Exception {
		HttpSession session=request.getSession();

		if (session.getAttribute("customer")!=null) {
			session.removeAttribute("customer");
			request.getRequestDispatcher("logout-out.jsp").forward(request, response);
			return;
		}

		request.getRequestDispatcher("logout-error.jsp").forward(request, response);
	}
}