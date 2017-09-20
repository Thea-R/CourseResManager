package forDao;

import java.util.List;

import forXml.Course;
import forXml.Courseware;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CoursewareDao {
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

	public boolean addOne(String course_no, String file_title) {
		openSession();
		
		boolean flag=false;
		Courseware p=(Courseware) s.get(Courseware.class, course_no);
		if(p==null) {
			p=new Courseware();
			p.setCourse_no(course_no);
			p.setFile_title(file_title);
			s.save(p);
			flag=true;
		}
		
		closeSession(flag);
		return flag;
	}
	
	public boolean deleteOne(String course_no) {
		openSession();
		
		boolean flag=false;
		Courseware p =(Courseware) s.get(Courseware.class, course_no);
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
		Courseware p=(Courseware) s.get(Courseware.class, course_no);
		if(p!=null) {
			flag=true;
		}
		
		closeSession(false);
		return flag;
	}
	
	public Courseware getbyCourse_no(String course_no) {
		openSession();

		Courseware p=(Courseware) s.get(Courseware.class, course_no);
		
		closeSession(false);
		return p;
	}
	
	public List<Courseware> getAll() {
		openSession();
		
		Query query=s.createQuery("from Courseware");
		List<Courseware> list=query.list();
		closeSession(false);
		
		return list;
	}
}
