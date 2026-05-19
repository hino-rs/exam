package scoremanager;

import bean.ClassNum;
import bean.School;
import dao.ClassNumDao;
import dao.SchoolDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class ClassNumDeleteExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
    throws Exception {

        String school_cd = request.getParameter("school_cd");
        String class_num = request.getParameter("class_num");

        SchoolDao sdao = new SchoolDao();
        School school = sdao.get(school_cd);

        ClassNumDao cndao = new ClassNumDao();

        // 学生登録がある場合は削除不可
        if (cndao.hasStudents(class_num, school)) {
            request.setAttribute("error", "このクラスには学生が登録されているため削除できません。");
            request.setAttribute("school", school);
            request.setAttribute("class_num", class_num);
            request.getRequestDispatcher("class_num_delete_confirm.jsp")
                   .forward(request, response);
            return;
        }

        // 成績登録がある場合も削除不可
        if (cndao.hasScores(class_num, school)) {
            request.setAttribute("error", "このクラスには成績データがあるため削除できません。");
            request.setAttribute("school", school);
            request.setAttribute("class_num", class_num);
            request.getRequestDispatcher("class_num_delete_confirm.jsp")
                   .forward(request, response);
            return;
        }

        // クラス削除
        ClassNum cn = cndao.get(class_num, school);
        if (cn != null) {
            cndao.delete(cn);
        }

        // 削除完了画面に渡す
        request.setAttribute("school_name", school.getName());
        request.setAttribute("school_cd", school_cd);
        request.setAttribute("class_num", class_num);

        request.getRequestDispatcher("class_num_delete_done.jsp")
               .forward(request, response);
    }
}
