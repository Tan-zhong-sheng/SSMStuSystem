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
</script>
<link rel="stylesheet" type="text/css" href="Css/table.css">
</head>

<body>
	<%
		try{
			List<teacher> list = (List<teacher>) request.getAttribute("TeacherConditional");
			if(list==null||list.equals("")){
				%>
				<div align="center">页面没有数据</div>
	<%
		}
	%>


	<table background="" align="center" border="solid">
		<tr>
			<td colspan="8">
				<h3 align="center">教师信息</h3>
			</td>
		</tr>
		<tr>
			<th>教师号</th>
			<th>教师名</th>
			<th>教师工资</th>
			<th>教师专业</th>
			<th>入职时间</th>
			<th>删除</th>
			<th>重置密码</th>
			<th>修改工资</th>
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
			<td><%=ter.getEntrytime().toLocaleString()%></td>
			<td><a href="deleteteacher?teacher=<%=ter.getTeacherid()%>"
				onclick="return sure()">删除信息</a></td>
			<td><a href="TerResetpwd?teacherid=<%=ter.getTeacherid()%>">重置密码</a></td>
			<td><a href="Teacher/updatesal.jsp?teacherid=<%=ter.getTeacherid()%>">修改工资</a></td>
		</tr>
		<%
			}
		%>
	</table>
	<table align="center" style="width: 400px;">
		<tr>
			<td style="width: 500px;"><label>第${pageindex}/${total}页 共${count}条</label> </td>
			<td>
				<form action="TeacherConditional" method="post">
					<input type="hidden" name="pageindex" value=1> 
					<input type="hidden" name="teacher"value=${teacher }>
					<input type="hidden" name="tname"value=${tname }>
					<input type="hidden" name="sal"value=${sal }>
					<input type="hidden" name="major"value=${major }>
					<input type="submit" value="首页">
				</form>
			</td>
			<td>		
				<form action="TeacherConditional" method="post">
					<input type="hidden" name="pageindex" value=${pageindex-1 }> 
					<input type="hidden" name="teacher"value=${teacher }>
					<input type="hidden" name="tname"value=${tname }>
					<input type="hidden" name="sal"value=${sal }>
					<input type="hidden" name="major"value=${major }>
					<input type="submit" value="上一页" onclick="return check()">
				</form>
		</td>
			<td>
				<form action="TeacherConditional" method="post">
					<input type="hidden" name="pageindex" value=${pageindex+1 }> 
					<input type="hidden" name="teacher"value=${teacher }>
					<input type="hidden" name="tname"value=${tname }>
					<input type="hidden" name="sal"value=${sal }>
					<input type="hidden" name="major"value=${major }>
					<input type="submit" value="下一页" onclick="return checknext()">
				</form>
			</td>
			<td>
				<form action="TeacherConditional" method="post">
					<input type="hidden" name="pageindex" value=${total }> 
					<input type="hidden" name="teacher"value=${teacher }>
					<input type="hidden" name="tname"value=${tname }>
					<input type="hidden" name="sal"value=${sal }>
					<input type="hidden" name="major"value=${major }>
					<input type="submit" value="尾页">
				</form>
			</td>
			<td style="width: 700px;">		
				<form action="TeacherConditional" method="post">
						<input type="text" name="pageindex" style="width:40 "> 
						<input type="hidden" name="teacher"value=${teacher }>
						<input type="hidden" name="tname"value=${tname }>
						<input type="hidden" name="sal"value=${sal }>
						<input type="hidden" name="major"value=${major }>
						<input type="submit" value="跳转">
				</form>
			</td>
		</tr>
	</table>
<%-- 	<div align="center">
		<label>第${pageindex}/${total}页 共${count}条</label> <a
			href="TeacherConditional?pageindex=1&&teacher=${teacher}&&tname=${tname}&&sal=${sal}&&major=${major}">首页</a>
		<form action="TeacherConditional" method="get">
			<input type="hidden" name="pageindex" value=${pageindex-1 }> 
			<input type="hidden" name="teacher"value=${teacher }>
			<input type="hidden" name="tname"value=${tname }>
			<input type="hidden" name="sal"value=${sal }>
			<input type="hidden" name="major"value=${major }>
			<input type="submit" value="上一页">
		</form>
		 <a
			href="TeacherConditional?pageindex=${pageindex-1}&&teacher=${teacher}&&tname=${tname}&&sal=${sal}&&major=${major}" onclick="return check()">上一页</a>
		<a href="TeacherConditional?pageindex=${pageindex+1}&&teacher=${teacher}&&tname=${tname}&&sal=${sal}&&major=${major}"
			onclick="return checknext()">下一页</a> 
		<form action="TeacherConditional" method="get">
			<input type="hidden" name="pageindex" value=${pageindex+1 }> 
			<input type="hidden" name="teacher"value=${teacher }>
			<input type="hidden" name="tname"value=${tname }>
			<input type="hidden" name="sal"value=${sal }>
			<input type="hidden" name="major"value=${major }>
			<input type="submit" value="下一页">
		</form>
			<a href="TeacherConditional?pageindex=${total}&&teacher=${teacher}&&tname=${tname}&&sal=${sal}&&major=${major}">尾页</a>
		<form action="TeacherConditional" method="get">
			<input type="text" name="pageindex" style="width:40 "> 
			<input type="hidden" name="teacher"value=${teacher }>
			<input type="hidden" name="tname"value=${tname }>
			<input type="hidden" name="sal"value=${sal }>
			<input type="hidden" name="major"value=${major }>
			<input type="submit" value="跳转">
		</form>
	</div> --%>
	<%}catch(Exception e){
		out.print("False");
	}
	 %>
</body>
</html>

