<%@ page language="java" import="java.util.*, java.text.*"
	contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%
	session = request.getSession();
	String identity = (String) session.getAttribute("identity");
	if (identity == null) {
%>
<script>
	alert('没有访问权限，请登陆');
	location.href = '/CMS/index.jsp'
</script>
<%
	}
%>