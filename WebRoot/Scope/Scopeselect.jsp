<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'Scopeselect.jsp' starting page</title>

  </head>
  
  <body>
  <div align="center">
  	<form action="Scopeselect">
  		学号:<input type="text" name="stuid"><br>
  			<input type="submit" value="查询">
  	</form>
 </div>
  </body>
</html>
