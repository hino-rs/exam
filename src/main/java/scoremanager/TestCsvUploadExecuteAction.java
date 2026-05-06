// 成績CSVファイルをアップロードして、内容をデータベースに登録するアクション
package scoremanager;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import bean.TestCsvUpload;
import dao.TestCsvUploadDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import tool.Action;

public class TestCsvUploadExecuteAction  extends Action {	@Override	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
        // ① アップロードされた CSV ファイルを受け取る
        // JSP の <input type="file" name="csvfile"> から Part を取得する
        // ファイルが選択されていない場合はエラーとして画面に戻す
		
		Part part = req.getPart("csvfile");
		
		if (part == null || part.getSize() == 0) {
		    // エラー処理
			req.setAttribute("error_message", "ファイルが選択されていません");
            req.getRequestDispatcher("/scoremanager/test_csv_upload.jsp").forward(req, res);
            return;
		}

        // ② CSV ファイルを読み込む準備
        // BufferedReader を使って 1 行ずつ読み込む
        // 文字コードは UTF-8 を指定する
		InputStream is = part.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
		//   CSVファイルの内容を1行ずつ読み込むための変数
		String line;	
		
		int successCount = 0;  // 登録成功件数
		int errorCount = 0;    // 登録失敗件数
		
		TestCsvUploadDao dao = new TestCsvUploadDao();
		
		while ((line = br.readLine()) != null) {
			
		    // 空行はスキップする（エラーカウントは増やす）
		    if (line.trim().isEmpty()) {
		        errorCount++;
		        continue;
		    }
		    
		    // クォート除去（行全体の " をすべて削除）
		    line = line.replace("\"", "").trim();
		
		    // line に CSV の 1 行（カンマ区切りの文字列）がそのまま入る"2325001,B02,oom,1,80,131"
		    String[] cols = line.split(",");    // ③ カンマで分割して 6 項目の配列にする
												//  cols[0]	"2325001"
												//	cols[1]	"B02"
												//	cols[2]	"oom"
												//	cols[3]	"1"
												//	cols[4]	"80"
												//	cols[5]	"131"
	        // 列数が 6列 あるかチェック（student_no, subject_cd, school_cd, no, point, class_num）
			if (cols.length != 6) {
			    errorCount++;		// エラーカウント
			    continue;      
			}

			// ④ 分割した値を TestCsvUpload オブジェクト（Bean）に詰める
			TestCsvUpload csv = new TestCsvUpload();

			try {
				csv.setStudentNo(cols[0]);					// 学籍番号
				csv.setSubjectCd(cols[1]);					// 科目コード
				csv.setSchoolCd(cols[2]);					// 学校コード
				csv.setNo(Integer.parseInt(cols[3]));		// 回数
				csv.setPoint(Integer.parseInt(cols[4]));	// 点数
				csv.setClassNum(cols[5]);					// クラス番号
			} catch (NumberFormatException e) {
			    errorCount++;								// 数値変換エラーもエラーカウント
			    continue;
			}
	        // ⑤ 作成した1行分の TestCsvUpload オブジェクトをTestCsvUploadDaoで登録する
			dao.insertOrUpdate(csv);
			successCount++;									// 登録成功カウント	
		}
		
        // ⑥ 結果を JSP に渡す
		req.setAttribute("successCount", successCount);
		req.setAttribute("errorCount", errorCount);
		req.getRequestDispatcher("/scoremanager/test_csv_upload.jsp").forward(req, res);		
		
	}}