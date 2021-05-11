<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="pojo.student"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>个人信息</title>

    <style type="text/css">
        table
        {
            border-collapse: collapse;
            margin: 0 auto;
            text-align: center;
        }
        table td, table th
        {
            border: 1px solid #cad9ea;
            color: #666;
            height: 30px;
        }
        table thead th
        {
            background-color: #CCE8EB;
            width: 100px;
        }
        table tr:nth-child(odd)
        {
            background: #fff;
        }
        table tr:nth-child(even)
        {
            background: #F5FAFA;
        }
    </style>

</head>
<body>
	<%
		student stu = (student) session.getAttribute("Information");
	%>
	<table align="center" border="solid">
		<tr>
			<td>学号</td>
			<td><%=stu.getStuid()%></td>
		</tr>
		<tr>
			<td>姓名</td>
			<td><%=stu.getName()%></td>
		</tr>
		<tr>
			<td>性别</td>
			<td><%=stu.getSex()%></td>
		</tr>
		<tr>
			<td>民族</td>
			<td><%=stu.getNation()%></td>
		</tr>
		
		<tr>
			<td>头像</td>
			 <td> <img alt="个人照片" src="/<%=stu.getPhoto() %>" width="150" height="100"></img></td> 
			<td><a href="Student/Upload.jsp">上传头像</a><br>上传头像不能超过5M上传头像不能含有中文</td>
		</tr>
		<tr>
			<td>学历</td>
			<td><%=stu.getEducation()%></td>
		</tr>
		<tr>
			<td>入学时间</td>
			<td><%=stu.getEnrolmenttime().toLocaleString()%></td>
		</tr>
		<tr>
			<td>专业</td>
			<td><%=stu.getMajor()%></td>
		</tr>
		<tr>
			<td>年级</td>
			<td><%=stu.getGrade()%></td>
		</tr>
		<tr>
			<td>出生日</td>
			<td><%=stu.getAge().toLocaleString()%></td>
		</tr>
		<tr>
			<td>电话</td>
			<td>
				<%				
					Long phone=stu.getPhone();
					if(phone==null){
						out.print("手机为空");
					}else{
						out.print(stu.getPhone());
					}
				%>
			</td>
			<td><a href="Student/UpdatePhone.jsp">修改手机号码</a></td>
		</tr>
	</table>
</body>
</html>
