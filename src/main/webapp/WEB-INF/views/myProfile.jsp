<jsp:include page="/WEB-INF/views/layout/head.jsp"></jsp:include>
<jsp:include page="/WEB-INF/views/component/nav.jsp"></jsp:include>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page import="com.spring.entity.User" %>
<div class="container" style="max-width: 40%;">
    <h2 style="margin-bottom: 10px;">My Profile</h2>
    <div style="display: flex; justify-content: space-between; align-items: center; margin: 15px 0;">
        <div style="width: 25%;">
            <label for="name">Display name</label>
        </div>
        <input type="text" id="name" disabled value="${user.name}">
    </div>
    <div style="display: flex; justify-content: space-between; align-items: center; gap: 30px; margin: 15px 0;">
        <div style="width: 25%;">
            <label for="email">Email</label>
        </div>
        <input type="text" id="email" disabled value="${user.email}">
    </div>
    <div style="display: flex; justify-content: space-between; align-items: center; gap: 30px; margin: 15px 0;">
        <div style="width: 25%;">
            <label for="password">Password</label>
        </div>
        <input type="password" id="password" disabled value="*******">
    </div>
    <div style="display: flex; justify-content: space-between; align-items: center; gap: 30px; margin: 15px 0;">
        <div style="width: 25%;">
            <label for="description">Description</label>
        </div>
        <input type="text" id="description" disabled value="${user.description}">
    </div>
    <div style="display: flex; justify-content: space-between; align-items: center; gap: 30px; margin: 15px 0;">
        <div style="width: 25%;">
            <label for="website">Website</label>
        </div>
        <input type="text" id="website" disabled value="${user.website}">
    </div>
    <div style="display: flex; justify-content: end; gap: 10px; margin: 25px 0;">
        <a href="/change-password/${user.id}"><button class="btn" style="border: 1px solid #007189; color: #007189;">Change Password</button></a>
        <a href="/edit-user/${user.id}"><button class="btn btn-primary">Edit</button></a>
    </div>
</div>
<jsp:include page="layout/foot.jsp"></jsp:include>