<%@ page language="java" import="java.util.* ,pojo.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>课程分配信息</title>
    <link rel="stylesheet" type="text/css" href="Css/table.css">
    <script type="text/javascript" src="Js/jquery-3.2.1.min.js"></script>
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
  		List<TC> list = (List<TC>)request.getAttribute("distribution");
  		Iterator<TC> it = list.iterator();
  	 %>
  	 <table align="center">
  	 	<tr>
  	 		<td colspan="4" align="center">
  	 			<h3>课程分配</h3>
  	 		</td>
  	 	</tr>
  	 	<tr>
  	 		<th>课程名</th>
  	 		<th>班级名</th>
  	 		<th>教师名</th>
  	 		<th>操作</th>
  	 	</tr>
  	 	 <%
  	 		while(it.hasNext()){
  	 			TC t = it.next();
  	 	 %>
  	 	<tr>

  	 		<td><%=t.getCname() %></td>
  	 		<td><%=t.getClassname() %></td>
  	 		<td><%=t.getTname() %></td>
  	 		<td><a href="closedistributioncourse?cid=<%=t.getCid()%>&&teacherid=<%=t.getTeacherid()%>&&Classid=<%=t.getClassid()%>">取消分配</a></td>
  	 	</tr>
  	 	  <%
  	 			}
  	 	  %>
  	 </table>
  	 <div align="center">
		<label>第${pageindex}/${total}页 共${count}条</label> <a
			href="distributioncourse?pageindex=1">首页</a> <a
			href="distributioncourse?pageindex=${pageindex-1}" onclick="return check()">上一页</a>
		<a href="distributioncourse?pageindex=${pageindex+1}"
			onclick="return checknext()">下一页</a> <a
			href="distributioncourse?pageindex=${total}">尾页</a>
		<form action="distributioncourse" method="get">
			<input type="text" name="pageindex" style="width:40 "> <input
				type="submit" value="跳转">
		</form>
	</div>
  </body>
</html>
