<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<script>
	function loadSections(sectionId) {
		fetch("getSubsections?sectionId=" + sectionId)
				.then(response => {
					if(!response.ok) {
						throw new Error("Network response was not ok " + response.statusText);
					}
					return response.json();
				})
				.then(subsections => {
					var subsectionSelect = document.getElementById("subsection");
					subsectionSelect.innerHTML = "";

					subsections.forEach(function (subsection) {
						var option = document.createElement("option");
						option.value = subsection.id;
						option.text = subsection.name;
						subsectionSelect.appendChild(option);
					});
				})
				.catch(error => {
					console.error("There was a problem with fetch operation:" + error);
				})
	}
</script>
<%--@elvariable id="cheatsheetDTO" type="com.spring.dto.CheatsheetDTO"--%>
<form:form action="add-cheatsheet" modelAttribute="cheatsheetDTO" method="post"
	class="container mx-auto row g-3">
	<h2 class="center-title">Create New Cheatsheet</h2>
	<div class="col-12">
		<label for="name" class="form-label">Name</label> 
		<form:input path="name" type="text"
			class="form-control" name="name" id="name" />
	</div>
	<div class="col-12">
		<label for="description" class="form-label">Description</label> 
		<form:input path="description"
			type="text" class="form-control" name="description" id="description" />
	</div>
	<div class="col-md-6">
		<label for="color" class="form-label">Color</label> 
		 <form:input path="color" type="color" class="form-control form-control-color" style="width: 100%" id="color" name="color" value="#CCCCCC" title="Choose a color" />
	</div>
	<div class="col-md-6">
		<label for="language" class="form-label">Language</label> <form:input path="language"
			type="text" id="language" name="language" class="form-control" />
	</div>
	<div class="col-md-6">
		<label for="style" class="form-label">Style</label> <form:select path="style" id="style"
			class="form-select" name="style">
			<option disabled selected>Select style</option>
			<option value="old">Original 2011</option>
			<option value="new">Modern 2020</option>
		</form:select>
	</div>
	<div class="col-md-6">
		<label for="type" class="form-label">Type</label> <form:select path="type" id="type"
			name="type" class="form-select">
			<option disabled selected>Select type</option>
			<option value="cheatsheet">Cheatsheet</option>
			<option value="shortcuts">Keyboard Shortcuts</option>
		</form:select>
	</div>
	<div class="col-6">
		<label for="section" class="form-label">Section</label> <form:select path="sectionId"
			id="section" name="section" onchange="loadSections(this.value)"
			class="form-select">
			<option disabled selected>Select section</option>
			<c:forEach var="section" items="${sections}">
				<option value="${section.id}">${section.name}</option>
			</c:forEach>
		</form:select>
	</div>
	<div class="col-6">
		<label for="subsection" class="form-label">Subsection</label> <form:select path="subsectionId"
			id="subsection" name="subsection" class="form-select">
			<option value="">Please select section first</option>
		</form:select>
	</div>
	<div class="col-12">
		<label for="layout" class="form-label">Layout</label> <form:select path="layout"
			id="layout" name="layout" class="form-select">
			<option value="2" selected>two columns</option>
			<!-- 			<option value="3">three columns</option>
			<option value="4">four columns</option> -->
		</form:select>
	</div>
	<div class="col-12">
		<div class="d-flex justify-content-between">
			<label class="form-label">Content</label>
<!-- 			<div id="block-add" style="margin-top: 2px;">
							<i class="bi bi-arrow-down-square"></i> <label
								class="form-check-label ml-2" for="gridCheck"
								style="cursor: pointer">Add New Block</label>
			</div> -->
		</div>
		<div class="col-12">
			<div class="row g-3" id="cheatsheetForm">
				<div class="col-12 mb-2">
					<label for="title" class="form-label">Title</label> <input
						type="text" id="title" class="form-control" name="title">
				</div>
				<div class="d-flex">
					<div class="col-md-6">
						<label for="column1" class="form-label" name="column1">Column
							1</label>
					</div>
					<div class="col-md-6">
						<label for="column2" class="form-label" name="column2">Column
							2</label>
					</div>
				</div>
				<div id="input-container">
					<div class="d-flex gap-2 mb-4">
						<div class="col-md-6">
							<input type="text" class="form-control" name="column1"
								id="column1">
						</div>
						<div class="col-md-6">
							<input type="text" class="form-control" name="column2"
								id="column2">
						</div>
					</div>
				</div>
				<div class="d-flex justify-content-end">
					<div id="row-add" style="margin-top: 2px;">
						<i class="bi bi-plus-circle"></i> <label
							class="form-check-label ml-2"
							style="cursor: pointer">Add New Row</label>
					</div>
				</div>
			</div>
</div>
<div class="col-12 d-flex justify-content-center my-4">
	<button type="submit" class="btn btn-secondary">Submit</button>
</div>
</form:form>
</div>
<script>
        const addRowBtn = document.getElementById('row-add');
        addRowBtn.addEventListener('click', (event) => {
            event.preventDefault();
            addNewInputFields();
        });
        function addNewInputFields() {
        	const container = document.getElementById("input-container");
        	var inputCount = container.getElementsByTagName("input").length;
            const newFieldSet = document.createElement('div');
    		newFieldSet.className = 'd-flex gap-2 mb-4';
    		const input1 = document.createElement("input");
    		input1.type = "text";
    		input1.name = "column" + (inputCount + 1);
    		input1.className = "form-control";
    		const input2 = document.createElement("input");
    		input2.type = "text";
    		input2.name = "column" + (inputCount + 2);
    		input2.className = "form-control";
    		const div1 = document.createElement("div");
            div1.className = "col-md-6";
            div1.appendChild(input1);

            const div2 = document.createElement("div");
            div2.className = "col-md-6";
            div2.appendChild(input2);

            newFieldSet.appendChild(div1);
            newFieldSet.appendChild(div2);

            container.appendChild(newFieldSet);
        }
</script>