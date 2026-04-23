package scoremanager;

import bean.School;
import bean.Subject;
import dao.SubjectDao;
import dao.TestListSubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class TestListSubjectExecuteAction extends Action {
	
	@Override
	public void execute(
		HttpServletRequest request, HttpServletResponse response
	) throws Exception {
		tool.Logger.execute("TestListSubjectExecuteAction");
		
		HttpSession session = request.getSession();
		
		SubjectDao sDao = new SubjectDao();
		TestListSubjectDao tDao = new TestListSubjectDao();
		
		int entYear = Integer.parseInt((String)request.getParameter("f1"));
		String classNum = request.getParameter("f2");
		String subjectCd = request.getParameter("f3");
		
		School school = (School)session.getAttribute("loginUserSchool");
		Subject subject = sDao.get(subjectCd);
		
		request.setAttribute("test_list_subject", tDao.filter(entYear, classNum, subject, school));
		request.getRequestDispatcher("").forward(request, response);
	}
}
