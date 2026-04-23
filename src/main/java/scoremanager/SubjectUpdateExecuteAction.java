package scoremanager;

import bean.School;
import bean.Subject;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class SubjectUpdateExecuteAction extends Action {
	
	@Override
	public void execute(
		HttpServletRequest request, HttpServletResponse response
	) throws Exception {
		tool.Logger.execute("SubjectUpdateExecuteAction");
		
		HttpSession session = request.getSession();
		
		String cd = (String) request.getParameter("cd");
		String name = (String) request.getParameter("name");
		School school = (School) session.getAttribute("loginUserSchool");
		
		Subject subject = new Subject();
		
		subject.setCd(cd);
		subject.setName(name);
		subject.setSchool(school);
		
		SubjectDao dao = new SubjectDao();
		if (dao.save(subject)) {
			request.getRequestDispatcher("subject_update_done.jsp").forward(request, response);
		} else {
			request.setAttribute("error", "科目が存在していません");
			request.getRequestDispatcher("subject_update.jsp").forward(request, response);
		}
	}
}
