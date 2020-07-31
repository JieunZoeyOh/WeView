<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

</head>
<body>
	<jsp:include page="adminHeader.jsp"></jsp:include>
	<!-- Begin Page Content -->
	<div class="container-fluid">
		<!-- Page Heading -->
		<br>
		<!-- DataTales Example -->
		<div class="card shadow mb-4">
			<div class="card-header py-3" style="height: 71px">
				<h6 class="m-0 font-weight-bold text-primary"
					style="line-height: 38px">Category</h6>
			</div>
			<div class="card-body">
				<div>

					<form action="AdminCategoryAddAction.com">

						<fieldset>
							<table>

								<tr>
									<td><label>카테고리</label></td>
									<td><input type="text" name="category" id="category"
										required></td>

									<td><input type="submit" value="추가"
										class="btn btn-primary"></td>
								</tr>

							</table>

						</fieldset>


					</form>

					<table class="table table-bordered" id="dataTable" width="100%"
						cellspacing="0">
						<thead>
							<tr>
								<th>번호</th>
								<th>카테고리</th>
								<th>삭제</th>
							</tr>
						</thead>

						<tbody>

							<c:forEach var="b" items="${totallist}">

								<tr>

									<td>${b.no}</td>
									<td>${b.name}</td>

									<td><button class="btn btn-danger btn-circle btn-sm" style="cursor:pointer"
											data-toggle="modal" data-target="#myModal"><i class="fas fa-trash" style="cursor:pointer"></i></button></td>
								</tr>
							</c:forEach>


						</tbody>

					</table>
					<!-- modal 시작 -->
					<div class="modal" id=myModal>
						<div class="modal-dialog">
							<div class="modal-content">

								<!-- modal body -->
								<div class="modal-body">

									<form name="deleteForm" action="AdminCategoryDeleteAction.com"
										method="post">

										<input type="hidden" name="delete">
										<div class="form-group">
											<label for="pwd">비밀번호</label> <input type="password"
												class="form-control" id="board_pass"
												placeholder="Enter password" name="pwd">
										</div>
										<button type="submit" class="btn btn-primary">전송</button>
										<button type="button" class=" btn btn-danger"
											data-dismiss="modal">취소</button>
									</form>

								</div>
							</div>
						</div>
					</div>
					<!-- modal 끝 -->





				</div>



			</div>
		</div>
	</div>
	<!-- /.container-fluid -->

	<script>
		$(".btn-circle").click(function() {//버튼을 클릭하면 tr안의 첫번쨰td의 값이 hidden의 값
			no = $(this).parent().parent().find("td:eq(0)").text();

			$("input[type=hidden]").val(no);

		})
	</script>


	</div><jsp:include page="adminFooter.jsp"></jsp:include>
</body>
</html>