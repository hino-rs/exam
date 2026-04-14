package bean;

public class ClassNum implements java.io.Serializable {
	private int class_num;
	private School school;
	
	// 学校
	public School getSchool() {
		return school;
	}
	public void setSchool(School school) {
		this.school=school;
	}
	
	// クラス番号
	public int getClass_num() {
		return class_num;
	}
	public void setName(int class_num) {
		this.class_num=class_num;
	}
		
}
