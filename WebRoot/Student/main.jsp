<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<title>首页</title>
		<link rel="stylesheet" href="Css/page.css" />
		<script type="text/javascript" src="Js/jquery-3.2.1.min.js" ></script>
		<script type="text/javascript" src="Js/main.js" ></script>
			 <style type="text/css">
	 audio{
		    z-index: 5;
		    position: absolute;
		    bottom: 0;
		    opacity: 0;
		    -webkit-transition: all 2s;
		    -moz-transition: all 2s;
		    -ms-transition: all 2s;
		    -o-transition: all 2s;
		    transition: all 2s;
		    right:0
     }
	audio:hover{
	    opacity: 1;
	}
	 </style>
	</head>

	<body>
	<audio controls autoplay loop="loop">
   		 <source src="mp3/student.mp3">
	</audio>
		<div class="left">
			<div class="bigTitle">学生后台管理系统</div>
			<div class="lines">
				<div onclick="pageClick(this)" class="active"><img src="img/icon-1.png" /><a target="meau" href="Studentinformation">个人信息</a></div>
				<div onclick="pageClick(this)"><img src="img/icon-2.png" /><a target="meau" href="studentphotocode">修改密码</a></div>
				<div onclick="pageClick(this)"><img src="img/icon-3.png" /><a target="meau" href="Studentselet">查看成绩 </a></div>
				<div onclick="pageClick(this)"><img src="img/icon-4.png" /><a target="meau" href="Elective">选修科目 </a></div>
				<div onclick="pageClick(this)"><img src="img/down.jpg" /><a target="meau" href="selectdata">查看资料 </a></div>
			</div>
		</div>
		<div class="top">
			<div class="leftTiyle" id="flTitle">个人信息</div>
			<div class="thisUser">欢迎${student.name}&nbsp;同学 <a href="Cancellation">注销</a></div>
		</div>
		<div class="content">
			<iframe name="meau" class="meau" src="Special.html"></iframe>
		</div>
	</body>
</html>