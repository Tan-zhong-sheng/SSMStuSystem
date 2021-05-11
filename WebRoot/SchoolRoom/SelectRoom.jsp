<%@page import="pojo.SchoolRoom"%>
<%@page import="pojo.scope"%>
<%@page import="pojo.student"%>
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
	function sure() {
		if (confirm("确定删除?") == true) {
			return true;
		} else {
			return false;
		}
	}
	function Sure() {
		if (confirm("确定修改?") == true) {
			return true;
		} else {
			return false;
		}
	}
</script>
</head>

<body>
	<%
		List<SchoolRoom> list = (List<SchoolRoom>) session.getAttribute("SchoolRoom");
	%>


	<table  align="center" border="solid">
		<tr>
			<td colspan="6">
				<h3 align="center">学生信息</h3>
			</td>
		</tr>
		<tr>
			<th>教室号</th>
			<th>教室地址</th>
			<th>教室负责人</th>
			<th>删除</th>
			<th>重置密码</th>
		</tr>
		<%
			Iterator<SchoolRoom> it = list.iterator();
			while (it.hasNext()) {
				SchoolRoom sc = it.next();
		%>
		<tr>
			<td><%=sc.getScid()%></td>
			<td><%=sc.getRoomAddress()%></td>
			<td><%=sc.getManagement() %></td>
			<td><a href="DeleteSchoolRoom?scid=<%=sc.getScid()%>"
				onclick="return sure()">删除信息</a></td>
			<td><a href="SchoolRoom/UpdateSchoolRoom.jsp?scid=<%=sc.getScid()%>">更换负责人</a>
			</td>
		</tr>
		<%
			}
		%>
	</table>
	<div align="center">
		<label>第${pageindex}/${total}页 共${count}条</label> <a
			href="SelectSchoolRoom?pageindex=1">首页</a> <a
			href="SelectSchoolRoom?pageindex=${pageindex-1}" onclick="return check()">上一页</a>
		<a href="SelectSchoolRoom?pageindex=${pageindex+1}"
			onclick="return checknext()">下一页</a> <a
			href="SelectSchoolRoom?pageindex=${total}">尾页</a><br>
		<form action="SelectSchoolRoom" method="get">
			<input type="text" name="pageindex" style="width:40 "> <input
				type="submit" value="跳转">
		</form>
		<a href="Administrator.jsp">返回管理页面</a>
	</div>
</body>
</html>

