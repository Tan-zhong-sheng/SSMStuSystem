<%@ page language="java" import="java.util.*,pojo.*" pageEncoding="utf-8"%>

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

<title>管理系统</title>

</head>

<body>
	<%
		List<student> list = (List<student>) session.getAttribute("list");
			Iterator<student> it = list.iterator();
			while (it.hasNext()) {
		student User = it.next();
	%>
	学生学号:
	<%=User.getStuid()%><br>
	学生姓名为:
	<%=User.getName()%><br>
	学生密码为:
	<%=User.getPwd()%><br>
	学生电话为:
	<%=User.getPhone()%><br>
	<br><br><br>
	<%
		}
	%>
</body>
</html>
