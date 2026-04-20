package scoremanager;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class SubjectCreateAction extends Action {
	
	@Override
	public void execute(
		HttpServletRequest request, HttpServletResponse response
	) throws Exception {
		tool.Logger.execute("SubjectCreateAction");
		
		request.getRequestDispatcher("subject_create.jsp").forward(request, response);
	}
}
