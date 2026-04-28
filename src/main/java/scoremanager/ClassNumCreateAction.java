package scoremanager;

import java.util.List;

import bean.ClassNum;
import dao.ClassNumDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class ClassNumCreateAction extends Action {
	
	@Override
	public void execute(
		HttpServletRequest request, HttpServletResponse response
	) throws Exception {
		tool.Logger.execute("UserCreateAction");
		
		List<ClassNum> class_num_all = null;
		
		ClassNumDao dao = new ClassNumDao();
		
		class_num_all = dao.getAll();
		
		request.setAttribute("class_num_list", class_num_all);

		request.getRequestDispatcher("class_num_create.jsp").forward(request, response);
	}
}

