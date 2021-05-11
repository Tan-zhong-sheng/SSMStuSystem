<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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

<title>课程查询页面</title>
<style type="text/css">
	.top{
		font-size: 30;
		text-align: center;
	}

</style>

</head>

<body>
	<div class="top">添加成功 <br>
	</div>
</body>
</html>
