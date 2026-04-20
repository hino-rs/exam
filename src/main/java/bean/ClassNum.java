package bean;

public class ClassNum implements java.io.Serializable {
<<<<<<< HEAD
	private String class_num;
=======
	private String classNum;
>>>>>>> 610ac05632840abc08583406ced5bbc502e3ffc2
	private School school;
	
	// 学校
	public School getSchool() {
		return school;
	}
	public void setSchool(School school) {
		this.school=school;
	}
	
	// クラス番号
<<<<<<< HEAD
	public String getClass_num() {
		return class_num;
	}
	public void setClass_num(String class_num) {
		this.class_num=class_num;
=======
	public String getClassNum() {
		return classNum;
	}
	public void setClassNum(String classNum) {
		this.classNum=classNum;
>>>>>>> 610ac05632840abc08583406ced5bbc502e3ffc2
	}
		
}
