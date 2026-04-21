package scoremanager;

import java.util.List;

import bean.Teacher;
import dao.TeacherDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class UserListAction extends Action {
	
	@Override
	public void execute(
		HttpServletRequest req, HttpServletResponse res
	) throws Exception {
		tool.Logger.execute("UserListAction");
		
		List<Teacher> teachers = null;
		
		TeacherDao dao = new TeacherDao();
		
		teachers = dao.getAll();
		
		req.setAttribute("teachers", teachers);
		req.getRequestDispatcher("teacher_list.jsp").forward(req, res);
	}
}
