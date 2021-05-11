<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
  <head>
    <base href="<%=basePath%>">
    
 <title>重置密码</title>
		<script type="text/javascript">
	   var xml;
       function create(){
        if(window.XMLHttpRequest){
          xml=new XMLHttpRequest();
        }else if(window.ActiveXobject){
          xml=new ActiveXObject("Microsoft.XMLHTTP");
        }else{
          document.write("无法创建");
        }
       }
       function doadj(){
         create();
         if(xml!=null){
         var name=document.getElementById("name").value;
         xml.open("post","servlet?tzs="+name,true);
         xml.setRequestHeader("Content-type","application/x-www-form-urlencoded");
         xml.onreadystatechange=hhh;/*当请求被发送到服务器时，我们需要执行一些基于响应的任务。
                                                                           每当 readyState 改变时，就会触发 onreadystatechange 事件。
                                      readyState 属性存有 XMLHttpRequest 的状态信息。
                                      readystate: XMLHttpRequest的状态信息 
                                                                           发送一个请求后，客户端无法确定什么时候会完成这个请求，所以需要用事件机制来捕获请求的状态
                                      */
         xml.send(null);//发送请求要写在最后
         }else{
				alert("不能创建XMLHttpRequest对象实例");
			}
       }
        function hhh(){
         if(xml.readyState==4){
            if(xml.status==200){
              if(xml.responseText==0){
              		alert("用户不存在");	
              }
            }
         
         }
     }
	</script>	
	<style type="text/css">
		.content{
			text-align: center;
		}
	</style>
  </head>
  
  <body>
    <form action="ResetUser" method="post" class="content">
    	重置学号为:<input type ="text" name= "stuid" id="name" onblur="doadj()"><br>
    	<input type ="hidden" name= "pwd"><br>
    			 <input type="submit" value ="提交">
    </form>
  </body>
</html>
