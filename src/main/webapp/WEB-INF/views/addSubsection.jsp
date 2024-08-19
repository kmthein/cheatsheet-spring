<jsp:include page="layout/adminHead.jsp"></jsp:include>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<div id="wrapper">
	<jsp:include page="component/admin/sideBar.jsp"></jsp:include>
	<div id="content-wrapper" class="d-flex flex-column">

		<div id="content">
			<jsp:include page="component/admin/navbar.jsp"></jsp:include>

			<div style="margin-left: 35px">
				<h5 style="margin-bottom: 15px">Add Subsection</h5>
				<form:form action="/admin/add-subsection" modelAttribute="subsectionDTO" method="post">
					<label for="name" class="d-block mb-1">Subsection Name</label> 
					<form:input path="name" required="true"
						type="text" name="name" id="name"
						style="border: 1px solid #bfbfbf; border-radius: 5px; width: 12%;" />
						<label
						for="name" class="d-block mb-1">Type</label> 
						<form:input path="type" type="text"
						name="type" id="type"
						style="border: 1px solid #bfbfbf; border-radius: 5px; width: 12%;" />
					<div>
						<label for="section" class="form-label"
							style="display: block; margin-bottom: 2px">Section</label> 
							<form:select path="sectionId" required="true"
							id="section" name="section" class="form-select"
							style="border: 1px solid #bfbfbf; border-radius: 5px; width: 12%;">
							<option disabled selected value="">Select a section</option>
							<c:forEach var="section" items="${sections}">
								<option
									value="${section.id}">${section.name}</option>
							</c:forEach>
					</form:select>
					</div> 
					<p class="error-msg">${error}</p>
					<button type="submit"
						style="background: #3158C9; color: #FFF; border: 0; font-size: 15px; padding: 5px 10px; border-radius: 5px;">Submit</button>
				</form:form>
			</div>
		</div>
	</div>
</div>
<jsp:include page="layout/adminFoot.jsp"></jsp:include>
