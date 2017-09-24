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

<table width="300px">
	<tr>
		<td width="35%">学/工号</td>
		<td width="35%">姓名</td>
		<td>身份</td>
	</tr>
	<%
		for(int i=0; i<adml.size(); i++) {
			Admin tmp=adml.get(i);
	%>
	<tr>
		<td><%=tmp.getAdm_id()%></td>
		<td>---</td>
		<td>管理员</td>
	</tr>
	<%
		}
		for(int i=0; i<stul.size(); i++) {
			Student tmp=stul.get(i);
	%>
	<tr>
		<td><%=tmp.getStu_id()%></td>
		<td><%=tmp.getName()%></td>
		<td>学生</td>
	</tr>
	<%
		}
		for(int i=0; i<teal.size(); i++) {
			Teacher tmp=teal.get(i);
	%>
	<tr>
		<td><%=tmp.getTea_id()%></td>
		<td><%=tmp.getName()%></td>
		<td>教师</td>
	</tr>
	<%
		}
	%>
</table>