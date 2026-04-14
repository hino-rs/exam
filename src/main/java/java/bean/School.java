package bean;

public class School implements java.io.Serializable {
	private String cd;
	private String name;
	
	// 学校コード
	public String getCd() {
		return cd;
	}
	public void setCd(String cd) {
		this.cd = cd;
	}
	
	// 学校名
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
		
}
