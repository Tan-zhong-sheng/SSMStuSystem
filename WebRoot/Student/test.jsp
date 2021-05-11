<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="pojo.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>选修考试页面</title>
    <script type="text/javascript" src="Js/jQuery1.7.js"></script>
	<script type="text/javascript">
	function check(){
		var cid = '${cid}';
		var check;
		 $.ajax({
				type:"POST",
				url:"selectstatus",
				data:{"cid":cid},
				async:false, 
				success:function(result){
					if(result==false){
						alert("已考试");
						check=false;
					}
				},
				error:function(errorThrown){
					alert("系统异常");
				}
		}); 
		return check;
	}
		
	</script>
	<style type="text/css">
		.hi{
			text-align: center;
		}
	</style>
  </head>
  
  <body>
  		<%
  			List<test> list = (List<test>)request.getAttribute("list");
  		%>
	  		<form action="correction" method="post">
	  			<table align="center">
	  			<tr>
	  				<th colspan="2">期末考试</th>
	  			</tr>
	  			 <%
	  				for(int i=0;i<list.size();i++){
	  			 %>
	  				<tr>
	  					<td style="width: 60px;height:60px;">第<%=i+1 %>题</td>
	  					<td>
	  						<input type="hidden" name="size" value=<%=list.size() %>>
	  						<input type="hidden" name="cid" value=${cid }>
	  						<%=list.get(i).getTestname() %>
	  					</td>
	  				</tr>
	  				<tr>
	  					<td colspan="2">
	  						<input type="radio" name="<%=i %>" value="x<%=list.get(i).getOption0()%>"><%=list.get(i).getOption0() %>
	  						<input type="radio" name="<%=i %>" value="<%=list.get(i).getOption1()%>"><%=list.get(i).getOption1() %>
	  						<input type="radio" name="<%=i %>" value="<%=list.get(i).getOption2()%>"><%=list.get(i).getOption2() %>
	  						<input type="radio" name="<%=i %>" value="<%=list.get(i).getOption3()%>"><%=list.get(i).getOption3() %>
	  					</td>
	  				</tr>
	  		
	  		 <%
	  			}
	  		 %>
	  		   		<tr>
	  					<td colspan="2" align="center">
	  						<input type="submit" value="提交" style="width:200px;" onclick="return check()">
	  					</td>
	  				</tr>
	  			</table>
	  		 </form>
  </body>
</html>
