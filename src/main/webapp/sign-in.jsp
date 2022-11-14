<%@ page contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8" %>
<%@ taglib prefix="c"
		   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fm"
		   uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<jsp:include page="head.jsp" />
</head>
<body>
<jsp:include page="header.jsp" />
<div class="container">
	<main>
		<h1>Sign in</h1>
		<form method="post">
			<div class="form-outline mb-4">
				<input type="text"
					   id="username"
					   name="username"
					   class="form-control"
					   <c:if test="${not empty username}">value="${fn.escapeXml(username)}"</c:if>
					   required="required" />
				<label class="form-label"
					   for="username">Username</label>
			</div>
			<div class="form-outline mb-4">
				<input type="password"
					   id="password"
					   name="password"
					   class="form-control"
					   required="required" />
				<label class="form-label"
					   for="password">Password</label>
			</div>
			<div class="row mb-4">
				<div class="col d-flex justify-content-center">
					<div class="form-check">
						<input class="form-check-input"
							   type="checkbox"
							   value=""
							   id="remember-me"
							   name="remember-me"
							   checked="checked" />
						<label class="form-check-label"
							   for="remember-me">Remember me</label>
					</div>
				</div>
				<div class="col">
					<a href="#!">Forgot password?</a>
				</div>
			</div>
			<button type="button"
					class="btn btn-primary btn-block mb-4">Sign in
			</button>
			<c:if test="${not empty errorMessage}">
				<div class="text-danger mb-4">${errorMessage}</div>
			</c:if>
		</form>
	</main>
</div>
</body>
<jsp:include page="scripts.jsp" />
</html>
