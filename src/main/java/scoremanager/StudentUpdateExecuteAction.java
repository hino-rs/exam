package scoremanager;

import bean.Student;
import dao.StudentDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class StudentUpdateExecuteAction extends Action {
	
	@Override
	public void execute(
		HttpServletRequest request, HttpServletResponse response
	) throws Exception {
		tool.Logger.execute("StudentUpdateExecuteAction");
		
		int entYear = Integer.parseInt(request.getParameter("ent_year"));
		String no = (String)request.getParameter("no");
		String name = (String) request.getParameter("name");
		String classNum = (String)request.getParameter("class_num");
		boolean isAttend = request.getParameter("is_attend") != null;
		
		Student student = new Student();
		student.setEntYear(entYear);
		student.setNo(no);
		student.setName(name);
		student.setClassNum(classNum);
		student.setAttend(isAttend);
		
		StudentDao dao = new StudentDao();
		if (dao.save(student)) {
			request.getRequestDispatcher("student_update_done.jsp").forward(request, response);
		} else {
			request.setAttribute("error", "科目が存在していません");
			request.getRequestDispatcher("subject_update.jsp").forward(request, response);
		}
	}
}
