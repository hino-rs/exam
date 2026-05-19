package scoremanager;

import java.util.List;

import bean.School;
import dao.SchoolDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class ClassNumCreateAction extends Action {
	
	@Override
	public void execute(
		HttpServletRequest request, HttpServletResponse response
	) throws Exception {
		SchoolDao dao = new SchoolDao();
		// School オブジェクトを取得
        List<School> school_list = dao.getAll();

        request.setAttribute("school_list", school_list);

		request.getRequestDispatcher("class_num_create.jsp").forward(request, response);
	}
}
