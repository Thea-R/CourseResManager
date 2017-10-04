<%@ page language="java" import="java.util.*, java.text.*, forXml.*, forDao.*"
	contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>

<jsp:useBean id="course" class="forDao.CourseDao" scope="page"></jsp:useBean>
<jsp:useBean id="courseware" class="forDao.CoursewareDao" scope="page"></jsp:useBean>
<jsp:useBean id="student" class="forDao.StudentDao" scope="page"></jsp:useBean>
<jsp:useBean id="stu_course" class="forDao.Stu_courseDao" scope="page"></jsp:useBean>

<%
	String tea_id=(String)request.getSession().getAttribute("id");
	List<Course> cl=course.getbyTea_id(tea_id);

	for(int i=0; i<cl.size(); i++) {
		Course cs=cl.get(i);
		String cno=cs.getCourse_no();
		List<Stu_course> scl=stu_course.getbyCourse_no(cno);
		%>
		<div class="tab-pane fade" id="grade<%=i%>">
			<table width="850px" class="table table-hover">
				<thead><th width="25%">学号</th><th width="25%">姓名</th><th width="25%">成绩</th><th></th></thead>
				<tbody>
	<%
			for(int j=0; j<scl.size (); j++) {
				Stu_course scs=scl.get(j);
				String grade=(scs.getGrade()==null ? null : scs.getGrade().toString());
				
				pkeyStu_course pkey=scs.getPkey();
				String stu_id=pkey.getStu_id();
				Student sdt=student.getbyId(stu_id);
				String nm=sdt.getName();
				
				int num=i*scl.size()+j;
	%>
				<tr>
					<td><%=stu_id %></td>
					<td><%=nm %></td>
					<td><input type="text" name="gd<%=num%>" value="<%=grade==null ? "" : grade %>"></td>
					<script language="javascript">
					function validateNum(val){//验证整数
						var patten = /^-?\d+$/;
						return patten.test(val);
					}
					function validateDBNum(val){//验证小数，保留一位小数点
						var patten = /^-?\d+\.?\d{0,1}$/;
						return patten.test(val);
					}
					function db<%=num%>(){
						var num=teaManage.gd<%=num%>.value;
						if(num=="") {
							alert("无成绩，请重新评分");
							return false;
						}
						if(num.indexOf("-")!=-1) {
							alert("非法分数，请重新评分");
							return false;
						}
						if(validateNum(num)==true && num.length<=3)	return true;
						if(validateNum(num)==false && validateDBNum(num)==true && num.length<=4)	return true;
						alert("非法分数，请重新评分");
						return false;
					}
					</script>
					<td><input type="submit" name="sgd<%=num%>" value="提交成绩" class="btn btn-primary" onclick="return db<%=num%>();"></td>
				</tr>
	<%
			}
	%>
				</tbody>
			</table>
		</div>
	<%
	}
%>