<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>查询页面</title>
   <link rel="stylesheet" type="text/css" href="Css/input.css">
<script type="text/javascript" src="Js/jQuery1.7.js"></script>
<!-- <link rel="stylesheet" type="text/css" href="Css/selectordie.css">
<script type="text/javascript" src="Js/selectordie.min.js"></script>
<script type="text/javascript">
$(function(){
	$('select').selectOrDie();
});
</script> -->
    <script type="text/javascript">
    	function check(){
    		var teacherid = $("#teacherid").val();
    		var tname = $("#tname").val();
    		var sal = $("#sal").val();
    		var major = $("#major").val();
    	 	if((teacherid==null||teacherid=="" )&&( tname==null||tname=="") && (sal==null||sal=="")&&(major==null || major=="")){
    				alert("请输入搜索条件");
    				return false;
    		}
    	}
    </script>

  </head>
  
  <body>
  <% %>
	<div class="top"  align="center">
		 <form action="TeacherConditional" method="post">
			教师号:<input type="text" name="teacher" style="width: 100" id="teacherid">&nbsp;
			姓名:<input type="text" name="tname" style="width: 100" id="tname">&nbsp;
			工资:<input type="text" name="sal" style="width: 100" id="sal">
		        专业 <select name="major" id="major">
				<option value="">请选择</option>
				<option value="计算机">计算机</option>
				<option value="商贸">商贸</option>
				<option value="英语">英语</option>
				<option value="考古">考古</option>
				<option value="通信">通信</option>
			</select> 
			<input type="submit" value="查詢" onclick="return check()">
		 </form> 
	</div>
  </body>
</html>
