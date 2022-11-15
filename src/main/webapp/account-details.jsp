<%@ page contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8" %>
<%@ taglib prefix="c"
		   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro"
		   uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<jsp:include page="head.jsp" />
</head>
<body>
<jsp:include page="header.jsp" />
<div class="container">
	<main>
		<h1>Account Details</h1>
		<p>Welcome <shiro:principal/></p>
		<p>This is a Shiro secured page. It should only be visible to authenticated users.</p>
	</main>
</div>
</body>
<jsp:include page="scripts.jsp" />
</html>
