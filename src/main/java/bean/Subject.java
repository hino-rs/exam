package bean;

public class Subject implements java.io.Serializable {
	private String cd;
	private String name;
	private School school;
	
	// コード
	public String getCd() {
		return cd;
	}
	public void setCd(String cd) {
		this.cd=cd;
	}
	
	// 名前
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name=name;
	}
	
	// 学校
	public School getSchool() {
		return school;
	}
	public void setSchool(School school) {
		this.school=school;
	}
}
