package dao;

import bean.Student;
import bean.StudentCsvUpload;


public class StudentCsvUploadDao extends DAO{

// ---------------------------------------------------------------------
//  CSV の 1 行（StudentCsvUpload）を Student に変換する 
// ---------------------------------------------------------------------
//	読み込み時はすべて String型で入ってくるため、Student クラスの型に
//	変換してセットする必要がある
	
	public Student toStudent(StudentCsvUpload csv, SchoolDao schoolDao) throws Exception {
	
	    // ① Student オブジェクトを生成
		Student st = new Student();
		
		st.setNo(csv.getStudentNo());			// 学生番号をセット
		st.setName(csv.getStudentName());		// 学生名をセット
		
		// 空欄チェック
		if (csv.getEntYear() == null || csv.getEntYear().trim().isEmpty()) {
			throw new Exception("入学年度が空欄です");
		}
		// 数字チェック  \\d:半角数字 [0-9],+:1回以上の繰り返し
		if (!csv.getEntYear().matches("\\d+")) {
			throw new Exception("入学年度が数字ではありません");
		}
		st.setEntYear(Integer.parseInt(csv.getEntYear()));  // 入学年度 int型に変換してセット
		st.setClassNum(csv.getClassNum());		// クラス番号をセット
	
	    // 在学中フラグ（String → boolean）に変換してセット
	    // "1" / "true" / "TRUE" / "○" → true 、それ以外は false	    
		String attend = csv.getIsAttend();
	    boolean isAttend = attend.equals("1") || attend.equalsIgnoreCase("true");
	    // Student クラスの isAttend にセット
	    st.setAttend(isAttend);	
	    
	    // 学校コードから School オブジェクトを取得してセット
	    // schoolDao.get(csv.getSchoolCd()) を呼び出して、Student クラスの school にセット
	    st.setSchool(schoolDao.get(csv.getSchoolCd()));
	    
	    return st;
	}	
}