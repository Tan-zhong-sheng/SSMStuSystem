<%@page import="Mytool.Mytool"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!doctype html>
<html>
  <head>
    <base href="<%=basePath%>">
    <title>上传人脸</title>
<script type="text/javascript" src="Js/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="Js/ajaxfileupload.js"></script>
<script type="text/javascript">
/* 	function check(){
			   $.ajaxFileUpload({
					type:"POST",
					url:"uploadface",
					secureuri: false,
					fileElementId:'file',
					dataType:"json",
					success:function(data,status){
						/* alert(data);
						var obj2 = JSON.parse(data);
						alert(obj2); */
					/*    	if(data!= 0){
							alert("上传失败");
						}else{
							alert("上传成功");
						} */
/* 					},
					error:function(errorThrown){
						alert("系统异常");
						alert(errorThrown);
					}
			});
		
	}
	function a (){
	
		var json = '{"result":true, "count":42}';
		var obj = JSON.parse(json);
		alert(obj.count);
} */ 
</script>
  </head>
  
  <body>
  <%
  	String name = (String)request.getParameter("teacherid");
  	Integer teacherid = Mytool.turn(name);
  	session.setAttribute("teacherid", teacherid);
   %>
		<form action="uploadface" method="post" enctype="multipart/form-data" id="upload">
			<table align="center">
				<tr>
					<td>上传头像:</td>
					<td><input type="file" name="file" id="file"></td>
				</tr>
				<tr>
					<td colspan="2" align="center"><input type="submit" value="上传"
						style="width: 200"  id="btn"></td>
				</tr>
			</table>
		</form>
  </body>
</html>
