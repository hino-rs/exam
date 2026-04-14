package action;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class MenuAction extends Action {
	
	@Override
	public void execute(
		HttpServletRequest request, HttpServletResponse response
	) throws Exception {
		System.out.println("hello MenuAction");
		request.getRequestDispatcher("mennu.jsp").forward(request, response);
	}
}
