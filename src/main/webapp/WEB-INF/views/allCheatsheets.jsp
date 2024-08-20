<jsp:include page="/WEB-INF/views/layout/head.jsp"></jsp:include>
<jsp:include page="/WEB-INF/views/component/nav.jsp"></jsp:include>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<style></style>
<div class="container">
	<h2 class="center-title">All Cheatsheets</h2>
	<div style="display: flex; gap: 50px;">
		<div class="row" style="width: 75%; gap: 20px">
			<div style="background: #EEEEEE; border-bottom: 3px solid #a0a0a0; padding-top: 15px; padding-bottom: 5px; max-height: 50px;">
				<h6>${fn:length(cheatsheets)} Cheat Sheet${fn:length(cheatsheets) > 1 ? "s" : ""}</h6>
			</div>
			<c:forEach var="cs" items="${cheatsheets}">
				<div class="col-4"
					 style="background-color: ${cs.color}; padding: 10px; color: white; width: calc(100% / 2 - 20px);">
<%--					<a href="/cheatsheets/${cs.id}">--%>
<%--						<div--%>
<%--								style="background: white; color: black; font-size: 15px; padding: 20px; margin: 10px 0; height: 200px; overflow: hidden;">--%>
<%--								${cs.content}</div>--%>
<%--					</a> --%>
					<a href="/cheatsheets/${cs.id}">
					<h5
							style="font-size: 20px; color: white;">${cs.name}</h5></a>
					<p style="font-size: 16px; margin: 5px 0;">${fn:substring(cs.description, 0, 100)}</p>
					<p style="font-size: 16px; margin: 5px 0;">${cs.name}</p>
					<span style="font-size: 15px; margin: 5px 0;'">${cs.formattedCreatedAt}</span>

				</div>
			</c:forEach>
		</div>
		<div style="width: 35%;">
			<div style="background: #EEEEEE; border-bottom: 3px solid #a0a0a0; padding-top: 15px; padding-bottom: 5px; padding-left: 10px; margin-bottom: 10px; max-height: 50px;">
				<h6>Filter By Section</h6>
			</div>
			<a href="/cheatsheets" style="display: block; padding-left: 10px;">All</a>
			<c:forEach var="section" items="${sections}">
				<a href="/cheatsheets/section/${section.id}" style="display: block; padding-left: 10px;">${section.name}</a>
			</c:forEach>
		</div>
	</div>
</div>
<jsp:include page="/WEB-INF/views/layout/foot.jsp"></jsp:include>