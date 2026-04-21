package scoremanager;

import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Student;
import dao.ClassNumDao;
import dao.StudentDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class StudentUpdateAction extends Action {
	
	@Override
	public void execute(
		HttpServletRequest request, HttpServletResponse response
	) throws Exception {
		tool.Logger.execute("StudentUpdateAction");
		
		List<String> list = new ArrayList<>();
		
		HttpSession session = request.getSession();
		School school = (School)session.getAttribute("loginUserSchool");
		ClassNumDao cDao = new ClassNumDao();
		list = cDao.filter(school);
		
		String no = (String) request.getParameter("no");
		StudentDao dao = new StudentDao();
		Student student = dao.get(no);
		
		int entYear = student.getEntYear();
		String name = student.getName();
		String classNum = student.getClassNum();
		boolean isAttend = student.getAttend();
		
		request.setAttribute("ent_year", entYear);
		request.setAttribute("no", no);
		request.setAttribute("name", name);
		request.setAttribute("class_num", classNum);
		request.setAttribute("is_attend", isAttend);
		request.setAttribute("class_list", list);
		
		request.getRequestDispatcher("student_update.jsp").forward(request, response);
	}
}
