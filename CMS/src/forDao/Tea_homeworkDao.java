package forDao;

import forXml.Tea_homework;
import forXml.pkeyTea_homework;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Tea_homeworkDao {
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

	public boolean addOne(String course_no, String homework_no) {
		openSession();
		
		boolean flag=false;
		
		pkeyTea_homework pkey=new pkeyTea_homework();
		pkey.setCourse_no(course_no);
		pkey.setHomework_no(homework_no);
		
		Tea_homework p=(Tea_homework) s.get(Tea_homework.class, pkey);
		if(p==null) {
			p=new Tea_homework();
			p.setPkey(pkey);
			s.save(p);
			flag=true;
		}
		
		closeSession(flag);
		return flag;
	}
	
	public boolean deleteOne(String course_no, String homework_no) {
		openSession();
		
		boolean flag=false;
		
		pkeyTea_homework pkey=new pkeyTea_homework();
		pkey.setCourse_no(course_no);
		pkey.setHomework_no(homework_no);
		
		Tea_homework p=(Tea_homework) s.get(Tea_homework.class, pkey);
		if(p!=null) {
			s.delete(p);
			flag=true;
		}
		
		closeSession(flag);
		return flag;
	}
	
	public List<Tea_homework> getbyCourse_no(String course_no) {
		openSession();

        Query query =s.createQuery("from Tea_homework p where p.course_no like ?");
        query.setString(0, "%"+course_no+"%");
		List<Tea_homework> list=query.list();
		closeSession(false);
		
        return list;
	}
}
