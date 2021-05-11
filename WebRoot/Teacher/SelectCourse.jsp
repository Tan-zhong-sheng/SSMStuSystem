<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="pojo.*"%>
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

<title>教学计划</title>
<link rel="stylesheet" type="text/css" href="Css/table.css">
</head>

<body>

	<table align="center">
		<tr>
			<td colspan="4" align="center">教学计划</td>
		</tr>
		<tr>
			<th>班级</th>
			<th>课程</th>
			<th>课程人员详情</th>
			<th>评定成绩</th>
		</tr>
		<%
			List<TC> list = (List<TC>) session
					.getAttribute("TeacherCourseInfo");
			Iterator<TC> it = list.iterator();
			while (it.hasNext()) {
				TC c = it.next();
		%>
		<tr>
			<td><a href="selectclassespeople?classid=<%=c.getClassid()%>"><%=c.getClassname()%></a> </td>
			<td><%=c.getCname()%></td>
			<td><a href="SelectClassesnumber?classesid=<%=c.getClassid()%>&&cid=<%=c.getCid()%>">查看学生成绩</a></td>
			<%-- <td><a href="Teacher/addscope.jsp?cid=<%=c.getCid()%>">评定成绩</a></td> --%>
			<td><a href="selectclass?classid=<%=c.getClassid()%>&&cid=<%=c.getCid()%>">评定成绩</a></td>
		</tr>
		<%
			}
		%>
	</table>
</body>
</html>
