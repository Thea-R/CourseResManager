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
	<jsp:include page="filterTeacher.jsp" />

	<jsp:useBean id="course" class="forDao.CourseDao" scope="page"></jsp:useBean>
	<jsp:useBean id="courseware" class="forDao.CoursewareDao" scope="page"></jsp:useBean>
	<%
		String tea_id=(String)request.getSession().getAttribute("id");
			List<Course> cl=course.getbyTea_id(tea_id);
	%>

<div class="container" style="width:850px">
	<ul id="myTab" class="nav nav-tabs">
		<li class="active"><a href="#course" data-toggle="tab"><h4>课程</h4></a></li>
		<li class="dropdown"><a href="#" class="dropdown-toggle"
			data-toggle="dropdown" id="TabHwk"><h4>作业<b class="caret"></b></h4></a>
			<ul class="dropdown-menu" role="menu" aria-labelledby="TabHwk">
				<%
					for(int i=0; i<cl.size(); i++) {
							Course tmp=cl.get(i);
							String cno=tmp.getCourse_no();
							String title=course.getTitlebyNo(cno);
				%>
				<li><a href="#hwk<%=i%>" tabindex="-1" data-toggle="tab"><%=title%></a></li>
				<%
					}
				%>
			</ul></li>
		<li class="dropdown"><a href="#" class="dropdown-toggle"
			data-toggle="dropdown" id="TabGrade"><h4>成绩<b class="caret"></b></h4></a>
			<ul class="dropdown-menu" role="menu" aria-labelledby="TabGrade">
				<%
					for(int i=0; i<cl.size(); i++) {
							Course tmp=cl.get(i);
							String cno=tmp.getCourse_no();
							String title=course.getTitlebyNo(cno);
				%>
				<li><a href="#grade<%=i%>" tabindex="-1" data-toggle="tab"><%=title%></a></li>
				<%
					}
				%>
			</ul></li>
		<li><a href="#modify_self" data-toggle="tab"><h4>修改密码</h4></a></li>
		<li style="float:right"><a href="<%=path%>/logout.jsp"><h4>登出</h4></a></li>
	</ul>

	<form name="tea_manage" action="/CMS/servlet/teaManage"
		enctype="multipart/form-data" method="post">
		<div id="myTabContent" class="tab-content">
			<div class="tab-pane fade in active" id="course">
				<jsp:include page="/print/teacherCourse.jsp" />
			</div>

			<jsp:include page="/print/teacherHwk.jsp" />
			<jsp:include page="/print/teacherGrade.jsp" />

			<div class="tab-pane fade" id="modify_self">
				<jsp:include page="print/teacherModify_self.jsp" />
			</div>
		</div>
	</form>
</div>

	<jsp:include page="footer.jsp" />
</body>
</html>
