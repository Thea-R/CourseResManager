package forAction;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import forDao.Stu_courseDao;
import forDao.StudentDao;
import forXml.Stu_course;
import forXml.pkeyStu_course;

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
				return ;
			}
			else {
				String script = "<script>alert('修改失败，请重新输入');location.href='../mainStudent.jsp'</script>";
				response.getWriter().println(script);
				return ;
			}
		}
	}
	
	public void doModify_evalua(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String stu_id=(String)request.getSession().getAttribute("id");
		Stu_courseDao stu_course=new Stu_courseDao();
		List<Stu_course> csl=stu_course.getbyStu_id(stu_id);
		
		for(int i=0; i<csl.size(); i++) {
			Stu_course tmp=csl.get(i);
			pkeyStu_course pkey=tmp.getPkey();
			
			String str="evalua"+i;
			if(request.getParameter(str)!=null) {
				String eva=request.getParameter("eva"+i), script=new String();
				if(eva.length()!=0 && stu_course.modifyTea_evaluation(pkey, eva)==true) {
					script = "<script>alert('修改评教成功');location.href='../mainStudent.jsp'</script>";
					response.getWriter().println(script);
					return ;
				}
				else {
					script = "<script>alert('修改评教失败，请重新评教');location.href='../mainStudent.jsp'</script>";
					response.getWriter().println(script);
					return ;
				}
			}
		}
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=GBK");
		
		doModify_self(request, response);
		doModify_evalua(request, response);
		return ;
	}

	public void init() throws ServletException {
	}
}
