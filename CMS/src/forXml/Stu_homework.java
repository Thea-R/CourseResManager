package forXml;

public class Stu_homework {
	static final long serialVersionUID = -4888836126783955019L; 
	
	String course_no, homework_no, stu_id, opinion;
	double grade;
	boolean status;
	
	@Override  
    public int hashCode()  
    {  
        final int prime = 31;  
        int result = 1;  
        result = prime * result + ((course_no == null) ? 0 : course_no.hashCode());  
        result = prime * result + ((stu_id == null) ? 0 : stu_id.hashCode());  
        result = prime * result + ((homework_no == null) ? 0 : homework_no.hashCode());
        return result;  
    }  
  
    @Override  
    public boolean equals(Object obj)  
    {  
        if (this == obj)  return true;  
        if (obj == null)  return false;  
        if (getClass() != obj.getClass())  return false;  
        
        Stu_homework other = (Stu_homework) obj;  
        if (course_no == null)  
        {  
            if (other.course_no != null)  return false;  
        }  
        else if (!course_no.equals(other.course_no))  return false; 
        
        if (stu_id == null)  
        {  
            if (other.stu_id != null)  return false;  
        }  
        else if (!stu_id.equals(other.stu_id))  return false;  
        
        if (homework_no == null)  
        {  
            if (other.homework_no != null)  return false;  
        }  
        else if (!homework_no.equals(other.homework_no))  return false; 
        return true;  
    }  
	
	public String getCourse_no() {
		return course_no;
	}
	
	public String getHomework_no() {
		return homework_no;
	}
	
	public String getStu_id() {
		return stu_id;
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
	
	public void setCourse_no(String course_no) {
		this.course_no=course_no;
	}
	
	public void setHomework_no(String homework_no) {
		this.homework_no=homework_no;
	}
	
	public void setStu_id(String stu_id) {
		this.stu_id=stu_id;
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
