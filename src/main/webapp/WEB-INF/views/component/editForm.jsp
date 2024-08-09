<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script>
	function loadSections(sectionId) {
		var xhr = new XMLHttpRequest();
		xhr.open("GET", "getSubsections?sectionId=" + sectionId, true);
        xhr.onreadystatechange = function () {
            if (xhr.readyState == 4 && xhr.status == 200) {
                var subsections = JSON.parse(xhr.responseText);
                var subsectionSelect = document.getElementById("subsection");
                subsectionSelect.innerHTML = "";

                subsections.forEach(function(subsection) {
                    var option = document.createElement("option");
                    option.value = subsection.id;
                    option.text = subsection.name;
                    subsectionSelect.appendChild(option);
                });
            }
        };
        xhr.send();
	}
</script>
<form action="edit-cheatsheet?id=${cs.id}" method="post"
	class="container mx-auto row g-3">
	<h2 class="center-title">Edit Cheatsheet</h2>
	<div class="col-12">
		<label for="name" class="form-label">Name</label> <input type="text"
			class="form-control" required name="name" id="name"
			value="${cs.name}">
	</div>
	<div class="col-12">
		<label for="description" class="form-label">Description</label> <input
			type="text" class="form-control" name="description" id="description"
			value="${cs.description}">
	</div>
	<div class="col-md-6">
		<label for="color" class="form-label">Color</label> 
		<input
			type="color" class="form-control form-control-color"
			style="width: 100%" id="color" name="color" value="${cs.color}"
			title="Choose a color">
	</div>
	<div class="col-md-6">
		<label for="language" class="form-label">Language</label> <input
			type="text" id="language" name="language" class="form-control"
			value="${cs.language}">
	</div>
	<div class="col-md-6">
		<label for="style" class="form-label">Style</label> <select id="style"
			class="form-select" name="style">
			<option disabled selected>Select style</option>
			<option value="old" ${cs.style == "old" ? 'selected' : ''}>Original
				2011</option>
			<option value="new" ${cs.style == "new" ? 'selected' : ''}>Modern
				2020</option>
		</select>
	</div>
	<div class="col-md-6">
		<label for="type" class="form-label">Type</label> <select id="type"
			name="type" class="form-select" value="${cs.type}">
			<!-- 			<option disabled selected>Select type</option> -->
			<option value="cheatsheet"
				${cs.type == "cheatsheet" ? 'selected' : ''}>Cheatsheet</option>
			<option value="shortcuts" ${cs.type == "shortcuts" ? 'selected' : ''}>Keyboard
				Shortcuts</option>
		</select>
	</div>
	<div class="col-6">
		<label for="section" class="form-label">Section</label> <select
			id="section" name="section" onchange="loadSections(this.value)"
			class="form-select">
			<!-- 			<option disabled>Select section</option> -->
			<c:forEach var="section" items="${sections}">
				<option value="${section.id}"
					${section.id == cs.section.id ? 'selected' : ''}>${section.name}</option>
			</c:forEach>
		</select>
	</div>
	<div class="col-6">
		<label for="subsection" class="form-label">Subsection</label> <select
			id="subsection" name="subsection" required class="form-select">
			<c:forEach var="subsection" items="${subsections}">
				<option value="${subsection.id}"
					${subsection.id == cs.subsection.id ? 'selected' : ''}>${subsection.name}</option>
			</c:forEach>
		</select>
	</div>
	<div class="col-12">
		<label for="subsection" class="form-label">Layout</label> <select
			id="layout" name="layout" class="form-select">
			<option value="2" selected>two columns</option>
			<!-- 			<option value="3">three columns</option>
			<option value="4">four columns</option> -->
		</select>
	</div>
	<div class="col-12">
		<div class="d-flex justify-content-between">
			<label for="inputAddress" class="form-label">Content</label>
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
						type="text" id="title" class="form-control" name="title"
						value="${title}">
				</div>
				<div class="d-flex">
					<div class="col-md-6">
						<label for="column1" class="form-label">Column 1</label>
					</div>
					<div class="col-md-6">
						<label for="column2" class="form-label">Column 2</label>
					</div>
				</div>
				<div id="input-container">
				<c:set var="counter" value="1" />
					<c:forEach var="column" items="${columns}">
						<div class="d-flex gap-2 mb-4">
							<div class="col-md-6">
								<input type="text" class="form-control" name="column${counter}" value="${column[0]}">
							</div>
							<c:set var="counter" value="${counter + 1}" />
							<div class="col-md-6">
								<input type="text" class="form-control" name="column${counter}" value="${column[1]}">
							</div>
							<c:set var="counter" value="${counter + 1}" />
						</div>
					</c:forEach>
				</div>
				<div class="d-flex justify-content-end">
					<div id="row-add" style="margin-top: 2px;" onclick="addNewRow()">
						<i class="bi bi-plus-circle"></i> <label
							class="form-check-label ml-2" style="cursor: pointer">Add
							New Row</label>
					</div>
				</div>
				<button type="submit" class="btn btn-primary">Update
					Cheatsheet</button>
			</div>
		</div>
	</div>
</form>

<script>
function addNewRow() {
    var container = document.getElementById('input-container');
    var newRow = document.createElement('div');
    newRow.className = 'd-flex gap-2 mb-4';
    var columnNumber = container.childElementCount * 2 + 1;
    console.log(columnNumber);
    newRow.innerHTML = `
        <div class="col-md-6">
            <input type="text" class="form-control" name="column${columnNumber}">
        </div>
        <div class="col-md-6">
            <input type="text" class="form-control" name="column${columnNumber + 1}">
        </div>
    `;
    container.appendChild(newRow);
}
</script>
