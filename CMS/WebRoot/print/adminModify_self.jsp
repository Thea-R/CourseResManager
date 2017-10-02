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

<div align="center" style="padding-top: 10px">
	<br>
	<div class="input-group">
        <input type="password" name="old" class="form-control" placeholder="旧密码" required>
    </div>
    <br>  
	<div class="input-group">
		<input type="password" name="now" class="form-control" placeholder="新密码" required>
	</div>
	<br>  
	<input type="submit" name="modify_self" value="修改密码" class="btn btn-primary">
</div>