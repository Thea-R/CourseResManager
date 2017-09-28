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

<table width="850px" class="table table-hover">
	<thead>
		<th width="30%">工号</th>
		<th width="30%">姓名</th>
		<th width="25%">密码</th>
		<th>操作</th>
	</thead>
	<tbody>
	<%
		for(int i=0; i<teal.size(); i++) {
		Teacher tmp=teal.get(i);
		String tid=tmp.getTea_id();
		String nm=tmp.getName();
		String pw=tmp.getPassword();
	%>
	<tr>
		<td><%=tid%></td>
		<td><%=nm%></td>
		<td><%=pw%></td>

		<td>
			<button type="button" class="btn btn-primary" data-toggle="modal"
				data-target="#mdtea<%=i%>">修改</button>
			<div class="modal fade" id="mdtea<%=i%>" tabindex="-1" role="dialog"
				aria-labelledby="myModalLabel">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button data-dismiss="modal" class="close" type="button">
								<span aria-hidden="true">×</span> <span class="sr-only">Close</span>
							</button>
							<h4 class="modal-title">修改信息</h4>
						</div>
						<div class="modal-body">
							<table>
								<tr>
									<td>工号：</td>
									<td width="450px"><%=tid%></td>
								</tr>
								<tr>
									<td>姓名：</td>
									<td><input type="text" name="tnm<%=i%>" value=<%=nm%>></td>
								</tr>
								<tr>
									<td>密码：</td>
									<td><input type="password" name="tpw<%=i%>" value=<%=pw%>></td>
								</tr>
							</table>
						</div>
						<div class="modal-footer">
							<input type="submit" name="tmod<%=i%>" value="修改"> <input
								type="submit" name="tdel<%=i%>" value="删除">
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

	<tr>
		<td><input name="tid_add" type="text"></td>
		<td><input name="tnm_add" type="text"></td>
		<td><input name="tpw_add" type="password"></td>
		<td><input name="tadd" type="submit" value="添加" class="btn btn-default"></td>
	</tr>
	</tbody>
</table>