package scoremanager;

import java.util.List;

import bean.Teacher;
import dao.ClassNumDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class StudentCreateAction extends Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		Teacher teacher = (Teacher)session.getAttribute("loginUser");
		
		ClassNumDao cndao = new ClassNumDao();
		List<String> list = cndao.filter(teacher.getSchool());
		
		request.setAttribute("class_num", list); 
		request.getRequestDispatcher("student_create.jsp").forward(request, response);
	}
	
}