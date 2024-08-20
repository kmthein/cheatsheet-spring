<jsp:include page="/WEB-INF/views/layout/head.jsp"></jsp:include>
<jsp:include page="/WEB-INF/views/component/nav.jsp"></jsp:include>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page import="com.spring.entity.User" %>
<div class="container" style="max-width: 40%;">
    <form:form action="/edit-user/${user.id}" modelAttribute="user" method="post">
        <h2 style="margin-bottom: 10px;">Edit Profile</h2>
        <form:hidden path="id"/>
        <div style="display: flex; justify-content: space-between; align-items: center; margin: 15px 0;">
            <div style="width: 25%;">
                <label for="name">Display name</label>
            </div>
            <form:input path="name" type="text" id="name"/>
        </div>
        <div style="display: flex; justify-content: space-between; align-items: center; gap: 30px; margin: 15px 0;">
            <div style="width: 25%;">
                <label for="email">Email</label>
            </div>
            <form:input path="email" disabled="true" type="text" id="email"/>
        </div>
        <div style="display: flex; justify-content: space-between; align-items: center; gap: 30px; margin: 15px 0;">
            <div style="width: 25%;">
                <label for="description">Description</label>
            </div>
            <form:input path="description" type="text" id="description"/>
        </div>
        <div style="display: flex; justify-content: space-between; align-items: center; gap: 30px; margin: 15px 0;">
            <div style="width: 25%;">
                <label for="website">Website</label>
            </div>
            <form:input path="website" type="text" id="website"/>
        </div>
        <div style="display: flex; justify-content: end; gap: 10px; margin: 25px 0;">
            <button type="submit" class="btn btn-primary">Update</button>
        </div>
    </form:form>
</div>
<jsp:include page="layout/foot.jsp"></jsp:include>