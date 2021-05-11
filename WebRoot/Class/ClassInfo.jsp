<%@page import="pojo.Classes"%>
<%@page import="pojo.course"%>
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
<link rel="stylesheet" type="text/css" href="Css/table.css">
<style type="text/css">
</style>
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
	<%
		List<Classes> list = (List<Classes>) session.getAttribute("ClassInfo");
	%>


	<table align="center" border="solid">
		<tr>
			<td colspan="5">
				<h3 align="center">课程列表</h3>
			</td>
		</tr>
		<tr>
			<th>班级号</th>
			<th>班级名</th>
			<th>班级人数</th>
			<th>操作</th>
		</tr>
		<%
			Iterator<Classes> it = list.iterator();
			while (it.hasNext()) {
				Classes cou = it.next();
		%>
		<tr>
			<td><%=cou.getClassid()%></td>
			<td><%=cou.getClassname()%></td>
			<td><%=cou.getClassnumber()%></td>
			<td><a href="DeleteClasses?classesid=<%=cou.getClassid()%>">删除班级</a></td>
		</tr>
		<%
			}
		%>
	</table>
	<div align="center">
		<label>第${pageindex}/${total}页 共${count}条</label> <a
			href="ClassInfo?pageindex=1">首页</a> <a
			href="ClassInfo?pageindex=${pageindex-1}" onclick="return check()">上一页</a>
		<a href="ClassInfo?pageindex=${pageindex+1}"
			onclick="return checknext()">下一页</a> <a
			href="ClassInfo?pageindex=${total}">尾页</a>
		<form action="ClassInfo" method="get">
			<input type="text" name="pageindex" style="width:40 "> <input
				type="submit" value="跳转">
		</form>
	</div>
</body>
</html>

