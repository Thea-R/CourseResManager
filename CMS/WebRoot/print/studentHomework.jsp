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

<table width="600px">
	<tr>
		<td width="15%">课程编号</td>
		<td width="15%">课程名字</td>
		<td width="15%">作业编号</td>
		<td width="15%">批改意见</td>
	</tr>
	<%
		for(int i=0; i<hkl.size(); i++) {
			Stu_homework tmp=hkl.get(i);
			pkeyStu_homework pkey=tmp.getPkey();
	%>
	<tr>
		<td><div><%=pkey.getCourse_no()%></div></td>
		<td><div><%=stu_course.getTitlebyNo(pkey.getCourse_no())%></div>
		<td><div><%=pkey.getHomework_no()%></div></td>
		<td><div><%=tmp.getOpinion()==null ? "" : tmp.getOpinion()%></div></td>
		<td><input type="submit" name="submit" value="提交作业"></td>
		<td><input type="submit" value="下载作业"></td>
	</tr>
	<%
		}
	%>
</table>