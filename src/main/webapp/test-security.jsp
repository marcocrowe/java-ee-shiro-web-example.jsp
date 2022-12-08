<%@ page contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8" %>
<%@ taglib prefix="c"
		   uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<jsp:include page="head.jsp" />
</head>
<body>
<jsp:include page="header.jsp" />
<div class="container">
	<main>
		<h1>Java EE Shiro Web example</h1>
		<ul>
			<li><a href="<c:url value="/" />">Home</a></li>
			<li><a href="<c:url value="/sign-in" />">Sign-in</a></li>
			<li><a href="<c:url value="/sign-out" />">Sign-out</a></li>
			<li><a href="<c:url value="/test-security" />">Test Security</a></li>
			<li><a href="<c:url value="/user/account-details" />">User - Account Details</a></li>
		</ul>
		<p>A simple example of a Java EE web application using Shiro for authentication and authorization.</p>
	</main>
</div>
</body>
<jsp:include page="scripts.jsp" />
</html>
