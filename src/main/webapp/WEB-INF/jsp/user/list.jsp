<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<%
String path = request.getContextPath();  
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;  
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ssm</title>
</head>
<body>
	<h1>用户列表</h1>
	<a href="add">添加</a>
	<br />
	<a href="upload">上传文件</a>
	<br />
	<table width="700"   border="1">
		<tr>
			<td>用户id</td>
			<td>用户名</td>
			<td>用户密码</td>
			<td>年龄</td>
			<td>附件</td>
			<td>操作</td>
		</tr>
		<c:forEach items="${users }" var="u">
			<tr>
				<td>${u.id }</td>
				<td><a href="<%=basePath %>/user/${u.id }">${u.user_name }</a></td>
				<td>${u.password }</td>
				<td>${u.age }</td>
				<td> <a href="<%=basePath %>/user/download?fileName=${u.file_name }">${u.file_name }  </a> </td>
				<td><a href="${u.id }/update">更新</a>&nbsp;
				<a href="${u.id }?json">JSON</a>&nbsp;
				<a href="${u.id }/delete">删除</a></td>
			</tr>
		</c:forEach>

	</table>

</body>
</html>