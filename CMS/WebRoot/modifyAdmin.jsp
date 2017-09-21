<%@ page language="java" import="java.util.*, java.text.*" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<script src="http://how2j.cn/study/js/jquery/2.0.0/jquery.min.js"></script>
<link href="http://how2j.cn/study/css/bootstrap/3.3.6/bootstrap.min.css" rel="stylesheet">
<script src="http://how2j.cn/study/js/bootstrap/3.3.6/bootstrap.min.js"></script>

<html>
	<head></head>
	<body>
	<%
		System.out.println("into modify.jsp");
		System.out.println(request.getParameter("id"));
		System.out.println(request.getParameter("nm"));
		System.out.println(request.getParameter("pw"));
		System.out.println(request.getParameter("modify_stu"));
		System.out.println(request.getParameter("delete_stu"));
		System.out.println(request.getParameter("modify_tea"));
		System.out.println(request.getParameter("modify_stu"));
	%>
	<script>
		location.href="mainAdmin.jsp";
	</script>
	<form name="m_d" action="/CMS/servlet/admManage" method="post">
		<input type="text" id="modify_id" value="<%=request.getParameter("mid")%>">
		<input type="text" id="modify_nm" value="<%=request.getParameter("mnm")%>">
		<input type="text" id="modify_pw" value="<%=request.getParameter("mpw")%>">
		<input type="text" id="modify_stu" value="<%=request.getParameter("modify_stu")%>">
		<input type="text" id="delete_stu" value="<%=request.getParameter("delete_stu")%>">
		<input type="text" id="modify_tea" value="<%=request.getParameter("modify_tea")%>">
		<input type="text" id="delete_tea" value="<%=request.getParameter("delete_tea")%>">
	</form>
	 <script >
 		//document.m_d.submit();
	</script>
	</body>
</html>