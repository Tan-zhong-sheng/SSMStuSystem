<%@ page language="java" import="java.util.* , pojo.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>查看班级人员</title>
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
  	 		<th>性别</th>
  	 		<th>年级</th>
  	 		<th>专业</th>
  	 	</tr>
  	 	<%
  	 		Iterator<student> it = list.iterator();
  	 		while(it.hasNext()){
  	 			student stu = it.next();
  	 	 %>
  	 	<tr>
  	 		<td><%=stu.getStuid() %></td>
  	 		<td><%=stu.getName() %></td>
  	 		<td><%=stu.getSex() %></td>
  	 		<td><%=stu.getGrade() %></td>
  	 		<td><%=stu.getMajor()%></td>
  	 	</tr>
  	 	<%
  	 		}
  	 	 %>
  	 </table>
  </body>
</html>
