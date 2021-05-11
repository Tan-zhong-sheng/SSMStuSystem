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
<script type="text/javascript" src="Js/jquery-3.2.1.min.js"></script>
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
			List<course> list = (List<course>)request.getAttribute("ExistingCid");
			List<Integer> list1 = (List<Integer>)request.getAttribute("list");
	%>


	<table align="center">
		<tr>
			<td colspan="6">
				<h3 align="center">选课信息</h3>
			</td>
		</tr>
		<tr>
			<th>课程号</th>
			<th>课程名</th>
			<th>是否选择</th>
			<th>考试</th>
			<th>操作</th>
			<th>操作</th>
		</tr>
		<%
			Iterator<course> it = list.iterator();
				while (it.hasNext()) {
					course cou = it.next();
					int check=0;
					int id = cou.getCid();
					//session.setAttribute("Electivesc", cou.getCid());
		%>
		<tr>
			<td><%=cou.getCid()%></td>
			<td><%=cou.getCname()%></td>
			<td>
				 <%
				 	if(list1!=null){
				 	 		if(list1.size()==0){
								out.print("没有选修");
							}else{
								for(int i =0;i<=list1.size();){
									int cid = list1.get(i);
									if(cid==id){
										check=1;
										out.print("已选修");
										list1.remove(i);
										break;
									}else{
										check=0;
										out.print("没有选修");
										break;
									}
								}
						}
					}else{
						out.print("没有选修");
					}
				 %>
			</td>
			<td>
				 <%
					if(check==1){
				 %>
				 	<a href="test?cid=<%=cou.getCid()%>">参加考试</a>
				 <%
					}else if(check==0){
						out.print("请先选修");
					}
				 %>
			</td>
			<td><a href="addElective?cid=<%=cou.getCid()%>">选修</a> </td>
			<td><a href="DeleteElective?cid=<%=cou.getCid()%>">退修</a> </td>
		</tr>
		<%
			}
		%>
	</table>
	<div align="center">
		<label>第${pageindex}/${total}页 共${count}条</label> <a
			href="ExistingCid?pageindex=1&&major=${major}">首页</a> <a
			href="ExistingCid?pageindex=${pageindex-1}&&major=${major}"
			onclick="return check()">上一页</a> <a
			href="ExistingCid?pageindex=${pageindex+1}&&major=${major}"
			onclick="return checknext()">下一页</a> <a
			href="ExistingCid?pageindex=${total}&&major=${major}">尾页</a><br>
		<form action="ExistingCid" method="get">
			<input type="text" name="pageindex" style="width:40 "> <input
				type="submit" value="跳转">
		</form>
	</div>
</body>
</html>

