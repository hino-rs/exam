package scoremanager;

import bean.Teacher;
import dao.SchoolDao;
import dao.TeacherDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class UserCreateExecuteAction extends Action {
	
	@Override
	public void execute(
		HttpServletRequest request, HttpServletResponse response
	) throws Exception {
		tool.Logger.execute("UserCreateExecuteAction");
		
		Teacher t = new Teacher();
		SchoolDao sDao = new SchoolDao();
		
		t.setId((String)request.getParameter("id"));
		t.setName((String)request.getParameter("name"));
		t.setPassword((String)request.getParameter("password"));
		t.setSchool(sDao.get((String)request.getParameter("school_cd")));
	
		TeacherDao tDao = new TeacherDao();

		// 重複チェック
		if (!tDao.isUnique(t.getId())) {
			tool.Logger.error("変更後のIDが重複");
			request.setAttribute("error", "IDが重複しています");
			request.getRequestDispatcher("UserCreate.action").forward(request, response);
		}
		
		// 登録処理
		if (tDao.create(t)) {
			tool.Logger.info("ユーザー登録成功");
			request.getRequestDispatcher("user_create_done.jsp").forward(request, response);
		} else {
			tool.Logger.error("ユーザー登録失敗");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}
}