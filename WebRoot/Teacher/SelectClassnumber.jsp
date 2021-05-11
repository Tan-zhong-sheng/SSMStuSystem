<%@ page language="java" import="java.util.*,pojo.*"
	pageEncoding="UTF-8"%>
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

<title>学生信息</title>

<link rel="stylesheet" type="text/css" href="Css/table.css">
<script type="text/javascript" src="Js/jQuery1.7.js"></script>
<script type="text/javascript" src="Js/echarts.min.js"></script>
<style type="text/css">
	#main{
		float: left;
	}
	#a{
		float: center;
	}
</style>
</head>

<body>
<div>
<div id="main"  style="width: 500px;height:400px;"></div>
<script type="text/javascript">
		var classid = '${classesid}';
		var cid = '${cid}';
		var passnum;
		var nopassnum;
		 $.ajax({
					type:"POST",
					url:"scopechart",
					data:{"classid":classid,"cid":cid},
					async:false, 
					success:function(result){
						var obj = JSON.parse(result);
						passnum = obj.passnum;
						nopassnum = obj.nopassnum;
					},
					error:function(errorThrown){
						alert("系统异常");
					}
			}); 
 		var myChart = echarts.init(document.getElementById("main"));
        // 指定图表的配置项和数据
                var option = {
	            title: {
	                text: '${classesid}班成绩表'
	            },
	            tooltip: {},
	            legend: {
	                data:['成绩表']
	            },
	            xAxis: {
	                data: ["及格","不及格"]
	            },
	            yAxis: {},
	            series: [{
	                name: '人数',
	                type: 'bar',
	                data: [passnum, nopassnum]
	            }]
	        };
	         
        // 使用刚指定的配置项和数据显示图表。
       		 myChart.setOption(option);
</script> 
<div id="a">
	<table align="center" border="solid">
		<tr>
			<th>学号</th>
			<th>姓名</th>
			<th>成绩</th>
		</tr>
		<%
			List<SelectScope> list = (List<SelectScope>) session
					.getAttribute("selectClassesnumber");
			Iterator<SelectScope> it = list.iterator();
			while (it.hasNext()) {
				SelectScope stu = it.next();
		%>
		<tr>
			<td><%=stu.getStuid()%></td>
			<td><%=stu.getName()%></td>
			<td><%=stu.getScope()%></td>
		</tr>
		<%
			}
		%>
		
	</table>
</div>
</div>
	<div class="div1" align="right" >
		<a href="Exportscope?classesid=${classesid }&&cid=${cid}">导出成绩</a>
	</div>
</body>
</html>
