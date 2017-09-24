<%@ page language="java" import="java.util.*, java.text.*, forXml.*"
	contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>

<jsp:useBean id="adm" class="forDao.AdminDao" scope="page"></jsp:useBean>
<jsp:useBean id="stu" class="forDao.StudentDao" scope="page"></jsp:useBean>
<jsp:useBean id="tea" class="forDao.TeacherDao" scope="page"></jsp:useBean>

<%
	List<Admin> adml=adm.getAll();
	List<Student> stul=stu.getAll();
	List<Teacher> teal=tea.getAll();
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