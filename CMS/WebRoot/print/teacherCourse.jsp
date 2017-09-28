<%@ page language="java" import="java.util.*, java.text.*, forXml.*"
	contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>

<jsp:useBean id="course" class="forDao.CourseDao" scope="page"></jsp:useBean>
<jsp:useBean id="courseware" class="forDao.CoursewareDao" scope="page"></jsp:useBean>

<%
	String tea_id=(String)request.getSession().getAttribute("id");
	List<Course> cl=course.getbyTea_id(tea_id);
%>
<table width="600px">
	<tr>
		<td width="20%">课程编号</td>
		<td width="20%">课程名字</td>
		<td>课件</td>
	</tr>
	<%
		for(int i=0; i<cl.size(); i++) {
			Course tmp=cl.get(i);
			String cno=tmp.getCourse_no();
			String title=course.getTitlebyNo(cno);
			String filetitle=courseware.getbyCourse_no(cno).getFile_title();
	%>
	<tr>
		<td><%=cno %></td>
		<td><%=title %></td>
		<td>
			<button type="button" class="btn btn-primary" data-toggle="modal"
				data-target="#myModal<%=i%>">查看课件</button>
			<div class="modal fade" id="myModal<%=i%>" tabindex="-1"
				role="dialog" aria-labelledby="myModalLabel">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button data-dismiss="modal" class="close" type="button">
								<span aria-hidden="true">×</span> <span class="sr-only">Close</span>
							</button>
							<h4 class="modal-title">查看课件</h4>
						</div>
						<div class="modal-body">
							<table>
								<tr>
									<td width="200px">课程编号：</td>
									<td width="450px"><%=cno %></td>
								</tr>
								<tr>
									<td>原课件：</td>
									<%
									if(filetitle==null) {%>
										<td>尚未上传课件</td>
									<%
									}else{%>
										<td><input type="submit" class="btn btn-primary"
										name="dnc<%=i%>" value="<%=filetitle%>"></td>
									<%
									}%>
								</tr>
								<tr>
									<td>新课件：</td>
									<td><input type="file" name="cware<%=i%>"></td>
								</tr>
							</table>
						</div>
						<div class="modal-footer">
							<input type="submit" class="btn btn-primary" name="upc<%=i%>"
								value="上传课件">
							<button data-dismiss="modal" class="btn btn-default"
								type="button">关闭</button>
						</div>
					</div>
					<!-- /.modal-content -->
				</div>
				<!-- /.modal-dialog -->
			</div>
		</td>
	</tr>
	<%}%>
</table>