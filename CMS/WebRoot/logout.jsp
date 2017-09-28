<%@ page language="java" import="java.util.*, java.text.*"
	contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>

<%
	String path = request.getContextPath();

	session.removeAttribute("id");
	session.removeAttribute("identity");

	String script = "<script>location.href='"+path+"/index.jsp'</script>";
	response.setContentType("text/html;charset=GBK");
	response.getWriter().println(script);
%>