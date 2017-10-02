<%@ page language="java" import="java.util.*, java.text.*, forXml.*"
	contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>

<jsp:useBean id="course" class="forDao.CourseDao" scope="page"></jsp:useBean>
<jsp:useBean id="courseware" class="forDao.CoursewareDao" scope="page"></jsp:useBean>

<%
	String tea_id=(String)request.getSession().getAttribute("id");
	List<Course> cl=course.getbyTea_id(tea_id);
%>

<table width="850px" class="table table-hover">
	<thead>
		<th width="35%">课程编号</th>
		<th width="35%">课程名字</th>
		<th>课件</th>
	</thead>
	<tbody>
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
							<h4 class="modal-title">查看</h4>
						</div>
						<div class="modal-body" align="center">
							<table>
								<tr>
									<td>课程编号：</td>
									<td><%=cno %></td>
								</tr>
								<tr>
									<td align="right">原课件：</td>
									<%
									if(filetitle==null) {%>
										<td>尚未上传课件</td>
									<%
									}else{%>
										<td><input type="submit" class="btn btn-primary btn-xs"
										name="dnc<%=i%>" value="<%=filetitle%>"></td>
									<%
									}%>
								</tr>
								<tr>
									<td align="right">新课件：</td>
									<td>
										<input type="file" id="cware<%=i%>" name="cware<%=i%>" class="filestyle" style="display:none">
										<div class="input-append">  
											<input id="title<%=i%>" type="text" placeholder="尚未上传文件">  
											<a onclick="$('input[id=cware<%=i%>]').click();"><input type="button" value="浏览" class="btn btn-xs"></a>  
										</div>
										<script type="text/javascript">  
											$('input[id=cware<%=i%>]').change(function() {  
											$('#title<%=i%>').val($(this).val());  
											});  
										</script>  
									</td>
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
	</tbody>
</table>