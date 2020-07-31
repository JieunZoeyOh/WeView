<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>WEVIEW</title>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>


	<!-- Breadcrumb Section Begin -->
	<section class="breadcrumb-section set-bg"
		data-setbg="img/breadcrumb.jpg">
		<div class="container">
			<div class="row">
				<div class="col-lg-12 text-center">
					<div class="breadcrumb__text">
						<h2>글작성</h2>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!-- Breadcrumb Section End -->

	<!-- Checkout Section Begin -->
	<section class="checkout spad contact-form spad">
		<div class="container">
			<div class="row">
				<div class="col-lg-3 col-md-5">
					<div class="sidebar" style="min-height: 0vh; margin-top: 20px;">
						<div class="hero__categories">
							<div class="hero__categories__all">
								<span>카테고리</span>
							</div>
							<ul>
								<c:forEach var="ca" items="${category }">
									<li><a href="#">${ca.name }</a></li>
								</c:forEach>
							</ul>
						</div>
					</div>
					<div class="sidebar__item">
						<div class="hero__search">
							<div class="hero__search__form">
								<form action="./WeviewSearch.com">
									<input type="text" placeholder="Search..." name="search" style="height: 39px;">
									<button type="submit" class="btn btn-primary"
										style="height: 40px; border-radius: 0 .25rem .25rem 0">
										<i class="fa fa-search"></i>
									</button>
								</form>
							</div>
						</div>
					</div>
					<div class="sidebar__item sidebar__item__color--option">
						<br>
					</div>
					<div class="sidebar__item write">
						<a href="./WeviewWrite.com">
							<button type="button" class="btn btn-primary" style="width: 100%">글작성</button>
						</a>
					</div>
				</div>
				<div class="col-lg-9 col-md-5">
					<div class="checkout__form">
						<h4>글 작성</h4>
						<form action="./WeviewAddAction.com" method="post"
							enctype="multipart/form-data">
							<div class="row">
								<div class="col-lg-12 col-md-6">
									<div class="row">
										<div class="col-lg-2">
											<div class="checkout__input">
												<p>카테고리</p>
												<select id="category" name="category">
													<c:forEach var="ca" items="${category }">
														<option value="${ca.no }">${ca.name }</option>
													</c:forEach>
												</select><br>
											</div>
										</div>
										<div class="col-lg-10">
											<div class="checkout__input">
												<p>제목</p>
												<input type="text" id="title" name="title"> <input
													type="hidden" value="${email }" name="member_email">
											</div>
										</div>
										<div class="col-lg-12">
											<div class="checkout__input">
												<p>내용</p>
												<textarea placeholder="Input content" id="content"
													name="content"></textarea>
											</div>
										</div>
										<div class="col-lg-12">
											<div class="checkout__input">
												<p>제품</p>
												<div class="row">
													<div class="col-lg-5">
														<label for="productName1">제품명</label>&nbsp;<input
															type="text" name="productName1" id="productName1"
															style="width: 277px;">
													</div>
													<div>
														<label for="image1"><i class="fa fa-plus"
															aria-hidden="true"></i></label> <input type="file" name="image1"
															id="image1" style="display: none;"> <span
															id="filevalue1"></span> <i class="fa fa-times"
															style="color: red" aria-hidden="true"></i>
													</div>
												</div>
												<div class="row">
													<div class="col-lg-5">
														<label for="productName2">제품명</label>&nbsp;<input
															type="text" name="productName2" id="productName2"
															style="width: 277px;">
													</div>
													<div>
														<label for="image2"><i class="fa fa-plus"
															aria-hidden="true"></i></label> <input type="file" name="image2"
															id="image2" style="display: none;"> <span
															id="filevalue2"></span> <i class="fa fa-times"
															style="color: red" aria-hidden="true"></i>
													</div>
												</div>
												<div class="row">
													<div class="col-lg-5">
														<label for="productName3">제품명</label>&nbsp;<input
															type="text" name="productName3" id="productName3"
															style="width: 277px;">
													</div>
													<div>
														<label for="image3"><i class="fa fa-plus"
															aria-hidden="true"></i></label> <input type="file" name="image3"
															id="image3" style="display: none;"> <span
															id="filevalue3"></span> <i class="fa fa-times"
															style="color: red" aria-hidden="true"></i>
													</div>
												</div>
											</div>
										</div>
									</div>
									<div class="col-lg-12" style="text-align: right;">
										<button type="submit" class="btn btn-primary">완료</button>
										<button type="submit" class="btn btn-danger">취소</button>
									</div>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!-- Checkout Section End -->

	<!-- Modal 1-->
	<div class="portfolio-modal modal fade" id="portfolioModal1"
		tabindex="-1" role="dialog" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="container">
					<div class="row justify-content-center">
						<div class="col-lg-8">
							<div class="modal-body">
								<!-- Project Details Go Here-->
								<h2 class="text-uppercase">제품 추가</h2>
								<br>
								<table>
									<tr>
										<td><div class="checkout__input">
												<input type="text" value="imageName.jpg">
											</div></td>
									</tr>
									<tr>
										<td><label for="image"><i class="fa fa-plus"
												aria-hidden="true"></i></label><input type="file" name="image"
											id="image" style="display: none;"></td>
									</tr>
								</table>
								<br>
								<div style="text-align: center">
									<button class="btn btn-primary" data-dismiss="modal"
										type="button">Submit</button>
									<button class="btn btn-danger" data-dismiss="modal"
										type="button">Close</button>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script>
		$("#image1").change(function() {
			var inputfile1 = $(this).val().split('\\');
			$('#filevalue1').text(inputfile1[inputfile1.length - 1]);
		})
		$("#image2").change(function() {
			var inputfile2 = $(this).val().split('\\');
			$('#filevalue2').text(inputfile2[inputfile2.length - 1]);
		})
		$("#image3").change(function() {
			var inputfile3 = $(this).val().split('\\');
			$('#filevalue3').text(inputfile3[inputfile3.length - 1]);
		})
	</script>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>