<%@page import="Mytool.Mytool"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>修改成绩</title>
	<script type="text/javascript">
		function check(){
			var scope = document.getElementById("scope").value;
			if(scope==null){
				alert("成绩不能为空");
				return false;
			}else{
				return true;
			}
		}
	</script>
  </head>
  
  <body>
  <div align="center">
  	 <%
  		String stuid = request.getParameter("stuid");
  		Integer Stuid = Mytool.turn(stuid);
  		session.setAttribute("ScStuid", Stuid);
  		String cid = request.getParameter("cid");
  		Integer Cid =Mytool.turn(cid);
  		session.setAttribute("Sccid", Cid);
  	 %>
  	 <form action="UpdateSC" method="post">
  	 		修改成绩为：<input type="text" name="scope" id="scope" onclick="return check()">
  	 				  <input type="submit" value="提交">
  	 </form>
 </div>
  </body>
</html>
