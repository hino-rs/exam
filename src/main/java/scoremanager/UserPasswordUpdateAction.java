package scoremanager;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class UserPasswordUpdateAction extends Action {
	
	@Override
	public void execute(
		HttpServletRequest request, HttpServletResponse response
	) throws Exception {
		tool.Logger.execute("UserPasswordUpdateAction");
		
		String id = (String) request.getParameter("id");
		request.setAttribute("id", id);
		
		request.getRequestDispatcher("user_password_update.jsp").forward(request, response);
	}
}
