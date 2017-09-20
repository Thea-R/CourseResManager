package forDao;

import forXml.Course;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CourseDao {
	private SessionFactory sf;
	private Session s;
	
	public void openSession() {
		sf = new Configuration().configure().buildSessionFactory();
        s = sf.openSession();
        s.beginTransaction();
	}
	
	public void closeSession(boolean modify) {
		if(modify==true) s.getTransaction().commit();
		else s.getTransaction().rollback();
        s.close();
        sf.close();
	}
	
	public boolean addOne(String course_no, String title, String tea_id) {
		openSession();
		
		boolean flag=false;
		Course p=(Course) s.get(Course.class, course_no);
		if(p==null) {
			p=new Course();
			p.setCourse_no(course_no);
			p.setTitle(title);
			p.setTea_id(tea_id);
			s.save(p);
			flag=true;
		}
		
		closeSession(flag);
		return flag;
	}
	
	public boolean deleteOne(String course_no) {
		openSession();
		
		boolean flag=false;
		Course p =(Course) s.get(Course.class, course_no);
		if(p!=null)	{
			s.delete(p);
			flag=true;
		}
        
        closeSession(flag);
        return flag;
	}
	
	public boolean findbyCourse_no(String course_no) {
		openSession();
		
		boolean flag=false;
		Course p=(Course) s.get(Course.class, course_no);
		if(p!=null) {
			flag=true;
		}
		
		closeSession(false);
		return flag;
	}
	
	public List<Course> getAll() {
		openSession();
		
		Query query=s.createQuery("from Course");
		List<Course> list=query.list();
		closeSession(false);
		
		return list;
	}
	
	public Course getbyCourse_no(String course_no) {
		openSession();

		Course p=(Course) s.get(Course.class, course_no);
		
		closeSession(false);
		return p;
	}
	
	public List<Course> getbyTitle(String title) {
		openSession();

        Query query =s.createQuery("from Course where title = ?");
        query.setString(0, title);
		List<Course> list=query.list();
		closeSession(false);
		
        return list;
	}
	
	public List<Course> getbyTea_id(String tea_id) {
		openSession();

        Query query =s.createQuery("from Course where tea_id = ?");
        query.setString(0, tea_id);
		List<Course> list=query.list();
		closeSession(false);
		
        return list;
	}
	
	public String getTitlebyNo(String course_no) {
		openSession();
		
		Course p=(Course) s.get(Course.class, course_no);
		String title=new String();
		if(p!=null) {
			title=p.getTitle();
		}
		
		closeSession(false);
		return title;
	}
}
