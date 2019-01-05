<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Spring REST Demo</title>
</head>
<body>
<p>This is Spring REST Demo welcome page!</p>
<hr/>

<a href="${pageContext.request.contextPath}/test/hello">Hello</a>
<a href="${pageContext.request.contextPath}/api/students">Students</a>
<a href="${pageContext.request.contextPath}/api/customers">Customers</a>


</body>
</html>