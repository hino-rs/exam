package scoremanager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bean.Test;
import dao.TestDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class TestRegistExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
       
        // リクエストパラメータ（f1〜f4）を取得
        String f1 = req.getParameter("f1"); // 入学年度
        String f2 = req.getParameter("f2"); // クラス
        String f3 = req.getParameter("f3"); // 科目
        String f4 = req.getParameter("f4"); // 回数
    	        
        // セッションから test_list を取得(検索時に保存しておいた成績一覧)
        List<Test> testList = (List<Test>) req.getSession().getAttribute("test_list");
        
        // 学生番号をキー、エラーメッセージを値とするマップ
        Map<String,String> errorMap = new HashMap<>(); 
        
        // 画面から送られてきた点数を全件ループで取得
        // 入力欄のnameは「point_学生番号」
        for(Test t : testList) {
        	String paramName = "point_" + t.getStudent().getNo(); 
        	String pointStr = req.getParameter(paramName);
        	
        	// 点数のバリデーション(未入力,0～100,数値チェック)
        	if (pointStr == null || pointStr.isEmpty()){
        		errorMap.put(t.getStudent().getNo(),"点数を入力してください");
        		continue;
        	}
        	try {
        		int point = Integer.parseInt(pointStr);
				if (point < 0 || point > 100) {
					errorMap.put(t.getStudent().getNo(),"点数は0～100の範囲で入力してください");
				} else {
					t.setPoint(point); // 成績オブジェクトに点数をセット
				}
			} catch (NumberFormatException e) {
				errorMap.put(t.getStudent().getNo(),"点数は数値で入力してください");
        	}        	
        }
        
        // エラーがある場合は詰めて戻す
        if(!errorMap.isEmpty()) {
        	req.setAttribute("error_map", errorMap);
			req.setAttribute("test_list", testList);
			req.setAttribute("f1", f1);
			req.setAttribute("f2", f2);
			req.setAttribute("f3", f3);
			req.setAttribute("f4", f4);
			
			req.getRequestDispatcher("/scoremanager/test_regist.jsp").forward(req, res);
			return;
        }
        
        // エラーがない場合はDBに保存（INSERT or UPDATE）
        TestDao tDao = new TestDao();

        for (Test t : testList) {
            Test existing = tDao.get(
                t.getStudent(),
                t.getSubject(),
                t.getSchool(),
                t.getNo()
            );

            if (existing == null) {
                tDao.insert(t);
            } else {
                tDao.update(t);
            }
        }

        // 完了画面へ  
        req.getRequestDispatcher("/scoremanager/test_regist_done.jsp").forward(req, res);
        return;        
    }
}
