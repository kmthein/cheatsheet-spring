<div class="modal fade" id="exampleModal" tabindex="-1"
	 aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<form class="row g-3" id="cheatsheetForm">
			<div class="modal-content">
				<div class="modal-header">
					<h1 class="modal-title fs-5" id="exampleModalLabel">Add New Block</h1>
					<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<div class="col-12">
						<label for="layout" class="form-label">Layout</label>
						<select id="layout" name="layout" class="form-select">
							<option value="1" selected>One column</option>
							<option value="2">Two columns</option>
							<option value="3">Three columns</option>
							<option value="4">Four columns</option>
						</select>
					</div>
					<div id="input-container"></div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
					<button type="button" id="submit-btn" class="btn btn-primary">Confirm</button>
				</div>
			</div>
		</form>
	</div>
</div>

<script>
	// Handle adding new input fields (if needed)
	const addRowBtn = document.getElementById('row-add');
	if (addRowBtn) {
		addRowBtn.addEventListener('click', (event) => {
			event.preventDefault();
			addNewInputFields();
		});
	}

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

	// Handle form submission and redirect with the selected layout value
	const submitBtn = document.getElementById('submit-btn');
	const form = document.getElementById('cheatsheetForm');

	submitBtn.addEventListener('click', (event) => {
		event.preventDefault();

		// Get the selected layout value
		const selectedLayout = document.getElementById('layout').value;

		// Redirect the user to the new URL with the selected layout value
		window.location.href =  '/add-block?cheatsheet='+ ${cs.id} + '&col=' + selectedLayout;

		console.log("Redirecting to", '/add-block?cheatsheet='+ ${cs.id} + '&col=' + selectedLayout);

		// Close the modal programmatically (if desired)
		// const modal = bootstrap.Modal.getInstance(document.getElementById('exampleModal'));
		// modal.hide();
	});
</script>
