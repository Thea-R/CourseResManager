<%@ page language="java" import="java.util.*, java.text.*"
	contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<head>
<base href="<%=basePath%>">
<title>课程资源管理系统</title>
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
	<jsp:include page="header.jsp"></jsp:include>

	<div class="container" style="width: 400px; padding-top: 100px">
		<form class="form-signin" name="login" action="/CMS/servlet/checkIdentity" method="post">
			<div class="input-group input-group-lg">
            	<span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>  
                <input type="text" name="id" class="form-control" placeholder="用户名" required autofocus>
            </div>
            <br>  
            	
            <div class="input-group input-group-lg">
                <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>  
                <input type="password" name="password" class="form-control" placeholder="密码" required>
            </div>
            <br>  

    		<a href="forgetPassword.jsp"><input type="button" class="btn btn-default" value="忘记密码" style="float: right"></a>
			<input class="btn btn-primary" type="submit" name="submit" value="用户登录" style="float: right">
		</form>
	</div>
	
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>