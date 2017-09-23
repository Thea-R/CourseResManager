package forAction;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.Enumeration;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.ProgressListener;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import forDao.CourseDao;
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
	
	public void doModify_self (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		TeacherDao tea=new TeacherDao();
		
		HttpSession session=request.getSession();
		String id=(String)session.getAttribute("id");
		String old=request.getParameter("old");
		String now=request.getParameter("now");
		String modify_self=request.getParameter("modify_self");
		
		if(modify_self!=null) {
			if(tea.modifyPassword(id, old, now)==true) {
				String script = "<script>alert('修改密码成功，请重新登录');location.href='../index.jsp'</script>";
				response.getWriter().println(script);
				return ;
			}
			else {
				String script = "<script>alert('修改失败，请重新输入');location.href='../mainTeacher.jsp'</script>";
				response.getWriter().println(script);
				return ;
			}
		}
	}
	
	public void doUpcware(HttpServletRequest request, HttpServletResponse response, String cno) throws ServletException, IOException {
		String script=new String();
		String savePath = this.getServletContext().getRealPath("/WEB-INF/courseware");//上传文件的保存目录
        String tempPath = this.getServletContext().getRealPath("/WEB-INF/temp");//上传时生成的临时文件保存目录
        File tmpFile = new File(tempPath);
        if(!tmpFile.exists()) tmpFile.mkdir();//创建临时目录

        //使用Apache文件上传组件处理文件
        try {
        	DiskFileItemFactory factory = new DiskFileItemFactory();//1、创建DiskFileItemFactory工厂
        	factory.setSizeThreshold(1024*100);//设置工厂缓冲区大小为100KB（默认10KB），当上传的文件大小超过缓冲区的大小，就会生成一个临时文件存放到指定的临时目录
        	factory.setRepository(tmpFile);//设置上传时生成的临时文件的保存目录
        
        	ServletFileUpload upload = new ServletFileUpload(factory);//2、创建一个文件上传解析器
        	upload.setProgressListener(new ProgressListener(){//监听文件上传进度
            public void update(long pBytesRead, long pContentLength, int arg2) {
                //System.out.println("文件大小为：" + pContentLength + ",当前已处理：" + pBytesRead);
            }});
        	upload.setHeaderEncoding("UTF-8"); //解决上传文件名的中文乱码
        
        	if(!ServletFileUpload.isMultipartContent(request))  return;//3、判断提交上来的数据是否是上传表单的数据
        	upload.setFileSizeMax(1024*1024);//设置上传单个文件的大小的最大值为1MB
        	upload.setSizeMax(1024*1024*10);//设置上传文件总量的最大值（同时上传多个文件大小最大值的和）为10MB
        
        	List<FileItem> list = upload.parseRequest(request);//4、使用ServletFileUpload解析器解析上传数据，解析结果返回的是一个List<FileItem>集合，每一个FileItem对应一个Form表单的输入项
        	for(FileItem item : list){
        		if(item.isFormField()){
        			//如果fileitem中封装的是普通输入项的数据
        			String name = item.getFieldName();
        			String value = item.getString("UTF-8");//解决普通输入项的数据的中文乱码问题
        		}else{
        			//如果fileitem中封装的是上传文件
        			String filename = item.getName();//得到上传的文件名称
        			if(filename==null || filename.trim().equals(""))	continue;
        			filename = filename.substring(filename.lastIndexOf("\\")+1);//注意有些浏览器提交上来的文件名是带有路径，有些只是单纯的文件名处理获取到的上传文件的文件名的路径部分，只保留文件名部分
        			String fileExtName = filename.substring(filename.lastIndexOf(".")+1);//得到上传文件的扩展名，如果需要限制上传的文件类型，那么可以通过文件的扩展名来判断上传的文件类型是否合法
        			InputStream in = item.getInputStream();//获取item中的上传文件的输入流
        			String saveFilename = cno+"_"+filename;//得到文件保存的名称
        			String realSavePath = savePath;//得到文件的保存目录
        			FileOutputStream out = new FileOutputStream(realSavePath + "\\" + saveFilename);//创建文件输出流
        			byte buffer[] = new byte[1024];//创建一个缓冲区
        			int len = 0;//判断输入流中的数据是否已经读完的标识
        			while((len=in.read(buffer))>0) out.write(buffer, 0, len);//循环将输入流读入到缓冲区当中，使用FileOutputStream输出流将缓冲区的数据写入到指定的目录(savePath + "\\" + filename)当中
        			in.close();//关闭输入流 
        			out.close();//关闭输出流
                	item.delete();//删除处理文件上传时生成的临时文件
                	script = "<script>alert('文件上传成功！');location.href='../mainTeacher.jsp'</script>";
                	response.getWriter().println(script);
                	return ;
        		}
        	}
        }catch (FileUploadBase.FileSizeLimitExceededException e) {
            e.printStackTrace();
            script = "<script>alert('单个文件超出最大值！');location.href='../mainTeacher.jsp'</script>";
            response.getWriter().println(script);
            return ;
        }catch (FileUploadBase.SizeLimitExceededException e) {
        	e.printStackTrace();
            script = "<script>alert('所有文件超出最大值！');location.href='../mainTeacher.jsp'</script>";
            response.getWriter().println(script);
            return;
        }catch (Exception e) {
        	e.printStackTrace();
            script = "<script>alert('上传失败！');location.href='../mainTeacher.jsp'</script>";
            response.getWriter().println(script);
            return ;
        }
        return ;
	}
	
	public void doDncware(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=GBK");
		
		/*
		Enumeration em = request.getParameterNames();
		 while (em.hasMoreElements()) {
		    String name = (String) em.nextElement();
		    String value = request.getParameter(name);
		    System.out.println(name+" "+value);
		}
		System.out.println("QAQ");
		*/
		
		System.out.println("1");
		doUpcware(request, response, "course001");
		System.out.println("2");
		doModify_self(request, response);
		System.out.println("3");
        return ;
	}

	public void init() throws ServletException {
	}
}
