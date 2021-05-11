<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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

<title>My JSP 'result.jsp' starting page</title>

</head>

<body>
<div align="center">
	<%
		scope sc = (scope) session.getAttribute("Scope");
	%>
	课程号为:<%=sc.getCourse().getCid()%><br>
	成绩为:<%=sc.getScope()%><br>
</div>
</body>
</html>
