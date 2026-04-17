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
		System.out.println("\u001b[00;36m"+"SubjectCreateExecuteAction"+"\u001b[00m");
		HttpSession session = request.getSession();
		School school = (School)session.getAttribute("loginUserSchool");
		String schoolCd = school.getCd();
		
		String cd = request.getParameter("cd");
		String name = request.getParameter("name");
		
		if (cd.length() != 3) {
			tool.Logger.warn("科目コードが不正: "+cd);
			request.setAttribute("error", "科目コードは3文字で入力してください");
			request.getRequestDispatcher("subject_create.jsp").forward(request, response);
		}

		SubjectDao dao = new SubjectDao();

		if (dao.save(cd, name, schoolCd)) {
			tool.Logger.info("科目登録成功");
			request.getRequestDispatcher("subject_create_done.jsp").forward(request, response);
		} else {
			tool.Logger.warn("科目が重複しています: "+cd);
			request.setAttribute("error", "科目コードが重複しています");
			request.getRequestDispatcher("subject_create.jsp").forward(request, response);
		}
	}
}