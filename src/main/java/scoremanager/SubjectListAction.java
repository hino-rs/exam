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
		HttpSession session = req.getSession();
		
		School loginUserSchool = (School)session.getAttribute("loginUserSchool");
		System.out.println("ログイン中ユーザーの学校コード: "+loginUserSchool.getCd());		
		
		List<Subject> subjects = null;
		
		SubjectDao sDao = new SubjectDao();
		subjects = sDao.filter(loginUserSchool);
		req.setAttribute("subjects", subjects);
		System.out.println("subjects: "+subjects);
		
		req.getRequestDispatcher("subject-list.jsp").forward(req, res);
	}
}