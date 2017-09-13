package forAction;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import forDao.*;

public class checkIdentity extends HttpServlet {
	public checkIdentity() {
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

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id=request.getParameter("id");
		String pw=request.getParameter("password");
		AdminDao adm=new AdminDao();
		StudentDao stu=new StudentDao();
		TeacherDao tea=new TeacherDao();
		
		response.setContentType("text/html;charset=GBK");
		
		if(adm.checkOne(id, pw)==true) {
			HttpSession session=request.getSession();
			session.setAttribute("id", id);
			response.sendRedirect("../mainAdmin.jsp");
		}
		else if(stu.checkOne(id, pw)==true) {
			HttpSession session=request.getSession();
			session.setAttribute("id", id);
			response.sendRedirect("../mainStudent.jsp");
		}
		else if(tea.checkOne(id, pw)==true) {
			HttpSession session=request.getSession();
			session.setAttribute("id", id);
			response.sendRedirect("../mainTeacher.jsp") ;
		}
		else {
			String script = "<script>alert('用户名或密码错误，请重新登陆');location.href='../index.jsp'</script>";
			response.getWriter().println(script);
		}
	}

	public void init() throws ServletException {
	}
}
