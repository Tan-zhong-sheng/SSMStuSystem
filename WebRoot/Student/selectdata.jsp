<%@page import="pojo.*"%>
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
<script type="text/javascript" src="Js/jquery-3.2.1.min.js"></script>
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
	function downsubmit(){
		var form  = document.getElementById("down");
		form.submit();
	}
	
</script>
</head>

<body>
	<%
		try {
			List<Studydata> list = (List<Studydata>) request.getAttribute("selectdata");
	%>


	<table align="center" border="solid">
		<tr>
			<td colspan="4">
				<h3 align="center">资料</h3>
			</td>
		</tr>
		<tr>
			<th>ID</th>
			<th>上传教师号</th>
			<th>文件名</th>
			<th>操作</th>
		</tr>
		<%
			Iterator<Studydata> it = list.iterator();
				while (it.hasNext()) {
					Studydata sy = it.next();
		%>
		<tr>
			<td><%=sy.getId()%></td>
			<td><%=sy.getTeacherid()%></td>
			<td><%=sy.getFilename()%></td>
		 	<%-- <td><a href="download?filename=<%=sy.getAddress()%>">下载</a></td> --%>  
		 	 <td>
		 	 <form action="download" method="post" id="down">
		 		<input type="hidden" value=<%=sy.getAddress() %> id="address" name="filename">
		 		<!-- <img alt="下载" src="img/down.jpg" onclick="downsubmit()"> -->
		 		<input type="submit" value="下载">
		 	</form>
		 	</td> 
		</tr>
		<%
			}
		%>
	</table>
	<div align="center">
		<label>第${pageindex}/${total}页 共${count}条</label> <a
			href="selectdata?pageindex=1">首页</a> <a
			href="selectdata?pageindex=${pageindex-1}"
			onclick="return check()">上一页</a> <a
			href="selectdata?pageindex=${pageindex+1}"
			onclick="return checknext()">下一页</a> <a
			href="selectdata?pageindex=${total}">尾页</a><br>
		<form action="selectdata" method="get">
			<input type="text" name="pageindex" style="width:40 "> 
			<input type="submit" value="跳转">
		</form>
		<%
			} catch (Exception e) {
				e.printStackTrace();
			}
		%>
	</div>
</body>
</html>

