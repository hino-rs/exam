package scoremanager;

import bean.School;
import dao.SchoolDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class SchoolUpdateAction extends Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
    throws Exception {

        String school_cd = request.getParameter("school_cd");

        SchoolDao sdao = new SchoolDao();
        School school = sdao.get(school_cd);

        request.setAttribute("school", school);

        request.getRequestDispatcher("school_update.jsp")
               .forward(request, response);
    }
}
