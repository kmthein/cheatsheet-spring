<div class="modal fade" id="exampleModal" tabindex="-1"
	aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h1 class="modal-title fs-5" id="exampleModalLabel">Add New
					Block Cheatsheet</h1>
				<button type="button" class="btn-close" data-bs-dismiss="modal"
					aria-label="Close"></button>
			</div>
			<div class="modal-body">
				<form class="row g-3" id="cheatsheetForm">
					<div class="col-12">
						<label for="inputAddress" class="form-label">Title</label> <input
							type="text" class="form-control" name="title">
					</div>
					<div class="col-md-6">
						<label for="column1" class="form-label" name="column1">Column
							1</label>
					</div>
					<div class="col-md-6">
						<label for="column2" class="form-label" name="column2">Column
							2</label>

					</div>
					<div id="input-container">
						<div class="d-flex gap-2 mb-4">
							<div class="col-md-6">
								<input type="text" class="form-control" id="column1">
							</div>
							<div class="col-md-6">
								<input type="text" class="form-control" id="column2">
							</div>
						</div>
					</div>
					<button id="row-add"
						style="border: 1px solid #606060; background: #606060; color: #FFF; font-size: 16px; margin-top: 2px; margin-bottom: 10px">Add
						New Row</button>
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary"
					data-bs-dismiss="modal">Close</button>
				<button type="button" id="submit-btn" class="btn btn-primary"
					data-bs-dismiss="modal">Save changes</button>
			</div>
		</div>
	</div>
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
        
        const form = document.getElementById('cheatsheetForm');
        
        const submitBtn = document.getElementById('submit-btn');
        submitBtn.addEventListener('click', (event) => {
        	event.preventDefault();
        	console.log("Submit");
        	const formData = new FormData(form);
        	const data = {};
            formData.forEach((value, key) => {
                if (!data[key]) {
                    data[key] = value;
                } else {
                    if (!Array.isArray(data[key])) {
                        data[key] = [data[key]];
                    }
                    data[key].push(value);
                }
            });
            console.log(data);
        })
</script>
