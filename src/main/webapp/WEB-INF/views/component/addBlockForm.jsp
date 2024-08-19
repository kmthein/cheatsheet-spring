<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="container mt-4">
    <form action="/add-block" method="post">
        <!-- Block ID (hidden) -->
        <input type="hidden" name="cheatsheet" value="${cheatsheet}" />
        <input type="hidden" name="layout" value="${col}" />
<%--        <input type="hidden" name="block_id" value="${blockId}" />--%>

        <!-- Title -->
        <div class="mb-3">
            <label for="title" class="form-label">Title</label>
            <input type="text" id="title" class="form-control" name="title" required>
        </div>

        <!-- Dynamic Column Headers -->
        <div class="row g-3 mb-3">
            <% int col = (int) request.getAttribute("col"); %>
            <c:forEach var="i" begin="1" end="${col}">
                <div class="${col == 2 ? 'col-md-6' : col == 3 ? 'col-md-4' : col == 4 ? 'col-md-3' : 'col-md-12'}">
                    <label for="column${i}" class="form-label">Column ${i}</label>
                </div>
            </c:forEach>
        </div>

        <!-- Dynamic Inputs -->
        <div id="input-container" class="mb-3">
            <%-- Initial Row --%>
            <div class="row g-3 mb-2">
                <c:forEach var="i" begin="1" end="${col}">
                    <div class="${col == 2 ? 'col-md-6' : col == 3 ? 'col-md-4' : col == 4 ? 'col-md-3' : 'col-md-12'}">
                        <input type="text" class="form-control" name="cell[1][${i}]">
                    </div>
                </c:forEach>
            </div>
        </div>

        <!-- Add New Row Button -->
        <div class="d-flex justify-content-end mb-3">
            <div id="row-add" class="d-flex align-items-center" style="cursor: pointer;">
                <i class="bi bi-plus-circle me-2"></i>
                <span class="form-check-label">Add New Row</span>
            </div>
        </div>

        <!-- Submit Button -->
        <div class="d-flex justify-content-end">
            <button type="submit" class="btn btn-primary">Submit</button>
        </div>
    </form>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"></script>
<script>
    document.addEventListener('DOMContentLoaded', (event) => {
        const col = <%= col %>; // Get the number of columns from server-side
        const addRowBtn = document.getElementById('row-add');
        const container = document.getElementById('input-container');
        let rowCount = 1; // Initial row count, should match with JSP initial row

        addRowBtn.addEventListener('click', (event) => {
            event.preventDefault();
            addNewInputFields(col, ++rowCount); // Increment rowCount and pass it to the function
        });

        function addNewInputFields(col, row) {
            const container = document.getElementById('input-container');

            // Create a new row container
            const newRow = document.createElement('div');
            newRow.className = 'row g-3 mb-2';

            // Loop through the number of columns and create inputs based on col value
            for (let i = 1; i <= col; i++) {
                const rowNum = row;
                const input = document.createElement('input');
                input.type = 'text';
                input.className = 'form-control';
                input.name = 'cell[' + rowNum + '][' + i + ']'; // Use row and col in input name

                const colDiv = document.createElement('div');
                colDiv.className = col == 2 ? 'col-md-6' : col == 3 ? 'col-md-4' : col == 4 ? 'col-md-3' : 'col-md-12';
                colDiv.appendChild(input);

                newRow.appendChild(colDiv);
            }

            // Append the new row to the container
            container.appendChild(newRow);
        }
    });
</script>

