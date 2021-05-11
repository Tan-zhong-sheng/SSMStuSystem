<%@page import="pojo.teacher"%>
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
<script type="text/javascript" src="Js/jquery-3.2.1.min.js"></script>
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
		try{
			List<teacher> list = (List<teacher>) session.getAttribute("Tmain");
			if(list==null||list.equals("")){
				%>
				<div align="center">页面没有数据</div>
	<%
		}
	%>


	<table background="" align="center" border="solid">
		<tr>
			<td colspan="13">
				<h3 align="center">教师信息</h3>
			</td>
		</tr>
		<tr>
			<th>教师号</th>
			<th>教师名</th>
			<th>教师工资</th>
			<th>教师专业</th>
			<th>教师电话</th>
			<th>教师QQ</th>
			<th>入职时间</th>
			<th>删除</th>
			<th>重置密码</th>
			<th>修改工资</th>
			<th>修改电话</th>
			<th>修改QQ</th>
			<th>上传人脸</th>
		</tr>
		<%
			Iterator<teacher> it = list.iterator();
			while (it.hasNext()) {
				teacher ter = it.next();
				
		%>
		<tr>
			<td><%=ter.getTeacherid()%></td>
			<td><%=ter.getTname()%></td>
			<td><%=ter.getSal()%></td>
			<td><%=ter.getMajor()%></td>
			<td><%=ter.getPhone()%></td>
			<td>
			<%
				Long QQ=ter.getQQ();
				if(QQ==null){
					out.print("QQ为空");
				}else{
					out.print(QQ);
				}
			%>
			</td>
			<td><%=ter.getEntrytime().toLocaleString()%></td>
			<td><a href="deleteteacher?teacher=<%=ter.getTeacherid()%>"
				onclick="return sure()">删除信息</a></td>
			<td><a href="TerResetpwd?teacherid=<%=ter.getTeacherid()%>" onclick="return Sure()">重置密码</a></td>
			<td><a href="Teacher/updatesal.jsp?teacherid=<%=ter.getTeacherid()%>">修改工资</a></td>
			<td><a href="Teacher/updatephone.jsp?teacherid=<%=ter.getTeacherid()%>">修改电话</a></td>
			<td><a href="Teacher/updateqq.jsp?teacherid=<%=ter.getTeacherid()%>">修改QQ</a></td>
			<td><a href="Teacher/uploadface.jsp?teacherid=<%=ter.getTeacherid()%>">上传人脸</a> </td>
		</tr>
		<%
			} 
		%>
	</table>
	<div align="center">
		<label>第${pageindex}/${total}页 共${count}条</label> <a
			href="Tselectall?pageindex=1">首页</a> <a
			href="Tselectall?pageindex=${pageindex-1}" onclick="return check()">上一页</a>
		<a href="Tselectall?pageindex=${pageindex+1}"
			onclick="return checknext()">下一页</a> <a
			href="Tselectall?pageindex=${total}">尾页</a>
		<form action="Tselectall" method="get">
			<input type="text" name="pageindex" style="width:40 "> <input
				type="submit" value="跳转">
		</form>
	</div>
	<%}catch(Exception e){
		out.print("False");
	}
	 %>
</body>
</html>

