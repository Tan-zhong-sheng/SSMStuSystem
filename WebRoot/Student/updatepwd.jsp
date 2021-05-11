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

<title>修改密码</title>
<link rel="stylesheet" type="text/css" href="Css/input.css">
 <script type="text/javascript" src="Js/jquery-3.2.1.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	 $("#bottom").hide();
}); 

	$(function() {
		var count = 60;
		var InterValObj;
		var curcount;
		var check;
		var describes = $("#describes").val();
		$("#a").click(function() {
				var photocode = document.getElementsByName("photocode");
				var choise="";
				for(var i=0;i<photocode.length;i++){
					if(photocode[i].checked == true){
						choise=photocode[i].value;
					}
				}
				$.ajax({
				type : "post",
				url : "Checkphotocode",
				async : false,
				data : {"photocode":choise,"describes":describes},
				success : function(result) {
					if (result == false) {
						alert("图形验证码错误");
						check = false;
					}else{
						check = true;
					}
				},
				error : function(errorThrown) {
					alert("系统异常");
				}
			});
			if(check==true){
			 	$("#bottom").hide();
			 	curCount = count;
				$("#btn1").attr("disabled", "true");
				$("#btn1").val(curCount + "秒后可重新发送");
				InterValObj = window.setInterval(SetRemainTime, 1000);
				//timer处理函数
				function SetRemainTime() {
					if (curCount == 0) {
						window.clearInterval(InterValObj);//停止计时器
						$("#btn1").removeAttr("disabled");//启用按钮
						$("#btn1").val("重新发送验证码");
					} else {
						curCount--;
						$("#btn1").val(curCount + "秒后可重新发送");
					}
				}
				$.ajax({
					type : "post",
					url : "SendCode1",
					success : function(result) {
						if (result == false) {
							alert("请填写手机号");
							check = false;
						}
					},
					error : function(errorThrown) {
						alert("系统异常");
						alert(errorThrown);
						return false;
					}
				}); 
			}else{
				return check;
			}
			return check;
		});
	}); 

	
	
	function CheckPhoneCode() {
		var Code = $("#Code").val();
		var Check;
		var pwd = document.getElementById("s").value;
		var pwd1 = document.getElementById("s1").value;
		if (pwd != pwd1) {
			alert("密码不一致");
			return false;
		}
		if (Code == null) {
			alert("验证码不能为空");
			return false;
		} else {
				$.ajax({
				type : "post",
				url : "CheckPhone",
				async : false,
				data : "Code=" + Code,
				success : function(result) {
					if (result == false) {
						alert("验证码错误");
						Check = false;
					}
				},
				error : function(errorThrown) {
					alert("系统异常");
				}
			});
		}
		return Check;
	}
</script>

</head>

<body>
		<%
  			List<photoverification> list = (List<photoverification> )session.getAttribute("updatepwd");
  		%>
	<div align="center">

		<form action="updatepwd" method="post">
			<table>
				<tr>
					<td>原密码:</td>
					<td><input type="password" name="pwd" placeholder="原密码"></td>
				</tr>
				<tr>
					<td>新密码:</td>
					<td><input type="password" name="pwd1" id="s" placeholder="新密码"></td>
				</tr>
				<tr>
					<td>确认密码:</td>
					<td><input type="password" name="pwd2" id="s1"
						 placeholder="确认密码"></td>
				</tr>
				<tr>
					<td>获取验证码</td>
					<td><input type="text" name="Code" id="Code" placeholder="验证码"></td>
					<td><input type="button" value="获取验证码" id="btn1"></td>
				</tr>
				<tr id="bottom">
					<td>
						<%
							for (int i = 0; i < list.size(); i++) {
								Random ra =new Random();
								Integer pid = ra.nextInt(4);
						%> 
						 <%
							if(i==0){
								out.print("以下哪个是"+list.get(pid).getDescribes());
						 %>
						 <input type="hidden" value="<%=list.get(pid).getDescribes()%>" id="describes">
						 <%
							}
						 %>
					</td>
				    <td>
							<input type="radio" value="<%=list.get(i).getPid()%>" id="photocode" name="photocode" >
							<img alt="" src="<%=list.get(i).getAddress()%>" width="100" height="100"></img>
						 <%
						 	}
						 %>
						 <input type="button" value="确定" id="a" onclick="sendMessage()">
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center"><input type="submit" id="Submission"  value="提交" style="width: 200" onclick="return CheckPhoneCode()" />
				</tr>
			</table>
		</form>
	</div>
	<script type="text/javascript">
	$("#btn1").click(function(){
		 $("#bottom").show();
	});
</script>
</body>
</html>
