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

<title>修改成功</title>
    <style type="text/css">
    	.top{
    		text-align: center;
    	}
    </style>

</head>

<body>
	<div class="top">
		修改成功
	</div>
</body>
</html>
