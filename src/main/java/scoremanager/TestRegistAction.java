package scoremanager;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Subject;
import bean.Teacher;
import bean.Test;
import dao.ClassNumDao;
import dao.SubjectDao;
import dao.TestDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class TestRegistAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        System.out.println(">> TestRegistAction");

        HttpSession session = req.getSession();

        // ログイン中の教師
        Teacher teacher = (Teacher)session.getAttribute("loginUser");
        School school = teacher.getSchool();

        // パラメータ取得
        String f1 = req.getParameter("f1"); // 入学年度
        String f2 = req.getParameter("f2"); // クラス
        String f3 = req.getParameter("f3"); // 科目コード
        String f4 = req.getParameter("f4"); // 回数

        // JSP に返す（selected のため）
        req.setAttribute("f1", f1);
        req.setAttribute("f2", f2);
        req.setAttribute("f3", f3);
        req.setAttribute("f4", f4);

        // セレクトボックス用データ
        // クラス一覧
        ClassNumDao cDao = new ClassNumDao();
        List<String> classList = cDao.filter(school);
        req.setAttribute("class_num_set", classList);

        // 科目一覧
        SubjectDao sDao = new SubjectDao();
        List<Subject> subjects = sDao.filter(school);
        req.setAttribute("school_subject_set", subjects);

        // 回数一覧（1〜5）
        List<Integer> numCountSet = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            numCountSet.add(i);
        }
        req.setAttribute("num_count_set", numCountSet);

        // 入学年度一覧（現在の年から過去10年）
        LocalDate now = LocalDate.now();
        int year = now.getYear();
        List<Integer> entYearSet = new ArrayList<>();
        for (int i = year - 10; i <= year; i++) {
            entYearSet.add(i);
        }
        req.setAttribute("ent_year_set", entYearSet);

        // 検索処理（f1〜f4 が揃っているときだけ）
        if (f1 != null && f2 != null && f3 != null && f4 != null &&
                !f1.equals("0") && !f2.equals("0") && !f3.equals("0") && !f4.equals("0")) {

                int entYear = Integer.parseInt(f1);
                int num = Integer.parseInt(f4);

                Subject subject = new Subject();
                subject.setCd(f3);

                TestDao tDao = new TestDao();
                List<Test> list = tDao.filter(entYear, f2, subject, num, school);

                // 検索結果を JSP に渡す
                req.setAttribute("test_list", list);

                // 科目名を JSP に渡す
                for (Subject s : subjects) {
                    if (s.getCd().equals(f3)) {
                        req.setAttribute("subject_name", s.getName());
                        break;
                    }
                }
            }

            // JSP へフォワード
            req.getRequestDispatcher("test_regist.jsp").forward(req, res);
        }
    }