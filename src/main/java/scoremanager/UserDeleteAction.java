package scoremanager;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class UserDeleteAction extends Action {
	
	@Override
	public void execute(
		HttpServletRequest request, HttpServletResponse response
	) throws Exception {
		tool.Logger.execute("UserDeleteAction");
		
		String id = (String) request.getParameter("id");
		String name = (String) request.getParameter("name");

		request.setAttribute("id", id);
		request.setAttribute("name", name);
		
		request.getRequestDispatcher("user_delete.jsp").forward(request, response);
	}
}
