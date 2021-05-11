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
		try {
			List<student> list = (List<student>) session.getAttribute("studentmain");
	%>


	<table align="center">
		<tr>
			<td colspan="13">
				<h3 align="center">学生信息</h3>
			</td>
		</tr>
		<tr>
			<th>学号</th>
			<th>姓名</th>
			<th>电话</th>
			<th>性别</th>
			<th>民族</th>
			<th>出生日期</th>
			<th>学历</th>
			<th>入学时间</th>
			<th>年级</th>
			<th>班级号</th>
			<th>所属专业</th>
			<th>删除</th>
			<th>重置密码</th>
		</tr>
		<%
			Iterator<student> it = list.iterator();
				while (it.hasNext()) {
					student stu = it.next();
		%>
		<tr>
			<td><%=stu.getStuid()%></td>
			<td><%=stu.getName()%></td>
			<td><%
				Long phone=stu.getPhone();
				if(phone==null){
					out.print("手机为空");
				}else{
					out.print(stu.getPhone());
				}
				%>
			</td>
			<td><%=stu.getSex()%></td>
			<td><%=stu.getNation()%></td>
			<td><%=stu.getAge().toLocaleString()%></td>
			<td><%=stu.getEducation()%></td>
			<td><%=stu.getEnrolmenttime().toLocaleString()%></td>
			<td><%=stu.getGrade()%></td>
			<td><%=stu.getClassid() %></td>
			<td><%=stu.getMajor()%> </td>
			<td><a href="Delete?stuid=<%=stu.getStuid()%>"
				onclick="return sure()">删除学生</a></td>
			<td><a href="Resetuser?stuid=<%=stu.getStuid()%>"
				onclick="return Sure()">重置密码</a></td>
		</tr>
		<%
			}
		%>
	</table>
	<div align="center">
		<label>第${pageindex}/${total}页 共${count}条</label> <a
			href="studentselectall?pageindex=1">首页</a> <a
			href="studentselectall?pageindex=${pageindex-1}"
			onclick="return check()">上一页</a> <a
			href="studentselectall?pageindex=${pageindex+1}"
			onclick="return checknext()">下一页</a> <a
			href="studentselectall?pageindex=${total}">尾页</a><br>
		<form action="studentselectall" method="get">
			<input type="text" name="pageindex" style="width:40 "> <input
				type="submit" value="跳转">
		</form>
		<%
			} catch (Exception e) {
				e.printStackTrace();
			}
		%>
	</div>
</body>
</html>

