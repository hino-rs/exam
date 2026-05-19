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

        // 検索条件の実体
        int entYear = 0;
        String classNum = "0";
        Boolean isAttend = false; // null = 全件、true = 在学中のみ
        
        // 検索結果の学生一覧
        List<Student> students = null;
        // DAO
        StudentDao sDao = new StudentDao();
        ClassNumDao cNumDao = new ClassNumDao();

        // クラス一覧（学校ごと）
        List<String> classNumList = cNumDao.filter(teacher.getSchool());
        req.setAttribute("class_num_set", classNumList);


        // 画面から送られてきた検索条件を取得
        String entYearStr = req.getParameter("f1");
        classNum = req.getParameter("f2");
        String isAttendStr = req.getParameter("f3");
        
        // 入学年度の変換
        if (entYearStr != null && !entYearStr.equals("0") && !entYearStr.isEmpty()) {
            entYear = Integer.parseInt(entYearStr);
        }
        
        // 入学年度の選択肢
        LocalDate todaysDate = LocalDate.now();
        int year = todaysDate.getYear();
        
        List<Integer> entYearSet = new ArrayList<>();
        for (int i = year - 10; i <= year; i++) {
            entYearSet.add(i);
        }
        
        // 在学中チェック
        if (isAttendStr != null) {
            isAttend = true; // チェックあり → 在学中のみ
        } else {
            isAttend = false; // チェックなし → 全件
        }
                
        // エラーメッセージ格納
        Map<String, String> errors = new HashMap<>();        

        // 学生検索
        if (entYear != 0 && classNum != null && !classNum.equals("0")) {
            // 入学年度 + クラス指定あり
        	if (isAttendStr == null) {
        		students = sDao.filter(teacher.getSchool(), entYear, classNum);
        	} else {
        		students = sDao.filter(teacher.getSchool(), entYear, classNum , true);
        	}

        } else if (entYear != 0 && classNum.equals("0")) {
            // 入学年度のみ指定
        	if (isAttendStr == null) {
        		students = sDao.filter(teacher.getSchool(), entYear);
        	}else {
				students = sDao.filter(teacher.getSchool(), entYear, true);
			}

        } else if (entYear == 0 && (classNum == null || classNum.equals("0"))) {
            // 指定なし → 在学フラグのみで検索
        	if (!isAttend) {
        		students = sDao.filter(teacher.getSchool());
        	}else {
				students = sDao.filter(teacher.getSchool(), true);
			}

        } else {
            // クラスだけ指定されている場合はエラー
            errors.put("f1", "クラスを指定する場合は入学年度も指定してください");
            req.setAttribute("errors", errors);
            if (isAttend == false) {
                students = sDao.filter(teacher.getSchool());
            } else {
                students = sDao.filter(teacher.getSchool(), true);
            }
        }

        // 入学年度とクラス番号を画面に戻す
        req.setAttribute("f1", entYearStr);
        req.setAttribute("f2", classNum);
        req.setAttribute("f3", isAttendStr);
        

        // JSP に渡すデータをセット
        req.setAttribute("students", students);       // 検索結果
        req.setAttribute("ent_year_set", entYearSet); // 入学年度選択肢

        // JSP へフォワード
        req.getRequestDispatcher("student_list.jsp").forward(req, res);
    }
}
