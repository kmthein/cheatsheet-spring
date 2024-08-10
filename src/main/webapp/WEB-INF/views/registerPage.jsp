<jsp:include page="layout/head.jsp"></jsp:include>
<jsp:include page="component/nav.jsp"></jsp:include>
<form action="register" method="post" class=" col-4 mx-auto row g-3">
	<h2 class="center-title">User Registration</h2>
	<div class="col-12">
		<label for="email" class="form-label">Email</label> <input
			type="text" class="form-control" id="email" name="email" value="${user.email}" required>
	</div>
	<div class="col-md-6">
		<label for="password" class="form-label">Password</label> <input
			type="password" class="form-control" id="password" name="password" value="${user.password}"  required>
	</div>
	<div class="col-md-6">
		<label for="name" class="form-label">Display Name</label> <input
			type="text" class="form-control" id="name" name="name" value="${user.name}"  required>
	</div>
	<div class="col-md-12">
		<label for="website" class="form-label">Website</label> <input
			type="text" class="form-control" id="website" name="website" value="${user.website}" >
	</div>
	<div class="col-md-12">
		<label for="description" class="form-label">Description</label> <input
			type="text" class="form-control" id="description" name="description" value="${user.description}" >
	</div>
	<p class="error-msg">${error}</p>
		<p style="margin-top: 2px; margin-bottom: 5px; text-align: center; font-size: 18px">Already have an account? <a href="${pageContext.request.contextPath}/login" style="text-decoration: underline">Log in</a></p>
	<div class="col-12 d-flex justify-content-center my-4">
		<button type="submit" class="btn btn-secondary">Submit</button>
	</div>
</form>
<jsp:include page="layout/foot.jsp"></jsp:include>