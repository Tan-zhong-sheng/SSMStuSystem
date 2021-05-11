<%@ page language="java" import="java.util.*,pojo.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>查看选修课程</title>

  </head>
  
  <body>
  <%
  	List<course> list = (List<course>)request.getAttribute("list");
  		if(list==null){
  			out.print("当前页面没有数据");
  		}
   %>
  	 <c:forEach items="${list}" var="Subject">
	  	  <table align="center" border="solid">
	  	 	<tr>
	  	 		<th>选修课程</th>
	  	 	</tr>
	  	 	<tr>
	  	 		<td>${Subject.cname}</td>
	  	 	</tr>
	  	 </table>
  	 </c:forEach>
  </body>
</html>
