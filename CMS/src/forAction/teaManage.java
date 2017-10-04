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
import forDao.Stu_courseDao;
import forDao.Stu_homeworkDao;
import forDao.StudentDao;
import forDao.Tea_homeworkDao;
import forDao.TeacherDao;
import forDao.Trans;
import forXml.Course;
import forXml.Stu_course;
import forXml.Stu_homework;
import forXml.Student;
import forXml.Tea_homework;
import forXml.pkeyStu_course;
import forXml.pkeyTea_homework;

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
		
		Trans trans=new Trans();
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
        		String script=new String();
        		
        		if(tea.modifyPassword(id, old, now)==true) script = "<script>alert('修改密码成功，请重新登录');location.href='../index.jsp'</script>";
        		else script = "<script>alert('修改失败，请重新输入');location.href='../mainTeacher.jsp'</script>";
        		response.getWriter().println(script);
            	return ;
            }
            else {
            	CourseDao course=new CourseDao();
            	StudentDao student=new StudentDao();
            	Stu_homeworkDao stu_homework=new Stu_homeworkDao();
            	Tea_homeworkDao tea_homework=new Tea_homeworkDao();
            	Stu_courseDao stu_course=new Stu_courseDao();
            	
            	String tea_id=(String)request.getSession().getAttribute("id");	
            	List<Course> cl=course.getbyTea_id(tea_id);

            	for(int i=0; i<cl.size(); i++) {
            		Course tmp=cl.get(i);
    				String cno=tmp.getCourse_no();
    				
    				String upc=req.getParameter("upc"+i);
    				String dnc=req.getParameter("dnc"+i);
    				String rel=req.getParameter("rel"+i);
    				
    				if(upc!=null) {
    					File file=su.getFiles().getFile(i);
    					if(file.isMissing()) {
        					String script = "<script>alert('未选择文件！');location.href='../mainTeacher.jsp'</script>";
        		    		response.getWriter().println(script);
        				}
    					else {
    						String savePath = getServletContext().getRealPath("/")+"WEB-INF/courseware";
    		                java.io.File f = new java.io.File(savePath);
    		                if (!f.exists() && !f.isDirectory()) f.mkdir();
    						
    						String filename=file.getFileName();
    						String ext=file.getFileExt();
    						file.saveAs("/WEB-INF/courseware/"+cno+"."+ext);
    		        		CoursewareDao cw=new CoursewareDao();
    		        		cw.modifyFile_title(cno, filename);
    		        		String script = "<script>alert('上传成功！');location.href='../mainTeacher.jsp'</script>";
    		        		response.getWriter().println(script);
    					}
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
    				else if(rel!=null) {
    					String hno=tea_homework.getNext();
    					tea_homework.addOne(cno, hno);
    					
    					List<Stu_course> scs=stu_course.getbyCourse_no(cno);
    					for(int j=0; j<scs.size(); j++) {
    						String stu_id=scs.get(j).getPkey().getStu_id();
    						stu_homework.addOne(cno, hno, stu_id);
    					}
    					String script = "<script>alert('发布作业成功！');location.href='../mainTeacher.jsp'</script>";
    		    		response.getWriter().println(script);
    		    		return ;
    				}
            		
            		List<Tea_homework> thk=tea_homework.getbyCourse_no(cno);
            		for(int k=0; k<thk.size(); k++) {
            			pkeyTea_homework pkey=thk.get(k).getPkey();
            			String hno=pkey.getHomework_no();
            			
            			List<Stu_homework> shk=stu_homework.getbyHomework_no(hno);
            			for(int j=0; j<shk.size (); j++) {
            				Stu_homework hk=shk.get(j);
            				String filename=(hk.getFilename()==null ? null : hk.getFilename().toString());
            				
            				String stu_id=hk.getPkey().getStu_id();
            				
            				int num=i*thk.size()*shk.size()+k*shk.size()+j;
            				String str1="dnhwk"+num;
            				String str2="upo"+num;
            				if(req.getParameter(str1)!=null) {
                				String dir=getServletContext().getRealPath("/")+"WEB-INF/homework/"+filename;
                				su.setContentDisposition(null);
                				su.downloadFile(dir);
            					return ;
            				}
            				else if(req.getParameter(str2)!=null) {
            					String op=req.getParameter("op"+num);
            					stu_homework.modifyOpinion(cno, stu_id, hno, op);
            					String script = "<script>alert('批改成功！');location.href='../mainTeacher.jsp'</script>";
            		    		response.getWriter().println(script);
            					return ;
            				}
            			}
            		}
            		
            		List<Stu_course> scl=stu_course.getbyCourse_no(cno);
            		for(int j=0; j<scl.size (); j++) {
            			Stu_course scs=scl.get(j);
        				pkeyStu_course pkey=scs.getPkey();
        				
            			int num=i*scl.size()+j;
        				String str="sgd"+num;
        				if(req.getParameter(str)!=null) {
        					String grade=req.getParameter("gd"+num), script=new String();
        					if(trans.db(grade)==false)	script = "<script>alert('非法分数，请重新评分');location.href='../mainTeacher.jsp'</script>";
        					else {
        						stu_course.modifyGrade(pkey, Double.valueOf(grade));
        						script = "<script>alert('评分成功！');location.href='../mainTeacher.jsp'</script>";
        					}
        					response.getWriter().println(script);
        					return ;
         				}
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
