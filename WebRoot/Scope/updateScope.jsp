<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>修改成绩</title>

  </head>
  
  <body>
  <div align="center">
  	  <form action="updatepwd" method="post">
  			学号：<input type="text" name="stuid" id="stuid" onblur="return checkid()"><br>
  			课程号:<input type="text" name="cid"><br>
  			成绩:<input type="text" name="scope"><br>
  					<input type="submit" value="提交">
  	  </form>
  	</div>
  </body>
</html>
