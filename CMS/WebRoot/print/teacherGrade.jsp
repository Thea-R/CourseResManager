<%@ page language="java" import="java.util.*, java.text.*, forXml.*, forDao.*"
	contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>

<jsp:useBean id="course" class="forDao.CourseDao" scope="page"></jsp:useBean>
<jsp:useBean id="courseware" class="forDao.CoursewareDao" scope="page"></jsp:useBean>
<jsp:useBean id="student" class="forDao.StudentDao" scope="page"></jsp:useBean>
<jsp:useBean id="stu_course" class="forDao.Stu_courseDao" scope="page"></jsp:useBean>

<%
	String tea_id=(String)request.getSession().getAttribute("id");
	List<Course> cl=course.getbyTea_id(tea_id);

	System.out.println("grade "+cl.size());
	for(int i=0; i<cl.size(); i++) {
		Course cs=cl.get(i);
		String cno=cs.getCourse_no();
		List<Stu_course> scl=stu_course.getbyCourse_no(cno);
		%>
		<div class="tab-pane fade" id="grade<%=i%>">
			<table width="600px">
				<tr><td width="25%">学号</td><td width="25%">姓名</td><td width="25%">成绩</td></tr>
	<%
			for(int j=0; j<scl.size (); j++) {
				Stu_course scs=scl.get(j);
				String grade=(scs.getGrade()==null ? null : scs.getGrade().toString());
				
				pkeyStu_course pkey=scs.getPkey();
				String stu_id=pkey.getStu_id();
				Student sdt=student.getbyId(stu_id);
				String nm=sdt.getName();
	%>
				<tr>
					<td><%=stu_id %></td>
					<td><%=nm %></td>
					<td><input type="text" name="gd<%=i*scl.size()+j %>" value="<%=grade==null ? "" : grade %>"></td>
					<td><input type="submit" name="sgd<%=i*scl.size()+j %>" value="提交成绩"></td>
				</tr>
	<%	
			}
	%>
			</table>
		</div>
	<%
	}
%>
