package scoremanager;

import bean.School;
import dao.SchoolDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class SchoolCreateExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
    throws Exception {

        // 入力値の取得
        String cd = request.getParameter("cd");
        String name = request.getParameter("name");

        SchoolDao dao = new SchoolDao();

        School exist = dao.get(cd);

        if (exist.getCd() != null) {
            // すでに登録されている場合はエラー表示
            request.setAttribute("error", "この学校コードはすでに登録されています");
            request.getRequestDispatcher("school_create.jsp")
                   .forward(request, response);
            return;
        }


        School s = new School();
        s.setCd(cd);
        s.setName(name);

        dao.save(s);

        // 完了画面へ
        request.getRequestDispatcher("school_create_done.jsp")
               .forward(request, response);
    }
}
