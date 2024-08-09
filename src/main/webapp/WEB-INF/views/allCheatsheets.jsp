<jsp:include page="/WEB-INF/views/layout/head.jsp"></jsp:include>
<jsp:include page="/WEB-INF/views/component/nav.jsp"></jsp:include>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<style></style>
<div class="container">
	<h2 class="center-title">All Cheatsheets</h2>
	<div class="row" style="gap: 20px;'">
		<c:forEach var="cs" items="${cheatsheets}">
			<div class="col-4"
				style="background-color: ${cs.color}; padding: 10px; color: white; width: calc(100% / 3 - 20px);">
				<a href="cheatsheets/${cs.id}">
					<div
						style="background: white; color: black; font-size: 15px; padding: 20px; margin: 10px 0; height: 200px; overflow: hidden;">
						${cs.content}</div>
				</a> <a href="cheatsheets/${cs.id}">
				<h5
						style="font-size: 20px; color: white;">${cs.name}</h5></a>
				<p style="font-size: 16px; margin: 5px 0;">${fn:substring(cs.description, 0, 100)}</p>
				<p style="font-size: 16px; margin: 5px 0;">${cs.user.name}</p>
				<span style="font-size: 15px; margin: 5px 0;'">${cs.updatedAtFormatted}</span>
			</div>
		</c:forEach>
	</div>
</div>
<jsp:include page="/WEB-INF/views/layout/foot.jsp"></jsp:include>