package scoremanager;

import bean.Student;
import bean.Teacher;
import dao.StudentDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class StudentCreateExecuteAction extends Action {
	
	@Override
	public void execute(
		HttpServletRequest request, HttpServletResponse response
	) throws Exception {
		tool.Logger.execute("StudentCreateExecute");
		
		HttpSession session = request.getSession();
		Teacher teacher = (Teacher)session.getAttribute("loginUser");
		
		String entyear = request.getParameter("ent_year");
		String no = request.getParameter("no");
		String name = request.getParameter("name");
		String classnum = request.getParameter("class_num");
		
		
		
		
		if (entyear == null || "".equals(entyear) || "0".equals(entyear)) {
			tool.Logger.error("入学年度が未入力: "+entyear);
			request.setAttribute("error1", "入学年度を選択してください");
			request.setAttribute("no", no);
            request.setAttribute("name", name);
            request.setAttribute("class_num", classnum);
			request.getRequestDispatcher("StudentCreate.action").forward(request, response);
			return ;
		}
		int ent_year = 0;
		
		boolean isAttend = true;
		try{
            ent_year = Integer.parseInt(entyear);
        }
        catch (NumberFormatException ex){
            ex.printStackTrace();
        }
		
		StudentDao dao = new StudentDao();
		Student s = dao.get(no);
		if (s == null) {
			Student student = new Student();
			student.setNo(no);
			student.setName(name);
			student.setEntYear(ent_year);
			student.setClassNum(classnum);
			student.setAttend(isAttend);
			student.setSchool(teacher.getSchool());
			dao.save(student);
			request.getRequestDispatcher("student_create_done.jsp").forward(request, response);
		} else {tool.Logger.error("学生番号重複");
		request.setAttribute("error2", "学生番号が重複しています");
		request.getRequestDispatcher("StudentCreate.action").forward(request, response);
		return ;
		}
	}
}