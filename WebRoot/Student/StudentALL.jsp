<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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

<title>管理页面</title>
<link rel="stylesheet" type="text/css" href="Css/input.css">
<script type="text/javascript" src="Js/jQuery1.7.js"></script>
<link rel="stylesheet" type="text/css" href="Css/selectordie.css">
<script type="text/javascript" src="Js/selectordie.min.js"></script>
<script type="text/javascript">
$(function(){
	$('select').selectOrDie();
});
</script>
<!-- <script type="text/javascript">
	$(function(){
	var name = $("#name").val();
	var stuid = $("#stuid").val();
	var major = $("#major").val();
	var sex = $("#sex").val();
	var Nation = $("#Nation").val();
	var education = $("#education").val();
	var grade = $("#grade").val();
	$("Result").load({
		type:"post",
		url:Selectcondition,
		data:{"name":name,"stuid":stuid,"major":major,"sex":sex,"Nation":Nation,"education":education,"grade":grade}
		
	});
	});
</script> -->
 <script type="text/javascript">
	function check(){
		var name = $("#name").val();
		var stuid = $("#stuid").val();
		var major = $("#major").val();
		var sex = $("#sex").val();
		var Nation = $("#Nation").val();
		var education = $("#education").val();
		var grade = $("#grade").val();
		//alert(grade);
		if((name==null || name=="") && (stuid == null || stuid=="") && (major == null || major=="") && (sex == null || sex=="") && (Nation == null || Nation=="")&& (education == null || education=="") && (grade==null || grade== "")){
			alert("请输入查询条件");
			return false;
		}		
	}
</script> 
</head>
<body>
	<div class="top"  align="center">
		 <form action="Selectcondition" method="post">
			学号:<input type="text" name="stuid" style="width: 100" id="stuid">&nbsp;
			姓名:<input type="text" name="name" style="width: 100" id="name">&nbsp;
			性别： <select name="sex" id="sex">
				<option value="">请选择</option>
				<option value="男">男</option>
				<option value="女">女</option>
			</select> 
			民族: <select name="Nation" id="Nation">
				<option value="">请选择</option>
				<option value="汉族" style="width: 50">汉族</option>
				<option value="其他">其他</option>
			</select> 
			学历 <select name="education" id="education">
				<option value="">请选择</option>
				<option value="大专">大专</option>
				<option value="高中">高中</option>
			</select> 
			年级 <select name="grade" id="grade">
				<option value="">请选择</option>
				<option value="第一学期">第一学期</option>
				<option value="第二学期">第二学期</option>
				<option value="第三学期">第三学期</option>
				<option value="第四学期">第四学期</option>
			</select> 
			专业 <select name="major" id="major">
				<option value="">请选择</option>
				<option value="计算机">计算机</option>
				<option value="商贸">商贸</option>
				<option value="英语">英语</option>
				<option value="考古">考古</option>
				<option value="通信">通信</option>
			</select> <br><br>
			<input type="submit" value="查詢" onclick="return check()" style="width: 500px;">
				
		 </form> 
	</div>
	<div id = "Result">
		
	</div>
</body>
</html>
