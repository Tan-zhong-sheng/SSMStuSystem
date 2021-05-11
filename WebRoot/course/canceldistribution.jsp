<%@page import="Mytool.Mytool"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>分配课程</title>


</head>

<body>
	<%
		String name = request.getParameter("cid");
		Integer Cid = Mytool.turn(name);
		session.setAttribute("CiD", Cid);
	 %>
	<form action="distribution" method="post">
		<table align="center">
			<tr>
				<td>负责教师号</td>
				<td>
					<input type="text" name="teacherid">
				</td>
			</tr>
			<tr>
				<td>负责班级号</td>
				<td>
					<input type="text" name="Classid">
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="submit" value="提交" style="width: 200">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>
