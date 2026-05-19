package scoremanager;

import bean.School;
import dao.SchoolDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class ClassNumDeleteConfirmAction extends Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
    throws Exception {

        String school_cd = request.getParameter("school_cd");
        String class_num = request.getParameter("class_num");

        // 学校名を表示するため School オブジェクトを取得
        SchoolDao schoolDao = new SchoolDao();
        School school = schoolDao.get(school_cd);

        request.setAttribute("school", school);
        request.setAttribute("class_num", class_num);

        request.getRequestDispatcher("class_num_delete_confirm.jsp")
               .forward(request, response);
    }
}
