package scoremanager;

import java.util.List;

import dao.SchoolDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class ClassNumCreateAction extends Action {
	
	@Override
	public void execute(
		HttpServletRequest request, HttpServletResponse response
	) throws Exception {
		tool.Logger.execute("UserCreateAction");
		
		List<String> class_num_all = null;
		
		SchoolDao dao = new SchoolDao();
		
		class_num_all = dao.getAllSchoolCd();
		
		request.setAttribute("class_num_list", class_num_all);

		request.getRequestDispatcher("class_num_create.jsp").forward(request, response);
	}
}
