package scoremanager;

import dao.TeacherDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class UserPasswordUpdateExecuteAction extends Action {
	
	@Override
	public void execute(
		HttpServletRequest request, HttpServletResponse response
	) throws Exception {
		tool.Logger.execute("UserPasswordUpdateExecuteAction");
		
		String id = (String) request.getParameter("id");
		String oldPassword = (String) request.getParameter("old_password");
		String newPassword = (String) request.getParameter("new_password");
		
		TeacherDao tDao = new TeacherDao();
		if (tDao.updatePassword(id, oldPassword, newPassword)) {
			request.getRequestDispatcher("user_password_update_done.jsp").forward(request, response);
		} else {
			request.setAttribute("error", "パスワードが間違えています。管理者に問い合わせてください。");
			request.getRequestDispatcher("UserPasswordUpdate.action").forward(request, response);
		}
	}
}
