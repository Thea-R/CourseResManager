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
			<table width="850px" class="table table-hover" style="border: 1px solid black">
				<thead>
					<th width="20%" style="border: 1px solid black">学号</th>
					<th width="20%" style="border: 1px solid black">姓名</th>
					<th width="20%" style="border: 1px solid black">评教</th>
					<th width="20%" style="border: 1px solid black">成绩</th>
					<th style="border: 1px solid black">操作</th>
				</thead>
				<tbody>
	<%
			for(int j=0; j<scl.size (); j++) {
				Stu_course scs=scl.get(j);
				String grade=(scs.getGrade()==null ? null : scs.getGrade().toString());
				
				pkeyStu_course pkey=scs.getPkey();
				String stu_id=pkey.getStu_id();
				Student sdt=student.getbyId(stu_id);
				String nm=sdt.getName();
				String eva=scs.getTea_evaluation();
				
				int num=i*scl.size()+j;
	%>
				<tr>
					<td style="border: 1px solid black"><%=stu_id %></td>
					<td style="border: 1px solid black"><%=nm %></td>
					<%
						if(eva==null) {
					%>
					<td style="border: 1px solid black"><input type="button" value="尚未评教" class="btn disabled"></td>
					<%
						}
						else {
					%>
					<td style="border: 1px solid black">
						<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myEva<%=i%>">查看评教</button>
						<div class="modal fade" id="myEva<%=i%>" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
							<div class="modal-dialog">
								<div class="modal-content">
									<div class="modal-header">
										<button data-dismiss="modal" class="close" type="button">
											<span aria-hidden="true">×</span> <span class="sr-only">Close</span>
										</button>
										<h4 class="modal-title">查看评教</h4>
									</div>
									<div class="modal-body" align="center">
										<table>
											<tr>
												<td>姓名：</td>
												<td><%=nm%></td>
											</tr>
											<tr>
												<td>评教：</td>
												<td><div style="width:320px; word-wrap: break-word; word-break: normal"><%=eva%></div></td>
											</tr>
										</table>
									</div>
									<div class="modal-footer">
										<button data-dismiss="modal" class="btn btn-default"
										type="button">关闭</button>
									</div>
								</div><!-- /.modal-content -->
							</div><!-- /.modal-dialog -->
						</div>
					</td>
					<%
						}
					%>
					<td style="border: 1px solid black"><input type="text" name="gd<%=num%>" value="<%=grade==null ? "" : grade %>"></td>
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
					<td style="border: 1px solid black"><input type="submit" name="sgd<%=num%>" value="提交成绩" class="btn btn-primary" onclick="return db<%=num%>();"></td>
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