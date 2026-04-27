package scoremanager;

import java.util.List;

import bean.School;
import bean.Subject;
import bean.TestListSubject;
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
		
		String entYearStr = request.getParameter("f1");
		String classNum = request.getParameter("f2");
		String subjectCd = request.getParameter("f3");
	
		if ((entYearStr.isEmpty()) || (classNum.isEmpty()) || (subjectCd.isEmpty())) {
			request.setAttribute("inErr", "入学年度とクラスと科目を選択してください");
			request.getRequestDispatcher("TestList.action").forward(request, response);
		}
		
		int entYear = Integer.parseInt(entYearStr);
		
		School school = (School)session.getAttribute("loginUserSchool");
		Subject subject = sDao.get(subjectCd);
		
		List<TestListSubject> data = tDao.filter(entYear, classNum, subject, school.getCd());
		
		if (data.size() == 0) {
			request.setAttribute("outErr", "学生情報が存在しませんでした");
		} else {
			request.setAttribute("data", data);
		}
		
		request.getRequestDispatcher("test_list_subject.jsp").forward(request, response);
	}
}
