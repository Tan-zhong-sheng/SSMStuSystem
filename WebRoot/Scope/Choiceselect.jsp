<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
<title>选择查询</title>
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
 		var stuid = $("#stuid").val();
 		var teacherid = $("#teacherid").val();
 		var cid = $("#cid").val();
 		var grade = $("#grade").val();
 		var scope = $("#scope").val();
 		var scope1 = $("#scope1").val();
 		var check;
 		if((stuid==null||stuid=="")&&(teacherid==null||teacherid=="")&&(cid==null||cid=="")&&(grade==null||grade=="")&&(scope==null||scope=="")&&(scope1==null||scope1=="")){
 			alert("请输入搜索条件");
 			return false;
 		}else if(scope<0){
 			alert("成绩不能为负数");
 			return false;
 		}else if(scope1>100){
 			alert("成绩不能超过100");
 			return false;
 		}else if(stuid==null){
 			 $.ajax({
					type:"POST",
					url:"checkstuid",
					data:{"stuid":stuid},
					async:false, 
					success:function(result){
						if(result==false){
							alert("查询学号不存在");
							check=false;
						}
					},
					error:function(errorThrown){
						alert("系统异常");
					}
			}); 
			return check;
 		}
 	}
 </script>
  </head>
  
  <body>
 	<div align="center">
  	<form action="choiseselect" method="get">
		学号：<input type="text" name="stuid" id="stuid" style="width: 50">
		课程号:<input type="text" name ="cid" id="cid" style="width: 50">
		负责老师:<input type="text" name="teacherid" id="teacherid" style="width: 50">
		年级 <select name="grade" id="grade" name="grade" style="height: 30px;">
				<option value="">请选择</option>
				<option value="One">第一学期</option>
				<option value="Two">第二学期</option>
				<option value="Three">第三学期</option>
				<option value="Four">第四学期</option>
			</select> 
		成绩:<input type="text" name="scope" style="width: 30" id="scope">-<input type="text" name="scope1" style="width: 30" id="scope1">
		<input type="submit" value="查询" onclick="return check()">
  	</form>
  	</div>
  </body>
</html>
