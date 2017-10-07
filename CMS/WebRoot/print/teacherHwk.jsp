<%@ page language="java" import="java.util.*, java.text.*, forXml.*, forDao.*"
	contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>

<jsp:useBean id="course" class="forDao.CourseDao" scope="page"></jsp:useBean>
<jsp:useBean id="courseware" class="forDao.CoursewareDao" scope="page"></jsp:useBean>
<jsp:useBean id="student" class="forDao.StudentDao" scope="page"></jsp:useBean>
<jsp:useBean id="stu_course" class="forDao.Stu_courseDao" scope="page"></jsp:useBean>
<jsp:useBean id="tea_homework" class="forDao.Tea_homeworkDao" scope="page"></jsp:useBean>
<jsp:useBean id="stu_homework" class="forDao.Stu_homeworkDao" scope="page"></jsp:useBean>

<%
	String tea_id=(String)request.getSession().getAttribute("id");
	List<Course> cl=course.getbyTea_id(tea_id);

	for(int i=0; i<cl.size(); i++) {
		Course cs=cl.get(i);
		String cno=cs.getCourse_no();
		
		List<Tea_homework> thk=tea_homework.getbyCourse_no(cno);
%>
		<div class="tab-pane fade" id="hwk<%=i%>">
			<div class="panel-group" id="accordion<%=i%>" role="tablist" >
<%
		for(int k=0; k<thk.size(); k++) {
			pkeyTea_homework pkey=thk.get(k).getPkey();
			String hno=pkey.getHomework_no();
			
			int sum=i*thk.size()+k;
			List<Stu_homework> shk=stu_homework.getbyHomework_no(hno);
%>			<div class="panel panel-default">
    			<div class="panel-heading" role="tab" id="heading<%=sum%>" style="background: #83a4d4">
   				   	<h3 class="panel-title" style="font-size: 20px">
          				<a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion<%=i%>" 
          					href="#collapse<%=sum%>" aria-expanded="false" aria-controls="collapse<%=sum%>">
          					<strong><%=hno %></strong>
    				    </a>
      				</h3>
    			</div>
    			<div id="collapse<%=sum%>" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="heading<%=sum%>" style="background: #83a4d4">
      				<div class="panel-body">
			<table width="850px" class="table table-hover">
				<thead>
					<th width="20%" style="border: 1px solid black">学号</th>
					<th width="20%" style="border: 1px solid black">姓名</th>
					<th width="40%" style="border: 1px solid black">作业</th>
					<th style="border: 1px solid black">批改</th>
				</thead>
				<tbody>
<%
			int cnt=0;
			for(int j=0; j<shk.size (); j++) {
				Stu_homework hk=shk.get(j);
				String filename=(hk.getFilename()==null ? null : hk.getFilename().toString());
				
				String stu_id=hk.getPkey().getStu_id();
				Student sdt=student.getbyId(stu_id);
				String nm=sdt.getName();
				String op=hk.getOpinion();
				int num=i*thk.size()*shk.size()+k*shk.size()+j;
%>
				<tr>
					<td style="border: 1px solid black"><%=stu_id %></td>
					<td style="border: 1px solid black"><%=nm %></td>
					<%
						if(filename==null) {
							++cnt;
					%>
						<td style="border: 1px solid black"><input type="button" class="btn btn-disabled" value="尚未提交"></td>
						<td style="border: 1px solid black"><input type="button" class="btn btn-disabled" value="查看"></td>
					<% 	}
						else {
					%>
						<td style="border: 1px solid black"><input type="submit" name="dnhwk<%=num%>" value="<%=filename%>" class="btn btn-info"></td>
						<td style="border: 1px solid black">
							<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myOpinion<%=num%>">批改</button>
							<div class="modal fade" id="myOpinion<%=num%>" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
								<div class="modal-dialog">
									<div class="modal-content">
										<div class="modal-header">
											<button data-dismiss="modal" class="close" type="button">
												<span aria-hidden="true">×</span> <span class="sr-only">Close</span>
											</button>
											<h4 class="modal-title">批改</h4>
										</div>
										<div class="modal-body" align="center">
											<table>
												<tr>
													<td valign="top">批改意见：</td>
													<td><div style="width:320px; word-wrap: break-word; word-break: normal"><%=op==null ? "尚未批改" : op%></div></td>
												</tr>
												<tr>
													<td valign="top">更新批改：</td>
													<td><textarea class="form-control" name="op<%=num%>"></textarea></td>
												</tr>
											</table>
										</div>
										<div class="modal-footer">
											<script language="javascript">
											function mdOp<%=num%> () {
												if (teaManage.op<%=num%>.value=="") {
													alert("信息填写不完整，请重新输入");
													return false;
												}
												if (teaManage.op<%=num%>.value.length>100) {
													alert("信息超过100个字符，请重新输入");
													return false;
												}
												return true;
											}
											</script>
											<input type="submit" name="upo<%=num%>" value="提交批改" onclick="return mdOp<%=num%>();" class="btn btn-primary">
											<button data-dismiss="modal" class="btn btn-default" type="button">关闭</button>
										</div>
									</div><!-- /.modal-content -->
								</div><!-- /.modal-dialog -->
							</div>
						</td>
					<%
					}
					%>
				</tr>
<%	
			}
%>
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td>尚未提交：<%=cnt%>人</td>
				</tr>
				</tbody>
			</table>
					</div>
		   		</div>
		   	</div>
<%
		}
%>			
      		</div>
      		<input type="submit" name="rel<%=i %>" value="发布作业" class="btn btn-success btn-lg" style="float:right">
		</div>
<%
	}
%>