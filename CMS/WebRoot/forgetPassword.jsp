<%@ page language="java" import="java.util.*, java.text.*" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>My JSP 'login.jsp' starting page</title>
    
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
  
 	<form name="reset" action="/CMS/servlet/resetPassword" method="post">
 	<div align="center">
		<table>
			<tr>
				<td>账号：</td>
				<td><input type="text" name="id" size="20"></td>
			</tr>
			<tr>
				<td>姓名：</td>
				<td><input type="text" name="name" size="20"></td>
			</tr>
		</table>
		<input type="submit" name="submit" value="重置密码">
		<a href="index.jsp"><input type="button" value="返回"></a>
	</div>
	</form>
  
  	<script>
		function printTime(){
			var d = new Date();
			var yyyy=d.getFullYear(), mm=d.getMonth()+1, dd=d.getDate(), day=d.getDay();
			var h=d.getHours(), m=d.getMinutes(), s=d.getSeconds();
			
			if(mm<10) mm="0"+mm;
			if(dd<10) dd="0"+dd;
			if(h<10) h="0"+h;
			if(m<10) m="0"+m;
			if(s<10) s="0"+s;
			
			if(day==1) day="Mon.";
			else if(day==2) day="Tues.";
			else if(day==3) day="Wed.";
			else if(day==4) day="Thurs.";
			else if(day==5) day="Fri.";
			else if(day==6) day="Sat.";
			else day="Sun.";
			
			document.getElementById("dt").innerHTML= h+":"+m+":"+s+"<br>"+yyyy+"/"+mm+"/" +dd+" "+day;
		}
  		window.setInterval("printTime();",1000);
	</script>
	<div id="dt" style="color:grey" align="center"></div>
  </body>
</html>