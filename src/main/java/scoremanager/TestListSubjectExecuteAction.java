package scoremanager;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Subject;
import bean.TestListSubject;
import dao.ClassNumDao;
import dao.StudentDao;
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
		
        // セッションに保存したログインユーザーの学校情報取得
        School school = (School)session.getAttribute("loginUserSchool");
		
        // プルダウン用 DAO
        StudentDao stuDao = new StudentDao();
        ClassNumDao cDao = new ClassNumDao();
        SubjectDao subDao = new SubjectDao();
        TestListSubjectDao tDao = new TestListSubjectDao();

        // プルダウンセット
        LocalDate now = LocalDate.now();
        int year = now.getYear();

        List<Integer> entYearSet = new ArrayList<>();
        for (int i = 2021; i <= year; i++) {
            entYearSet.add(i);
        }
        request.setAttribute("ent_year_set", entYearSet);
        request.setAttribute("class_num_set", cDao.filter(school));
        request.setAttribute("school_subject_set", subDao.filter(school));
		
		String entYearStr = request.getParameter("f1");
		String classNum = request.getParameter("f2");
		String subjectCd = request.getParameter("f3");
	
		if ((entYearStr.isEmpty()) || (classNum.isEmpty()) || (subjectCd.isEmpty())) {
			request.setAttribute("inErr", "入学年度とクラスと科目を選択してください");
			request.getRequestDispatcher("TestList.action").forward(request, response);
			return;
		}
		
		int entYear = Integer.parseInt(entYearStr);

		Subject subject = subDao.get(subjectCd);
		
		List<TestListSubject> data = tDao.filter(entYear, classNum, subject, school.getCd());
		
		if (data.size() == 0) {
			request.setAttribute("outErr", "学生情報が存在しませんでした");
		} else {
			request.setAttribute("data", data);
		}
		
		// 検索条件再表示用
		request.setAttribute("selectedEntYear", entYear);
		request.setAttribute("selectedClassNum", classNum);
		request.setAttribute("selectedSubjectCd", subjectCd);
		
		request.getRequestDispatcher("test_list_subject.jsp").forward(request, response);
	}
}
