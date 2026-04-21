package scoremanager;

import bean.School;
import bean.Subject;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class SubjectDeleteExecuteAction extends Action {
	
	@Override
	public void execute(
		HttpServletRequest request, HttpServletResponse response
	) throws Exception {
		tool.Logger.execute("SubjectDeleteExecuteAction");
		
		HttpSession session = request.getSession();
		
		String cd = (String) request.getParameter("cd");
		String name = (String) request.getParameter("name");
		School loginUserSchool = (School) session.getAttribute("loginUserSchool");
		
		Subject s = new Subject();
		
		s.setCd(cd);
		s.setName(name);
		s.setSchool(loginUserSchool);
		
		
		SubjectDao dao = new SubjectDao();
		
		if (dao.delete(s)) {
			tool.Logger.info("科目削除に成功");
			request.getRequestDispatcher("subject_delete_done.jsp").forward(request, response);
		} else {
			tool.Logger.error("科目削除に失敗");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}
}
