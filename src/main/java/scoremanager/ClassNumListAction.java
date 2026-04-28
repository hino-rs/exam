package scoremanager;

import java.util.List;

import bean.ClassNum;
import dao.ClassNumDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class ClassNumListAction extends Action {
	
	@Override
	public void execute(
		HttpServletRequest req, HttpServletResponse res
	) throws Exception {
		tool.Logger.execute("ClassNumListAction");
		
		List<ClassNum> class_num_all = null;
		
		ClassNumDao dao = new ClassNumDao();
		
		class_num_all = dao.getAll();
		
		req.setAttribute("class_num_all", class_num_all);
		req.getRequestDispatcher("class_num_list.jsp").forward(req, res);
	}
}