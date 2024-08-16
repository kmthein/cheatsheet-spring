<jsp:include page="layout/head.jsp"></jsp:include>
<jsp:include page="component/nav.jsp"></jsp:include>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<form:form action="/register" method="post" modelAttribute="user" class=" col-4 mx-auto row g-3">
	<h2 class="center-title">User Registration</h2>
	<div class="col-12">
		<label for="email" class="form-label">Email</label> <form:input path="email"
			type="text" class="form-control" id="email" name="email" value="${user.email}" required="true" />
	</div>
	<div class="col-md-6">
		<label for="password" class="form-label">Password</label> <form:password path="password" class="form-control" id="password" name="password" value="${user.password}" required="true" />
	</div>
	<div class="col-md-6">
		<label for="name" class="form-label">Display Name</label> <form:input path="name"
			type="text" class="form-control" id="name" name="name" value="${user.name}" required="true" />
	</div>
	<div class="col-md-12">
		<label for="website" class="form-label">Website</label> <form:input path="website"
			type="text" class="form-control" id="website" name="website" value="${user.website}" />
	</div>
	<div class="col-md-12">
		<label for="description" class="form-label">Description</label> <form:input path="description"
			type="text" class="form-control" id="description" name="description" value="${user.description}" />
	</div>
	<p class="error-msg">${message}</p>
		<p style="margin-top: 2px; margin-bottom: 5px; text-align: center; font-size: 18px">Already have an account? <a href="/login" style="text-decoration: underline">Log in</a></p>
	<div class="col-12 d-flex justify-content-center my-4">
		<button type="submit" class="btn btn-secondary">Submit</button>
	</div>
</form:form>
<jsp:include page="layout/foot.jsp"></jsp:include>