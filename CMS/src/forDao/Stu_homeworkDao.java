package forDao;

import forXml.Stu_homework;
import forXml.pkeyStu_homework;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Stu_homeworkDao {
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

	public boolean addOne(String course_no, String homework_no, String stu_id) {
		openSession();
		
		boolean flag=false;
		
		pkeyStu_homework pkey=new pkeyStu_homework();
		pkey.setCourse_no(course_no);
		pkey.setStu_id(stu_id);
		pkey.setHomework_no(homework_no);
		
		Stu_homework p=(Stu_homework) s.get(Stu_homework.class, pkey);
		if(p==null) {
			p=new Stu_homework();
			p.setPkey(pkey);
			s.save(p);
			flag=true;
		}
		
		closeSession(flag);
		return flag;
	}
	
	public boolean deleteOne(String course_no, String homework_no, String stu_id) {
		openSession();
		
		boolean flag=false;
		
		pkeyStu_homework pkey=new pkeyStu_homework();
		pkey.setCourse_no(course_no);
		pkey.setStu_id(stu_id);
		pkey.setHomework_no(homework_no);
		
		Stu_homework p=(Stu_homework) s.get(Stu_homework.class, pkey);
		if(p==null) {
			s.delete(p);
			flag=true;
		}
		
		closeSession(flag);
		return flag;
	}
	
	public boolean modifyGrade(String course_no, String stu_id, String homework_no, Double grade) {
		openSession();
		
		boolean flag=false;
		
		pkeyStu_homework pkey=new pkeyStu_homework();
		pkey.setCourse_no(course_no);
		pkey.setStu_id(stu_id);
		pkey.setHomework_no(homework_no);
		
		Stu_homework p=(Stu_homework) s.get(Stu_homework.class, pkey);
		if(p!=null) {
			p.setGrade(grade);
			s.save(p);
			flag=true;
		}
		
		closeSession(flag);
		return flag;
	}
	
	public boolean modifyStatus(String course_no, String stu_id, String homework_no, boolean status) {
		openSession();
		
		boolean flag=false;
		
		pkeyStu_homework pkey=new pkeyStu_homework();
		pkey.setCourse_no(course_no);
		pkey.setStu_id(stu_id);
		pkey.setHomework_no(homework_no);
		
		Stu_homework p=(Stu_homework) s.get(Stu_homework.class, pkey);
		if(p!=null) {
			p.setStatus(status);
			s.save(p);
			flag=true;
		}
		
		closeSession(flag);
		return flag;
	}
	
	public boolean modifyOpinion(String course_no, String stu_id, String homework_no, String opinion) {
		openSession();
		
		boolean flag=false;
		
		pkeyStu_homework pkey=new pkeyStu_homework();
		pkey.setCourse_no(course_no);
		pkey.setStu_id(stu_id);
		pkey.setHomework_no(homework_no);
		
		Stu_homework p=(Stu_homework) s.get(Stu_homework.class, pkey);
		if(p!=null) {
			p.setOpinion(opinion);
			s.save(p);
			flag=true;
		}
		
		closeSession(flag);
		return flag;
	}
	
	public List<Stu_homework> getbyCourse_no(String course_no) {
		openSession();

        Query query =s.createQuery("from Stu_homework p where p.course_no like ?");
        query.setString(0, "%"+course_no+"%");
		List<Stu_homework> list=query.list();
		closeSession(false);
		
        return list;
	}
	
	public List<Stu_homework> getbyStu_id(String stu_id) {
		openSession();

        Query query =s.createQuery("from Stu_homework p where stu_id like ?");
        query.setString(0, "%"+stu_id+"%");
		List<Stu_homework> list=query.list();
		closeSession(false);
		
        return list;
	}
}
