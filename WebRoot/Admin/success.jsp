<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="pojo.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>

  </head>
  
  <body>
  <div align="center">
    <%
    	out.print("注册成功请记住当前账号信息");
    %><br>
     <%
     	student User=(student)session.getAttribute("number");
     %>
           学号为：<%=User.getStuid()%><br>
           姓名：<%=User.getName()%><br>
           密码为：<%=User.getPwd() %><br>
  </div>
  </body>
</html>
