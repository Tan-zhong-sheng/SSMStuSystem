<%@page import="Mytool.Mytool"%>
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
<link rel="stylesheet" type="text/css" href="Css/table.css">
</head>

<body>
	<%
		List<scope> list = (List<scope>) request.getAttribute("list");
	%>


	<table align="center" border="solid">
		<tr>
			<td colspan="8">
				<h3 align="center">成绩信息</h3>
			</td>
		</tr>
		<tr>
			<th>学号</th>
			<th>课程名</th>
			<th>负责老师</th>
			<th>学期</th>
			<th>成绩</th>
			<th>状态</th>
			<th>删除</th>
			<th>重置密码</th>
		</tr>
		<%
			Iterator<scope> it = list.iterator();
			while (it.hasNext()) {
				scope sc = it.next();
		%>
		<tr>
			<td><%=sc.getStudent().getStuid()%></td>
			<td><%=sc.getCourse().getCname()%></td>
			<td><%=sc.getTeacherid()%></td>
			<td><%=sc.getCourse().getGrade()%></td>
			<td><%=sc.getScope()%></td>
			<td>
				<%
					if (sc.getStatus() == 0) {
							out.print("舞弊");
						} else if (sc.getStatus() == 0) {
							out.print("缺考");
						} else {
							out.print("正常考试");
						}
				%>
			</td>
			<td><a
				href="deletescope?stuid=<%=sc.getStudent().getStuid()%>&&cid=<%=sc.getCourse().getCid()%>"
				onclick="return sure()">删除信息</a></td>
			<td><a
				href="Scope/UpdateSc.jsp?stuid=<%=sc.getStudent().getStuid()%>&&cid=<%=sc.getCourse().getCid()%>">修改成绩</a>
			</td>
		</tr>
		<%
			}
		%>
	</table>
	<div align="center">
		<label>第${pageindex}/${total}页 共${count}条</label> <a
			href="choiseselect?pageindex=1&&stuid=${stuid}&&cid=${cid}&&teacherid=${teacherid}&&grade=${grade}&&scope=${scope}&&scope1=${scope1}">首页</a>
		<a
			href="choiseselect?pageindex=${pageindex-1}&&stuid=${stuid}&&cid=${cid}&&teacherid=${teacherid}&&grade=${grade}&&scope=${scope}&&scope1=${scope1}"
			onclick="return check()">上一页</a> <a
			href="choiseselect?pageindex=${pageindex+1}&&stuid=${stuid}&&cid=${cid}&&teacherid=${teacherid}&&grade=${grade}&&scope=${scope}&&scope1=${scope1}"
			onclick="return checknext()">下一页</a> <a
			href="choiseselect?pageindex=${total}&&stuid=${stuid}&&cid=${cid}&&teacherid=${teacherid}&&grade=${grade}&&scope=${scope}&&scope1=${scope1}">尾页</a><br>
		<form action="choiseselect" method="get">
			<input type="text" name="pageindex" style="width:40 "> <input
				type="hidden" name="stuid" value=${stuid }> <input
				type="hidden" name="cid" value=${cid }> <input type="hidden"
				name="teacherid" value=${teacherid }> <input type="hidden"
				name="grade" value=${grade }> <input type="hidden"
				name="scope" value=${scope }> <input type="hidden"
				name="scope1" value=${scope1 }> <input type="submit"
				value="跳转">
		</form>
	</div>
</body>
</html>

