package forAction;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import forDao.StudentDao;

public class stuManage extends HttpServlet {
	public stuManage() {
		super();
	}

	public void destroy() {
		super.destroy(); 
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
	
	public void doModify_self (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		StudentDao stu=new StudentDao();
		
		HttpSession session=request.getSession();
		String id=(String)session.getAttribute("id");
		String old=request.getParameter("old");
		String now=request.getParameter("now");
		String modify_self=request.getParameter("modify_self");
		
		if(modify_self!=null) {
			if(stu.modifyPassword(id, old, now)==true) {
				String script = "<script>alert('修改密码成功，请重新登录');location.href='../index.jsp'</script>";
				response.getWriter().println(script);
			}
			else {
				String script = "<script>alert('修改失败，请重新输入');location.href='../mainStudent.jsp'</script>";
				response.getWriter().println(script);
			}
		}
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=GBK");
		
		doModify_self(request, response);
	}

	public void init() throws ServletException {
	}
}
