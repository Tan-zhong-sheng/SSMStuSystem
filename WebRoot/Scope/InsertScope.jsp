<%@ page language="java" import="java.util.* ,pojo.*" pageEncoding="utf-8"%>
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

<title>添加成绩</title>
<link rel="stylesheet" type="text/css" href="Css/input.css">
<script type="text/javascript" src="Js/jQuery1.7.js"></script>
<link rel="stylesheet" type="text/css" href="Css/selectordie.css">
<script type="text/javascript" src="Js/selectordie.min.js"></script>
<script type="text/javascript">
$(function(){
	$('select').selectOrDie();
});
</script>
<script type="text/javascript">
	function check(){
		var scope = $("#scope").val();
		if(scope==null){
			alert("成绩不能为空");
		}
	}
/* 	$(function(){
		var stuid = $("#stuid").val();
		$("#stuid").change(function(){
			$("#grade").load("selecgrade",{"stuid":stuid});
		});
	});  */
</script>
</head>

<body>
		<%
			List<course> Cid = (List<course>) request.getAttribute("Cid");
			List<teacher> Tid = (List<teacher>) request.getAttribute("Tid");
			List<student> Stuid = (List<student>) request.getAttribute("Stuid");
		%>

		<form action="InsertScope" method="post">
			<table align="center">
				<tr>
					<td>学号:</td>
					<td>
					<select name="stuid" id ="stuid">
							<option value="请选择">
							<%
								for (int i = 0; i < Stuid.size(); i++) {
							%>
							<option value="<%=Stuid.get(i).getStuid()%>">
								<%=Stuid.get(i).getStuid()%>
							</option>
							<%
								}
							%>
					</select>
					</td>
				</tr>
				<tr>
					<td>课程名:</td>
					<td>

			 		<select name="cid">
				 <%
				 	for (int i = 0; i < Cid.size(); i++) {
				 %> 
							<option value="<%=Cid.get(i).getCid()%>"><%=Cid.get(i).getCname()%></option>
					 <%
					 	}
					 %>
					</select> 

					</td>
				</tr>
				<tr>
					<td>成绩:</td>
					<td><input type="text" name="scope" id="scope" style="width: 200px;"></td>
				</tr>
				
				<tr>
					<td>负责老师:</td>
					<td>
						<select name="teacherid">
						<%
							for (int i = 0; i < Tid.size(); i++) {
						%> 
							<option value="<%=Tid.get(i).getTeacherid()%>"><%=Tid.get(i).getTname()%></option>
						<%
						 	}
						 %>
					</select> 
					</td>
				</tr>
				<tr>
					<td>状态</td>
					<td>
						<select name="status">
							<option value="">请选择</option>
							<option value="1">正常考试</option>
							<option value="0">舞弊</option>
							<option value="2">缺考</option>
						</select>
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center"><input type="submit" value="提交" style="width: 200px;" onclick="return check()"></td>
				</tr>
				</table>
			</form>
</body>
</html>
