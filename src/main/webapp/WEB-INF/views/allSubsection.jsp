<jsp:include page="layout/adminHead.jsp"></jsp:include>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<!-- Page Wrapper -->
	<div id="wrapper">
		<jsp:include page="component/admin/sideBar.jsp"></jsp:include>
		<!-- Content Wrapper -->
		<div id="content-wrapper" class="d-flex flex-column">

			<!-- Main Content -->
			<div id="content">
				<jsp:include page="component/admin/navbar.jsp"></jsp:include>
				<!-- Begin Page Content -->
				<div class="container-fluid">

					<!-- Page Heading -->
					<div style="display: flex; justify-content: space-between; margin-bottom: 10px">
						<h5 style="margin-bottom: 15px">All Subsections</h5>
						<a href="add-subsection" style="background: #3158C9; color: #FFF; border: 0; font-size: 15px; padding: 5px 10px; border-radius: 5px;">Add Subsection</a>
					</div>
					<!-- DataTales Example -->
					<div class="card shadow mb-4">
						<div class="card-header py-3">
							<h6 class="m-0 font-weight-bold text-primary">Subsections</h6>
						</div>
						<div class="card-body">
							<div class="table-responsive">
								<table class="table table-bordered" id="dataTable" width="100%"
									cellspacing="0">
									<thead>
										<tr>
											<th>No</th>
											<th>Name</th>
											<th>Type</th>
											<th>Section</th>
											<th>Created At</th>
										</tr>
									</thead>
									<tbody>
									<c:forEach var="s" items="${subsections}" varStatus="status">
										<tr>
											<td>${status.index + 1}</td>
											<td>${s.name}</td>
											<td>${s.type}</td>
											<td>${s.section.name}</td>
											<td>${s.updatedAtFormatted}</td>
										</tr>
									</c:forEach>
									</tbody>
								</table>
							</div>
						</div>
					</div>

				</div>
				<!-- /.container-fluid -->

			</div>
			<!-- End of Main Content -->

			<!-- Footer -->
			<footer class="sticky-footer bg-white">
				<div class="container my-auto">
					<div class="copyright text-center my-auto">
						<span>Copyright &copy; Your Website 2020</span>
					</div>
				</div>
			</footer>
			<!-- End of Footer -->

		</div>
		<!-- End of Content Wrapper -->

	</div>
	<!-- End of Page Wrapper -->

	<!-- Scroll to Top Button-->
	<a class="scroll-to-top rounded" href="#page-top"> <i
		class="fas fa-angle-up"></i>
	</a>

	<!-- Logout Modal-->
	<div class="modal fade" id="logoutModal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5>
					<button class="close" type="button" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">×</span>
					</button>
				</div>
				<div class="modal-body">Select "Logout" below if you are ready
					to end your current session.</div>
				<div class="modal-footer">
					<button class="btn btn-secondary" type="button"
						data-dismiss="modal">Cancel</button>
					<a class="btn btn-primary" href="login.html">Logout</a>
				</div>
			</div>
		</div>
	</div>
<jsp:include page="layout/adminFoot.jsp"></jsp:include>