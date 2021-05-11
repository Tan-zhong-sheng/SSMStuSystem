<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>修改年级</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script type="text/javascript" src="Js/jquery-3.2.1.min.js"></script>
<link rel="stylesheet" type="text/css" href="Css/selectordie.css">
<script type="text/javascript" src="Js/selectordie.min.js"></script>
<script type="text/javascript">
$(function(){
	$('select').selectOrDie();
});
</script>
</head>

<body>
	<form action="UpdateGrade" method="post">
		<table align="center">
			<tr>
				<td>专业</td>
				<td><select name="major">
						<option>请选择</option>
						<option value="计算机">计算机</option>
						<option value="商贸">商贸</option>
						<option value="英语">英语</option>
						<option value="考古">考古</option>
						<option value="通信">通信</option>
				</select></td>
			</tr>
			<tr>
				<td>修改年级为</td>
				<td><select name="grade">
						<option>请选择</option>
						<option value="第一学期">第一学期</option>
						<option value="第二学期">第二学期</option>
						<option value="第三学期">第三学期</option>
						<option value="第四学期">第四学期</option>
				</select></td>
			</tr>
			<tr>
				<td>修改后年级为</td>
				<td><select name="grade1">
						<option>请选择</option>
						<option value="第一学期">第一学期</option>
						<option value="第二学期">第二学期</option>
						<option value="第三学期">第三学期</option>
						<option value="第四学期">第四学期</option>
				</select></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="修改"></td>
			</tr>
		</table>

	</form>
</body>
</html>
