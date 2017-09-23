<%@ page language="java" import="java.util.*, java.text.*, forXml.*" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<script src="http://how2j.cn/study/js/jquery/2.0.0/jquery.min.js"></script>
<link href="http://how2j.cn/study/css/bootstrap/3.3.6/bootstrap.min.css" rel="stylesheet">
<script src="http://how2j.cn/study/js/bootstrap/3.3.6/bootstrap.min.js"></script>

<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'mainAdmin.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="pragma" content="no-cache"> 
    <meta http-equiv="cache-control" content="no-cache"> 
    <meta http-equiv="expires" content="0">   
    
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  	<jsp:include page="header.jsp" />
  	<jsp:include page="filterTeacher.jsp" />
  	
	<jsp:useBean id="course" class="forDao.CourseDao" scope="page"></jsp:useBean>
	<jsp:useBean id="courseware" class="forDao.CoursewareDao" scope="page"></jsp:useBean>

	<%
		String tea_id=(String)request.getSession().getAttribute("id");
		List<Course> list=course.getbyTea_id(tea_id);
	%>
	
	<form name="logout" action="/CMS/servlet/logout" method="post">
		<input type="submit" name="lgo" value="登出">
	</form>
	
	<br>
	
	<ul id="myTab" class="nav nav-tabs">
		<li class="active"><a href="#course" data-toggle="tab">开课情况</a></li>
		<li><a href="#student" data-toggle="tab">选课情况</a></li>
   		<li><a href="#homework" data-toggle="tab">作业情况</a></li>
   		<li><a href="#inform" data-toggle="tab">教学通告</a></li>
   		<li><a href="#modify_self" data-toggle="tab">修改密码</a></li>
	</ul>
	
	<form name="tea_manage" action="/CMS/servlet/teaManage" enctype="multipart/form-data" method="post">
	<div id="myTabContent" class="tab-content">
   		<div class="tab-pane fade in active" id="course">
			<table width="600px">
			<tr><td width="20%">课程编号</td><td width="20%">课程名字</td><td>课件</td></tr>
			<%for(int i=0; i<list.size(); i++) {
				Course tmp=list.get(i);
				String cno=tmp.getCourse_no();
				String title=course.getTitlebyNo(cno);
				String filetitle=courseware.getbyCourse_no(cno).getFile_title();
			%>
			<tr>
				<td><%=cno %></td>
				<td><%=title %></td>
				<td>
					<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal<%=i%>">查看课件</button>
					<div class="modal fade" id="myModal<%=i%>" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
						<div class="modal-dialog"><div class="modal-content">
							<div class="modal-header">
           						<button data-dismiss="modal" class="close" type="button">
           							<span aria-hidden="true">×</span>
           							<span class="sr-only">Close</span>
           						</button>
            					<h4 class="modal-title">查看课件</h4>
          					</div>
          					<div class="modal-body">
          						<table>
          							<tr><td width="200px">课程编号：</td><td width="450px"><%=cno %></td></tr>
          							<tr>
          								<td>原课件：</td>
          								<%if(filetitle==null) {%>	<td>尚未上传课件</td>
          								<%}else{%>	<td><input type="submit" class="btn btn-primary" name="dnc<%=i%>" value="<%=filetitle%>"></td>
          								<%}%>
          							</tr>
          							<tr>
          								<td>新课件：</td><td><input type="file" name="cware<%=i%>"></td>
          							</tr>
            					</table>
          					</div>
          					<div class="modal-footer">
          						<input type="submit" class="btn btn-primary" name="upc<%=i%>" value="上传课件">
            					<button data-dismiss="modal" class="btn btn-default" type="button">关闭</button>
          					</div>
        				</div><!-- /.modal-content --></div><!-- /.modal-dialog -->
        			</div>
				</td>
			</tr>
			<%}%>
			</table>
   		</div>
   		
   		<div class="tab-pane fade" id="student">
			<p>to be completed..</p>
   		</div>
   		
   		<div class="tab-pane fade" id="homework">
			<p>to be completed..</p>
   		</div>
   		
   		<div class="tab-pane fade" id="inform">
   			<p>to be completed..</p>
   		</div>
   		
   		<div class="tab-pane fade" id="modify_self">
			<table>
			<tr>
				<td>旧密码：</td>
				<td><input type="password" name="old" size="20"></td>
			</tr>
			<tr>
				<td>新密码：</td>
				<td><input type="password" name="now" size="20"></td>
			</tr>
			</table>
			<input type="submit" name="modify_self" value="修改密码">
   		</div>
	</div>
	</form>
	
    <jsp:include page="footer.jsp" />
  </body>
</html>
