package forTest;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import forDao.*;

public class testHibernate {
	static void testAdmin() {
		AdminDao dao=new AdminDao();
		
		//dao.addOne("abc");
		//dao.addOne("ff");
		//dao.addOne("rsl");
		
		//System.out.println("deleted whoes adm_id = ... "+dao.deleteOne("..."));
		//System.out.println("deleted whoes adm_id = abc "+dao.deleteOne("abc"));
		
		//System.out.println("find whoes adm_id = rsl : "+dao.findOne("rsl"));
		//System.out.println("find whoes adm_id = rsl : "+dao.findOne("abc"));
		
		//System.out.println("modified whoes adm_id = abc "+dao.modifyPassword("abc", "...", "..."));
		//System.out.println("modified whoes adm_id = rsl "+dao.modifyPassword("rsl", "...", "..."));
		//System.out.println("modified whoes adm_id = rsl : "+dao.modifyPassword("rsl", "000000", "134727"));
		
		//dao.getbyId("rsl");
		//dao.showAll();
		return ;
	}
	
	static void testStudent() {
		StudentDao dao=new StudentDao();
		
		//dao.addOne("1001");
		//dao.addOne("1002");
		//dao.addOne("1003");
				
		//System.out.println("deleted whoes stu_id = ... "+dao.deleteOne("..."));
		//System.out.println("deleted whoes stu_id = 1003 "+dao.deleteOne("1003"));
		
		//System.out.println("find whoes stu_id = 1002 : "+dao.findOne("1002"));
		//System.out.println("find whoes stu_id = 1003 : "+dao.findOne("1003"));
		
		//System.out.println("modified whoes stu_id = 1001 "+dao.modifyPassword("1001", "---", "000000", "100100"));
		//System.out.println("modified whoes stu_id = 1001 "+dao.modifyName("1001", "Ann"));
		//dao.getbyId("1001");
		
		//System.out.println("modified whoes stu_id = 1002 "+dao.modifyName("1002", "Zoe"));
		//System.out.println("modified whoes stu_id = 1002 "+dao.modifyPassword("1002", "Zoe", "000000", "100200"));
		//dao.getbyId("1002");
		
		//dao.addOne("1003", "Jack");
		//System.out.println("modified whoes stu_id = 1003 "+dao.modifyPassword("1003", "Jack", "000000", "100300"));
		//dao.getbyId("1003");
		
		//dao.showAll();
		return ;
	}
	
	static void testTeacher() {
		TeacherDao dao=new TeacherDao();
		
		//dao.addOne("2001");
		//dao.addOne("2002");
		//dao.addOne("2003");
				
		//System.out.println("deleted whoes tea_id = ... "+dao.deleteOne("..."));
		//System.out.println("deleted whoes tea_id = 2003 "+dao.deleteOne("2003"));
		
		//System.out.println("find whoes tea_id = 2002 : "+dao.findOne("2002"));
		//System.out.println("find whoes tea_id = 2003 : "+dao.findOne("2003"));
		
		//System.out.println("modified whoes tea_id = 2001 "+dao.modifyPassword("2001", "---", "000000", "200100"));
		//System.out.println("modified whoes tea_id = 2001 "+dao.modifyName("2001", "Susan"));
		//dao.getbyId("2001");
		
		//System.out.println("modified whoes tea_id = 2002 "+dao.modifyName("2002", "Mike"));
		//System.out.println("modified whoes tea_id = 2002 "+dao.modifyPassword("2002", "Mike", "000000", "200200"));
		//dao.getbyId("2002");
		
		//dao.addOne("2003", "Bob");
		//System.out.println("modified whoes tea_id = 2003 "+dao.modifyPassword("2003", "Bob", "000000", "200300"));
		//dao.getbyId("2003");
		
		//dao.showAll();
		return ;
	}
	
	static public void main(String args[]) {
		//testAdmin();
		//testStudent();
		//testTeacher();
	}
}
