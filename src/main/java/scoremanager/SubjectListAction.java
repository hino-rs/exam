package scoremanager;

import java.util.List;

import bean.School;
import bean.Subject;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class SubjectListAction extends Action {
	
	@Override
	public void execute(
		HttpServletRequest req, HttpServletResponse res
	) throws Exception {
		tool.Logger.execute("SubjectListAction");
		HttpSession session = req.getSession();
		School loginUserSchool = (School)session.getAttribute("loginUserSchool");
		List<Subject> subjects = null;
		SubjectDao sDao = new SubjectDao();
		subjects = sDao.filter(loginUserSchool);
		req.setAttribute("subjects", subjects);
		req.getRequestDispatcher("subject_list.jsp").forward(req, res);
	}
}
