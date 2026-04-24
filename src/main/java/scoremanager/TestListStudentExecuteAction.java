package scoremanager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bean.Student;
import bean.TestListStudent;
import dao.StudentDao;
import dao.TestListStudentDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class TestListStudentExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        // 学生番号を取得
        String studentNo = request.getParameter("studentNo");

        Map<String, String> errors = new HashMap<>();

        // 入力チェック
        if (studentNo == null || studentNo.isEmpty()) {
            errors.put("studentNo", "学生番号を入力してください");
            request.setAttribute("errors", errors);

            request.getRequestDispatcher("/scoremanager/test_list_student.jsp").forward(request, response);
            return;
        }

        // 学生番号の存在チェック
        StudentDao sDao = new StudentDao();
        Student student = sDao.get(studentNo);

        if (student == null) {
            errors.put("studentNo", "該当する学生が見つかりません");
            request.setAttribute("errors", errors);

            request.getRequestDispatcher("/scoremanager/test_list_student.jsp").forward(request, response);
            return;
        }

        // 成績一覧を取得（TestListStudentDao）
        TestListStudentDao tlsDao = new TestListStudentDao();
        List<TestListStudent> list = tlsDao.filter(student);

        // JSP に渡す
        request.setAttribute("student", student);
        request.setAttribute("list", list);

        request.getRequestDispatcher("/scoremanager/test_list_student.jsp").forward(request, response);
        return;
    }
}
