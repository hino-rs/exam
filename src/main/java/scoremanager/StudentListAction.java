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

public class StudentListAction extends Action {
    
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        // セッションからログイン中の教師情報を取得
        HttpSession session = req.getSession();
        Teacher teacher = (Teacher)session.getAttribute("loginUser");

        // 画面からの入力値（検索条件）
        String entYearStr = "";
        String classNum = "";
        String isAttendStr = "";

        // 検索条件の実体
        int entYear = 0;
        boolean isAttend = true;
        

        
        // 検索結果の学生一覧
        List<Student> students = null;

        // 現在の西暦を取得（入学年度の選択肢）
        LocalDate todaysDate = LocalDate.now();
        int year = todaysDate.getYear();

        // DAO の準備（必ず最初に宣言）
        StudentDao sDao = new StudentDao();
        ClassNumDao cNumDao = new ClassNumDao();

        // クラス一覧（学校ごと）
        List<String> classNumList = cNumDao.filter(teacher.getSchool());
        req.setAttribute("class_num_set", classNumList);
        
        

        // 回数一覧（1〜5）
        List<Integer> numCountSet = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            numCountSet.add(i);
        }
        req.setAttribute("num_count_set", numCountSet);

        // エラーメッセージ格納
        Map<String, String> errors = new HashMap<>();

        // 画面から送られてきた検索条件を取得
        entYearStr = req.getParameter("f1");
        classNum = req.getParameter("f2");
        isAttendStr = req.getParameter("f3");
        
        if (entYearStr == null && classNum == null && isAttendStr == null) {
            students = sDao.filter(teacher.getSchool(), true) ; 
        } else {
            isAttend = (isAttendStr != null);
        }
        // 入学年度が指定されていれば数値に変換
        if (entYearStr != null && !entYearStr.isEmpty()) {
            entYear = Integer.parseInt(entYearStr);
        }

        // 入学年度の選択肢（現在の年から過去10年分）
        List<Integer> entYearSet = new ArrayList<>();
        for (int i = year - 10; i <= year; i++) {
            entYearSet.add(i);
        }

        // 学生検索
        if (entYear != 0 && classNum != null && !classNum.equals("0")) {
            // 入学年度 + クラス指定あり
            students = sDao.filter(teacher.getSchool(), entYear, classNum, isAttend);

        } else if (entYear != 0 && classNum.equals("0")) {
            // 入学年度のみ指定
            students = sDao.filter(teacher.getSchool(), entYear, isAttend);

        } else if (entYear == 0 && (classNum == null || classNum.equals("0"))) {
            // 指定なし → 在学フラグのみで検索
            students = sDao.filter(teacher.getSchool(), isAttend);

        } else {
            // クラスだけ指定されている場合はエラー
            errors.put("f1", "クラスを指定する場合は入学年度も指定してください");
            req.setAttribute("errors", errors);
            students = sDao.filter(teacher.getSchool(), isAttend);
        }

        // 入学年度とクラス番号を画面に戻す
        req.setAttribute("f1", entYear);
        req.setAttribute("f2", classNum);
        req.setAttribute("f3", isAttendStr);
        

        // JSP に渡すデータをセット
        req.setAttribute("students", students);       // 検索結果
        req.setAttribute("ent_year_set", entYearSet); // 入学年度選択肢

        // JSP へフォワード
        req.getRequestDispatcher("student_list.jsp").forward(req, res);
    }
}
