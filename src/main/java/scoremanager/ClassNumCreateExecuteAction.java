package scoremanager;

import bean.ClassNum;
import bean.School;
import dao.ClassNumDao;
import dao.SchoolDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class ClassNumCreateExecuteAction extends Action {

    @Override
    public void execute(
        HttpServletRequest request, HttpServletResponse response
    ) throws Exception {

        String mode = request.getParameter("school_mode");
        SchoolDao schoolDao = new SchoolDao();
        ClassNumDao classNumDao = new ClassNumDao();

        School school = null;
        String school_cd = null;
      
        // 学校の決定（既存 or 新規）      
        if ("new".equals(mode)) {

            String new_cd = request.getParameter("new_school_cd");
            String new_name = request.getParameter("new_school_name");

            if (new_cd == null || new_cd.isEmpty() ||
                new_name == null || new_name.isEmpty()) {

                request.setAttribute("error2", "学校コードと学校名を入力してください。");
                request.setAttribute("school_list", schoolDao.getAll());
                request.getRequestDispatcher("class_num_create.jsp")
                       .forward(request, response);
                return;
            }

         
            if (schoolDao.get(new_cd) != null) {
                request.setAttribute("error2", "学校コードが既に存在しています。");
                request.setAttribute("school_list", schoolDao.getAll());
                request.getRequestDispatcher("class_num_create.jsp")
                       .forward(request, response);
                return;
            }

            // 新規学校登録
            School newSchool = new School();
            newSchool.setCd(new_cd);
            newSchool.setName(new_name);
            schoolDao.save(newSchool);

            school = newSchool;
            school_cd = new_cd;

        } else {
            school_cd = request.getParameter("school_cd");
            school = schoolDao.get(school_cd);
        }
      
        // クラス番号全角禁止      
        String class_num_raw = request.getParameter("class_num");

        if (class_num_raw == null || class_num_raw.isEmpty()) {
        	request.setAttribute("error2", "クラス番号を入力してください");
	        	request.setAttribute("school_list", schoolDao.getAll());
	         request.getRequestDispatcher("class_num_create.jsp")
	         .forward(request, response);
	         return;
        }

        // 全角数字が含まれていたらエラー
        if (class_num_raw.matches(".*[０-９].*")) {
        	request.setAttribute("error2", "クラス番号に全角数字は使用できません（半角で入力してください）");
        	request.setAttribute("school_list", schoolDao.getAll());
        	request.getRequestDispatcher("class_num_create.jsp")
        	.forward(request, response);
         	return;
        }

        String class_num = class_num_raw;      
      
        if (classNumDao.get(class_num, school) != null) {
        	request.setAttribute("error2", "この学校にはすでに同じクラス番号が登録されています");
        	request.setAttribute("school_list", schoolDao.getAll());
        	request.getRequestDispatcher("class_num_create.jsp")
                .forward(request, response);
        	return;
        }
      
        // クラス登録（半角）      
        ClassNum cn = new ClassNum();
        cn.setClass_num(class_num);
        cn.setSchool(school);

        classNumDao.save(cn);

        request.getRequestDispatcher("class_num_create_done.jsp")
               .forward(request, response);
    }
  
    // 全角 → 半角 変換  
    private String toHalfWidth(String s) {
        return s.replaceAll("０", "0")
                .replaceAll("１", "1")
                .replaceAll("２", "2")
                .replaceAll("３", "3")
                .replaceAll("４", "4")
                .replaceAll("５", "5")
                .replaceAll("６", "6")
                .replaceAll("７", "7")
                .replaceAll("８", "8")
                .replaceAll("９", "9");
    }
}
