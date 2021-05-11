<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>上传页面</title>

  </head>
  
  <body>
    <form action="updatedata" method="post" enctype="multipart/form-data">
    <table align="center">
    	<tr>
    		<td>
    			<input type="file" name="file">
    		</td>
    	</tr>
    	<tr>
    		<td colspan="2" align="center">
    			<input type="submit" value="上传" style="width:200 ">
    		</td>
    	</tr>
	</table>
	</form> 
  </body>
</html>
