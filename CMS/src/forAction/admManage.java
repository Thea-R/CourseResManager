package forAction;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import forDao.AdminDao;
import forDao.StudentDao;
import forDao.TeacherDao;
import forXml.Student;
import forXml.Teacher;

public class admManage extends HttpServlet {
	public admManage() {
		super();
	}

	public void destroy() {
		super.destroy(); 
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the GET method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}

	public void doAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		StudentDao stu=new StudentDao();
		TeacherDao tea=new TeacherDao();
		
		String add_stu=request.getParameter("add_stu");
		String add_tea=request.getParameter("add_tea");
		if(add_stu!=null) {
			String adds_id=request.getParameter("adds_id");
			String adds_nm=request.getParameter("adds_nm");
			String adds_pw=request.getParameter("adds_pw");
			
			System.out.println(adds_id+" "+adds_nm+" "+adds_pw);
			if(adds_id.length()==0 || adds_nm.length()==0 || adds_pw.length()==0) {
				String script = "<script>alert('信息不完整，请重新输入');location.href='../mainAdmin.jsp'</script>";
				response.getWriter().println(script);
			}
			else {
				if(stu.findOne(adds_id)==true || tea.findOne(adds_id)==true) {
					String script = "<script>alert('帐号冲突，请重新输入');location.href='../mainAdmin.jsp'</script>";
					response.getWriter().println(script);
				}
				else {
					
					if(stu.addOne(adds_id, adds_nm, adds_pw)==true) {
						String script = "<script>alert('添加学生帐号成功');location.href='../mainAdmin.jsp'</script>";
						response.getWriter().println(script);
					}
					else {
						System.out.println("no ???");
						String script = "<script>alert('添加帐号失败？？？');location.href='../mainAdmin.jsp'</script>";
						response.getWriter().println(script);
					}
				}
			}
		}
		else if(add_tea!=null) {
			String addt_id=request.getParameter("addt_id");
			String addt_nm=request.getParameter("addt_nm");
			String addt_pw=request.getParameter("addt_pw");
			
			System.out.println(addt_id+" "+addt_nm+" "+addt_pw);
			if(addt_id.length()==0 || addt_nm.length()==0 || addt_pw.length()==0) {
				String script = "<script>alert('信息不完整，请重新输入');location.href='../mainAdmin.jsp'</script>";
				response.getWriter().println(script);
			}
			else {
				if(stu.findOne(addt_id)==true || tea.findOne(addt_id)==true) {
					String script = "<script>alert('帐号冲突，请重新输入');location.href='../mainAdmin.jsp'</script>";
					response.getWriter().println(script);
				}
				else {
					if(tea.addOne(addt_id, addt_nm, addt_pw)==true) {
						System.out.println("get ???");
						String script = "<script>alert('添加教师帐号成功');location.href='../mainAdmin.jsp'</script>";
						response.getWriter().println(script);
					}
					else {
						System.out.println("no ???");
						String script = "<script>alert('添加帐号失败？？？');location.href='../mainAdmin.jsp'</script>";
						response.getWriter().println(script);
					}
				}
			}
		}
	}
	
	public void doModify (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		StudentDao stu=new StudentDao();
		TeacherDao tea=new TeacherDao();
		
		String modify_stu=request.getParameter("modify_stu");
		String modify_tea=request.getParameter("modify_tea");
		
		if(modify_stu!=null) {
			String id=request.getParameter("modify_id");
			String nm=request.getParameter("modify_nm");
			String pw=request.getParameter("modify_pw");
			
			boolean flag=false;
			Student tmp=stu.getbyId(id);
			if(nm.length()!=0) {
				flag=true;
				tmp.setName(nm);
			}
			if(pw.length()!=0) {
				flag=true;
				tmp.setPassword(pw);
			}
			
			if(flag==true) {
				String script = "<script>alert('修改成功');location.href='../mainAdmin.jsp'</script>";
				response.getWriter().println(script);
			}
			else {
				String script = "<script>alert('修改失败');location.href='../mainAdmin.jsp'</script>";
				response.getWriter().println(script);
			}
		}
		else if(modify_tea!=null) {
			String id=request.getParameter("modify_id");
			String nm=request.getParameter("modify_nm");
			String pw=request.getParameter("modify_pw");
			
			boolean flag=false;
			Teacher tmp=tea.getbyId(id);
			if(nm.length()!=0) {
				flag=true;
				tmp.setName(nm);
			}
			if(pw.length()!=0) {
				flag=true;
				tmp.setPassword(pw);
			}
			
			if(flag==true) {
				String script = "<script>alert('修改成功');location.href='../mainAdmin.jsp'</script>";
				response.getWriter().println(script);
			}
			else {
				String script = "<script>alert('修改失败');location.href='../mainAdmin.jsp'</script>";
				response.getWriter().println(script);
			}
		}
	}
	
	public void doDelete (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		StudentDao stu=new StudentDao();
		TeacherDao tea=new TeacherDao();
		
		String modify_stu=request.getParameter("modify_stu");
		String modify_tea=request.getParameter("modify_tea");
		
		if(modify_stu!=null) {
			
		}
		else if(modify_tea!=null) {
			
		}
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=GBK");
		
		doAdd(request, response);
		doModify(request, response);
		doDelete(request, response);
		
		String script = "<script>alert('nothing's done');location.href='../mainAdmin.jsp'</script>";
		response.getWriter().println(script);
		
		/*
		if(adm.checkOne(id, pw)==true) {
			HttpSession session=request.getSession();
			session.setAttribute("id", id);
			session.setAttribute("identity", "admin");
			response.sendRedirect("../mainAdmin.jsp");
		}
		else if(stu.checkOne(id, pw)==true) {
			HttpSession session=request.getSession();
			session.setAttribute("id", id);
			session.setAttribute("identity", "student");
			response.sendRedirect("../mainStudent.jsp");
		}
		else if(tea.checkOne(id, pw)==true) {
			HttpSession session=request.getSession();
			session.setAttribute("id", id);
			session.setAttribute("identity", "teacher");
			response.sendRedirect("../mainTeacher.jsp") ;
		}
		else {
			String script = "<script>alert('用户名或密码错误，请重新登陆');location.href='../index.jsp'</script>";
			response.getWriter().println(script);
		}
		*/
	}

	public void init() throws ServletException {
	}
}
