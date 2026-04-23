package scoremanager;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Subject;
import bean.Teacher;
import dao.ClassNumDao;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class TestListAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        HttpSession session = req.getSession();

        // ログイン中の教師取得
        Teacher teacher = (Teacher)session.getAttribute("loginUser");
        School school = teacher.getSchool(); 

        // パラメータ（selected 用）
        String f1 = req.getParameter("f1");
        String f2 = req.getParameter("f2");
        String f3 = req.getParameter("f3");

        req.setAttribute("f1", f1);
        req.setAttribute("f2", f2);
        req.setAttribute("f3", f3);

        // 入学年度セット
        LocalDate now = LocalDate.now();
        int year = now.getYear();

        List<Integer> entYearSet = new ArrayList<>();
        for (int i = year - 10; i <= year; i++) {
            entYearSet.add(i);
        }
        req.setAttribute("ent_year_set", entYearSet);

        // クラス一覧
        ClassNumDao cDao = new ClassNumDao();
        List<String> classList = cDao.filter(school);
        req.setAttribute("class_num_set", classList);

        // 科目一覧
        SubjectDao sDao = new SubjectDao();
        List<Subject> subjects = sDao.filter(school);
        req.setAttribute("school_subject_set", subjects);

        // 検索画面へ
        req.getRequestDispatcher("test_list.jsp").forward(req, res);
    }
}
