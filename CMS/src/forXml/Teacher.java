package forXml;

public class Teacher {
	String tea_id, password, name;
	
	public String getTea_id() {
		return tea_id;
	}
	public void setTea_id(String tea_id) {
		this.tea_id = tea_id;
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
	public void resetPassword() {
		String id=getTea_id(), pw;
		if(id.length()>=6) pw=id.substring(id.length()-6, id.length());
		else {
			pw=id;
			for(int i=6-id.length(); i>0; i--) pw=pw+"0";
		}
		setPassword(pw);
		return ;
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
