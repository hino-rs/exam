package scoremanager;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bean.Student;
import bean.Teacher;
import dao.ClassNumDao;
import dao.StudentDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class TestListAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        HttpSession session = req.getSession();

        // ログイン中の教師を取得
        Teacher teacher = (Teacher)session.getAttribute("loginUser");

        String entYearStr = "";
        String classNum = "";
        String isAttendStr = "";
        int entYear = 0;
        boolean isAttend = false;

        List<Student> students = null;

        LocalDate todaysDate = LocalDate.now();
        int year = todaysDate.getYear();

        StudentDao sDao = new StudentDao();
        ClassNumDao cNumDao = new ClassNumDao();
        Map<String, String> errors = new HashMap<>();

        // パラメータ取得
        entYearStr = req.getParameter("f1");
        classNum = req.getParameter("f2");
        isAttendStr = req.getParameter("f3");

        if (entYearStr != null && !entYearStr.isEmpty()) {
            entYear = Integer.parseInt(entYearStr);
        }

        // 入学年度セット
        List<Integer> entYearSet = new ArrayList<>();
        for (int i = year - 10; i <= year; i++) {
            entYearSet.add(i);
        }

        // クラス一覧
        List<String> classList = cNumDao.filter(teacher.getSchool());

        // 検索条件に応じて学生一覧を取得
        if (entYear != 0 && classNum != null && !classNum.equals("0")) {
            students = sDao.filter(teacher.getSchool(), entYear, classNum, isAttend);
        } else if (entYear != 0 && classNum.equals("0")) {
            students = sDao.filter(teacher.getSchool(), entYear, isAttend);
        } else if (entYear == 0) {
            students = sDao.filter(teacher.getSchool(), isAttend);
        } else {
            errors.put("f1", "クラスを指定する場合は入学年度も指定してください");
            req.setAttribute("errors", errors);
            students = sDao.filter(teacher.getSchool(), isAttend);
        }

        // 画面に渡す値
        req.setAttribute("f1", entYear);
        req.setAttribute("f2", classNum);

        if (isAttendStr != null) {
            isAttend = true;
            req.setAttribute("f3", isAttendStr);
        }

        req.setAttribute("students", students);
        req.setAttribute("class_num_set", classList);
        req.setAttribute("ent_year_set", entYearSet);

        // 成績一覧画面へ
        req.getRequestDispatcher("test_list.jsp").forward(req, res);
    }
}
