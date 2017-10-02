<%@ page language="java" import="java.util.*, java.text.*, forXml.*"
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
	<jsp:include page="header.jsp" />
	<jsp:include page="filterStudent.jsp" />

	<div class="container" style="width:850px">
		<ul id="myTab" class="nav nav-tabs">
			<li class="active"><a href="#course" data-toggle="tab"><h4>课程</h4></a></li>
			<li><a href="#homework" data-toggle="tab"><h4>作业</h4></a></li>
			<li><a href="#modify_self" data-toggle="tab"><h4>修改密码</h4></a></li>
			<li style="float:right"><a href="<%=path%>/logout.jsp"><h4>登出</h4></a></li>
		</ul>
		<div id="myTabContent" class="tab-content">
			<div class="tab-pane fade in active" id="course">
				<jsp:include page="print/studentCourse.jsp" />
			</div>

			<div class="tab-pane fade" id="homework">
				<jsp:include page="print/studentHomework.jsp" />
			</div>

			<div class="tab-pane fade" id="modify_self">
				<jsp:include page="print/studentModify_self.jsp" />
			</div>
		</div>
	</div>

	<jsp:include page="footer.jsp" />
</body>
</html>
