package scoremanager;

import bean.ClassNum;
import bean.School;
import dao.ClassNumDao;
import dao.SchoolDao;
import dao.StudentDao;
import dao.TestDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class ClassNumUpdateExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
    throws Exception {

        String school_cd = request.getParameter("school_cd");
        String old_class_num = request.getParameter("old_class_num");
        String new_class_num = request.getParameter("class_num");

        SchoolDao sdao = new SchoolDao();
        ClassNumDao cdao = new ClassNumDao();
        StudentDao stdao = new StudentDao();
        TestDao tdao = new TestDao();

        School school = sdao.get(school_cd);
        
        // 学生 or 成績がある場合は変更禁止        
        int studentCount = stdao.countByClass(school_cd, old_class_num);
        int testCount = tdao.countByClass(school_cd, old_class_num);

        if (studentCount > 0 || testCount > 0) {

            request.setAttribute("error", "学生または成績が登録されているため、このクラス番号は変更できません。");
            request.setAttribute("school_cd", school_cd);
            request.setAttribute("school_name", school.getName());
            request.setAttribute("class_num", old_class_num);

            request.getRequestDispatcher("class_num_update.jsp")
                   .forward(request, response);
            return;
        }
        
        // 変更なしの場合        
        if (old_class_num.equals(new_class_num)) {

            request.setAttribute("school_name", school.getName());
            request.setAttribute("school_cd", school.getCd());
            request.setAttribute("old_class_num", old_class_num);
            request.setAttribute("new_class_num", new_class_num);
            request.setAttribute("no_change", true);

            request.getRequestDispatcher("class_num_update_done.jsp")
                   .forward(request, response);
            return;
        }
      
        ClassNum exist = cdao.get(new_class_num, school);

        if (exist != null) {

            request.setAttribute("error", "この学校にはすでに同じクラス番号が登録されています。");
            request.setAttribute("school_cd", school_cd);
            request.setAttribute("school_name", school.getName());
            request.setAttribute("class_num", old_class_num);

            request.getRequestDispatcher("class_num_update.jsp")
                   .forward(request, response);
            return;
        }
        
        // クラス番号更新        
        ClassNum cn = new ClassNum();
        cn.setClass_num(old_class_num);
        cn.setSchool(school);

        cdao.update(cn, new_class_num);

        // 完了画面へ渡す
        request.setAttribute("school_name", school.getName());
        request.setAttribute("school_cd", school.getCd());
        request.setAttribute("old_class_num", old_class_num);
        request.setAttribute("new_class_num", new_class_num);

        request.getRequestDispatcher("class_num_update_done.jsp")
               .forward(request, response);
    }
}
