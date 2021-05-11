<%@ page language="java" import="java.util.*" pageEncoding="utf-8" contentType="text/html; charset=utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>添加页面</title>
<link rel="stylesheet" type="text/css" href="Css/input.css">
<script type="text/javascript" src="Js/jquery-3.2.1.min.js"></script>
<link rel="stylesheet" type="text/css" href="Css/selectordie.css">
<script type="text/javascript" src="Js/selectordie.min.js"></script>
<script type="text/javascript">
$(function(){
	$('select').selectOrDie();
});
</script>
<script type="text/javascript">
$(function(){
$("#Classid").blur(function(){
 var classid = $("#Classid").val();
$.ajax({
	type:"get",
	url:"SelectStudentNum",
	data:"classid="+classid,
	success:function(result){
		if(result==false){
			alert("班级人数超过上限制");
			return false;
		}
	},
	error:function(errorThrown){
		alert("系统异常");
		alert(errorThrown);
		return false;
	}
});
});
});
</script>
</head>

<body>
	<%
		List<Integer> list = (List<Integer>) request.getAttribute("list");
	%>
	<form action="register" method="post">
		<table align="center">
			<tr>
				<td>姓名:</td>
				<td><input type="text" name="name"></td>
			</tr>
			<tr>
				<td>密码:</td>
				<td><input type="password" name="pwd"></td>
			</tr>
			<tr>
				<td>出生日期:</td>
				<td><input type="text" name="age" placeholder="格式为xxxx-xx-xx"></td>
			</tr>
			<tr>
				<td>性别:</td>
				<td>男：<input type="radio" name="sex" value="男"> 女：<input
					type="radio" name="sex" value="女">
				</td>
			</tr>
			<tr>
				<td>所属班级号：</td>
				<td><select name="Classid" id="Classid">
						<option>请选择</option>
						<%
							for (int i = 0; i < list.size(); i++) {
						%>
						<option value=<%=list.get(i)%>><%=list.get(i)%></option>
						<%
							}
						%>
				</select></td>
			</tr>
			<tr>
				<td>民族:</td>
				<td><select name="Nation">
						<option>请选择</option>
						<option value="汉族" style="width: 50">汉族</option>
						<option value="其他">其他</option>
				</select></td>
			</tr>
			<tr>
				<td>学历</td>
				<td><select name="education">
						<option>请选择</option>
						<option value="大专">大专</option>
						<option value="高中">高中</option>
				</select></td>
			</tr>
			<tr>
				<td>年级</td>
				<td><select name="grade">
						<option>请选择</option>
						<option value="第一学期">第一学期</option>
				</select></td>
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
				<td colspan="2"><input type="submit" value="提交"
					style="width: 300"></td>
			</tr>
		</table>
	</form>
</body>
</html>
