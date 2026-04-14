package scoremanager;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class LoginAction extends Action {
	
	@Override
	public void execute(
		HttpServletRequest request, HttpServletResponse response
	) throws Exception {
		System.out.println("hello LoginAction");
		request.getRequestDispatcher("login.jsp").forward(request, response);
	}
}
