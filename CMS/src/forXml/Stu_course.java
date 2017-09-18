package forXml;

public class Stu_course {
	static final long serialVersionUID = -4888836126783955019L; 
	
	String course_no, stu_id;
	Double grade, tea_evaluation;
	
	@Override  
    public int hashCode()  
    {  
        final int prime = 31;  
        int result = 1;  
        result = prime * result + ((course_no == null) ? 0 : course_no.hashCode());  
        result = prime * result + ((stu_id == null) ? 0 : stu_id.hashCode());  
        return result;  
    }  
  
    @Override  
    public boolean equals(Object obj)  
    {  
        if (this == obj)  return true;  
        if (obj == null)  return false;  
        if (getClass() != obj.getClass())  return false;  
        
        Stu_course other = (Stu_course) obj;  
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
        return true;  
    }  
	
	public String getCourse_no() {
		return course_no;
	}
	
	public String getStu_id() {
		return stu_id;
	}
	
	public Double getGrade() {
		return grade;
	}
	
	public Double getTea_evaluation() {
		return tea_evaluation;
	}
	
	public void setCourse_no(String course_no) {
		this.course_no=course_no;
	}
	
	public void setStu_id(String stu_id) {
		this.stu_id=stu_id;
	}
	
	public void setGrade(Double grade) {
		this.grade=grade;
	}
	
	public void setTea_evaluation(Double tea_evaluation) {
		this.tea_evaluation=tea_evaluation;
	}
}
