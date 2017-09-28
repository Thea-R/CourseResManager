package forDao;

import forXml.Stu_homework;
import forXml.Tea_homework;
import forXml.pkeyStu_homework;

import java.util.ArrayList;
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
		pkey.setHomework_no(homework_no);
		pkey.setStu_id(stu_id);
		
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
	
	public boolean modifyFilename(String course_no, String stu_id, String homework_no, String filename) {
		openSession();
		
		boolean flag=false;
		
		pkeyStu_homework pkey=new pkeyStu_homework();
		pkey.setCourse_no(course_no);
		pkey.setStu_id(stu_id);
		pkey.setHomework_no(homework_no);
		
		Stu_homework p=(Stu_homework) s.get(Stu_homework.class, pkey);
		if(p!=null) {
			p.setFilename(filename);
			s.save(p);
			flag=true;
		}
		
		closeSession(flag);
		return flag;
	}
	
	public List<Stu_homework> getbyCourse_no(String course_no) {
		openSession();

		String sql = "select * from stu_homework where course_no = '"+course_no+"'";
        Query query= s.createSQLQuery(sql);
        List<Object[]> list= query.list();
        List<Stu_homework> ret=new ArrayList<Stu_homework>();
        
        for (Object[] os : list) {
        	int i=0;
        	Stu_homework tmp=new Stu_homework();
        	pkeyStu_homework pkey=new pkeyStu_homework();
            for (Object filed: os) {
            	i++;
            	if(i==1) pkey.setCourse_no(filed.toString());
            	else if(i==2) pkey.setHomework_no(filed.toString());
            	else if(i==3) pkey.setStu_id(filed.toString());
            	else if(i==4 && filed!=null) tmp.setFilename(filed.toString());
            	else if(i==5 && filed!=null) tmp.setOpinion(filed.toString());
            }
            tmp.setPkey(pkey);
            ret.add(tmp);
        }
		
		closeSession(false);
        return ret;
	}
	
	public List<Stu_homework> getbyStu_id(String stu_id) {
		openSession();

		String sql = "select * from stu_homework where stu_id = '"+stu_id+"'";
        Query query= s.createSQLQuery(sql);
        List<Object[]> list= query.list();
        List<Stu_homework> ret=new ArrayList<Stu_homework>();
        
        for (Object[] os : list) {
        	
        	int i=0;
        	Stu_homework tmp=new Stu_homework();
        	pkeyStu_homework pkey=new pkeyStu_homework();
            for (Object filed: os) {
            	i++;
            	if(i==1) pkey.setCourse_no(filed.toString());
            	else if(i==2) pkey.setHomework_no(filed.toString());
            	else if(i==3) pkey.setStu_id(filed.toString());
            	else if(i==4 && filed!=null) tmp.setFilename(filed.toString());
            	else if(i==5 && filed!=null) tmp.setOpinion(filed.toString());
            }
            tmp.setPkey(pkey);
            ret.add(tmp);
        }
		
		closeSession(false);
        return ret;
	}
	
	public List<Stu_homework> getbyHomework_no(String homework_no) {
		openSession();

		String sql = "select * from stu_homework where homework_no = '"+homework_no+"'";
        Query query= s.createSQLQuery(sql);
        List<Object[]> list= query.list();
        List<Stu_homework> ret=new ArrayList<Stu_homework>();
        
        for (Object[] os : list) {
        	
        	int i=0;
        	Stu_homework tmp=new Stu_homework();
        	pkeyStu_homework pkey=new pkeyStu_homework();
            for (Object filed: os) {
            	i++;
            	if(i==1) pkey.setCourse_no(filed.toString());
            	else if(i==2) pkey.setHomework_no(filed.toString());
            	else if(i==3) pkey.setStu_id(filed.toString());
            	else if(i==4 && filed!=null) tmp.setFilename(filed.toString());
            	else if(i==5 && filed!=null) tmp.setOpinion(filed.toString());
            }
            tmp.setPkey(pkey);
            ret.add(tmp);
        }
		
		closeSession(false);
        return ret;
	}
	
	public List<Stu_homework> getAll() {
		openSession();
		
		Query query=s.createQuery("from Stu_homework");
		List<Stu_homework> list=query.list();
		closeSession(false);
		
		return list;
	}
}
