package forXml;

public class Stu_homework {
	pkeyStu_homework pkey;
	String opinion;
	Boolean status;
	
	public pkeyStu_homework getPkey() {
		return pkey;
	}
	
	public String getOpinion() {
		return opinion;
	}
	
	public Boolean getStatus() {
		return status;
	}
	
	public void setPkey(pkeyStu_homework pkey) {
		this.pkey=pkey;
	}
	
	public void setOpinion(String opinion) {
		this.opinion=opinion;
	}
	
	public void setStatus(Boolean status) {
		this.status=status;
	}
}
