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
	<jsp:include page="filterTeacher.jsp" />

	<form name="logout" action="/CMS/servlet/logout" method="post">
		<input type="submit" name="lgo" value="登出">
	</form>

	<jsp:useBean id="course" class="forDao.CourseDao" scope="page"></jsp:useBean>
	<jsp:useBean id="courseware" class="forDao.CoursewareDao" scope="page"></jsp:useBean>
	<%
		String tea_id=(String)request.getSession().getAttribute("id");
			List<Course> cl=course.getbyTea_id(tea_id);
	%>

	<br>
	<ul id="myTab" class="nav nav-tabs">
		<li class="active"><a href="#course" data-toggle="tab">课程</a></li>
		<li class="dropdown"><a href="#" class="dropdown-toggle"
			data-toggle="dropdown" id="TabHwk">作业<b class="caret"></b></a>
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
			data-toggle="dropdown" id="TabGrade">成绩<b class="caret"></b></a>
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
		<li><a href="#modify_self" data-toggle="tab">修改密码</a></li>
	</ul>

	<form name="tea_manage" action="/CMS/servlet/teaManage"
		enctype="multipart/form-data" method="post">
		<div id="myTabContent" class="tab-content">
			<div class="tab-pane fade in active" id="course">
				<jsp:include page="/print/teacherCourse.jsp"></jsp:include>
			</div>

			<jsp:include page="/print/teacherHwk.jsp"></jsp:include>
			<jsp:include page="/print/teacherGrade.jsp"></jsp:include>

			<div class="tab-pane fade" id="modify_self">
				<jsp:include page="print/teacherModify_self.jsp"></jsp:include>
			</div>
		</div>
	</form>

	<jsp:include page="footer.jsp" />
</body>
</html>
