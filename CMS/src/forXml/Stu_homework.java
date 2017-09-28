package forXml;

public class Stu_homework {
	pkeyStu_homework pkey;
	String filename, opinion;
	
	public pkeyStu_homework getPkey() {
		return pkey;
	}
	
	public String getOpinion() {
		return opinion;
	}
	
	public String getFilename() {
		return filename;
	}
	
	public void setPkey(pkeyStu_homework pkey) {
		this.pkey=pkey;
	}
	
	public void setOpinion(String opinion) {
		this.opinion=opinion;
	}
	
	public void setFilename(String filename) {
		this.filename=filename;
	}
}
