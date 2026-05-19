package scoremanager;

import bean.School;
import dao.SchoolDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class SchoolUpdateExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
    throws Exception {

        String cd = request.getParameter("cd");
        String name = request.getParameter("name");

        SchoolDao sdao = new SchoolDao();

        School school = new School();
        school.setCd(cd);
        school.setName(name);

        sdao.update(school);

        request.setAttribute("school", school);

        request.getRequestDispatcher("school_update_done.jsp")
               .forward(request, response);
    }
}
