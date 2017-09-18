package forXml;

import java.io.Serializable;

public class pkeyTea_homework implements Serializable {
	private static final long serialVersionUID = -1190986010439330142L;
    
    private String course_no, homework_no;
    
    public String getCourse_no() {
		return course_no;
	}
	
    public String getHomework_no() {
		return homework_no;
	}

	public void setCourse_no(String course_no) {
		this.course_no=course_no;
	}
	
	public void setHomework_no(String homework_no) {
		this.homework_no=homework_no;
	}

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((course_no == null) ? 0 : course_no.hashCode());
        result = prime * result + ((homework_no == null) ? 0 : homework_no.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)	return true;
        if (obj == null)	return false;
        if (getClass() != obj.getClass())	return false;
        pkeyTea_homework other = (pkeyTea_homework) obj;
        
        if (course_no == null)
        {
            if (other.course_no != null)	return false;
        }
        else if (!course_no.equals(other.course_no))	return false;
        
        if (homework_no == null)
        {
            if (other.homework_no != null)	return false;
        }
        else if (!homework_no.equals(other.homework_no))	return false;
        return true;
    }
}
