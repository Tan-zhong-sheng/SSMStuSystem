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
<link rel="stylesheet" type="text/css" href="Css/table.css">
</head>

<body>
	<%
		List<course> list = (List<course>) session.getAttribute("Cmain");
	%>


	<table align="center" border="solid">
		<tr>
			<td colspan="7">
				<h3 align="center">课程列表</h3>
			</td>
		</tr>
		<tr>
			<th>课程号</th>
			<th>课程名</th>
			<th>专业</th>
			<th>查看选课学生</th>
			<th>分配课程</th>
			<th>操作</th>
		</tr>
		<%
			Iterator<course> it = list.iterator();
			while (it.hasNext()) {
				course cou = it.next();
				Integer Cid=cou.getCid();
		%>
		<tr>
			<td><%=cou.getCid()%></td>
			<td><%=cou.getCname()%></td>
			<td><%=cou.getMajor()%></td>
			<td><a href="Studentinfo?cid=<%=cou.getCid()%>"> <%=cou.getCname() %> </a></td>
			<td><a href="coursedistribution?major=<%=cou.getMajor()%>&&cid=<%=cou.getCid()%>">分配课程</a></td>
			<%-- <td><a href="course/canceldistribution.jsp?cid=<%=cou.getCid()%>">取消分配</a></td> --%>
			<td><a href="deleteCourse?cid=<%=cou.getCid()%>">删除课程</a></td>
		</tr>
		<%
			}
		%>
	</table>
	<div align="center">
		<label>第${pageindex}/${total}页 共${count}条</label> <a
			href="Cselectall?pageindex=1">首页</a> <a
			href="Cselectall?pageindex=${pageindex-1}" onclick="return check()">上一页</a>
		<a href="Cselectall?pageindex=${pageindex+1}"
			onclick="return checknext()">下一页</a> <a
			href="Cselectall?pageindex=${total}">尾页</a>
		<form action="Cselectall" method="get">
			<input type="text" name="pageindex" style="width:40 "> <input
				type="submit" value="跳转">
		</form>
	</div>
</body>
</html>

