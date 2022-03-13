<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Zoo Login Page</h1>
	<form action="login" method="post">
		Username:<input type="text" name="username"/><br/>
		Password:<input type="password" name="password"/><br/>
		Remember me:<input type="checkbox" name="remember-me"/><br/>
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
		<input type="submit" value="Login"/>
		<font color="red">
			<c:if test="${not empty param.loginFailed}">
				<c:out value="Login Failed, Incorrect Username or Password"/>
			</c:if>
		</font>
	</form>
</body>
</html>