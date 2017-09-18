package forXml;

public class Stu_homework {
	pkeyStu_homework pkey;
	String opinion;
	double grade;
	boolean status;
	
	public pkeyStu_homework getPkey() {
		return pkey;
	}
	
	public String getOpinion() {
		return opinion;
	}
	
	public double getGrade() {
		return grade;
	}
	
	public boolean getStatus() {
		return status;
	}
	
	public void setPkey(pkeyStu_homework pkey) {
		this.pkey=pkey;
	}
	
	public void setOpinion(String opinion) {
		this.opinion=opinion;
	}
	
	public void setGrade(double grade) {
		this.grade=grade;
	}
	
	public void setStatus(boolean status) {
		this.status=status;
	}
}
