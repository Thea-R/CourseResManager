<%@ page language="java" import="java.util.*, java.text.*, forXml.*, forBean.*" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>

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
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  	<jsp:include page="header.jsp" />
  	
	<jsp:useBean id="adm" class="forDao.AdminDao" scope="session"></jsp:useBean>
	<jsp:useBean id="stu" class="forDao.StudentDao" scope="session"></jsp:useBean>
	<jsp:useBean id="tea" class="forDao.TeacherDao" scope="session"></jsp:useBean>
	<%
		List<Admin> adml=adm.getAll();
		List<Student> stul=stu.getAll();
		List<Teacher> teal=tea.getAll();
	%>
	
	<a href="modifyPassword_adm.jsp"><input type="button" value="修改密码" ></a>
	<a href="index.jsp"><input type="button" value="登出"></a>
	
	<br>
	
	<ul id="myTab" class="nav nav-tabs">
		<li><a href="#listAll" data-toggle="tab">所有账户</a></li>
   		<li class="active"><a href="#listStu" data-toggle="tab">学生列表</a></li>
   		<li><a href="#listTea" data-toggle="tab">教师列表</a></li>
	</ul>
	
	<form name="adm_manage" action="/CMS/servlet/admManage" method="post">
	<div id="myTabContent" class="tab-content">
   		<div class="tab-pane fade in active" id="listAll">
			<table width="300px">
				<tr><td width="35%">学/工号</td><td width="35%">姓名</td><td>身份</td></tr>
				<%for(int i=0; i<adml.size(); i++) {
					Admin tmp=adml.get(i);%>
				<tr><td><%=tmp.getAdm_id() %></td><td>---</td><td>管理员</td></tr>
				<%}
					for(int i=0; i<stul.size(); i++) {
						Student tmp=stul.get(i);%>
				<tr><td><%=tmp.getStu_id() %></td><td><%=tmp.getName() %></td><td>学生</td></tr>
				<%}
					for(int i=0; i<teal.size(); i++) {
						Teacher tmp=teal.get(i);%>
				<tr><td><%=tmp.getTea_id() %></td><td><%=tmp.getName() %></td><td>教师</td></tr>
				<%}%>
			</table>
   		</div>
   		
   		<div class="tab-pane fade" id="listStu">
			<table width="400px">
				<tr><td width="30%">学号</td><td width="30%">姓名</td><td width="25%">密码</td><td>操作</td></tr>
				<%for(int i=0; i<stul.size(); i++) {
						Student tmp=stul.get(i);%>
				<tr>
					<td><%=tmp.getStu_id() %></td>
					<td><%=tmp.getName() %></td>
					<td><%=tmp.getPassword() %></td>
					<td width="7%"><input type="button" value="修改"></td>
					<td><input type="button" value="删除"></td>
				</tr>
				<%}%>
				<tr><td><input type="text"></td><td><input type="text"></td><td><input type="text"></td><td><input type="button" value="增加"></td></tr>
			</table>
   		</div>
   		
   		<div class="tab-pane fade" id="listTea">
   			<table width="400px">
				<tr><td width="30%">工号</td><td width="30%">姓名</td><td width="25%">密码</td><td>操作</td></tr>
				<%for(int i=0; i<teal.size(); i++) {
						Teacher tmp=teal.get(i);%>
				<tr>
					<td><%=tmp.getTea_id() %></td>
					<td><%=tmp.getName() %></td>
					<td><%=tmp.getPassword() %></td>
					<td width="7%"><input type="button" value="修改"></td>
					<td><input type="button" value="删除"></td>
				</tr>
				<%}%>
				
				<tr><td><input type="text"></td><td><input type="text"></td><td><input type="text"></td><td><input type="button" value="增加"></td></tr>
			</table>
   		</div>
	</div>
	</form>
    
    <jsp:include page="footer.jsp" />
  </body>
</html>
