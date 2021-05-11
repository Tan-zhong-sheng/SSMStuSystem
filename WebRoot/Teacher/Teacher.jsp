<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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

<title>添加教师</title>
<link rel="stylesheet" type="text/css" href="Css/input.css">
<script type="text/javascript" src="Js/jQuery1.7.js"></script>
<link rel="stylesheet" type="text/css" href="Css/selectordie.css">
<script type="text/javascript" src="Js/selectordie.min.js"></script>
<script type="text/javascript">
$(function(){
	$('select').selectOrDie();
});
</script>
<script type="text/javascript">
	function check() {
		var pwd = document.getElementById("pwd");
		if (pwd == null) {
			alert("密码不能为空");
			return false;
		}
	}
</script>

</head>

<body>
	<div align="center">
		<form action="Tinsert" method="post" >
			<table>
				<tr>
					<td>教师姓名:</td>
					<td><input type="text" name="tname"></td>
				</tr>
				<tr>
					<td>教师密码:</td>
					<td><input type="password" name="tpwd" id="pwd"
						onblur="return check()"></td>
				</tr>
				<tr>
					<td>教师工资:</td>
					<td><input type="text" name="sal"></td>
				</tr>
				<tr>
					<td>教师电话:</td>
					<td><input type="text" name="phone"></td>
				</tr>
				<tr>
					<td>教师QQ:</td>
					<td><input type="text" name="QQ"></td>
				</tr>
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
					<td colspan="2" align="center"><input type="submit" value="添加"
						style="width: 200"></td>
				</tr>

			</table>
		</form>
	</div>
</body>
</html>
