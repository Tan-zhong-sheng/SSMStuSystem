<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>500</title>

  </head>
  
  <body>
  	<div align="center">
  		<h4>页面发现错误O(∩_∩)O请您与管理员联系</h4>
  	</div>
  </body>
</html>
