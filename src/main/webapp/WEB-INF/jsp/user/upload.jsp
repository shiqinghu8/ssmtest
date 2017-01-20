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
	<h1>文件上传</h1>
<sf:form method="post"  enctype="multipart/form-data">

	附件:<input type="file" name="attachs"/> 
	<input type="file" name="attachs"/> 	
	<input type="file" name="attachs"/><br/><br/><br/>
	<input type="submit" value="上传"/>
</sf:form>

</body>
</html>