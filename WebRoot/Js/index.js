function check(){
		var a = true;
		var name = $("#stu_username_hide").val();
		var pwd = $("#stu_password_hide").val();
		var form  = $("#student");
		var power = $("#customer").val();
		var Code = $("#stu_code_hide").val();
			if(name==""){
				alert("账号不能为空");
				return false;
		   }else if(pwd==""){
				alert("密码不能为空");
				return false;
		   }else if(Code==""){
		   		alert("验证码不能为空");
		   		return false;
		   } else{
			    $.ajax({
					type:"POST",
					url:"checklogin",
					data:{"name":name,"pwd":pwd,"power":power,"checkVerification":Code},
					async:false, 
					success:function(result){
						if(result==-2){
							alert("验证码错误");
							a=false;
						}
						if(result==-1){
							alert("账号或密码错误");
							a=false;
						}
						if(result==-1500){
							alert("学号必须为数字");
							a=false;
						}
					},
					error:function(errorThrown){
						alert("系统异常");
					}
			}); 
			if(a==true){
				form.submit();
			}else{
				return a;
			}
		}
	}
function teachercheck(){
	var a = true;
	var name = $("#tea_username_hide").val();
	var pwd = $("#tea_password_hide").val();
	var form  = $("#teacherlogin");
	var power = $("#teacher").val();
	var Code = $("#tea_code_hide").val();
		if(name==""){
			alert("账号不能为空");
			return false;
	   }else if(pwd==""){
			alert("密码不能为空");
			return false;
	   }else if(Code==""){
	   		alert("验证码不能为空");
	   		return false;
	   } else{
		    $.ajax({
				type:"POST",
				url:"checklogin",
				data:{"name":name,"pwd":pwd,"power":power,"checkVerification":Code},
				async:false, 
				success:function(result){
					if(result==-2){
						alert("验证码错误");
						a=false;
					}
					if(result==-1){
						alert("账号或密码错误");
						a=false;
					}
					if(result==-1500){
						alert("教师号必须为数字");
						a=false;
					}
				},
				error:function(errorThrown){
					alert("系统异常");
				}
		}); 
		if(a==true){
			form.submit();
		}else{
			return a;
		}
	}
}
function governorcheck(){
	var a = true;
	var name = $("#sec_username_hide").val();
	var pwd = $("#sec_password_hide").val();
	var form  = $("#governorlogin");
	var power = $("#governor").val();
	var Code = $("#sec_code_hide").val();
		if(name==""){
			alert("账号不能为空");
			return false;
	   }else if(pwd==""){
			alert("密码不能为空");
			return false;
	   }else if(Code==""){
	   		alert("验证码不能为空");
	   		return false;
	   } else{
		    $.ajax({
				type:"POST",
				url:"checklogin",
				data:{"name":name,"pwd":pwd,"power":power,"checkVerification":Code},
				async:false, 
				success:function(result){
					if(result==-2){
						alert("验证码错误");
						a=false;
					}
					if(result==-1){
						alert("账号或密码错误");
						a=false;
					}
					if(result==-1500){
						alert("教务号必须为数字");
						a=false;
					}
				},
				error:function(errorThrown){
					alert("系统异常");
				}
		}); 
		if(a==true){
			form.submit();
		}else{
			return a;
		}
	}
}