<%@page import="Mytool.Mytool"%>
<%@ page language="java" import="java.util.* , pojo.*" pageEncoding="UTF-8"%>
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

<title>分配课程</title>


</head>

<body>
	 <%
		List<Classes> c = (List<Classes>)request.getAttribute("C");
		List<teacher> t = (List<teacher>)request.getAttribute("T");
		Iterator<Classes> C = c.iterator();
		Iterator<teacher> T = t.iterator();
	 %>
	<form action="distribution" method="post">
		<table align="center">
			<tr>
				<td>负责教师号</td>
				<td>
					<!-- <input type="text" name="teacherid"> -->
					<select name="teacherid">
						<%
							while(T.hasNext()){
								teacher ter = T.next();
						%>
						<option value=<%=ter.getTeacherid() %>><%=ter.getTname() %></option>
						<%
							}
						 %>
						
					</select>
				</td>
			</tr>
			<tr>
				<td>负责班级号</td>
				<td>
					<!-- <input type="text" name="Classid"> -->
					<select name="Classid">
						<%
							while(C.hasNext()){
								Classes cl = C.next();
						 %>
						 <option value="<%=cl.getClassid()%>"><%=cl.getClassname() %></option>
						 <%
						 	}
						  %>
					</select>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="submit" value="提交" style="width: 200">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>
