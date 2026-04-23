package scoremanager;

import bean.Teacher;
import dao.SchoolDao;
import dao.TeacherDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class UserUpdateExecuteAction extends Action {
	
	@Override
	public void execute(
		HttpServletRequest request, HttpServletResponse response
	) throws Exception {
		tool.Logger.execute("UserUpdateExecuteAction");
		
		String id = (String) request.getParameter("id");
		String name = (String) request.getParameter("name");
		String schoolCd = (String) request.getParameter("school_cd");
		
		Teacher t = new Teacher();
		
		SchoolDao sDao = new SchoolDao();
		
		t.setId(id);
		t.setName(name);
		t.setSchool(sDao.get(schoolCd));
		
		TeacherDao tDao = new TeacherDao();
		if (tDao.update(t)) {
			request.getRequestDispatcher("user_update_done.jsp").forward(request, response);
		} else {
			request.setAttribute("error", "ユーザーが存在していません");
			request.getRequestDispatcher("user_update.jsp").forward(request, response);
		}
	}
}
