<%@ page import="com.spring.model.User"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<nav class="navbar navbar-expand-lg navbar-light" id="mainNav">
	<div class="container">
		<a class="navbar-brand" href="${pageContext.request.contextPath}/home">Cheatography</a>
		<button class="navbar-toggler" type="button" data-bs-toggle="collapse"
			data-bs-target="#navbarResponsive" aria-controls="navbarResponsive"
			aria-expanded="false" aria-label="Toggle navigation">
			Menu <i class="fas fa-bars"></i>
		</button>
		<div class="collapse navbar-collapse" id="navbarResponsive">
			<ul class="navbar-nav ms-auto py-4 py-lg-0">
				<li class="nav-item"><a class="nav-link px-lg-3 py-3 py-lg-4"
					href="${pageContext.request.contextPath}/home">Home</a></li>
				<li class="nav-item"><a class="nav-link px-lg-3 py-3 py-lg-4"
					href="${pageContext.request.contextPath}/cheatsheets">Cheatsheet</a></li>
				<%
				User user = (User) session.getAttribute("user");
				if (user != null) {
				%>
				<li class="nav-item"><a class="nav-link px-lg-3 py-3 py-lg-4"
					href="${pageContext.request.contextPath}/add-cheatsheet">Create</a></li>
				<div class="collapse navbar-collapse" id="navbarNavDarkDropdown">
					<ul class="navbar-nav">
						<li class="nav-item dropdown"><a
							class="nav-link dropdown-toggle" href="#" role="button"
							data-bs-toggle="dropdown" aria-expanded="false"><%= user.getName() %> </a>
							<ul class="dropdown-menu dropdown-menu-dark">
							<%
								if (user != null) {
									if (user.getRole().name().equals("ADMIN")) {
								%>
									<li><a class="dropdown-item" href="${pageContext.request.contextPath}/admin">Go to Admin
											Panel</a></li>
								<%
								}
								}
								%>
								<li><a class="dropdown-item" href="${pageContext.request.contextPath}/logout">Logout</a></li>
							</ul></li>
					</ul>
				</div>
				<%
				} else {
				%>
				<li class="nav-item"><a class="nav-link px-lg-3 py-3 py-lg-4"
					href="${pageContext.request.contextPath}/login">Login</a></li>
				<li class="nav-item"><a class="nav-link px-lg-3 py-3 py-lg-4"
					href="${pageContext.request.contextPath}/register">Register</a></li>
				<%
				}
				%>
			</ul>
		</div>
	</div>
</nav>
<!-- Page Header-->
<header class="masthead"
	style="background-image: url('${pageContext.request.contextPath}/resources/assets/img/home-bg.jpg')">
	<div class="container position-relative px-4 px-lg-5">
		<div class="row gx-4 gx-lg-5 justify-content-center">
			<div class="col-md-10 col-lg-8 col-xl-7">
				<div class="site-heading">
					<h1>Cheatsheets</h1>
					<span class="subheading">Easily Create Your Cheatsheet Now</span>
				</div>
			</div>
		</div>
	</div>
</header>