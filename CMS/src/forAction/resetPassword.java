package forAction;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import forDao.*;

public class resetPassword extends HttpServlet {
	public resetPassword() {
		super();
	}

	public void destroy() {
		super.destroy();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id=request.getParameter("id");
		String nm=request.getParameter("name");
		StudentDao stu=new StudentDao();
		TeacherDao tea=new TeacherDao();
		
		response.setContentType("text/html;charset=GBK");
		
		if(stu.resetPassword(id, nm)==true) {
			String script = "<script>alert('重置密码成功，请重新登录');location.href='../index.jsp'</script>";
			response.getWriter().println(script);
		}
		else if(tea.resetPassword(id, nm)==true) {
			String script = "<script>alert('重置密码成功，请重新登录');location.href='../index.jsp'</script>";
			response.getWriter().println(script);
		}
		else {
			String script = "<script>alert('姓名和帐号不匹配，请重新输入');location.href='../forgetPassword.jsp'</script>";
			response.getWriter().println(script);
		}
	}

	public void init() throws ServletException {
	}
}
