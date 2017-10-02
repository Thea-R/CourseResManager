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
import javax.servlet.jsp.JspFactory;
import javax.servlet.jsp.PageContext;

import org.apache.commons.io.FileUtils;

import com.jspsmart.upload.File;
import com.jspsmart.upload.Request;
import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;

import forDao.CoursewareDao;
import forDao.Stu_homeworkDao;
import forDao.TeacherDao;
import forDao.Trans;
import forDao.Stu_courseDao;
import forDao.StudentDao;
import forXml.Stu_course;
import forXml.Stu_homework;
import forXml.pkeyStu_course;
import forXml.pkeyStu_homework;

public class stuManage extends HttpServlet {
	public stuManage() {
		super();
	}

	public void destroy() {
		super.destroy(); 
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=GBK");
		Trans trans=new Trans();

		SmartUpload su = new SmartUpload();
        Request req = su.getRequest();// 由于multipart/form-data的传输原因，使用smartupload产生的req  
        PageContext pageContext = JspFactory.getDefaultFactory().getPageContext(this, request, response, null, true, 8192, true);  
        su.initialize(pageContext);
        
        try {  
            su.upload();

            String modify_self=req.getParameter("modify_self");
            if(modify_self!=null) {
            	StudentDao stu=new StudentDao();
        		HttpSession session=request.getSession();
        		String id=(String)session.getAttribute("id");
        		String old=trans.to(req.getParameter("old"));
        		String now=trans.to(req.getParameter("now"));
        		
        		if(now.length()>20)	{
    				String script = "<script>alert('密码长度大于20，请重新输入');location.href='../mainStudent.jsp'</script>";
    				response.getWriter().println(script);
    			}
        		else if(stu.modifyPassword(id, old, now)==true) {
    				String script = "<script>alert('修改密码成功，请重新登录');location.href='../index.jsp'</script>";
    				response.getWriter().println(script);
    			}
    			else {
    				String script = "<script>alert('修改密码失败，请重新输入');location.href='../mainStudent.jsp'</script>";
    				response.getWriter().println(script);
    			}
        		return ;
            }
            else {
        		HttpSession session=request.getSession();
        		String stu_id=(String)session.getAttribute("id");
        		
        		Stu_courseDao stu_course=new Stu_courseDao();
        		List<Stu_course> csl=stu_course.getbyStu_id(stu_id);
        		for(int i=0; i<csl.size(); i++) {
        			Stu_course tmp=csl.get(i);
        			pkeyStu_course pkey=tmp.getPkey();
        			String cno=pkey.getCourse_no();
        			
        			String str1="evalua"+i;
        			String str2="dnc"+i;
        			String str3="eva"+i;
        			if(req.getParameter(str1)!=null) {
        				String eva=trans.to(req.getParameter(str3)), script=new String();
        				
        				if(eva.length()>100) script = "<script>alert('评教内容过长（请输入少于100字）');location.href='../mainStudent.jsp'</script>";
        				else if(eva.length()==0) script = "<script>alert('无评教内容，请重新评教');location.href='../mainStudent.jsp'</script>";
        				else if(stu_course.modifyTea_evaluation(pkey, eva)==true) script = "<script>alert('评教成功');location.href='../mainStudent.jsp'</script>";
        				else script = "<script>alert('评教失败，请重新评教');location.href='../mainStudent.jsp'</script>";
        				
        				response.getWriter().println(script);
        				return ;
        			}
        			else if(req.getParameter(str2)!=null) {
        				CoursewareDao cw=new CoursewareDao();
    					String filename=cw.getbyCourse_no(cno).getFile_title();
    					String dir1=getServletContext().getRealPath("/")+"WEB-INF/courseware/"+cno+filename.substring(filename.lastIndexOf("."));
    					String dir2=getServletContext().getRealPath("/")+"WEB-INF/courseware/"+filename;
    					java.io.File file1=new java.io.File(dir1);
    					java.io.File file2=new java.io.File(dir2);
    					
    					FileUtils.copyFile(file1, file2);
    					su.setContentDisposition(null);//设定contentDisposition为null以禁止浏览器自动打开文件，保证点击链接后是下载文件
    					su.downloadFile(dir2);// 下载文件，保证Web应用下的目录下有文件
    					file2.delete();
    					return ;
    				}
        		}
        		
        		Stu_homeworkDao stu_homework=new Stu_homeworkDao();
        		List<Stu_homework> hkl=stu_homework.getbyStu_id(stu_id);
        		
        		for(int i=0, j=0; i<hkl.size(); i++) {
        			Stu_homework tmp=hkl.get(i);
        			pkeyStu_homework pkey=tmp.getPkey();
        			String cno=pkey.getCourse_no();
        			String hno=pkey.getHomework_no();
        			
        			if(tmp.getOpinion()!=null)	continue;
        			
        			String str1="uphwk"+i;
        			String str2="dnhwk"+i;
        			if(req.getParameter(str1)!=null) {
        				File file=su.getFiles().getFile(j);
        				if(file.isMissing()) {
        					String script = "<script>alert('未选择文件！');location.href='../mainStudent.jsp'</script>";
        		    		response.getWriter().println(script);
        				}
        				else {
        					String ext=file.getFileExt();
        					file.saveAs("/WEB-INF/homework/"+hno+"_"+stu_id+"."+ext);
        					stu_homework.modifyFilename(cno, stu_id, hno, hno+"_"+stu_id+"."+ext);
        					String script = "<script>alert('上传成功！');location.href='../mainStudent.jsp'</script>";
        					response.getWriter().println(script);
        				}
    		    		return ;
        			}
        			else if(req.getParameter(str2)!=null) {
        				String filename=tmp.getFilename();
        				String dir=getServletContext().getRealPath("/")+"WEB-INF/homework/"+filename;

        				su.setContentDisposition(null);
        				su.downloadFile(dir);
        				return ;
        			}
        			j++;
        		}
            }
        } catch (SmartUploadException e) {  
            e.printStackTrace();  
        }
		return ;
	}

	public void init() throws ServletException {
	}
}
