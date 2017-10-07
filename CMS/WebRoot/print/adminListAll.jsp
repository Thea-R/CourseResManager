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

<table width="850px" class="table table-hover" style="font-size:18px; border: 1px solid black">
	<thead>
		<th width="35%" style="border: 1px solid black">帐号</th>
		<th width="35%" style="border: 1px solid black">姓名</th>
		<th style="border: 1px solid black">身份</th>
	</thead>
	<tbody>
	<%
		for(int i=0; i<adml.size(); i++) {
		Admin tmp=adml.get(i);
	%>
	<tr>
		<td style="border: 1px solid black"><%=tmp.getAdm_id()%></td>
		<td style="border: 1px solid black">---</td>
		<td style="border: 1px solid black">管理员</td>
	</tr>
	<%
		}
			for(int i=0; i<stul.size(); i++) {
		Student tmp=stul.get(i);
	%>
	<tr>
		<td style="border: 1px solid black"><%=tmp.getStu_id()%></td>
		<td style="border: 1px solid black"><%=tmp.getName()%></td>
		<td style="border: 1px solid black">学生</td>
	</tr>
	<%
		}
			for(int i=0; i<teal.size(); i++) {
		Teacher tmp=teal.get(i);
	%>
	<tr>
		<td style="border: 1px solid black"><%=tmp.getTea_id()%></td>
		<td style="border: 1px solid black"><%=tmp.getName()%></td>
		<td style="border: 1px solid black">教师</td>
	</tr>
	<%
		}
	%>
	</tbody>
</table>