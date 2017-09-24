<%@ page language="java" import="java.util.*, java.text.*, forXml.*"
	contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>

<jsp:useBean id="stu_course" class="forDao.Stu_courseDao" scope="page"></jsp:useBean>
<jsp:useBean id="stu_homework" class="forDao.Stu_homeworkDao"
	scope="page"></jsp:useBean>

<%
	String stu_id=(String)request.getSession().getAttribute("id");
	List<Stu_course> csl=stu_course.getbyStu_id(stu_id);
	List<Stu_homework> hkl=stu_homework.getbyStu_id(stu_id);
%>

<table>
	<tr>
		<td>旧密码：</td>
		<td><input type="password" name="old" size="20"></td>
	</tr>
	<tr>
		<td>新密码：</td>
		<td><input type="password" name="now" size="20"></td>
	</tr>
</table>
<input type="submit" name="modify_self" value="修改密码">