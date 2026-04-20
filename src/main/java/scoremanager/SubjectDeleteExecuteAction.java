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
		System.out.println(">> SubjectDeleteExecuteAction");
		
		HttpSession session = request.getSession();
		School loginUserSchool = (School) session.getAttribute("loginUserSchool");
		
		String cd = (String) request.getParameter("cd");
		String name = (String) request.getParameter("name");
		
		Subject s = new Subject();
		s.setCd(cd);
		s.setName(name);
		s.setSchool(loginUserSchool);
		
		SubjectDao dao = new SubjectDao();
		if (dao.delete(s)) {
			request.getRequestDispatcher("subject_delete_done.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}
}
