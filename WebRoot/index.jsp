<%@ page language="java" import="java.util.* ,pojo.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>闽江学院登陆系统</title>
<link href="Css/login.css" rel="stylesheet" rev="stylesheet" type="text/css" media="all" />
<script type="text/javascript" src="Js/jQuery1.7.js"></script>
<script type="text/javascript" src="Js/index.js"></script>
<script type="text/javascript" src="Js/jquery.SuperSlide.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	var $tab_li = $('#tab ul li');
	$tab_li.hover(function(){
		$(this).addClass('selected').siblings().removeClass('selected');
		var index = $tab_li.index(this);
		$('div.tab_box > div').eq(index).show().siblings().hide();
	});	
});
</script>
<script type="text/javascript">
$(function(){
	$(".screenbg ul li").each(function(){
		$(this).css("opacity","0");
	});
	$(".screenbg ul li:first").css("opacity","1");
	var index = 0;
	var t;
	var li = $(".screenbg ul li");	
	var number = li.size();
	function change(index){
		li.css("visibility","visible");
		li.eq(index).siblings().animate({opacity:0},3000);
		li.eq(index).animate({opacity:1},3000);
	}
	function show(){
		index = index + 1;
		if(index<=number-1){
			change(index);
		}else{
			index = 0;
			change(index);
		}
	}
	t = setInterval(show,8000);
	//根据窗口宽度生成图片宽度
	var width = $(window).width();
	$(".screenbg ul img").css("width",width+"px");
});
</script>
</head>

<body>
	<audio controls autoplay loop="loop">
   		 <source src="mp3/index.mp3">
	</audio>
<div id="tab">
  <ul class="tab_menu">
    <li class="selected">学生登录</li>
    <li>教师登录</li>
    <li>教务登录</li>
  </ul>
  <div class="tab_box"> 
    <!-- 学生登录开始 -->
    <div>
      <!-- <div class="stu_error_box"></div> -->
      <form action="login" method="post" class="login" id="student" >
        <div id="username">
          <label>学&nbsp;&nbsp;&nbsp;号：</label>
          <input type="text" id="stu_username_hide" name="id"/>
        </div>
        <div id="password">
          <label>密&nbsp;&nbsp;&nbsp;码：</label>
          <input type="password" id="stu_password_hide" name="pwd"/>
       	  <input type="hidden" value="customer" name="power" id="customer"/>
        </div>
        <div id="code">
          <label>验证码：</label>
          <input type="text" id="stu_code_hide" name="code"/>
          <img src="Verification" title="点击更换" alt="验证码" onclick="this.src='Verification?d='+Math.random();"/> </div>
        <div id="login">
          <button type="submit" onclick="return check()">登录</button>
        </div>
      </form>
    </div>
   <!-- 学生登录结束-->
   <!-- 导师登录开始-->
    <div class="hide">
      <form action="login" method="post" class="login" id="teacherlogin">
        <div id="username">
          <label>教师号：</label>
          <input type="text" id="tea_username_hide" name="id"/>
        </div>
        <div id="password">
          <label>密&nbsp;&nbsp;&nbsp;码：</label>
          <input type="password" id="tea_password_hide" name="pwd"/>
          <input type="hidden" value="teacher" name="power" id="teacher"/>
        </div>
        <div id="code">
          <label>验证码：</label>
          <input type="text" id="tea_code_hide" name="code" />
          <img src="Verification" title="点击更换" alt="验证码" onclick="this.src='Verification?d='+Math.random();"/> </div>
        <div id="remember">
          <a href="faceverify">人脸登陆</a>
        </div>
        <div id="login">
          <button type="submit" onclick="return teachercheck()">登录</button>
        </div>
      </form>
    </div>
     <!-- 导师登录结束-->
     <!-- 教务登录开始-->
    <div class="hide">
      <form action="login" method="post" class="login" id="governorlogin">
        <div id="username">
          <label>教务号：</label>
          <input type="text" id="sec_username_hide" name="id"/>
        </div>
        <div id="password">
          <label>密&nbsp;&nbsp;&nbsp;码：</label>
          <input type="password" id="sec_password_hide" name="pwd"/>
          <input type="hidden" value="governor" name="power" id="governor"/>
        </div>
        <div id="code">
          <label>验证码：</label>
          <input type="text" id="sec_code_hide" name="code" />
          <img src="Verification" title="点击更换" alt="验证码" onclick="this.src='Verification?d='+Math.random();"/> </div>
        <div id="login">
          <button type="submit" onclick="return governorcheck()">登录</button>
        </div>
      </form>
    </div>
     <!-- 教务登录结束-->
  </div>
</div>
<div class="bottom">©2018 Leting <a href="javascript:;" target="_blank">关于</a> <span>福州xxx号</span>  More Templates <img width="13" height="16" src="images/copy_rignt_24.png" /></div>
<div class="screenbg">
  <ul>
    <li><a href="javascript:;"><img src="images/4.jpg"></a></li>
    <li><a href="javascript:;"><img src="images/3.jpg"></a></li>
    <li><a href="javascript:;"><img src="images/2.jpg"></a></li>
  </ul>
</div>
</body>
</html>

<!-- <!DOCTYPE html>
<html>
  <head>
    <title>学生管理系统</title>
<script type="text/javascript" src="Js/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="Js/index.js"></script>
<link rel="stylesheet" type="text/css" href="Css/index.css">
 </head>
  
  
  <body>
   
  <div class="header" id="head">
  		<div class="title">学生管理系统</div>
  </div>
  <div class="wrap" id="wrap">
  <div class="logGet">
	  <div class="top">
		 <h1 class="p1">登陆页面</h1>
	  </div>
   <form action="login" method="post">
   		<div class="lgD">
   		<img alt="" src="image/2.jpg"  width="20px;" +height="20">
   		<input type ="text" name ="id" id="name" placeholder="账号" style="width: 250px;"><br>
   		</div>
   		<div class="lgD">
   		<img alt="" src="image/3.jpg"  width="20" height="20">
   		<input type ="password" name ="pwd" id="pwd" placeholder="密码" style="width: 250px;"><br>
   		</div>
   		
   		管理员<input type="radio" name ="power" value="governor" id="power">
   		学生<input type="radio" name ="power" value="customer" checked="checked" id="power">
   		教师<input type="radio" name ="power" value="teacher" id="power"><br>
   		验证码： <input type="text" name = "checkVerification" style="width: 60" id="checkVerification">
   		  <img alt="验证码" src="Verification" id="IMG" onclick="this.src='Verification?d='+Math.random();">
   		 <br>
   		 <a href="faceverify">人脸登陆</a>
   			<div class="button">
   				<input type ="submit" value="登陆" id="Sum" onclick="return check()" style="width: 200px;">
   			</div>
   </form>
   		</div>
    </div>
    <div class="foot">
    	<div class="ss">
    		Copyright Â© 2018 tzs. All Rights Reserved.
    	</div>
    	闽江学院
    </div>
  </body>
</html>
 -->