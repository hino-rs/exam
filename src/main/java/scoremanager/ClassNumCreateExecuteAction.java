package scoremanager;

import bean.ClassNum;
import bean.School;
import dao.ClassNumDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class ClassNumCreateExecuteAction extends Action {
	
	@Override
	public void execute(
		HttpServletRequest request, HttpServletResponse response
	) throws Exception {
		String classnum = request.getParameter("class_num");
		String school_cd = request.getParameter("school_cd");
		
		ClassNumDao cndao = new ClassNumDao();
		School s = new School() ;
		s.setCd(school_cd);
		ClassNum cn = cndao.get(classnum,s);
		if (cn == null) {
			ClassNum cnm = new ClassNum();
			cnm.setClass_num(classnum);
			cnm.setSchool(s);
			cndao.save(cnm);
			request.getRequestDispatcher("class_num_create_done.jsp").forward(request, response);
		} else {
			request.setAttribute("error2", "クラス番号が重複しています");
			request.getRequestDispatcher("ClassNumCreate.action").forward(request, response);
			return ;
		}
	}
}