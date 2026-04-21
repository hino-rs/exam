package scoremanager;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class UserUpdateAction extends Action {
	
	@Override
	public void execute(
		HttpServletRequest request, HttpServletResponse response
	) throws Exception {
		tool.Logger.execute("UserUpdateAction");
		
		String id = (String) request.getParameter("id");
		String name = (String) request.getParameter("name");
		String schoolCd = (String) request.getParameter("school_cd");

		request.setAttribute("id", id);
		request.setAttribute("name", name);
		request.setAttribute("schoolCd", schoolCd);
		
		request.getRequestDispatcher("user_update.jsp").forward(request, response);
	}
}
