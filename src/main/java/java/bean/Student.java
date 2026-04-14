package bean;

public class Student implements java.io.Serializable {
	private String no;
	private String name;
	private int entYear;
	private String classNum;
	private boolean isAttend;
	private School school;
	
	// 学生番号
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no=no;
	}
	
	// 学生名
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name=name;
	}
	
	// 入学年度
	public int getEntYear() {
		return entYear;
	}
	public void setEntYear(int entYear) {
		this.entYear=entYear;
	}
	
	// クラス番号
	public String getClassNum() {
		return classNum;
	}
	public void setClassNum(String classNum) {
		this.classNum=classNum;
	}
	
	// 在学中フラグ
	public boolean getAttend() {
		return isAttend;
	}
	public void setAttend(boolean isAttend) {
		this.isAttend = isAttend;
	}
	
	// 学校コード
	public School getSchool() {
		return school;
	}
	public void setSchool(School school) {
		this.school=school;
	}
}
