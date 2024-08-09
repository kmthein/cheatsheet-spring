<jsp:include page="layout/head.jsp"></jsp:include>
<jsp:include page="component/nav.jsp"></jsp:include>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<form:form action="login" method="post" modelAttribute="user" class=" col-4 mx-auto row g-3">
    <h2 class="center-title">Login User</h2>
    <div class="col-12">
        <label for="email" class="form-label">Email</label>
        <form:input
            type="text" id="email" class="form-control" name="email" path="email" />
    </div>
    <div class="col-md-12">
        <label for="password" class="form-label">Password</label>
        <form:input
            type="password" id="password" class="form-control" name="password" path="password" />
    </div>
    <p class="error-msg">${error}</p>
    <p style="padding-top: 15px; margin-bottom: 5px; text-align: center; font-size: 18px">Don't have an account? <a
            href="${pageContext.request.contextPath}/register" style="text-decoration: underline">Sign up</a></p>
    <div class="col-12 d-flex justify-content-center my-4">
        <button type="submit" class="btn btn-secondary">Login</button>
    </div>
</form:form>
<jsp:include page="layout/foot.jsp"></jsp:include>