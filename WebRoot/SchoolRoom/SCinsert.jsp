<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>添加页面</title>
<link rel="stylesheet" type="text/css" href="Css/input.css">
  </head>
  
  <body>
    	<form action="Scinsert" method="post">
		<table align="center">
			<tr>
				<td>教室地址:</td>
				<td><input type="text" name="RoomAddress"></td>
			</tr>
			<tr>
				<td>管理员:</td>
				<td><input type="text" name="Management"></td>
			</tr>
			<tr>
				<td colspan="2">
					<input type ="submit" value ="保存" style="width: 200">
				</td>
			</tr>
		</table>

	</form>
  </body>
</html>
