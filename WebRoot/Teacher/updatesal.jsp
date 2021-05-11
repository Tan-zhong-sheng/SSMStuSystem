<%@page import="Mytool.Mytool"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>修改工资</title>
    <link rel="stylesheet" type="text/css" href="Css/input.css">
	<script type="text/javascript">
		function check(){
			var teacherid = document.getElementById("teacherid").value;
			if(teacherid==null){
				alert("教师号不能为空");
				return false;
			}
		}
	</script>
  </head>
  
  <body>
  <div align="center">
  <%
  		String name = request.getParameter("teacherid");
  		Integer teacherid = Mytool.turn(name);
	  	session.setAttribute("teacherid", teacherid);
   %>
  	<form action="updatesal" method="post">
  		工资修改为:<input type="text" name="sal"><br>
  				  <input type="submit" value="提交" onclick="return check()">
  	</form>
  </div>
  </body>
</html>
