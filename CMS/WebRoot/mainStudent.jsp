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
<title>My JSP 'mainAdmin.jsp' starting page</title>
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

	<form name="logout" action="/CMS/servlet/logout" method="post">
		<input type="submit" name="lgo" value="登出">
	</form>

	<br>

	<ul id="myTab" class="nav nav-tabs">
		<li class="active"><a href="#course" data-toggle="tab">课程</a></li>
		<li><a href="#homework" data-toggle="tab">作业</a></li>
		<li><a href="#modify_self" data-toggle="tab">修改密码</a></li>
	</ul>

	<form name="stu_manage" action="/CMS/servlet/stuManage"
		enctype="multipart/form-data" method="post">
		<div id="myTabContent" class="tab-content">
			<div class="tab-pane fade in active" id="course">
				<jsp:include page="print/studentCourse.jsp"></jsp:include>
			</div>

			<div class="tab-pane fade" id="homework">
				<jsp:include page="print/studentHomework.jsp"></jsp:include>
			</div>

			<div class="tab-pane fade" id="modify_self">
				<jsp:include page="print/studentModify_self.jsp"></jsp:include>
			</div>
		</div>
	</form>

	<jsp:include page="footer.jsp" />
</body>
</html>
