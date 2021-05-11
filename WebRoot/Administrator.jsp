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
	 <script type="text/javascript">
	 	$(function(){
	 		$("#btn1").click(function (){
	 			$.ajax({
						type:"get",
						url:"openElective",
						success:function(result){
							if(result==true){
								alert("选课开启");
							}else{
								alert("选课已经开启");
							}
						},
						error:function(errorThrown){
							alert("系统异常");
						}
				}); 
	 		});
	 	});
	 	 $(function(){
	 		$("#btn2").click(function (){
	 			$.ajax({
						type:"get",
						url:"closeElective",
						success:function(result){
							if(result==true){
								alert("选课关闭");
							}else{
								alert("选课已经关闭");
							}
						},
						error:function(errorThrown){
							alert("系统异常");
						}
				}); 
	 		});
	 	});
	 	$(function(){
	 		$("#btn3").click(function (){
	 			$.ajax({
						type:"get",
						url:"optentest",
						success:function(result){
							if(result==true){
								alert("考试开始");
							}else{
								alert("开始失败");
							}
						},
						error:function(errorThrown){
							alert("系统异常");
						}
				}); 
	 		});
	 	});
	 	$(function(){
	 		$("#btn4").click(function (){
	 			$.ajax({
						type:"get",
						url:"closttest",
						success:function(result){
							if(result==true){
								alert("考试关闭");
							}else{
								alert("关闭失败");
							}
						},
						error:function(errorThrown){
							alert("系统异常");
						}
				}); 
	 		});
	 	});
	 </script>
	 <style type="text/css">
	 audio{
		    z-index: 5;
		    position: absolute;
		    bottom: 0;
		    opacity: 0.1;
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
   		 <source src="mp3/zzsc.mp3">
	</audio>
		<%
			Integer id = (Integer) session.getAttribute("adminname");
		%>
		<div class="left">
			<div class="bigTitle">教务后台管理系统</div>
			<div class="lines">
				<div onclick="pageClick(this)" class="active"><img src="img/icon-1.png" /><a href="studentselectall" target="meau">学生操作</a></div>
			    <div onclick="pageClick(this)"><img src="img/6.jpg" /><a href="Student/StudentALL.jsp" target="meau">查询学生</a></div>
				<div onclick="pageClick(this)"><img src="img/7.jpg" /><a href="SelectClassid" target="meau">添加学生</a></div>
				<div onclick="pageClick(this)"><img src="img/icon-4.png" /><a href="Student/UpdateGrade.jsp" target="meau">修改年级</a></div>
				<div onclick="pageClick(this)"><img src="img/6.jpg" /><a href="Adminlevel" target="meau">查看等级</a></div>
				<div onclick="pageClick(this)"><img src="img/icon-5.png" /><a href="Admin/governolupdate.jsp" target="meau">修改管理者密码</a></div>
				<div onclick="pageClick(this)"><img src="img/6.jpg" /><a href="Scopesontroller" target="meau">查询所有成绩</a></div> 
				<div onclick="pageClick(this)"><img src="img/6.jpg" /><a href="Scope/Choiceselect.jsp" target="meau">成绩选择查询</a></div> 
				<div onclick="pageClick(this)"><img src="img/7.jpg" /><a href="AddRecord" target="meau">添加成绩</a></div> 
				<div onclick="pageClick(this)"><img src="img/6.jpg" /><a href="Cselectall" target="meau">查询所有课程</a></div> 
				<div onclick="pageClick(this)"><img src="img/6.jpg" /><a href="distributioncourse" target="meau">查询已分配课程</a></div> 
				<div onclick="pageClick(this)"><img src="img/7.jpg" /><a href="course/course.jsp" target="meau">添加课程</a></div> 
				<div onclick="pageClick(this)"><img src="img/6.jpg" /><a href="Tselectall" target="meau">教师管理页面</a></div> 
				<div onclick="pageClick(this)"><img src="img/7.jpg" /><a href="Teacher/Teacher.jsp" target="meau">添加教师</a></div> 
				<div onclick="pageClick(this)"><img src="img/6.jpg" /><a href="Teacher/Select.jsp" target="meau">查询教师</a></div> 
				<div onclick="pageClick(this)"><img src="img/6.jpg" /><a href="ClassInfo" target="meau">班级信息</a></div> 
				<div onclick="pageClick(this)"><img src="img/7.jpg" /><a href="Class/Classinsert.jsp" target="meau">添加班级</a></div> 
				<div onclick="pageClick(this)"><img src="img/6.jpg" /><a href="SelectSchoolRoom" target="meau">教室信息</a></div>
				<div onclick="pageClick(this)"><img src="img/7.jpg" /><a href="SchoolRoom/SCinsert.jsp" target="meau">添加教室</a></div>
				<div ><input type="button" id= "btn1" value="开启选修课"></div>
				<div ><input type="button" id= "btn2" value="关闭选修课"></div>
				<div ><input type="button" id= "btn3" value="开启考试"></div>
				<div ><input type="button" id= "btn4" value="结束考试"></div>
			</div>
		</div>
		<div class="top">
			<div class="leftTiyle" id="flTitle">个人信息</div>
			<div class="thisUser">欢迎<%=id%>号&nbsp;管理员 <a href="Cancellation">注销</a></div>
		</div>
		<div class="content">
			<iframe name="meau" class="meau" src="background.html"></iframe>
		</div>
	</body>
</html>
<%-- <!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>

 
<head>
<base href="<%=basePath%>">

<title>管理页面</title>
<link rel="stylesheet" type="text/css" href="Css/Administrator.css">
<link rel="stylesheet" type="text/css" href="Css/dtree.css">
<script src="Js/dtree.js" type="text/javascript"> </script>
	 <script type="text/javascript">
	 	$(function(){
	 		$("#btn1").click(function (){
	 			$.ajax({
						type:"get",
						url:"openElective",
						success:function(result){
							if(result==true){
								alert("选课开启");
							}else{
								alert("选课已经开启");
							}
						},
						error:function(errorThrown){
							alert("系统异常");
						}
				}); 
	 		});
	 	});
	 	
	 	 	$(function(){
	 		$("#btn2").click(function (){
	 			$.ajax({
						type:"get",
						url:"closeElective",
						success:function(result){
							if(result==true){
								alert("选课关闭");
							}else{
								alert("选课已经关闭");
							}
						},
						error:function(errorThrown){
							alert("系统异常");
						}
				}); 
	 		});
	 	});
	 </script>
</head>

<body>
	<%
		Integer id = (Integer) session.getAttribute("adminname");
	%>
		<div class="head">
			<P class="title">管理员后台</P>
			<p class="id">
				欢迎<%=id%>号管理员
				<a href="index.jsp">返回首页</a>
			</p>
		</div>
		<div class="content">
		<div class="left">
		 <ul>
				<li><a href="studentselectall" target="meau">学生操作页面</a></li>
				<li><a href="Student/StudentALL.jsp" target="meau">查询学生</a></li>
				<li><a href="SelectClassid" target="meau">添加学生</a></li>
				<li><a href="Student/UpdateGrade.jsp" target="meau">修改年级</a></li>
			</ul>
			<br>
			<ul>
				<li><a href="Adminlevel" target="meau">查看等级</a></li>
				<li><a href="Admin/governolupdate.jsp" target="meau">修改管理者密码</a></li>
			</ul>
			<br>
			<ul>
				<li><a href="Scopesontroller" target="meau">查询所有成绩</a></li>
				<li><a href="Scope/Choiceselect.jsp" target="meau">成绩选择查询</a></li>
				<li><a href="AddRecord" target="meau">添加成绩</a></li>
			</ul>
			<br>
			<ul>
				<li><a href="Cselectall" target="meau">查询所有课程</a></li>
				<li><a href="course/course.jsp" target="meau">添加课程</a></li>
				<!-- <li><a href="course/Cselect.jsp" target="meau">查询课程</a></li> -->
			</ul>
			<br>
			<ul>
				<li><a href="Tselectall" target="meau">教师管理页面</a></li>
				<li><a href="Teacher/Teacher.jsp" target="meau">添加教师</a></li>
				<li><a href="Teacher/Select.jsp" target="meau">查询教师</a></li>
			</ul>
			<br>
			<ul>
				<li><a href="ClassInfo" target="meau">班级信息</a></li>
				<li><a href="Class/Classinsert.jsp" target="meau">添加班级</a></li>
			</ul>
			<ul>
				<li><a href="SelectSchoolRoom" target="meau">教室信息</a></li>
				<li><a href="SchoolRoom/SCinsert.jsp" target="meau">添加教室</a></li>
			</ul> 
			<ul>
				<li><input type="button" id= "btn1" value="开启选修课"> </li>
				<li><input type="button" id= "btn2" value="关闭选修课"> </li>
			</ul>
		</div>
			<div class="right">
				<iframe src="" name="meau" class="Meau"></iframe>
			</div>
		</div>
		<div class="foot">
			Copyright © 2018 Qunar.com Inc. All Rights Reserved.<br> 闽江学院
		</div>
</body>
</html> --%>
