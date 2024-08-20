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
	.block {
		margin-bottom: 30px;
		padding: 20px;
		border: 1px solid #ddd;
		border-radius: 8px;
		background-color: #f9f9f9;
	}

	.block-title {
		font-weight: bold;
		margin-bottom: 10px;
		font-size: 18px;
		color: <%=hexColor%>;
	}

	.block-description {
		margin-bottom: 20px;
	}

	.block-table {
		width: 100%;
		border-collapse: collapse;
		margin-bottom: 10px;
	}

	.block-table th, .block-table td {
		border: 1px solid #ddd;
		padding: 8px;
		text-align: center;
	}

	.block-table th {
		background-color: #f7c0c5;
	}
	tr:nth-child(odd) {
		background-color: <%=rgbaColor%>;
	}
	block-table td:nth-child(odd) {
		background-color: <%=rgbaColor%>;
	}
</style>
<div class="container">
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
			<a href="/edit-cheatsheet/${cs.user.id}"><button class="btn btn-primary">Edit</button></a>
			<a href="/delete-cheatsheet/${cs.id}"><button class="btn" style="background: red">Delete</button></a>
		</div>
		<% }} %>
	</div>
	<div class="block-description">
		${cs.description}
	</div>
	<c:set var="lastBlockId" value="0" /> <!-- Initialize the lastBlockId variable -->

	<c:forEach var="block" items="${blocks}">
		<!-- Only render the block title and description if the block ID has changed -->
		<c:if test="${lastBlockId != block.blockId}">
				<div class="block-title">
						${block.title}
				</div>
			<c:set var="lastBlockId" value="${block.blockId}" /> <!-- Update lastBlockId -->
		</c:if>

		<!-- Table for the block's cells -->
		<table class="block-table">
			<tbody>
			<!-- Iterate over rows -->
			<c:forEach var="row" items="${block.rows}">
				<tr>
					<!-- Iterate over cells in the row -->
					<c:forEach var="cell" items="${row}">
						<td>${cell.content}</td>
					</c:forEach>
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</c:forEach>
</div>
<jsp:include page="layout/foot.jsp"></jsp:include>
