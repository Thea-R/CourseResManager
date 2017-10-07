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
		<th width="30%" style="border: 1px solid black">工号</th>
		<th width="30%" style="border: 1px solid black">姓名</th>
		<th width="25%" style="border: 1px solid black">密码</th>
		<th style="border: 1px solid black">操作</th>
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
		<td style="border: 1px solid black"><%=tid%></td>
		<td style="border: 1px solid black"><%=nm%></td>
		<td style="border: 1px solid black"><%=pw%></td>
		<td style="border: 1px solid black">
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
						<div class="modal-body" align="center">
							<table style="font-size:18px">
								<tr>
									<td>工号：</td>
									<td><%=tid%></td>
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
							<script language="javascript">
							function tMod<%=i%> () {
								if (admManage.tnm<%=i%>.value=="" || admManage.tpw<%=i%>.value=="") {
									alert("信息填写不完整，请重新输入");
									return false;
								}
								if (admManage.tnm<%=i%>.value.length>20 || admManage.tpw<%=i%>.value.length>20) {
									alert("信息超过20个字符，请重新输入");
									return false;
								}
								return true;
							}
							</script>
							<input type="submit" name="tmod<%=i%>" value="修改" onclick="return tMod<%=i%>();" class="btn btn-success">
							<input type="submit" name="tdel<%=i%>" value="删除" class="btn btn-danger">
							<button data-dismiss="modal" class="btn btn-default" type="button" >关闭</button>
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
		function addT () {
			if (admManage.tid_add.value=="" || admManage.tnm_add.value=="" || admManage.tpw_add.value=="") {
				alert("信息填写不完整，请重新输入");
				return false;
			}
			if (admManage.tid._add.value.length>20 || admManage.tnm_add.value.length>20 || admManage.tpw_add.value.length>20) {
				alert("信息超过20个字符，请重新输入");
				return false;
			}
			return true;
		}
		</script>
		<td style="border: 1px solid black"><input name="tid_add" type="text"></td>
		<td style="border: 1px solid black"><input name="tnm_add" type="text"></td>
		<td style="border: 1px solid black"><input name="tpw_add" type="password"></td>
		<td style="border: 1px solid black"><input name="tadd" type="submit" value="添加" onclick="return addT();" class="btn btn-default"></td>
	</tr>
	</tbody>
</table>