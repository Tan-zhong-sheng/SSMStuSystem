<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>查询界面</title>
    

  </head>
  
  <body>
  	教师号:${TeacherResult.teacherid}<br>
  	教师姓名:${TeacherResult.tname }<br>
  	工资：${TeacherResult.sal}<br>
  	教师专业:${TeacherResult.major }<br>
  	<a href="Administrator.jsp">返回管理页面</a>
  </body>
</html>
