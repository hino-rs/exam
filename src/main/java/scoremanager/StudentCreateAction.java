package scoremanager;

import java.time.LocalDate;
import java.util.ArrayList;
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
		String entYearStr = "";
		int entYear = 0;
		LocalDate todaysDate = LocalDate.now();
        int year = todaysDate.getYear();
        entYearStr = request.getParameter("f1");
		ClassNumDao cndao = new ClassNumDao();
		List<String> list = cndao.filter(teacher.getSchool());
		
        if (entYearStr != null && !entYearStr.isEmpty()) {
            entYear = Integer.parseInt(entYearStr);
        }

        List<Integer> entYearSet = new ArrayList<>();
        for (int i = year - 10; i <= year + 10; i++) {
            entYearSet.add(i);
        }
		request.setAttribute("now_year", year);
		request.setAttribute("class_num", list);
		request.setAttribute("ent_year_set", entYearSet);
		request.setAttribute("f1", entYear);
		request.getRequestDispatcher("student_create.jsp").forward(request, response);
		
		
        }
	}
