<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'Cselect.jsp' starting page</title>
    

  </head>
  
  <body>
  <div align="center">
  	<form action="Cselect">
  		请输入课程号:<input type="text" name="cid"><br>
  		<input type="submit" value="查询">
  	</form>
  </div>
  </body>
</html>
