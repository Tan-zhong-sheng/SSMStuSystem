<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <link rel="stylesheet" type="text/css" href="Css/input.css">
    
    <title>修改电话</title>

  </head>
  
  <body>
  	<div align="center">
  	<form action="UpdatePhone" method="post">
  	<table>
  		<tr>
  			<td>
  			电话修改为:
  			</td>
  			<td><input type="text" name="phone"></td>
  		</tr>
  		<tr>
  			<td colspan="2" align="center">
  				<input type="submit" style="width: 200" value="修改">
  			</td>
  		</tr>
  	</table>
  </form>
  	</div>
  </body>
</html>
