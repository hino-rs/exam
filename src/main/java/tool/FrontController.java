package tool;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;  // 追加：ファイルアップロード（multipart/form-data）を扱う
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns= {"*.action"})
@MultipartConfig   // 追加：partを処理するためのアノテーション
public class FrontController extends HttpServlet {
	public void doPost(
		HttpServletRequest request, HttpServletResponse response
	) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		try {
			String path=request.getServletPath().substring(1);
			
			// パッケージ名 "scoremanager." を追加
			String name = "scoremanager." + path.substring(path.lastIndexOf("/") + 1).replace(".action", "") + "Action";
			
			Action action=(Action)Class.forName(name).getDeclaredConstructor().newInstance();
			action.execute(request, response);
		} catch (Exception e) {
			
			// ログ
            e.printStackTrace();
            request.setAttribute("error_message", "エラーが発生しました。");
         // "scoremanager." を追加
            request.getRequestDispatcher("/scoremanager/error.jsp").forward(request, response);
		}
	}
	
	public void doGet(
		HttpServletRequest request, HttpServletResponse response
	) throws ServletException, IOException {
		doPost(request, response);
	}
}
