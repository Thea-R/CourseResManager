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
  	<jsp:include page="filterStudent.jsp" />
  	
	<jsp:useBean id="stu_course" class="forDao.Stu_courseDao" scope="page"></jsp:useBean>
	<jsp:useBean id="stu_homework" class="forDao.Stu_homeworkDao" scope="page"></jsp:useBean>

	<%
		String stu_id=(String)request.getSession().getAttribute("id");
		List<Stu_course> csl=stu_course.getbyStu_id(stu_id);
		List<Stu_homework> hkl=stu_homework.getbyStu_id(stu_id);
	%>
	
	<form name="logout" action="/CMS/servlet/logout" method="post">
		<input type="submit" name="lgo" value="登出">
	</form>
	
	<br>
	
	<ul id="myTab" class="nav nav-tabs">
		<li class="active"><a href="#course" data-toggle="tab">已选课程</a></li>
   		<li><a href="#homework" data-toggle="tab">作业情况</a></li>
   		<li><a href="#inform" data-toggle="tab">教学通告</a></li>
   		<li><a href="#modify_self" data-toggle="tab">修改密码</a></li>
	</ul>
	
	<form name="stu_manage" action="/CMS/servlet/stuManage" method="post">
	<div id="myTabContent" class="tab-content">
   		<div class="tab-pane fade in active" id="course">
			<table width="600px">
			<tr><td width="15%">课程编号</td><td width="15%">课程名字</td><td width="15%">任课教师</td><td width="15%">课程成绩</td><td>评教情况</td></tr>
				<%
				for(int i=0; i<csl.size(); i++) {
					Stu_course tmp=csl.get(i);
					pkeyStu_course pkey=tmp.getPkey();
					String cno=pkey.getCourse_no();
					String title=stu_course.getTitlebyNo(cno);
					String tname=stu_course.getTnamebyNo(cno);
					String grade=stu_course.getGrade(pkey);
				%>
			<tr>
				<td><%=cno %></td>
				<td><%=title %></div>
				<td><%=tname %></td>
				<td><%=grade==null ? "" : grade %></td>
				<td>
					<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal<%=i%>">查看评教</button>
					<div class="modal fade" id="myModal<%=i%>" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
						<div class="modal-dialog"><div class="modal-content">
							<div class="modal-header">
           						<button data-dismiss="modal" class="close" type="button">
           							<span aria-hidden="true">×</span>
           							<span class="sr-only">Close</span>
           						</button>
            					<h4 class="modal-title">查看评教</h4>
          					</div>
          					<div class="modal-body">
          						<table>
          							<tr><td>课程：</td><td width="450px"><%=title %></td></tr>
          							<tr><td>教师：</td><td><%=tname %></td></tr>
            						<tr><td valign="top">评教内容：</td><td><%=tmp.getTea_evaluation()==null ? "尚未评教" : tmp.getTea_evaluation() %></td></tr>
            						<tr><td valign="top">更新评教：</td>
            							<td><textarea class="form-control" name="eva<%=i%>"></textarea></td></tr>
            					</table>
          					</div>
          					<div class="modal-footer">
          						<input type="submit" class="btn btn-primary" name="evalua<%=i%>" value="提交评教">
            					<button data-dismiss="modal" class="btn btn-default" type="button">关闭</button>
          					</div>
        				</div><!-- /.modal-content --></div><!-- /.modal-dialog -->
        			</div>
				</td>
				<td><input type="button" value="下载课件"></td>
			</tr>
			<%}%>
			</table>
   		</div>
   		
   		<div class="tab-pane fade" id="homework">
			<table width="600px">
			<tr><td width="15%">课程编号</td><td width="15%">课程名字</td><td width="15%">作业编号</td><td width="15%">批改意见</td></tr>
				<%for(int i=0; i<hkl.size(); i++) {
					Stu_homework tmp=hkl.get(i);
					pkeyStu_homework pkey=tmp.getPkey();
				%>
			<tr>
				<td><div><%=pkey.getCourse_no() %></div></td>
				<td><div><%=stu_course.getTitlebyNo(pkey.getCourse_no()) %></div>
				<td><div><%=pkey.getHomework_no() %></div></td>
				<td><div><%=tmp.getOpinion()==null ? "" : tmp.getOpinion()%></div></td>
				<td><input type="submit" name="submit" value="提交作业"></td>
				<td><input type="submit" value="下载作业"></td>
			</tr>
			<%}%>
			</table>
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
