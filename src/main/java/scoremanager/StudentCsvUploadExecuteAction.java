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
        	req.setAttribute("error_message", "ファイルが選択されていません。");
        	req.getRequestDispatcher("/scoremanager/student_csv_upload.jsp").forward(req, res);
            return;
        }
        
        // ② CSV を 1 行ずつ読み込む準備
        // isはファイルの内容を読み込むためのバイトストリーム(データが順番に流れてくる通路)
        InputStream is = part.getInputStream();	
        
        // InputStreamReader → バイト → 文字ストリームに変換（UTF-8）
        // BufferedReader → 文字を「1 行ずつ」読み込める行ストリームに変換
        BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
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
	
	    while (line != null) {
	
	         // 空行はスキップ
	         if (line.trim().length() == 0) {
	             line = br.readLine();
	             continue;
	         }	         
		     // クォート除去（ 行全体の " をすべて削除 ）
		     line = line.replace("\"", "").trim();
	
	         // カンマ区切りで分割
	         String[] cols = line.split(",");
	
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

         List<Student> studentList = new ArrayList<>();
         List<String> errorList = new ArrayList<>();

         for (StudentCsvUpload csv : csvList) {
             try {
                 Student st = uploadDao.toStudent(csv, schoolDao);
                 studentList.add(st);
             } catch (Exception e) {
                 errorList.add("学生番号 " + csv.getStudentNo() + " の行でエラー: " + e.getMessage());
             }
         }

        // ⑤ StudentDao insertOrUpdate で DB 登録または更新
        StudentDao studentDao = new StudentDao();
        int successCount = 0;
        int errorCount = 0;

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
