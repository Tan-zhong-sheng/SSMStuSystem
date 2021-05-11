<%@page import="Mytool.Mytool"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>教师修改页面</title>
<script type="text/javascript" src="Js/jquery-3.2.1.min.js"></script>
<link rel="stylesheet" type="text/css" href="Css/input.css">
<script type="text/javascript">
	function check(){
	var QQ = $("#QQ").val();
	if(QQ==null){
		alert("QQ不能为空");
		return false;
		}
	}
</script>
  </head>
  
  <body>
  <%
  	String teacherid = request.getParameter("teacherid");
  	Integer Phone = Mytool.turn(teacherid);
  	session.setAttribute("Teacherid", Phone);
   %>
	  <form action="Teacherupdateqq" method="post">
	  	<table align="center">
	  		<tr>
	  			<td>修改QQ为:</td>
	  			<td><input type="text" name="QQ" id="QQ"></td>
	  		</tr>
	  		<tr>
	  			<td colspan="2" align="center">
	  				<input type="submit" value="修改" style="width: 200" onclick="return check()">
	  			</td>
	  		</tr>
	  	</table>
	  </form>
  </body>
</html>
