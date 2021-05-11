<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="pojo.*"%>
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

<title>学生选课页面</title>
<link rel="stylesheet" type="text/css" href="Css/table.css">
<script type="text/javascript">
	function check() {
		var pageindex = "${pageindex}";
		if (pageindex > 1) {
			return true;
		} else {
			alert("已经是页首");
			return false;
		}
	}
	function checknext() {
		var pageindex = "${pageindex}";
		var total = "${total}";
		if (pageindex >= total) {
			alert("已经是页尾");
			return false;
		} else {
			return true;
		}
	}
</script>

</head>

<body>
	<div align="center">
		<%
			List<student> stuid = (List<student>) session
					.getAttribute("Studentinfo");
			Iterator<student> it = stuid.iterator();
			while (it.hasNext()) {
				student stu = it.next();
		%>
		<table border="solid">
			<tr>
				<th>选课学生学号</th>
				<th>选课学生姓名</th>
			</tr>
			<tr>
				<td><%=stu.getStuid()%></td>
				<td><%=stu.getName()%></td>
			</tr>
			<%
				}
			%>
		</table>
		<div align="center">
			<label>第${pageindex}/${total}页 共${count}条</label>
			 <a href="Studentinfo?pageindex=1&&cid=${Cid}">首页</a>
			 <a href="Studentinfo?pageindex=${pageindex-1}&&cid=${Cid}" onclick="return check()">上一页</a>
			 <a href="Studentinfo?pageindex=${pageindex+1}&&cid=${Cid}" onclick="return checknext()">下一页</a>
			 <a href="Studentinfo?pageindex=${total}&&cid=${Cid}">尾页</a>
			<form action="Studentinfo" method="get">
				<input type="text" name="pageindex" style="width:40 ">
				<input type="hidden" name="cid" value=${Cid }>
				 <input type="submit" value="跳转">
			</form>
		</div>
	</div>
</body>
</html>
