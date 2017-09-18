package forDao;

import forXml.Stu_course;
import forXml.pkeyStu_course;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Stu_courseDao {
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

	public boolean addOne(String course_no, String stu_id) {
		openSession();
		
		boolean flag=false;
		
		pkeyStu_course pkey=new pkeyStu_course();
		pkey.setCourse_no(course_no);
		pkey.setStu_id(stu_id);
		
		Stu_course p=(Stu_course) s.get(Stu_course.class, pkey);
		if(p==null) {
			p=new Stu_course();
			p.setPkey(pkey);
			s.save(p);
			flag=true;
		}
		
		closeSession(flag);
		return flag;
	}
	
	public boolean deleteOne(String course_no, String stu_id) {
		openSession();
		
		boolean flag=false;
		
		pkeyStu_course pkey=new pkeyStu_course();
		pkey.setCourse_no(course_no);
		pkey.setStu_id(stu_id);
		
		Stu_course p=(Stu_course) s.get(Stu_course.class, pkey);
		if(p!=null)	{
			s.delete(p);
			flag=true;
		}
        
        closeSession(flag);
        return flag;
	}
	
	public boolean modifyGrade(String course_no, String stu_id, Double grade) {
		openSession();
		
		boolean flag=false;
		
		pkeyStu_course pkey=new pkeyStu_course();
		pkey.setCourse_no(course_no);
		pkey.setStu_id(stu_id);
		
		Stu_course p=(Stu_course) s.get(Stu_course.class, pkey);
		if(p!=null) {
			p.setGrade(grade);
			s.save(p);
			flag=true;
		}
		
		closeSession(flag);
		return flag;
	}
	
	public boolean modifyTea_evaluation(String course_no, String stu_id, Double tea_evaluation) {
		openSession();
		
		boolean flag=false;
		
		pkeyStu_course pkey=new pkeyStu_course();
		pkey.setCourse_no(course_no);
		pkey.setStu_id(stu_id);
		
		Stu_course p=(Stu_course) s.get(Stu_course.class, pkey);
		if(p!=null) {
			p.setTea_evaluation(tea_evaluation);
			s.save(p);
			flag=true;
		}
		
		closeSession(flag);
		return flag;
	}
	
	public List<Stu_course> getbyCourse_no(String course_no) {
		openSession();

        Query query =s.createQuery("from Stu_course p where p.course_no like ?");
        query.setString(0, "%"+course_no+"%");
		List<Stu_course> list=query.list();
		closeSession(false);
		
        return list;
	}
	
	public List<Stu_course> getbyStu_id(String stu_id) {
		openSession();

        Query query =s.createQuery("from Stu_course p where p.stu_id like ?");
        query.setString(0, "%"+stu_id+"%");
		List<Stu_course> list=query.list();
		closeSession(false);
		
        return list;
	}
}
