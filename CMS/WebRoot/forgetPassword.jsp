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
<title>课程资源管理系统 - 重置密码</title>
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

<body style="background: #757f9a; /* fallback for old browsers */
  background: -webkit-linear-gradient(to top, #757f9a, #d7dde8); /* Chrome 10-25, Safari 5.1-6 */
  background: linear-gradient(to top, #757f9a, #d7dde8);">
	<jsp:include page="header.jsp" />
	
	<div class="container" style="width: 400px; padding-top: 100px">
	<form name="reset" action="/CMS/servlet/resetPassword" method="post">
		<div align="center">
			<div class="input-group input-group-lg">
            	<span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                <input type="text" name="id" class="form-control" placeholder="账号" autofocus>
            </div>
            <br>  
            	
            <div class="input-group input-group-lg">
                <span class="input-group-addon"><i class="glyphicon glyphicon-cloud"></i></span>
                <input type="text" name="name" class="form-control" placeholder="姓名">
            </div>
            <br>
			
			<script language="javascript">
			function check () {
				if (reset.id.value=="" || reset.name.value=="") {
					alert("信息填写不完整，请重新输入");
					return false;
				}
				return true;
			}
			</script>
			
			<a href="index.jsp"><input type="button" class="btn btn-default" value="返回" style="float: right"></a>
			<input class="btn btn-primary" type="submit" name="submit" value="重置密码" style="float: right" onclick="return check();">
		</div>
	</form>
	</div>

	<jsp:include page="footer.jsp" />
</body>
</html>