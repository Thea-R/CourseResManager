package forTest;

import java.util.List;

import forDao.*;
import forXml.*;

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
	
	static void testCourse() {
		CourseDao dao=new CourseDao();
		/*
		dao.addOne("course001", "语文", "2001");
		dao.addOne("course002", "语文", "2002");
		dao.addOne("course003", "数学", "2001");
		dao.addOne("course004", "数学", "2003");
		dao.addOne("course005", "英语", "2002");
		dao.addOne("course006", "英语", "2003");
		
		List<Course> list=dao.getAll();
		for(int i=0; i<list.size(); i++) {
			Course tmp=list.get(i);
			System.out.println("no="+tmp.getCourse_no()+" title="+tmp.getTitle()+" tea_id="+tmp.getTea_id());
		}
		*/
		return ;
	}
	
	static void testCourseware() {
		CoursewareDao dao=new CoursewareDao();
		/*
		dao.addOne("course001", "语文课件_赵甲");
		dao.addOne("course002", "语文课件_钱乙");
		dao.addOne("course003", "数学课件_赵甲");
		dao.addOne("course004", "数学课件_孙丙");
		dao.addOne("course005", "英语课件_钱乙");
		dao.addOne("course006", "英语课件_孙丙");
		
		List<Courseware> list=dao.getAll();
		for(int i=0; i<list.size(); i++) {
			Courseware tmp=list.get(i);
			System.out.println("no="+tmp.getCourse_no()+" file_title="+tmp.getFile_title());
		}
		*/
		return ;
	}
	
	static void testTea_homework() {
		Tea_homeworkDao dao=new Tea_homeworkDao();
		/*
		dao.addOne("course001", "hwk0001");
		dao.addOne("course002", "hwk0002");
		dao.addOne("course003", "hwk0003");
		dao.addOne("course003", "hwk0004");
		dao.addOne("course004", "hwk0005");
		dao.addOne("course004", "hwk0006");
		dao.addOne("course005", "hwk0007");
		dao.addOne("course006", "hwk0008");
		
		List<Tea_homework> list=dao.getAll();
		for(int i=0; i<list.size(); i++) {
			pkeyTea_homework tmp=list.get(i).getPkey();
			System.out.println("no="+tmp.getCourse_no()+" homework_no="+tmp.getHomework_no());
		}
		*/
		return ;
	}
	
	static void testStu_course() {
		Stu_courseDao dao=new Stu_courseDao();
		/*
		dao.addOne("course001", "1001");
		dao.addOne("course004", "1001");
		dao.addOne("course005", "1001");
		dao.addOne("course001", "1002");
		dao.addOne("course003", "1002");
		dao.addOne("course006", "1002");
		dao.addOne("course002", "1003");
		dao.addOne("course004", "1003");
		dao.addOne("course006", "1003");
		
		List<Stu_course> list=dao.getbyStu_id("1001");
		for(int i=0; i<list.size(); i++) {
			Stu_course tmp=list.get(i);
			pkeyStu_course pkey=tmp.getPkey();
			System.out.println("no="+pkey.getCourse_no()+" id="+pkey.getStu_id()+" grade="+tmp.getGrade()+" eva="+tmp.getTea_evaluation());
		}
		*/
		return ;
	}
	
	static void testStu_homework() {
		Stu_homeworkDao dao=new Stu_homeworkDao();
		
		dao.addOne("course001", "hwk0001", "1001");
		dao.addOne("course001", "hwk0001", "1002");
		dao.addOne("course002", "hwk0002", "1003");
		dao.addOne("course003", "hwk0003", "1002");
		dao.addOne("course003", "hwk0004", "1002");
		dao.addOne("course004", "hwk0005", "1001");
		dao.addOne("course004", "hwk0005", "1003");
		dao.addOne("course004", "hwk0006", "1001");
		dao.addOne("course004", "hwk0006", "1003");
		dao.addOne("course005", "hwk0007", "1001");
		dao.addOne("course006", "hwk0008", "1002");
		dao.addOne("course006", "hwk0008", "1003");
		/*
		List<Stu_homework> list=dao.getAll();
		for(int i=0; i<list.size(); i++) {
			Stu_homework tmp=list.get(i);
			pkeyStu_homework pkey=tmp.getPkey();
			System.out.println("no="+pkey.getCourse_no()+" hmk="+pkey.getHomework_no()+" id="+pkey.getStu_id()+" status="+tmp.getStatus()+" opinion="+tmp.getOpinion());
		}
		
		List<Stu_homework> list=dao.getbyStu_id("1001"); 
			for(int i=0; i<list.size(); i++) {
				Stu_homework tmp=list.get(i);
				pkeyStu_homework pkey=tmp.getPkey();
				System.out.println("no="+pkey.getCourse_no()+" id="+pkey.getStu_id()+" hmk="+pkey.getHomework_no()+" status="+tmp.getStatus()+" opinion="+tmp.getOpinion());
			}
		*/
		return ;
	}
	
	static public void main(String args[]) {
		//testAdmin();
		//testStudent();
		//testTeacher();
		//testCourse();
		//testCourseware();
		//testTea_homework();
		//testStu_course();
		testStu_homework();
	}
}
