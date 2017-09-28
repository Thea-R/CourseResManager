<%@ page language="java" import="java.util.*, java.text.*, forXml.*"
	contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>

<jsp:useBean id="stu_course" class="forDao.Stu_courseDao" scope="page"></jsp:useBean>
<jsp:useBean id="stu_homework" class="forDao.Stu_homeworkDao"
	scope="page"></jsp:useBean>
<jsp:useBean id="courseware" class="forDao.CoursewareDao" scope="page"></jsp:useBean>

<%
	String stu_id=(String)request.getSession().getAttribute("id");
	List<Stu_course> csl=stu_course.getbyStu_id(stu_id);
	List<Stu_homework> hkl=stu_homework.getbyStu_id(stu_id);
%>

<table width="850px" class="table table-hover">
	<thead>
		<th width="15%">课程编号</th>
		<th width="15%">课程名字</th>
		<th width="15%">任课教师</th>
		<th width="15%">课程成绩</th>
		<th></th>
		<th></th>
	</thead>
	<tbody>
	<%
		for(int i=0; i<csl.size(); i++) {
			Stu_course tmp=csl.get(i);
			pkeyStu_course pkey=tmp.getPkey();
			String cno=pkey.getCourse_no();
			String title=stu_course.getTitlebyNo(cno);
			String tname=stu_course.getTnamebyNo(cno);
			String grade=stu_course.getGrade(pkey);
			String eva=tmp.getTea_evaluation();
	%>
	<tr>
		<td><%=cno%></td>
		<td><%=title%></div>
		<td><%=tname%></td>
		<td><%=grade==null ? "尚无成绩" : grade%></td>
		<td>
			<button type="button" class="btn btn-primary" data-toggle="modal"
				data-target="#myEva<%=i%>">评教</button>
			<div class="modal fade" id="myEva<%=i%>" tabindex="-1" role="dialog"
				aria-labelledby="myEvaLabel">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button data-dismiss="modal" class="close" type="button">
								<span aria-hidden="true">×</span> <span class="sr-only">Close</span>
							</button>
							<h4 class="modal-title">评教</h4>
						</div>
						<div class="modal-body">
							<table>
								<tr>
									<td>课程：</td>
									<td><%=title%></td>
								</tr>
								<tr>
									<td>教师：</td>
									<td><%=tname%></td>
								</tr>
								<tr>
									<td valign="top">评教内容：</td>
									<td><%=eva==null ? "尚未评教" : eva%></td>
								</tr>
								<tr>
									<td valign="top">更新评教：</td>
									<td><textarea class="form-control" name="eva<%=i%>"></textarea></td>
								</tr>
							</table>
						</div>
						<div class="modal-footer">
							<input type="submit" class="btn btn-primary" name="evalua<%=i%>"
								value="提交评教">
							<button data-dismiss="modal" class="btn btn-default"
								type="button">关闭</button>
						</div>
					</div>
					<!-- /.modal-content -->
				</div>
				<!-- /.modal-dialog -->
			</div>
		</td>

		<%
			Courseware cw=courseware.getbyCourse_no(cno);
			String filetitle=cw.getFile_title();
			if(filetitle==null) {
		%>
		<td><input type="button" value="尚无课件" class="btn disabled"></td>
		<%
			}else{
		%>
		<td><input type="submit" class="btn btn-primary" name="dnc<%=i%>"
			value="下载课件"></td>
		<%
			}
		%>
		</td>

	</tr>
	<%
		}
	%>
	</tbody>
</table>