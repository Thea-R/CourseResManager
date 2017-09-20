package forXml;

public class Admin {
	String adm_id, password;
	
	public String getAdm_id() {
		return adm_id;
	}
	public void setAdm_id(String adm_id) {
		this.adm_id=adm_id;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password=password;
	}
	public boolean checkPassword(String password) {
		if(this.password.equals(password)) return true;
		return false;
	}
	public boolean modifyPassword(String old, String _new) {
		if(checkPassword(old)==true) {
			setPassword(_new);
			return true;
		}
		return false;
	}
}
