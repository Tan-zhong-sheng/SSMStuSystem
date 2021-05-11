<%@page import="com.alibaba.druid.sql.visitor.functions.Ltrim"%>
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

<title>添加成绩</title>
<script type="text/javascript" src="Js/jquery-3.2.1.min.js"></script>
<link rel="stylesheet" type="text/css" href="Css/table.css">
<script type="text/javascript">
	function check(){
		var scope = document.getElementsByName("scope");
		var stuid = document.getElementsByName("stuid");
		var Name = document.getElementsByName("name");
		var status = document.getElementsByName("status");
		var sc = new Array();
		var st = new Array();
		var Statu = new Array();
		var j = scope.length;
		var v = stuid.length;
		var l = status.length;
		for(var i =0;i<j;i++){
			sc[i] = scope[i].value;
			if(sc[i]>100){
				alert("成绩输入不能超过100");
				return false;
			}
		}
		for(var i =0;i<j;i++){
			st[i] = stuid[i].value;
		} 
		for(var i =0;i<l;i++){
			Statu[i]=status[i].value;
		}
		if(status==""||status==null){
			alert("请选择考试状态");
			return false;
		}
		$.ajax({
					type:"get",
					url:"addteracherscope",
					contentType:"application/json charset=utf-8",
					data:{"sc":sc,"st":st,"status":Statu},
					traditional: true,
					success:function(result){
						if(result==false){
							alert("保存失败");
						}else{
							alert("保存成功");
						}
					},
					error:function(errorThrown){
						alert("系统异常");
						alert(errorThrown);
					}
			}); 
	}
	function checksubmission(){
		if (confirm("确定提交?") == true){
			
		}else{
			return false;
		} 
		var stuid = document.getElementsByName("stuid");
		var sc = new Array();
		var v = stuid.length;
		for(var i =0;i<v;i++){
			sc[i] = stuid[i].value;
		} 
			$.ajax({
					type:"get",
					url:"updatescopestatus",
					contentType:"application/json;charset=utf-8",
					data:{"sc":sc},
					traditional: true,
					success:function(result){
						if(result==false){
							alert("提交失败");
						}else{
							alert("提交成功");
						}
					},
					error:function(errorThrown){
						alert("系统异常");
					}
				}); 
	}
</script>
</head>

<body>
	 <%
		List<student> list = (List<student>)request.getAttribute("list");
		Iterator<student> it = list.iterator();
		List<Integer> scopelist = new ArrayList<Integer>();
	 %>
	 <table align="center" border="solid">
	 	<tr>
	 		<th>学号</th>
	 		<th>姓名</th>
	 		<th>评分</th>
	 		<th>状态</th>
	 	</tr>
	 	 <%
	 		while(it.hasNext()){
	 			student stu = it.next();
	 	 %>
	 	<tr>
	 		<td><%=stu.getStuid() %><input type="hidden" value="<%=stu.getStuid()%>" id="stuid" name ="stuid"></td>
	 		<td><%=stu.getName() %><input type="hidden" value="<%=stu.getName()%>"  name ="name"></td>
	 		<td>
	 			<input type="text" style="width: 50" name="scope"  id="scope"> 
	 		</td>
	 		<td>
	 			<select name="status" id="status">
	 				<option value="">请选择</option>
	 				<option value="1">正常考试</option>
					<option value="0">舞弊</option>
					<option value="2">缺考</option>
	 			</select>
	 		</td>
	 	</tr>
	 	 
	 	 <%
	 		 } 
	 	 %>
	 </table>
	 <div align="right">
		<input type="button" value="保存" onclick="check()">
	 	<input type="button" value="提交" style="width: 100" onclick="checksubmission()">
	 </div>
</body>
</html>
