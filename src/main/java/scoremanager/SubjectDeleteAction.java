package scoremanager;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class SubjectDeleteAction extends Action {
	
	@Override
	public void execute(
		HttpServletRequest request, HttpServletResponse response
	) throws Exception {
		System.out.println(">> SubjectDeleteAction");
		
		String cd = (String) request.getParameter("cd");
		String name = (String) request.getParameter("name");

		request.setAttribute("cd", cd);
		request.setAttribute("name", name);
		
		request.getRequestDispatcher("subject_delete.jsp").forward(request, response);
	}
}
