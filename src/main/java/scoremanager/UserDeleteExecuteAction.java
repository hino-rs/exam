package scoremanager;

import bean.Teacher;
import dao.TeacherDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class UserDeleteExecuteAction extends Action {
	
	@Override
	public void execute(
		HttpServletRequest request, HttpServletResponse response
	) throws Exception {
		tool.Logger.execute("UserDeleteExecuteAction");
		HttpSession session = request.getSession();
		Teacher loginUser = (Teacher) session.getAttribute("loginUser");
		
		String loginId = loginUser.getId();
		String deleteId = (String) request.getParameter("id");
		
		TeacherDao dao = new TeacherDao();
		
		if (loginId.equals(deleteId)) {
			request.setAttribute("targetIsYourself", "あなた自身を消すことはできません");
			request.getRequestDispatcher("user_delete.jsp").forward(request, response);
		} else if (dao.delete(deleteId)) {
			tool.Logger.info("ユーザー削除に成功");
			request.getRequestDispatcher("user_delete_done.jsp").forward(request, response);
		} else {
			tool.Logger.error("ユーザー削除に失敗");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}
}
