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

<title>添加课程</title>
<script type="text/javascript" src="Js/jquery-3.2.1.min.js"></script>
<script type="text/javascript">
	function check(){
		var cname = $("#cname").val();
		var major = $("#major").val();
		var grade = $("#grade").val();
		if(cname==null||cname==""){
			alert("课程名不能为空");
			return false;
		}else if(major==null||major==""){
			alert("专业不能为空");
			return false;
		}else if(grade==null||grade==""){
			alert("年级不能为空");
			return false;
		}
	}
</script>


</head>

<body>
	<form action="Cinsert" method="post">
		<div align="center">
			<input type="hidden" name="cid">
			<table>
				<tr>
					<td>课程名：</td>
					<td><input type="text" name="cname" id="cname"><br></td>
				</tr>
				<tr>
					<td>年级</td>
					<td>
							<select name="grade" id="grade">
								<option value="">请选择</option>
								<option value="第一学期">第一学期</option>
								<option value="第二学期">第二学期</option>
								<option value="第三学期">第三学期</option>
								<option value="第四学期">第四学期</option>
							</select>
					</td>
			</tr>
			<tr>
					<td>专业</td>
					<td><select name="major" id="major">
						<option value="">请选择</option>
						<option value="计算机">计算机</option>
						<option value="商贸">商贸</option>
						<option value="英语">英语</option>
						<option value="考古">考古</option>
						<option value="通信">通信</option>
					</select>  </td>
				</tr>
				<tr>
					<td colspan="2" align="center"><input type="submit" value="提交"
						style="width: 200" onclick="return check()"></td>
				</tr>
			</table>
		</div>
	</form>
</body>
</html>
