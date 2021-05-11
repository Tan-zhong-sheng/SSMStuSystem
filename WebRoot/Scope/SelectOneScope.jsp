<%@page import="pojo.scope"%>
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

<title>查询成绩</title>
<link rel="stylesheet" type="text/css" href="Css/table.css">
</head>

<body>
	<%
		List<scope> sc = (List<scope>) session.getAttribute("onescope");
	%>
	<div class="top" align="center">
		<table>
			<tr>
				<td colspan="3" align="center">
					<h3>成绩查询</h3>
				</td>
			</tr>
			<tr>
				<th>课程名</th>
				<th>成绩</th>
				<th>学期</th>
			</tr>
			<%
				Iterator<scope> it = sc.iterator();
				while (it.hasNext()) {
					scope Sc = it.next();
			%>
			<tr>
				<td><%=Sc.getCourse().getCname()%></td>
				<td><%=Sc.getScope()%></td>
				<td><%=Sc.getGrade() %></td>
			</tr>
		<%
			}
		%>
		</table>

	</div>
</body>
</html>
