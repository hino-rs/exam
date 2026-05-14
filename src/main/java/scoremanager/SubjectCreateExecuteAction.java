package scoremanager;

import bean.School;
import bean.Subject;
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
		HttpSession session = request.getSession();
		
		String cd = request.getParameter("cd");
		String name = request.getParameter("name");
		School school = (School)session.getAttribute("loginUserSchool");
		
		request.setAttribute("cd", cd);
		request.setAttribute("name", name);
		
		Subject subject = new Subject();
		
		subject.setCd(cd);
		subject.setName(name);
		subject.setSchool(school);
		
		// バリデーション
		if (cd.length() != 3) {
			request.setAttribute("error", "科目コードは3文字で入力してください");
			request.getRequestDispatcher("subject_create.jsp").forward(request, response);
		}

		SubjectDao dao = new SubjectDao();

		// 重複チェック
		if (!dao.isUnique(cd)) {
			request.setAttribute("error", "科目コードが重複しています");
			request.getRequestDispatcher("subject_create.jsp").forward(request, response);
		}
		
		// 登録処理
		if (dao.save(subject)) {
			request.getRequestDispatcher("subject_create_done.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}
}