package scoremanager;

import bean.School;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class SubjectCreateExecuteAction extends Action {
	
	@Override
	public void execute(
		HttpServletRequest request, HttpServletResponse response
	) throws Exception {
		tool.Logger.execute("SubjectCreateExecuteAction");
		
		HttpSession session = request.getSession();
		School school = (School)session.getAttribute("loginUserSchool");
		String schoolCd = school.getCd();
		
		String cd = request.getParameter("cd");
		String name = request.getParameter("name");
		
		if (cd.length() != 3) {
			tool.Logger.error("変更後の科目コードが不正: "+cd);
			request.setAttribute("error", "科目コードは3文字で入力してください");
			request.getRequestDispatcher("subject_create.jsp").forward(request, response);
		}

		SubjectDao dao = new SubjectDao();
		String result = dao.save(cd, name, schoolCd);

		if (result.equals("SUCCESS")) {
			tool.Logger.info("科目登録成功");
			request.getRequestDispatcher("subject_create_done.jsp").forward(request, response);
		} else if (result.equals("DUPLICATE")) {
			tool.Logger.error("変更後の科目が重複: "+cd);
			request.setAttribute("error", "科目コードが重複しています");
			request.getRequestDispatcher("subject_create.jsp").forward(request, response);
		} else {
			tool.Logger.error("科目登録失敗");
		}
	}
}