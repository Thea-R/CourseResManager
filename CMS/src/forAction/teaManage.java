package forAction;

import java.io.IOException;
import java.util.Enumeration;
import java.util.List;

import com.jspsmart.upload.*;

import javax.activation.MimetypesFileTypeMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspFactory;
import javax.servlet.jsp.PageContext;

import org.apache.commons.io.FileUtils;

import forDao.CourseDao;
import forDao.CoursewareDao;
import forDao.TeacherDao;
import forXml.Course;

public class teaManage extends HttpServlet {
	public teaManage() {
		super();
	}

	public void destroy() {
		super.destroy(); 
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=GBK");
		
		SmartUpload su = new SmartUpload();
        Request req = su.getRequest();// 由于multipart/form-data的传输原因，使用smartupload产生的req  
        PageContext pageContext = JspFactory.getDefaultFactory().getPageContext(this, request, response, null, true, 8192, true);  
        su.initialize(pageContext);
        
        try {  
            su.upload();

            String modify_self=req.getParameter("modify_self");
            if(modify_self!=null) {
            	TeacherDao tea=new TeacherDao();
        		
        		String id=(String)request.getSession().getAttribute("id");
        		String old=req.getParameter("old");
        		String now=req.getParameter("now");
        		
        		if(tea.modifyPassword(id, old, now)==true) {
        			String script = "<script>alert('修改密码成功，请重新登录');location.href='../index.jsp'</script>";
        			response.getWriter().println(script);
        		}
        		else {
        			String script = "<script>alert('修改失败，请重新输入');location.href='../mainTeacher.jsp'</script>";
        			response.getWriter().println(script);
        		}
            	return ;
            }
            else {
            	CourseDao course=new CourseDao();
            	
            	String tea_id=(String)request.getSession().getAttribute("id");
            	List<Course> list=course.getbyTea_id(tea_id);
            	
            	for(int i=0; i<list.size(); i++) {
            		Course tmp=list.get(i);
    				String cno=tmp.getCourse_no();
    				
    				String upc=req.getParameter("upc"+i);
    				String dnc=req.getParameter("dnc"+i);
    				String bro=req.getParameter("bro"+i);
    				
    				if(upc!=null) {
    					File file=su.getFiles().getFile(i);
    		        	String filename=file.getFileName();
    		        	String ext=file.getFileExt();
    		        	file.saveAs("/WEB-INF/courseware/"+cno+"."+ext);
    		            
    		        	CoursewareDao cw=new CoursewareDao();
    		        	cw.modifyFile_title(cno, filename);
    		        	
    		            String script = "<script>alert('上传成功！');location.href='../mainTeacher.jsp'</script>";
    		    		response.getWriter().println(script);
    		    		return ;
    				}
    				else if(dnc!=null) {
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
            }
        } catch (SmartUploadException e) {  
            e.printStackTrace();  
        }
        
        String script = "<script>alert('QAQ');location.href='../mainTeacher.jsp'</script>";
		response.getWriter().println(script);
        return ;
	}

	public void init() throws ServletException {
	}
}
