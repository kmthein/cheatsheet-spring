<jsp:include page="/WEB-INF/views/layout/head.jsp"></jsp:include>
<jsp:include page="/WEB-INF/views/component/nav.jsp"></jsp:include>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page import="com.spring.utils.ColorUtil" %>
<%@ page import="com.spring.entity.Cheatsheet" %>
<%@ page import="com.spring.entity.User" %>
<%
	// Access the Cheatsheet object using request scope
	Cheatsheet cs = (Cheatsheet) request.getAttribute("cs");
	String hexColor = (cs != null) ? cs.getColor() : "#000000"; // Default to black if not found
	float opacity = 0.2f; // Example opacity for lighter background
	String rgbaColor = ColorUtil.hexToRgba(hexColor, opacity);
%>
<style>
tr {
	border: 1px solid black;
}
th {
	background: <%=hexColor%>;
	color: white;
	padding: 10px;
}
td {
	border: 1px solid black;
	padding: 10px;
}
tr:nth-child(odd) {
	background-color: <%=rgbaColor%>;
}
</style>
<div class="container">
	<div class="col">
		<div style="display: flex; justify-content: space-between; align-items: center;">
			<h2>
				<c:out value="${cs.name}" />
			</h2>
			<%
				if(session.getAttribute("user") != null) {
				User user = (User) session.getAttribute("user");
				if (user.getId() == cs.getUser().getId()) {
			%>
			<div>
				<a href="/edit-cheatsheet/${cs.id}"><button class="btn btn-primary">Edit</button></a>
				<a href="/delete-cheatsheet/${cs.id}"><button class="btn" style="background: red">Delete</button></a>
			</div>
			<% }} %>
		</div>
		<p style="margin: 5px 0;">
			<c:out value="${cs.description}" />
		</p>
		<hr>
<%--		${cs.content}--%>
	</div>
</div>
<jsp:include page="layout/foot.jsp"></jsp:include>
