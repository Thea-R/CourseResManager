package forDao;

import forXml.Teacher;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
 
public class TeacherDao  {
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
	
	public boolean addOne(String tea_id) {
		openSession();
		
		boolean flag=false;
		Teacher p=(Teacher) s.get(Teacher.class, tea_id);
		if(p==null) {
			p=new Teacher();
			p.setTea_id(tea_id);
			p.resetPassword();
			p.setName("---");
			s.save(p);
			flag=true;
		}
		
		closeSession(flag);
		return flag;
	}
	
	public boolean addOne(String tea_id, String name) {
		openSession();
		
		boolean flag=false;
		Teacher p=(Teacher) s.get(Teacher.class, tea_id);
		if(p==null) {
			p=new Teacher();
			p.setTea_id(tea_id);
			p.setPassword("000000");
			p.setName(name);
			s.save(p);
			flag=true;
		}
		
		closeSession(flag);
		return flag;	
	}
	
	public boolean deleteOne(String tea_id) {
		openSession();
		
		boolean flag=false;
		Teacher p =(Teacher) s.get(Teacher.class, tea_id);
		if(p!=null)	{
			s.delete(p);
			flag=true;
		}
        
        closeSession(flag);
        return flag;
	}
	
	public boolean modifyName(String tea_id, String name) {
		openSession();
		
		boolean flag=false;
		Teacher p=(Teacher) s.get(Teacher.class, tea_id);
		if(p!=null) {
			p.setName(name);
			flag=true;
		}
		
		closeSession(flag);
		return flag;
	}
	
	public boolean modifyPassword(String tea_id, String name, String old, String _new) {
		openSession();
		
		boolean flag=false;
		Teacher p=(Teacher) s.get(Teacher.class, tea_id);
		if(p!=null) {
			if(p.modifyPassword(name, old, _new)==true) {
				flag=true;
			}
		}
		
		closeSession(flag);
		return flag;
	}
	
	public boolean resetPassword(String tea_id, String name) {
		openSession();
		
		boolean flag=false;
		Teacher p =(Teacher) s.get(Teacher.class, tea_id);
		if(p!=null)	{
			if(p.checkName(name)==true) {
				flag=true;
				p.resetPassword();
			}
		}
        
        closeSession(flag);
        return flag;
	}
	
	public boolean findOne(String tea_id) {
		openSession();
		
		boolean flag=false;
		Teacher p=(Teacher) s.get(Teacher.class, tea_id);
		if(p!=null) {
			flag=true;
		}
		
		closeSession(false);
		return flag;
	}
	
	public boolean checkOne(String tea_id, String password) {
		openSession();
		
		boolean flag=false;
		Teacher p =(Teacher) s.get(Teacher.class, tea_id);
		if(p!=null)	{
			if(p.checkPassword(password)==true) {
				flag=true;
			}
		}
        
        closeSession(false);
        return flag;
	}
	
	public void getbyId(String tea_id) {
		openSession();
		
		Teacher p =(Teacher) s.get(Teacher.class, tea_id);
		if(p!=null)	{
			System.out.println("tea_id = "+p.getTea_id()+" , name = "+p.getName()+" , password = "+p.getPassword());
		}
        
        closeSession(false);
	}
	
	public void showAll() {
		openSession();
		
		Query query=s.createQuery("from Teacher");
		List<Teacher> list=query.list();
		
		for(int i=0; i<list.size(); i++) {
			Teacher tmp=list.get(i);
			System.out.println("tea_id = "+tmp.getTea_id()+" , name = "+tmp.getName()+" , password = "+tmp.getPassword());
		}

		closeSession(false);
	}
	
	public List<Teacher> getAll() {
		openSession();
		
		Query query=s.createQuery("from Teacher");
		List<Teacher> list=query.list();
		closeSession(false);
		
		return list;
	}
}