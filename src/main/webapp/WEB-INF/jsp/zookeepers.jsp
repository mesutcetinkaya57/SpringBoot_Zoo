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
	<table>
		<thead>
			<tr style="font-weight: bold;" bgcolor="lightblue">
				<td>ID</td>
				<td>First Name</td>
				<td>Last Name</td>
			</tr>
		</thead>
		<c:forEach items="${zookeepers}" var="zookeeper" varStatus="status">
			<tr bgcolor="${status.index % 2 == 0 ? 'white' : 'lightgray'}">
				<td>${zookeeper.id}</td>
				<td>${zookeeper.firstName}</td>
				<td>${zookeeper.lastName}</td>
			</tr>
		</c:forEach>
	</table>
	
	<c:if test="${not empty message}">
		<div style="color:blue">
			${message}
		</div>
	</c:if>
</body>
</html>