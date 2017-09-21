<%@ page language="java" import="java.util.*, java.text.*" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>


-----------------------------------------------<br>
mainAdmin.jsp:<br>

				<tr>
					<td><input type="text" readonly="readonly" name="mid" value=<%=tmp.getStu_id() %>></td>
					<td><input type="text" name="mnm" value=<%=tmp.getName() %>></td>
					<td><input type="text" name="mpw" value=<%=tmp.getPassword() %>></td>
					<td>
						<a href="modifyAdmin.jsp?id=<%=tmp.getStu_id()%>&nm=<%=request.getParameter("mnm")%>&pw=<%=request.getParameter("mpw")%>">
							<input type="button" name="modify_stu" value="修改">
						</a>
					</td>
					<td>
						<a href="modifyAdmin.jsp?id=<%=tmp.getStu_id()%>&nm=<%=request.getParameter("mnm")%>&pw=<%=request.getParameter("mpw")%>">
							<input type="button" name="delete_stu" value="删除">
						</a>
					</td>
				</tr>
				<%}%>
				
				<tr>
					<td><input name="adds_id" type="text"></td><td><input name="adds_nm" type="text"></td>
					<td><input name="adds_pw" type="text"></td><td><input name="add_stu" type="submit" value="添加"></td>
				</tr>

