<%@ page contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8" %>
<%@ taglib prefix="c"
		   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro"
		   uri="http://shiro.apache.org/tags" %>
<div class="container">
	<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
		<div class="container-fluid">
			<a class="navbar-brand"
			   href="<c:url value="/" />">Java EE Shiro</a>
			<button class="navbar-toggler"
					type="button"
					data-bs-toggle="collapse"
					data-bs-target="#navbarColor01"
					aria-controls="navbarColor01"
					aria-expanded="false"
					aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse"
				 id="navbarColor01">
				<ul class="navbar-nav me-auto">
					<li class="nav-item active">
						<a class="nav-link"
						   href="<c:url value="/" />">Home
							<span class="visually-hidden">(current)</span>
						</a>
					</li>
					<shiro:guest>
						<li class="nav-item">
							<a class="nav-link"
							   href="<c:url value="/debug-sign-in" />">Debug Sign-in</a>
						</li>
						<li class="nav-item">
							<a class="nav-link"
							   href="<c:url value="/sign-in" />">Sign-in</a>
						</li>
						<li class="nav-item">
							<a class="nav-link"
							   href="<c:url value="/user/account-details" />">Test Security</a>
						</li>
					</shiro:guest>
					<shiro:authenticated>
						<li class="nav-item">
							<a class="nav-link"
							   href="<c:url value="/user/account-details" />">My Account</a>
						</li>
						<li class="nav-item">
							<a class="nav-link"
							   href="<c:url value="/sign-out" />">Sign Out</a>
						</li>
					</shiro:authenticated>
				</ul>
			</div>
		</div>
	</nav>
</div>