package bean;

public class User implements java.io.Serializable {
	private boolean isAuthenticate;
	
	public boolean isAuthenticate() {
		return isAuthenticate;
	}
	public void setAuthenticate(boolean isAuthenticate) {
		this.isAuthenticate = isAuthenticate;
	}	
}
