package forXml;

public class Stu_course {
	pkeyStu_course pkey;
	Double grade;
	String tea_evaluation;
	
	public pkeyStu_course getPkey() {
		return pkey;
	}
	
	public Double getGrade() {
		return grade;
	}
	
	public String getTea_evaluation() {
		return tea_evaluation;
	}
	
	public void setPkey(pkeyStu_course pkey) {
		this.pkey=pkey;
	}
	
	public void setGrade(Double grade) {
		this.grade=grade;
	}
	
	public void setTea_evaluation(String tea_evaluation) {
		this.tea_evaluation=tea_evaluation;
	}
}
