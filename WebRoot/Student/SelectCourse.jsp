<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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

<title>查看课程</title>
<link rel="stylesheet" type="text/css" href="Css/table.css">
</head>

<body>
	<div align="center">
		<table border="solid">

			<c:forEach var="list" items="${Course}">
				<tr>
					<td>课程名:</td>
					<td>${list}</td>
				</tr>
			</c:forEach>

		</table>
	</div>
</body>
</html>
