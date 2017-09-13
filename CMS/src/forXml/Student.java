package forXml;

public class Student {
	String stu_id, password, name;
	
	public String getStu_id() {
		return stu_id;
	}
	public void setStu_id(String stu_id) {
		this.stu_id = stu_id;
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
	public boolean modifyPassword(String name, String old, String _new) {
		if(checkPassword(old)==true && checkName(name)==true) {
			setPassword(_new);
			return true;
		}
		return false;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name=name;
	}
	public boolean checkName(String name) {
		if(this.name.equals(name)) return true;
		return false;
	}
}
