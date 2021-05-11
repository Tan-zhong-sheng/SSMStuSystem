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

<title>添加班级</title>
<link rel="stylesheet" type="text/css" href="Css/input.css">
</head>

<body>
	<form action="Classinsert" method="post">
		<table align="center">
			<tr>
				<td>班级:</td>
				<td><input type="text" name="Classname"></td>
			</tr>
			<tr>
				<td>人数:</td>
				<td><input type="text" name="Classnumber"></td>
			</tr>
			<tr>
				<td colspan="2">
					<input type ="submit" value ="保存" style="width: 200">
				</td>
			</tr>
		</table>

	</form>
</body>
</html>
