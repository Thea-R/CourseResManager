package forAction;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.*;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import forDao.CourseDao;
import forDao.Stu_courseDao;
import forDao.Trans;
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

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	public void doAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Trans trans=new Trans();
		StudentDao stu=new StudentDao();
		TeacherDao tea=new TeacherDao();
		
		String sadd=request.getParameter("sadd");
		String tadd=request.getParameter("tadd");
		if(sadd!=null) {
			String sid=trans.to(request.getParameter("sid_add"));
			String snm=trans.to(request.getParameter("snm_add"));
			String spw=trans.to(request.getParameter("spw_add"));
			
			String script=new String();
			if(sid.length()==0 || snm.length()==0 || spw.length()==0) script = "<script>alert('信息不完整，请重新输入');location.href='../mainAdmin.jsp'</script>";
			else if(stu.findOne(sid)==true || tea.findOne(sid)==true) script = "<script>alert('帐号冲突，请重新输入');location.href='../mainAdmin.jsp'</script>";
			else if(sid.length()>20 || snm.length()>20 || spw.length()>20)	script = "<script>alert('信息长度大于20，请重新输入');location.href='../mainAdmin.jsp'</script>";
			else if(stu.addOne(sid, snm, spw)==true) script = "<script>alert('添加学生帐号成功');location.href='../mainAdmin.jsp'</script>";
			else script = "<script>alert('添加帐号失败？？？');location.href='../mainAdmin.jsp'</script>";
			response.getWriter().println(script);
			return ;
		}
		else if(tadd!=null) {
			String tid=trans.to(request.getParameter("tid_add"));
			String tnm=trans.to(request.getParameter("tnm_add"));
			String tpw=trans.to(request.getParameter("tpw_add"));
			
			String script=new String();
			if(tid.length()==0 || tnm.length()==0 || tpw.length()==0) script = "<script>alert('信息不完整，请重新输入');location.href='../mainAdmin.jsp'</script>";
			else if(stu.findOne(tid)==true || tea.findOne(tid)==true) script = "<script>alert('帐号冲突，请重新输入');location.href='../mainAdmin.jsp'</script>";
			else if(tid.length()>20 || tnm.length()>20 || tpw.length()>20)	script = "<script>alert('信息长度大于20，请重新输入');location.href='../mainAdmin.jsp'</script>";
			else if(tea.addOne(tid, tnm, tpw)==true) script = "<script>alert('添加教师帐号成功');location.href='../mainAdmin.jsp'</script>";
			else script = "<script>alert('添加帐号失败？？？');location.href='../mainAdmin.jsp'</script>";
			response.getWriter().println(script);
			return ;
		}
	}
	
	public void doModify (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Trans trans=new Trans();
		StudentDao stu=new StudentDao();
		TeacherDao tea=new TeacherDao();
		
		List<Student> stul=stu.getAll();
		List<Teacher> teal=tea.getAll();
		
		for(int i=0; i<stul.size(); i++) {
			Student tmp=stul.get(i);
			String str="smod"+i;
			if(request.getParameter(str)!=null) {
				String stu_id=tmp.getStu_id(),	old=tmp.getPassword(), script=new String();
				String name=trans.to(request.getParameter("snm"+i));
				String _new=trans.to(request.getParameter("spw"+i));
				
				if(stu_id.length()>20 || name.length()>20 || _new.length()>20)	script = "<script>alert('信息长度大于20，请重新输入');location.href='../mainAdmin.jsp'</script>";
				else {
					stu.modifyName(stu_id, name);
					stu.modifyPassword(stu_id, old, _new);
					script = "<script>alert('修改帐号信息成功');location.href='../mainAdmin.jsp'</script>";
				}
				response.getWriter().println(script);
				return ;
			}
		}
		
		for(int i=0; i<teal.size(); i++) {
			Teacher tmp=teal.get(i);
			String str="tmod"+i;
			if(request.getParameter(str)!=null) {
				String tea_id=tmp.getTea_id(), old=tmp.getPassword(), script=new String();
				String name=trans.to(request.getParameter("tnm"+i));
				String _new=trans.to(request.getParameter("tpw"+i));
				
				if(tea_id.length()>20 || name.length()>20 || _new.length()>20)	script = "<script>alert('信息长度大于20，请重新输入');location.href='../mainAdmin.jsp'</script>";
				else {
					tea.modifyName(tea_id, name);
					tea.modifyPassword(tea_id, old, _new);
					script = "<script>alert('修改帐号信息成功');location.href='../mainAdmin.jsp'</script>";
				}
				response.getWriter().println(script);
				return ;
			}
		}
	}
	
	public void doDelete (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		StudentDao stu=new StudentDao();
		TeacherDao tea=new TeacherDao();
		Stu_courseDao scs=new Stu_courseDao();
		CourseDao cs=new CourseDao();
		
		List<Student> stul=stu.getAll();
		List<Teacher> teal=tea.getAll();
		
		for(int i=0; i<stul.size(); i++) {
			Student tmp=stul.get(i);
			String str="sdel"+i;
			if(request.getParameter(str)!=null) {
				String stu_id=tmp.getStu_id(), script;
				stu.deleteOne(stu_id);
				scs.deletebyStu_id(stu_id);
				script = "<script>alert('删除帐号成功');location.href='../mainAdmin.jsp'</script>";
				response.getWriter().println(script);
				return ;
			}
		}
		
		for(int i=0; i<teal.size(); i++) {
			Teacher tmp=teal.get(i);
			String str="tdel"+i;
			if(request.getParameter(str)!=null) {
				String tea_id=tmp.getTea_id(), script;
				tea.deleteOne(tea_id);
				cs.deletebyTea_id(tea_id);
				script = "<script>alert('删除帐号成功');location.href='../mainAdmin.jsp'</script>";
				response.getWriter().println(script);
				return ;
			}
		}
	}
	
	public void doModify_self (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Trans trans=new Trans();
		AdminDao adm=new AdminDao();
		
		HttpSession session=request.getSession();
		String id=(String)session.getAttribute("id");
		String old=trans.to(request.getParameter("old"));
		String now=trans.to(request.getParameter("now"));
		String modify_self=request.getParameter("modify_self");
		
		if(modify_self!=null) {
			if(now.length()>20)	{
				String script = "<script>alert('密码长度大于20，请重新输入');location.href='../mainAdmin.jsp'</script>";
				response.getWriter().println(script);
			}
			else if(adm.modifyPassword(id, old, now)==true) {
				String script = "<script>alert('修改密码成功，请重新登录');location.href='../index.jsp'</script>";
				response.getWriter().println(script);
			}
			else {
				String script = "<script>alert('修改失败，请重新输入');location.href='../mainAdmin.jsp'</script>";
				response.getWriter().println(script);
			}
		}
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=GBK");
		
		doAdd(request, response);
		doModify(request, response);
		doDelete(request, response);
		doModify_self(request, response);
		String script = "<script>alert('nothing's done');location.href='../mainAdmin.jsp'</script>";
		response.getWriter().println(script);
	}

	public void init() throws ServletException {
	}
}
