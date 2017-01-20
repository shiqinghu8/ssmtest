<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ page isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ssm</title>
</head>
<body>
	<h1>用户修改</h1>
 <!-- 此时没有写action,直接提交会提交给/add -->
<sf:form method="post" modelAttribute="user" enctype="multipart/form-data">
 
	用户名:<sf:input path="user_name"/><sf:errors path="user_name"/><br/>
	密码:<sf:password path="password"/><sf:errors path="password"/><br/>
	年龄:<sf:input path="age"/><sf:errors path="age"/><br/>
	附件:<input type="file" name="attach"/><br/>	 
	<input type="submit" value="修改用户"/>
</sf:form>

 

</body>
</html>