package forDao;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import forXml.Student;
 
public class StudentDao  {
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
	
	public boolean addOne(String stu_id) {
		openSession();
		
		boolean flag=false;
		Student p=(Student) s.get(Student.class, stu_id);
		if(p==null) {
			p=new Student();
			p.setStu_id(stu_id);
			p.resetPassword();
			p.setName("---");
			s.save(p);
			flag=true;
		}
		
		closeSession(flag);
		return flag;
	}
	
	public boolean addOne(String stu_id, String name) {
		openSession();
		
		boolean flag=false;
		Student p=(Student) s.get(Student.class, stu_id);
		if(p==null) {
			p=new Student();
			p.setStu_id(stu_id);
			p.setPassword("000000");
			p.setName(name);
			s.save(p);
			flag=true;
		}
		
		closeSession(flag);
		return flag;	
	}
	
	public boolean deleteOne(String stu_id) {
		openSession();
		
		boolean flag=false;
		Student p =(Student) s.get(Student.class, stu_id);
		if(p!=null)	{
			s.delete(p);
			flag=true;
		}
        
        closeSession(flag);
        return flag;
	}
	
	public boolean modifyName(String stu_id, String name) {
		openSession();
		
		boolean flag=false;
		Student p=(Student) s.get(Student.class, stu_id);
		if(p!=null) {
			p.setName(name);
			flag=true;
		}
		
		closeSession(flag);
		return flag;
	}
	
	public boolean modifyPassword(String stu_id, String name, String old, String _new) {
		openSession();
		
		boolean flag=false;
		Student p=(Student) s.get(Student.class, stu_id);
		if(p!=null) {
			if(p.modifyPassword(name, old, _new)==true) {
				flag=true;
			}
		}
		
		closeSession(flag);
		return flag;
	}

	public boolean resetPassword(String stu_id, String name) {
		openSession();
		
		System.out.println("stu_id = "+stu_id+" name = "+name);
		
		boolean flag=false;
		Student p =(Student) s.get(Student.class, stu_id);
		if(p!=null)	{
			if(p.checkName(name)==true) {
				flag=true;
				p.resetPassword();
			}
		}
        
        closeSession(flag);
        return flag;
	}
	
	public boolean findOne(String stu_id) {
		openSession();
		
		boolean flag=false;
		Student p=(Student) s.get(Student.class, stu_id);
		if(p!=null) {
			flag=true;
		}
		
		closeSession(false);
		return flag;
	}
	
	public boolean checkOne(String stu_id, String password) {
		openSession();
		
		boolean flag=false;
		Student p =(Student) s.get(Student.class, stu_id);
		if(p!=null)	{
			if(p.checkPassword(password)==true) {
				flag=true;
			}
		}
        
        closeSession(false);
        return flag;
	}
	
	public void getbyId(String stu_id) {
		openSession();
		
		Student p =(Student) s.get(Student.class, stu_id);
		if(p!=null)	{
			System.out.println("stu_id = "+p.getStu_id()+" , name = "+p.getName()+" , password = "+p.getPassword());
		}
        
        closeSession(false);
	}
	
	public void showAll() {
		openSession();
		
		Query query=s.createQuery("from Student");
		List<Student> list=query.list();
		
		for(int i=0; i<list.size(); i++) {
			Student tmp=list.get(i);
			System.out.println("stu_id = "+tmp.getStu_id()+" , name = "+tmp.getName()+" , password = "+tmp.getPassword());
		}

		closeSession(false);
	}
	
	public List<Student> getAll() {
		openSession();
		
		Query query=s.createQuery("from Student");
		List<Student> list=query.list();
		closeSession(false);
		
		return list;
	}
}