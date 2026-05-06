package bean;

import java.io.Serializable;

// CSV 専用ビーンのため、すべて String型で定義（CSV ファイルを読み込むと “すべて文字列（String）として入ってくるため）
public class StudentCsvUpload implements Serializable {	private String studentNo;		// 学生番号	private String studentName;		// 学生名
	private String entYear;			// 入学年度		private String classNum;		// クラス番号
	private String isAttend;		// 在学中フラグ
	private String schoolCd;		// 学校コード
	
	// 学生番号
	public String getStudentNo() {
		return studentNo;
	}
	public void setStudentNo(String studentNo) {
		this.studentNo = studentNo;
	}
	
	// 学生名
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	
	// 入学年度（Student クラスの entYear は int型）
	public String getEntYear() {
		return entYear;
	}
	public void setEntYear(String entYear) {
		this.entYear = entYear;
	}
	
	// クラス番号
	public String getClassNum() {
		return classNum;
	}
	public void setClassNum(String classNum) {
		this.classNum = classNum;
	}
	
	// 在学中フラグ（Student クラスの isAttend は boolean型）
	public String getIsAttend() {
		return isAttend;
	}
	public void setIsAttend(String isAttend) {
		this.isAttend = isAttend;
	}
	
	// 学校コード（Student クラスの school は Schoolクラス）
	public String getSchoolCd() {
		return schoolCd;
	}
	public void setSchoolCd(String schoolCd) {
		this.schoolCd = schoolCd;
	}	
}