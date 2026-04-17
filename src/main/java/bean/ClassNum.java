package bean;

public class ClassNum implements java.io.Serializable {
	private String classNum;
	private School school;
	
	// 学校
	public School getSchool() {
		return school;
	}
	public void setSchool(School school) {
		this.school=school;
	}
	
	// クラス番号
	public String getClassNum() {
		return classNum;
	}
	public void setClassNum(String classNum) {
		this.classNum=classNum;
	}
		
}
