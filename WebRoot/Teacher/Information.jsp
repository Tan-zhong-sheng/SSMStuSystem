<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="pojo.teacher"%>
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
<link rel="stylesheet" type="text/css" href="Css/table.css">
</head>

<body>
	<%
		teacher ter = (teacher) session.getAttribute("Terinfo");
	%>
	<table align="center" border="solid">
		<tr>
			<td>教师号</td>
			<td><%=ter.getTeacherid()%></td>
		</tr>
		<tr>
			<td>姓名</td>
			<td><%=ter.getTname()%></td>
		</tr>
		<tr>
			<td>工资</td>
			<td><%=ter.getSal()%></td>
		</tr>
		<tr>
			<td>照片</td>
			<td><img alt="个人照片" src="/<%=ter.getPhoto() %>" width="100" height="100"></td>
		<!-- 	<td><a href="Teacher/Upload.jsp">上传照片</a> </td> -->
		</tr>
		<tr>
			<td>负责科目</td>
			<td><%=ter.getMajor()%></td>
		</tr>
		<tr>
			<td>入职时间</td>
			<td><%=ter.getEntrytime().toLocaleString()%></td>
		</tr>
		<tr>
			<td>手机</td>
			<td>
				<%
					if(ter.getPhone()==null){
						out.print("手机号为空");
					}else{
						out.print(ter.getPhone());
					}
				 %>
			</td>
			<td><a href="Teacher/changephone.jsp">修改手机</a></td>
		</tr>
		<tr>
			<td>QQ</td>
			<td>
				<%
					if(ter.getQQ()==null){
						out.print("QQ为空");
					}else{
						out.print(ter.getQQ());
					}
				 %>
			</td>
			<td><a href="Teacher/changeqq.jsp">修改QQ</a></td>
		</tr>
	</table>
</body>
</html>
