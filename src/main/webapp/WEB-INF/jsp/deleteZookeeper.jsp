<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form:form modelAttribute="zookeeper" method="post">
		First Name:<form:input path="firstName"/>
		Last Name:<form:input path="lastName"/>
		<form:button name="submit">Delete</form:button>
	</form:form>
</body>
</html>