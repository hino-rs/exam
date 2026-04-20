package scoremanager;

import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class SubjectUpdateExecuteAction extends Action {
	
	@Override
	public void execute(
		HttpServletRequest request, HttpServletResponse response
	) throws Exception {
		tool.Logger.execute("SubjectUpdateExecuteAction");
		
		String cd = (String) request.getParameter("cd");
		String name = (String) request.getParameter("name");
		
		SubjectDao dao = new SubjectDao();
		if (dao.update(cd, name)) {
			request.getRequestDispatcher("subject_update_done.jsp").forward(request, response);
		} else {
			request.setAttribute("error", "科目が存在していません");
			request.getRequestDispatcher("subject_update.jsp").forward(request, response);
		}
	}
}
