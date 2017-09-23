package forDao;

import forXml.Course;
import forXml.Stu_course;
import forXml.Stu_homework;
import forXml.Teacher;
import forXml.pkeyStu_course;
import forXml.pkeyStu_homework;

import java.util.ArrayList;
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
	
	public boolean modifyGrade(pkeyStu_course pkey, Double grade) {
		openSession();
		
		boolean flag=false;
		Stu_course p=(Stu_course) s.get(Stu_course.class, pkey);
		if(p!=null) {
			p.setGrade(grade);
			s.save(p);
			flag=true;
		}
		
		closeSession(flag);
		return flag;
	}
	
	public boolean modifyTea_evaluation(pkeyStu_course pkey, String tea_evaluation) {
		openSession();
		
		boolean flag=false;
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

        String sql = "select * from stu_course where course_no ="+course_no;
        Query query= s.createSQLQuery(sql);
        List<Object[]> list= query.list();
        List<Stu_course> ret=new ArrayList<Stu_course>();
        
        for (Object[] os : list) {
        	
        	int i=0;
        	Stu_course tmp=new Stu_course();
        	pkeyStu_course pkey=new pkeyStu_course();
            for (Object filed: os) {
            	i++;
            	if(i==1) pkey.setCourse_no(filed.toString());
            	else if(i==2) pkey.setStu_id(filed.toString());
            	else if(i==3 && filed!=null) tmp.setGrade((Double)filed);
            	else if(i==4 && filed!=null) tmp.setTea_evaluation(filed.toString());
            }
            tmp.setPkey(pkey);
            ret.add(tmp);
        }
		
		closeSession(false);
        return ret;
	}
	
	public List<Stu_course> getbyStu_id(String stu_id) {
		openSession();

		String sql = "select * from stu_course where stu_id ="+stu_id;
        Query query= s.createSQLQuery(sql);
        List<Object[]> list= query.list();
        List<Stu_course> ret=new ArrayList<Stu_course>();
        
        for (Object[] os : list) {
        	
        	int i=0;
        	Stu_course tmp=new Stu_course();
        	pkeyStu_course pkey=new pkeyStu_course();
            for (Object filed: os) {
            	i++;
            	if(i==1) pkey.setCourse_no(filed.toString());
            	else if(i==2) pkey.setStu_id(filed.toString());
            	else if(i==3 && filed!=null) tmp.setGrade((Double)filed);
            	else if(i==4 && filed!=null) tmp.setTea_evaluation(filed.toString());
            }
            tmp.setPkey(pkey);
            ret.add(tmp);
        }

		closeSession(false);		
        return ret;
	}
	
	public Stu_course getbyPkey(pkeyStu_course pkey) {
		openSession();
		
		Stu_course p=(Stu_course) s.get(Stu_course.class, pkey);
		
		closeSession(false);
		return p;
	}
	
	public List<Stu_course> getAll() {
		openSession();
		
		Query query=s.createQuery("from Stu_course");
		List<Stu_course> list=query.list();
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
	
	public String getTnamebyNo(String course_no) {
		openSession();

		Course p=(Course) s.get(Course.class, course_no);
		String tea_name=new String();
		if(p!=null) {
			String tea_id=p.getTea_id();
			TeacherDao tea=new TeacherDao();
			Teacher tmp=tea.getbyId(tea_id);
			tea_name=tmp.getName();
		}
		
		closeSession(false);
		return tea_name;
	}
	
	public String getGrade(pkeyStu_course pkey) {
		openSession();
		
		Stu_course p=(Stu_course) s.get(Stu_course.class, pkey);
		String grade=new String();
		if(p!=null && p.getGrade()!=null) {
			grade=p.getGrade().toString();
		}
		
		closeSession(false);
		return grade;
	}
}
