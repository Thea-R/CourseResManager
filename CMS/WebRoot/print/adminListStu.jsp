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

<table width="850px" class="table table-hover" style="font-size:18px; border: 1px solid black">
	<thead>
		<th width="30%" style="border: 1px solid black">学号</th>
		<th width="30%" style="border: 1px solid black">姓名</th>
		<th width="25%" style="border: 1px solid black">密码</th>
		<th style="border: 1px solid black">操作</th>
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
		<td style="border: 1px solid black"><%=sid%></td>
		<td style="border: 1px solid black"><%=nm%></td>
		<td style="border: 1px solid black"><%=pw%></td>
		<td style="border: 1px solid black">
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
									<td><input type="text" name="snm<%=i%>" value=<%=nm%>></td>
								</tr>
								<tr>
									<td>密码：</td>
									<td><input type="password" name="spw<%=i%>" value=<%=pw%>></td>
								</tr>
							</table>
						</div>
						<div class="modal-footer">
							<script language="javascript">
							function sMod<%=i%> () {
								if (admManage.snm<%=i%>.value=="" || admManage.spw<%=i%>.value=="") {
									alert("信息填写不完整，请重新输入");
									return false;
								}
								if (admManage.snm<%=i%>.value.length>20 || admManage.spw<%=i%>.value.length>20) {
									alert("信息超过20个字符，请重新输入");
									return false;
								}
								return true;
							}
							</script>
							<input type="submit" name="smod<%=i%>" value="修改" onclick="return sMod<%=i%>();" class="btn btn-success">
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
		<script language="javascript">
		function addS () {
			if (admManage.sid_add.value=="" || admManage.snm_add.value=="" || admManage.spw_add.value=="") {
				alert("信息填写不完整，请重新输入");
				return false;
			}
			if (admManage.sid._add.value.length>20 || admManage.snm_add.value.length>20 || admManage.spw_add.value.length>20) {
				alert("信息超过20个字符，请重新输入");
				return false;
			}
			return true;
		}
		</script>
		<td style="border: 1px solid black"><input name="sid_add" type="text"></td>
		<td style="border: 1px solid black"><input name="snm_add" type="text"></td>
		<td style="border: 1px solid black"><input name="spw_add" type="password"></td>
		<td style="border: 1px solid black"><input name="sadd" type="submit" value="添加" onclick="return addS();" class="btn btn-default"></td>
	</tr>
	</tbody>
</table>