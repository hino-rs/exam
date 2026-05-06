package scoremanager;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import bean.Student;
import bean.StudentCsvUpload;
import dao.SchoolDao;
import dao.StudentCsvUploadDao;
import dao.StudentDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import tool.Action;

public class StudentCsvUploadExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res)  throws Exception {

        // ① JSP から送られてきた CSV ファイルを受け取る
        Part part = req.getPart("csvFile");			//partはファイルそのもの
        if (part == null || part.getSize() == 0){
        	req.setAttribute("error_message", "ファイルのアップロードに失敗しました。もう一度お試しください。");
        	req.getRequestDispatcher("/scoremanager/student_csv_upload.jsp").forward(req, res);
            return;
        }
        
        // ② CSV を 1 行ずつ読み込む準備
        // isはファイルの内容を読み込むためのバイトストリーム(データが順番に流れてくる通路)
        InputStream is = part.getInputStream();	
        
        // InputStreamReader → バイト → 文字ストリームに変換（UTF-8では文字化けしたのでMS932）
        // BufferedReader → 文字を「1 行ずつ」読み込める行ストリームに変換
        BufferedReader br = new BufferedReader(new InputStreamReader(is,"MS932"));
        String line = br.readLine();

	     // 1行目がヘッダーぽい場合は読み飛ばす
        if (line != null && (
                line.contains("studentNo") ||
                line.contains("name") ||
                line.contains("entYear") ||
                line.contains("classNum")
            )) {
            line = br.readLine();   // 次の行へ（ヘッダー行を読み飛ばす）
        }
        
	    // ③ CSV の全行を読み込む
	    List<StudentCsvUpload> csvList = new ArrayList<>();
        List<Student> studentList = new ArrayList<>();
        List<String> errorList = new ArrayList<>();
        
        int successCount = 0;
        int errorCount = 0;
	
	    while (line != null) {
	
	         // 空行はスキップ
	         if (line.trim().length() == 0) {
	             line = br.readLine();
	             continue;
	         }	         
		     
	         // カンマ区切りで分割
	         String[] cols = line.split(",");
	         
	         // 列数チェック（6列固定）
	         if (cols.length < 6) {
	             errorList.add("CSV形式エラー（列数が不足しています）: " + line);
	             line = br.readLine();
	             continue;
	         }
	         
		      // 各列の前後の空白とクォートを削除
	         for (int i = 0; i < cols.length; i++) {
	             cols[i] = cols[i].replace("\"", "").trim();
	         }
	
	         // CSV → StudentCsvUpload（ 全部 String のまま ）
	         StudentCsvUpload csv = new StudentCsvUpload();
	         csv.setStudentNo(cols[0]);
	         csv.setStudentName(cols[1]);
	         csv.setEntYear(cols[2]);
	         csv.setClassNum(cols[3]);
	         csv.setIsAttend(cols[4]);
	         csv.setSchoolCd(cols[5]);
	
	         csvList.add(csv);
	         
	         line = br.readLine();
	     }
	     
	     br.close();

	     // ④ CSV → Student へ変換（StudentCsvUploadDao toStudent で 型変換 と バリデーション）
         StudentCsvUploadDao uploadDao = new StudentCsvUploadDao();
         SchoolDao schoolDao = new SchoolDao();

         for (StudentCsvUpload csv : csvList) {
             try {
                 Student st = uploadDao.toStudent(csv, schoolDao);
                 studentList.add(st);
             } catch (Exception e) {
            	 errorCount++;
            	 errorList.add("入力エラー（学生番号 " + csv.getStudentNo() + "）: " + e.getMessage());
            	 continue; 
             }
         }

        // ⑤ StudentDao insertOrUpdate で DB 登録または更新
        StudentDao studentDao = new StudentDao();

        for (Student st : studentList) {
            try {
                studentDao.insertOrUpdate(st);
                successCount++;
            } catch (Exception e) {
                errorCount++;
                errorList.add("DB 登録エラー（学生番号 " + st.getNo() + "）: " + e.getMessage());
            }
        }

        // ⑥ 結果を JSP に渡す
        req.setAttribute("successCount", successCount);
        req.setAttribute("errorCount", errorCount);
        req.setAttribute("errorList", errorList);

        req.getRequestDispatcher("/scoremanager/student_csv_upload.jsp").forward(req, res);	
    }
}
