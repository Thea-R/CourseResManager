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

<table width="850px" class="table table-hover" style="font-size:18px">
	<thead>
		<th width="20%">课程编号</th>
		<th width="20%">课程名字</th>
		<th width="20%">作业编号</th>
		<th width="20%">批改意见</th>
		<th>提交作业</th>
	</thead>
	<tbody>
	<%
		for(int i=0; i<hkl.size(); i++) {
			Stu_homework tmp=hkl.get(i);
			pkeyStu_homework pkey=tmp.getPkey();
			String cno=pkey.getCourse_no();
			String title=stu_course.getTitlebyNo(pkey.getCourse_no());
			String hno=pkey.getHomework_no();
			String op=tmp.getOpinion();
			String filename=tmp.getFilename();
			String opinion=tmp.getOpinion();
	%>
	<tr>
		<td><%=cno%></td>
		<td><%=title%></td>
		<td><%=hno%></td>
		<%
			if(op==null) {
		%>
		<td><input type="button" value="尚无" class="btn disabled"></td>
		<%
			}
			else {
		%>
		<td>
			<button type="button" class="btn btn-primary" data-toggle="modal"
				data-target="#myOp<%=i%>">查看</button>
			<div class="modal fade" id="myOp<%=i%>" tabindex="-1"
				role="dialog" aria-labelledby="myModalLabel">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button data-dismiss="modal" class="close" type="button">
								<span aria-hidden="true">×</span> <span class="sr-only">Close</span>
							</button>
							<h4 class="modal-title">查看批改</h4>
						</div>
						<div class="modal-body" align="center">
							<table>
								<tr>
									<td>课程名字：</td>
									<td><%=title%></td>
								</tr>
								<tr>
									<td>作业编号：</td>
									<td><%=hno%></td>
								</tr>
								<tr>
									<td>批改意见：</td>
									<td><div style="width:320px; word-wrap: break-word; word-break: normal"><%=op%></div></td>
								</tr>
							</table>
						</div>
						<div class="modal-footer">
							<button data-dismiss="modal" class="btn btn-default"
								type="button">关闭</button>
						</div>
					</div>
					<!-- /.modal-content -->
				</div>
				<!-- /.modal-dialog -->
			</div>
		</td>
		<% } %>
		
		<td>
			<button type="button" class="btn btn-primary" data-toggle="modal"
				data-target="#myHwk<%=i%>">查看</button>
			<div class="modal fade" id="myHwk<%=i%>" tabindex="-1"
				role="dialog" aria-labelledby="myModalLabel">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button data-dismiss="modal" class="close" type="button">
								<span aria-hidden="true">×</span> <span class="sr-only">Close</span>
							</button>
							<h4 class="modal-title">查看作业</h4>
						</div>
						<div class="modal-body" align="center">
							<table>
								<tr>
									<td>课程名字：</td>
									<td><%=title%></td>
								</tr>
								<tr>
									<td>作业编号：</td>
									<td><%=hno%></td>
								</tr>
							<%
							if(filename==null) {
							%>
								<tr>
									<td>上传作业：</td>
									<td>
										<input type="file" id="hwk<%=i%>" name="hwk<%=i%>" class="filestyle" style="display:none">
										<div class="input-append">  
											<input id="title<%=i%>" type="text" placeholder="尚未上传文件">  
											<a onclick="$('input[id=hwk<%=i%>]').click();"><input type="button" value="浏览" class="btn btn-xs"></a>  
										</div>
										<script type="text/javascript">  
											$('input[id=hwk<%=i%>]').change(function() {  
											$('#title<%=i%>').val($(this).val());  
											});  
										</script>  
									</td>
								</tr>
							</table>
						</div>
						<div class="modal-footer">
							<input type="submit" class="btn btn-primary" name="uphwk<%=i%>"
								value="提交">
							<%
							}
							else {
							%>
								<tr>
									<td align="right">原作业：</td>
									<td><input type="submit" class="btn btn-info btn-xs"
										name="dnhwk<%=i%>" value="<%=filename%>"></td></td>
								</tr>
							<% 
								if(opinion==null) {
							%>
								<tr>
									<td>重传作业：</td>
									<td>
										<input type="file" id="hwk<%=i%>" name="hwk<%=i%>" class="filestyle" style="display:none">
										<div class="input-append">  
											<input id="title<%=i%>" type="text" placeholder="尚未上传文件">  
											<a onclick="$('input[id=hwk<%=i%>]').click();"><input type="button" value="浏览" class="btn btn-xs"></a>  
										</div>
										<script type="text/javascript">  
											$('input[id=hwk<%=i%>]').change(function() {  
											$('#title<%=i%>').val($(this).val());  
											});  
										</script>  
									</td>

								</tr>
							</table>
						</div>
						<div class="modal-footer">
							<input type="submit" class="btn btn-primary" name="uphwk<%=i %>"
								value="提交">
							<%
								}
								else {
							%>
								<tr>
									<td></td>
									<td>教师已经批改，不接受重新上传作业。</td>
								</tr>
							</table>
						</div>
						<div class="modal-footer">
							<%
								}
							}
							%>
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
	<%
		}
	%>
	</tbody>
</table>