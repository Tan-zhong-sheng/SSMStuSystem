<%@ page language="java" import="java.util.*,pojo.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>查询页面</title>
    
	<link rel="stylesheet" type="text/css" href="Css/table.css">
  </head>
  
  <body>
		<%
			List<student> list = (List<student>)request.getAttribute("list");
		 %>
		 <table align="center" border="solid">
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
		 	</tr>
		<%
			Iterator<student> it = list.iterator();
				while (it.hasNext()) {
					student stu = it.next();
		%>
		<tr>
			<td><%=stu.getStuid()%></td>
			<td><%=stu.getName()%></td>
			<td><%=stu.getPhone()%></td>
			<td><%=stu.getSex()%></td>
			<td><%=stu.getNation()%></td>
			<td><%=stu.getAge().toLocaleString()%></td>
			<td><%=stu.getEducation()%></td>
			<td><%=stu.getEnrolmenttime().toLocaleString()%></td>
			<td><%=stu.getGrade()%></td>
			<td><%=stu.getClassid() %></td>
			<td><%=stu.getMajor()%> </td>
		</tr>
		<%
			}
		%>
		 </table>
  </body>
</html>
