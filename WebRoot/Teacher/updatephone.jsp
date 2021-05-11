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
    <link rel="stylesheet" type="text/css" href="Css/input.css">
<script type="text/javascript" src="Js/jquery-3.2.1.min.js"></script>
<script type="text/javascript">
	function check(){
	var phone = $("#phone").val();
	if(phone==null){
		alert("电话不能为空");
		return false;
		}
	}
	</script>
  </head>

  <body>
  <%
  	String phone = request.getParameter("teacherid");
  	Integer Phone = Mytool.turn(phone);
  	session.setAttribute("Teaid", Phone);
   %>
	  <form action="Teacherupdatephone" method="post">
	  	<table align="center">
	  		<tr>
	  			<td>修改电话为:</td>
	  			<td><input type="text" name="phone" id="phone"></td>
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
