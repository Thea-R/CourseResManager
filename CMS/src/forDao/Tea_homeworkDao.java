package forDao;

import forXml.Course;
import forXml.Stu_course;
import forXml.Tea_homework;
import forXml.pkeyStu_course;
import forXml.pkeyTea_homework;

import java.util.ArrayList;
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

		String sql = "select * from tea_homework where course_no = '"+course_no+"'";
        Query query= s.createSQLQuery(sql);
        List<Object[]> list= query.list();
        List<Tea_homework> ret=new ArrayList<Tea_homework>();
        
        for (Object[] os : list) {
        	
        	int i=0;
        	Tea_homework tmp=new Tea_homework();
        	pkeyTea_homework pkey=new pkeyTea_homework();
            for (Object filed: os) {
            	i++;
            	if(i==1) pkey.setCourse_no(filed.toString());
            	else if(i==2) pkey.setHomework_no(filed.toString());
            }
            tmp.setPkey(pkey);
            ret.add(tmp);
        }
		
		closeSession(false);
        return ret;
	}
	
	public List<Tea_homework> getAll() {
		openSession();
		
		Query query=s.createQuery("from Tea_homework");
		List<Tea_homework> list=query.list();
		closeSession(false);
		
		return list;
	}
}
