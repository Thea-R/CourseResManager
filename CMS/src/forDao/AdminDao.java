package forDao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import forXml.Admin;
 
public class AdminDao  {
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
	
	public boolean addOne(String adm_id) {
		openSession();
		
		boolean flag=false;
		Admin p=(Admin) s.get(Admin.class, adm_id);
		if(p==null) {
			p=new Admin();
			p.setAdm_id(adm_id);
			p.setPassword("000000");
			s.save(p);
			flag=true;
		}
		
		closeSession(flag);
		return flag;
	}
	
	public boolean deleteOne(String adm_id) {
		openSession();
		
		boolean flag=false;
		Admin p =(Admin) s.get(Admin.class, adm_id);
		if(p!=null)	{
			s.delete(p);
			flag=true;
		}
        
        closeSession(flag);
        return flag;
	}
	
	public boolean modifyPassword(String adm_id, String old, String _new) {
		openSession();
		
		boolean flag=false;
		Admin p=(Admin) s.get(Admin.class, adm_id);
		if(p!=null) {
			if(p.modifyPassword(old, _new)==true) {
				flag=true;
			}
		}
		
		closeSession(flag);
		return flag;
	}
	
	public boolean findOne(String adm_id) {
		openSession();
		
		boolean flag=false;
		Admin p=(Admin) s.get(Admin.class, adm_id);
		if(p!=null) {
			flag=true;
		}
		
		closeSession(false);
		return flag;
	}
	
	public boolean checkOne(String adm_id, String password) {
		openSession();
		
		boolean flag=false;
		Admin p =(Admin) s.get(Admin.class, adm_id);
		if(p!=null)	{
			if(p.checkPassword(password)==true) {
				flag=true;
			}
		}
        
        closeSession(false);
        return flag;
	}
	
	public void getbyId(String adm_id) {
		openSession();
		
		Admin p =(Admin) s.get(Admin.class, adm_id);
		if(p!=null)	{
			System.out.println("adm_id = "+p.getAdm_id()+" , password = "+p.getPassword());
		}
        
        closeSession(false);
        return ;
	}
	
	public void showAll() {
		openSession();
		
		Query query=s.createQuery("from Admin");
		List<Admin> list=query.list();
		
		for(int i=0; i<list.size(); i++) {
			Admin tmp=list.get(i);
			System.out.println("adm_id = "+tmp.getAdm_id()+" , password = "+tmp.getPassword());
		}

		closeSession(false);
	}
	
	public List<Admin> getAll() {
		openSession();
		
		Query query=s.createQuery("from Admin");
		List<Admin> list=query.list();
		closeSession(false);
		
		return list;
	}
}