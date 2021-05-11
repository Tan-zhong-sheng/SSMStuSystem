<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>修改管理者页面</title>
<link rel="stylesheet" type="text/css" href="Css/input.css">

  </head>
  
  <body>
  <div align="center">
  		    	<form action="governolupdate" method="post">
  		    	<table>
  		    		<tr>
  		    			<td>管理号：</td>
  		    			<td><input type="text" name ="id" id="name"></td>
  		    		</tr>
  		    		<tr>
  		    			<td>密码：</td>
  		    			<td><input type="password" name ="pwd"></td>
  		    		</tr>
  		    		<tr>
  		    			<td colspan="2" align="center"> <input type="submit" value="提交" style="width: 150px;"></td>
  		    		</tr>
    			  </table>
    	</form>
   </div>
  </body>
</html>
