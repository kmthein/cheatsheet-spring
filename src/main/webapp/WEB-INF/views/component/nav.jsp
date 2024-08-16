<%@ page import="com.spring.entity.User" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<nav class="navbar navbar-expand-lg navbar-light" id="mainNav"
	style="position: relative">
	<div class="container">
		<a class="navbar-brand" style="color: black" href="${pageContext.request.contextPath}/home">Cheatography</a>
		<button class="navbar-toggler" type="button" data-bs-toggle="collapse"
			data-bs-target="#navbarResponsive" aria-controls="navbarResponsive"
			aria-expanded="false" aria-label="Toggle navigation">
			Menu <i class="fas fa-bars"></i>
		</button>
		<div class="collapse navbar-collapse" id="navbarResponsive">
			<ul class="navbar-nav ms-auto py-4 py-lg-0">
				<li class="nav-item"><a style="color: black"
					class="nav-link px-lg-3 py-3 py-lg-4" href="/home">Home</a></li>
				<li class="nav-item"><a style="color: black"
					class="nav-link px-lg-3 py-3 py-lg-4" href="/cheatsheets">Cheatsheet</a></li>
				<%
				User user = (User) session.getAttribute("user");
				if (user != null) {
				%>
				<li class="nav-item"><a style="color: black"
					class="nav-link px-lg-3 py-3 py-lg-4" href="/add-cheatsheet">Create</a></li>
				<div class="collapse navbar-collapse" id="navbarNavDarkDropdown">
					<ul class="navbar-nav">
						<li class="nav-item dropdown"><a style="color: black"
							class="nav-link dropdown-toggle" href="#" role="button"
							data-bs-toggle="dropdown" aria-expanded="false"> <%=user.getName()%>
						</a>
							<ul class="dropdown-menu dropdown-menu-dark">
								<%
								if (user != null) {
									if (user.getRole().name().equals("ADMIN")) {
								%>
									<li><a class="dropdown-item" href="/admin">Go to Admin
											Panel</a></li>
								<%
								}
								}
								%>
								<li><a class="dropdown-item" href="/logout">Logout</a></li>
							</ul></li>
					</ul>
				</div>
				<%
				} else {
				%>
				<li class="nav-item"><a style="color: black"
					class="nav-link px-lg-3 py-3 py-lg-4" href="/login">Login</a></li>
				<li class="nav-item"><a style="color: black"
					class="nav-link px-lg-3 py-3 py-lg-4" href="/register">Register</a></li>
				<%
				}
				%>
			</ul>
		</div>
	</div>
</nav>
