<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>修改密码</title>
    

  </head>
  
  <body>
  	<div align="center">
  	<form action="Terchangepwd" method="post">
  		修改密码为:<input type="text" name ="tpwd"><br>
  				  <input type="submit" value="提交">
  	</form>
  	</div>
  </body>
</html>
