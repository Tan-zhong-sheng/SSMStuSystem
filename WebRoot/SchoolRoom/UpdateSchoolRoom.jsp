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

<title>更换人员</title>


</head>

<body>
	<%
		String name = request.getParameter("scid");
		Integer Scid = Mytool.turn(name);
		session.setAttribute("Scid", Scid);
	%>
	<form action="UpdateSchoolRoom">
		<table align="center">
			<tr>
				<td>更换人:</td>
				<td><input type = "text" name = "Management"></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type ="submit" value="修改" style="width: 200">
				</td>
			</tr>
		</table>

	</form>
</body>
</html>
