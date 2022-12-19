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
		<h1>Sign in</h1>
		<form method="post">
			<div class="form-outline mb-4">
				<input type="text"
					   id="username"
					   name="username"
					   class="form-control"
					   <c:if test="${not empty username}">value="<c:out value="${username}" />"</c:if>
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
			<button type="submit"
					class="btn btn-primary btn-block mb-4">Sign in
			</button>
			<c:if test="${not empty errorMessage}">
				<div class="text-danger mb-4"><c:out value="${errorMessage}" /></div>
			</c:if>
		</form>

		<c:if test="${not empty debugUsers}">
			<jsp:useBean id="debugUsers" scope="request" type="java.util.List<io.gitlab.markcrowe.User>" />
			<h2>Debugging</h2>
			<p>These are debug buttons to allow you to login as a user without having to enter a username and password.</p>
			<p>These buttons are only visible when the application is running in debug mode.</p>
			<div class="row row-cols-auto">
				<c:forEach var="user" items="${debugUsers}">
				<div class="col">
					<form method="post">
							<input type="hidden"
								name="username"
								value="<c:out value="${user.username}" />" />
						<input type="hidden"
							name="password"
							value="<c:out value="${user.plainTextPassword}" />" />
						<button type="submit" class="btn btn-danger"><c:out value="${user.name}" /> Login</button>
					</form>
				</div>
				</c:forEach>
			</div>
		</c:if>
	</main>
</div>
</body>
<jsp:include page="scripts.jsp" />
</html>
