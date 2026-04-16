package scoremanager;

import bean.Teacher;
import dao.TeacherDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class LoginExecuteAction extends Action {
	
	@Override
	public void execute(
		HttpServletRequest request, HttpServletResponse response
	) throws Exception {
		System.out.println("[LoginExecuteAction]: hello");
		HttpSession session=request.getSession();

		String id = request.getParameter("id");
		String password = request.getParameter("password");

		System.out.println("[LoginExecuteAction]: id{"+id+"} pw{"+password+"}");

		TeacherDao dao = new TeacherDao();
		Object loginData = dao.login(id, password);

		if (loginData != null) {
			System.out.println("ログイン成功");

			Teacher teacher = (Teacher)loginData;
			session.setAttribute("loginUser", teacher);
			session.setAttribute("loginUserName", teacher.getName());
			session.setAttribute("loginUserSchool", teacher.getSchool());

			MenuAction menu = new MenuAction();
			menu.execute(request, response);
		} else {
			System.out.println("ログイン失敗");
			request.setAttribute("errors", "IDまたはパスワードが確認できませんでした");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
	}
}