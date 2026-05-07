package scoremanager;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Student;
import bean.TestListStudent;
import dao.ClassNumDao;
import dao.StudentDao;
import dao.SubjectDao;
import dao.TestListStudentDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class TestListStudentExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        // 学生番号を取得
        String studentNo = request.getParameter("studentNo");
        
        // DAO 準備
        StudentDao sDao = new StudentDao();
        ClassNumDao cDao = new ClassNumDao();
        SubjectDao subDao = new SubjectDao();

        HttpSession session = request.getSession();

	    // セッションに保存した学校情報を取得
	    School school = (School)session.getAttribute("loginUserSchool");		
        
        // 検索プルダウン用データ
        LocalDate now = LocalDate.now();
        int year = now.getYear();

        List<Integer> entYearSet = new ArrayList<>();
        for (int i = 2021; i <= year; i++) {
            entYearSet.add(i);
        }
        request.setAttribute("ent_year_set", entYearSet);
        request.setAttribute("class_num_set", cDao.filter(school));
        request.setAttribute("school_subject_set", subDao.filter(school));

        // 学生番号の存在チェック
        Student student = sDao.get(studentNo);

        if (student == null) {
            request.setAttribute("error_message", "該当する学生が見つかりませんでした。");
            request.getRequestDispatcher("/scoremanager/test_list_student.jsp").forward(request, response);
            return;
        }

        // 成績一覧を取得
        TestListStudentDao tlsDao = new TestListStudentDao();
        List<TestListStudent> list = tlsDao.filter(student);

        // JSP に渡す
        request.setAttribute("student", student);
        request.setAttribute("list", list);

        request.getRequestDispatcher("/scoremanager/test_list_student.jsp").forward(request, response);
        return;
    }
}
