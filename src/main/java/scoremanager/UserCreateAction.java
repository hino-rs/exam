package scoremanager;

import java.util.List;

import dao.SchoolDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class UserCreateAction extends Action {
	
	@Override
	public void execute(
		HttpServletRequest request, HttpServletResponse response
	) throws Exception {
		tool.Logger.execute("UserCreateAction");
		
		SchoolDao dao = new SchoolDao();
	
		List<String> schoolCodes = dao.getAllSchoolCd();
		request.setAttribute("schoolCodes", schoolCodes);
		
		request.getRequestDispatcher("user_create.jsp").forward(request, response);
	}
}
