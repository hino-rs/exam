package bean;

public class Test implements java.io.Serializable {
	private String id;
	private String password;
	private String name;
	private School school;
	
	// ID
	public String getId() { return id; }
	public void setId(String id) { this.id=id; }
	
	// Password
	public String getPassword() { return password; }
	public void setPassword(String password) { this.password=password; }
	
	// Name
	public String getName() { return name; }
	public void setName(String name) { this.name=name; }
	
	// School
	public School getSchool() { return school; }
	public void setSchool(School school) { this.school=school; }
}
