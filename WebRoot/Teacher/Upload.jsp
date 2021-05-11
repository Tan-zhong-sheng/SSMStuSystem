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

<title>上传头像</title>

</head>

<body>

	<div>
		<form action="upload" method="post" enctype="multipart/form-data">
			<table align="center">
				<tr>
					<td>上传头像:</td>
					<td><input type="file" name="file"></td>
				</tr>
				<tr>
					<td colspan="2" align="center"><input type="submit" value="上传"
						style="width: 200"></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>
