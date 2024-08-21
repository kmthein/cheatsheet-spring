<jsp:include page="/WEB-INF/views/layout/head.jsp"></jsp:include>
<jsp:include page="/WEB-INF/views/component/nav.jsp"></jsp:include>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page import="com.spring.entity.User" %>
<div class="container" style="max-width: 40%;">
    <form action="/change-password/${user.id}" method="post">
    <h2 style="margin-bottom: 10px;">My Profile</h2>
    <div style="display: flex; justify-content: space-between; align-items: center; gap: 30px; margin: 15px 0;">
        <div>
            <label for="oldPassword">Enter Old Password</label>
        </div>
        <input type="password" id="oldPassword" name="oldPassword">
    </div>
    <div style="display: flex; justify-content: space-between; align-items: center; gap: 30px; margin: 15px 0;">
        <div>
            <label for="newPassword">Enter New Password</label>
        </div>
        <input type="password" id="newPassword" name="newPassword">
    </div>
    <div style="display: flex; justify-content: space-between; align-items: center; gap: 30px; margin: 15px 0;">
        <div>
            <label for="confirmPassword">Confirm New Password</label>
        </div>
        <input type="password" id="confirmPassword" name="confirmPassword">
    </div>
        <p class="error-msg">${error}</p>
    <div style="display: flex; justify-content: end; gap: 10px; margin: 25px 0;">
        <button type="submit" class="btn btn-primary">Confirm</button>
    </div>
    </form>
</div>
<jsp:include page="layout/foot.jsp"></jsp:include>