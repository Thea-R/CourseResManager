package forAction;

import java.io.IOException;
import java.io.PrintWriter;

import javax.mail.Session;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import forDao.*;

public class modifyPassword_adm extends HttpServlet {
	public modifyPassword_adm() {
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
		HttpSession session=request.getSession();
		String adm_id=(String)session.getAttribute("id");
		String old=request.getParameter("old");
		String now=request.getParameter("now");
		AdminDao adm=new AdminDao();
		
		response.setContentType("text/html;charset=GBK");
		
		if(adm.modifyPassword(adm_id, old, now)==true) {
			String script = "<script>alert('修改密码成功，请重新登录');location.href='../index.jsp'</script>";
			response.getWriter().println(script);
		}
		else {
			String script = "<script>alert('修改失败，请重新输入');location.href='../modifyPassword_adm.jsp'</script>";
			response.getWriter().println(script);
		}
	}

	public void init() throws ServletException {
	}
}