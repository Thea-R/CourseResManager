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
  <head>
    <base href="<%=basePath%>">
    <title>My JSP 'login.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="pragma" content="no-cache"> 
    <meta http-equiv="cache-control" content="no-cache"> 
    <meta http-equiv="expires" content="0">   
  </head>
  
  <body>
  	<jsp:include page="header.jsp" />
  
 	<form name="login" action="/CMS/servlet/checkIdentity" method="post">
 	<div align="center">
		<table>
			<tr>
				<td>账号：</td>
				<td><input type="text" name="id" size="20"></td>
			</tr>
			<tr>
				<td>密码：</td>
				<td><input type="password" name="password" size="20" ></td>
			</tr>
			<tr>
				<td><input type="submit" name="submit" value="登录"></td>
				<td><a href="forgetPassword.jsp"><input type="button" value="忘记密码"></a></td>
			</tr>
		</table>
	</div>
	</form>
	
  	<jsp:include page = "footer.jsp"/>
  </body>
</html>