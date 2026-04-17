package scoremanager;

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

public class TestRegistAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        System.out.println(">> TestRegistAction");

        HttpSession session = req.getSession();

        // ログイン中の教師を取得
        Teacher teacher = (Teacher)session.getAttribute("loginUser");

        // 学校情報を取得
        School school = teacher.getSchool();

        // クラス一覧を取得
        ClassNumDao cDao = new ClassNumDao();
        List<String> classList = cDao.filter(school);

        req.setAttribute("classnum", classList);

        // 科目一覧取得（成績登録画面用）
        SubjectDao sDao = new SubjectDao();
        List<Subject> subjects = sDao.filter(school);
        req.setAttribute("subjects", subjects);

        // JSP へフォワード
        req.getRequestDispatcher("test_regist.jsp").forward(req, res);
    }
}
