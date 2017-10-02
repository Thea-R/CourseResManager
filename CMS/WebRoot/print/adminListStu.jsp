<%@ page language="java" import="java.util.*, java.text.*, forXml.*"
	contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>

<jsp:useBean id="adm" class="forDao.AdminDao" scope="page"></jsp:useBean>
<jsp:useBean id="stu" class="forDao.StudentDao" scope="page"></jsp:useBean>
<jsp:useBean id="tea" class="forDao.TeacherDao" scope="page"></jsp:useBean>

<%
	List<Admin> adml=adm.getAll();
	List<Student> stul=stu.getAll();
	List<Teacher> teal=tea.getAll();
%>

<form action="/CMS/servlet/admManage" method="post">
<table width="850px" class="table table-hover" style="font-size:18px">
	<thead>
		<th width="30%">学号</th>
		<th width="30%">姓名</th>
		<th width="25%">密码</th>
		<th>操作</th>
	</thead>
	<tbody>
	<%
		for(int i=0; i<stul.size(); i++) {
		Student tmp=stul.get(i);
		String sid=tmp.getStu_id();
		String nm=tmp.getName();
		String pw=tmp.getPassword();
	%>
	<tr>
		<td><%=sid%></td>
		<td><%=nm%></td>
		<td><%=pw%></td>
		<td>
			<button type="button" class="btn btn-primary" data-toggle="modal"
				data-target="#mdstu<%=i%>">修改</button>
			<div class="modal fade" id="mdstu<%=i%>" tabindex="-1" role="dialog"
				aria-labelledby="myModalLabel">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button data-dismiss="modal" class="close" type="button">
								<span aria-hidden="true">×</span> <span class="sr-only">Close</span>
							</button>
							<h4 class="modal-title">修改信息</h4>
						</div>
						<div class="modal-body" align="center">
							<table style="font-size:18px">
								<tr>
									<td>学号：</td>
									<td><%=sid%></td>
								</tr>
								<tr>
									<td>姓名：</td>
									<td><input type="text" name="snm<%=i%>" value=<%=nm%> required></td>
								</tr>
								<tr>
									<td>密码：</td>
									<td><input type="password" name="spw<%=i%>" value=<%=pw%> required></td>
								</tr>
							</table>
						</div>
						<div class="modal-footer">
							<input type="submit" name="smod<%=i%>" value="修改" class="btn btn-success">
							<input type="submit" name="sdel<%=i%>" value="删除" class="btn btn-danger">
							<button data-dismiss="modal" class="btn btn-default" type="button">关闭</button>
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
	<tr>
		<td><input name="sid_add" type="text" required></td>
		<td><input name="snm_add" type="text" required></td>
		<td><input name="spw_add" type="password" required></td>
		<td><input name="sadd" type="submit" value="添加" class="btn btn-default"></td>
	</tr>
	</tbody>
</table>
</form>