package scoremanager;

import dao.TeacherDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class UserDeleteExecuteAction extends Action {
	
	@Override
	public void execute(
		HttpServletRequest request, HttpServletResponse response
	) throws Exception {
		tool.Logger.execute("UserDeleteExecuteAction");
		
		String id = (String) request.getParameter("id");
		
		TeacherDao dao = new TeacherDao();
		
		if (dao.delete(id)) {
			tool.Logger.info("ユーザー削除に成功");
			request.getRequestDispatcher("user_delete_done.jsp").forward(request, response);
		} else {
			tool.Logger.error("ユーザー削除に失敗");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}
}
