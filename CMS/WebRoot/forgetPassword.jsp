<%@ page language="java" import="java.util.*, java.text.*"
	contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>

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
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="http://how2j.cn/study/css/bootstrap/3.3.6/bootstrap.min.css"
	rel="stylesheet">
<script src="http://how2j.cn/study/js/jquery/2.0.0/jquery.min.js"></script>
<script src="http://how2j.cn/study/js/bootstrap/3.3.6/bootstrap.min.js"></script>
</head>

<body>
	<jsp:include page="header.jsp" />
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
			<input type="submit" name="submit" value="重置密码"> <a
				href="index.jsp"><input type="button" value="返回"></a>
		</div>
	</form>

	<jsp:include page="footer.jsp" />
</body>
</html>