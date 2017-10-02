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

<form action="/CMS/servlet/stuManage"
		enctype="multipart/form-data" method="post">
<table width="850px" class="table table-hover" style="font-size:18px">
	<thead>
		<th width="20%">课程编号</th>
		<th width="20%">课程名字</th>
		<th width="20%">任课教师</th>
		<th width="20%">课程成绩</th>
		<th width="10%">评教</th>
		<th>课件</th>
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
				aria-labelledby="myModalLabel">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button data-dismiss="modal" class="close" type="button">
								<span aria-hidden="true">×</span> <span class="sr-only">Close</span>
							</button>
							<h4 class="modal-title">评教</h4>
						</div>
						<div class="modal-body" align="center">
							<table>
								<tr>
									<td align="right">课程：</td>
									<td><%=title%></td>
								</tr>
								<tr>
									<td align="right">教师：</td>
									<td><%=tname%></td>
								</tr>
								<tr>
									<td valign="top">评教内容：</td>
									<td><div style="width:320px; word-wrap: break-word; word-break: normal"><%=eva==null ? "尚未评教" : eva%></div></td>
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
		<td><input type="button" value="尚无" class="btn disabled"></td>
		<%
			}else{
		%>
		<td><input type="submit" class="btn btn-primary" name="dnc<%=i%>"
			value="下载"></td>
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
</form>