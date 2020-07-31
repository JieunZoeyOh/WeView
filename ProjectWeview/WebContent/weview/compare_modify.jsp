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
						<c:set var="b" value="${list }" />
						<form action="./WeviewModify.com" method="post"
							enctype="multipart/form-data">
							<div class="row">
								<div class="col-lg-12 col-md-6">
									<div class="row">
										<div class="col-lg-2">
											<div class="checkout__input">
												<p>카테고리</p>
												<select id="category" name="category">
													<c:forEach var="ca" items="${category }">
														<c:choose>
															<c:when test="${b.category_no == ca.no }">
																<option value="${ca.no }" selected>${ca.name }</option>
															</c:when>
															<c:otherwise>
																<option value="${ca.no }">${ca.name }</option>
															</c:otherwise>
														</c:choose>
													</c:forEach>
												</select><br>
											</div>
										</div>
										<div class="col-lg-10">
											<div class="checkout__input">
												<p>제목</p>
												<input type="text" id="title" name="title"
													value="${b.title }"> <input type="hidden"
													value="${email }" name="member_email"> <input
													type="hidden" value="${b.no }" name="no">
											</div>
										</div>
										<div class="col-lg-12">
											<div class="checkout__input">
												<p>내용</p>
												<textarea placeholder="Input content" id="content"
													name="content">${b.content }</textarea>
											</div>
										</div>
										<div class="col-lg-12">
											<div class="checkout__input">
												<p>제품</p>
												<div class="row">
													<div class="col-lg-5">
														<label for="productName1">제품명</label>&nbsp;<input
															type="text" name="productName1" id="productName1"
															style="width: 277px;" value="${b.productName1 }">
													</div>
													<div>
														<label for="image1"><i class="fa fa-plus"
															aria-hidden="true"></i></label> <input type="file" name="image1"
															id="image1" style="display: none;"> <span
															id="filevalue1">${b.image1 }</span> <i
															class="fa fa-times" style="color: red" aria-hidden="true"
															id="remove1"></i>
													</div>
												</div>
												<div class="row">
													<div class="col-lg-5">
														<label for="productName2">제품명</label>&nbsp;<input
															type="text" name="productName2" id="productName2"
															style="width: 277px;" value="${b.productName2 }">
													</div>
													<div>
														<label for="image2"><i class="fa fa-plus"
															aria-hidden="true"></i></label> <input type="file" name="image2"
															id="image2" style="display: none;"> <span
															id="filevalue2">${b.image2 }</span> <i
															class="fa fa-times" style="color: red" aria-hidden="true"
															id="remove2"></i>
													</div>
												</div>
												<div class="row">
													<div class="col-lg-5">
														<label for="productName3">제품명</label>&nbsp;<input
															type="text" name="productName3" id="productName3"
															style="width: 277px;" value="${b.productName3 }">
													</div>
													<div>
														<label for="image3"><i class="fa fa-plus"
															aria-hidden="true"></i></label> <input type="file" name="image3"
															id="image3" style="display: none;"> <span
															id="filevalue3">${b.image3 }</span> <i
															class="fa fa-times" style="color: red" aria-hidden="true"
															id="remove3"></i>
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
												aria-hidden="true"></i></label><input type="file" name="image3"
											id="image3" style="display: none;"></td>
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
		var check1 = 0;
		var check2 = 0;
		var check3 = 0;
		$("form")
				.submit(
						function() {
							// 한번도 변경하지 않으면 $('#filevalue').text()의 파일명을 전송합니다.
							if (check1 == 0) {
								value1 = $('#filevalue1').text();
								html = "<input type='text' value='" + value1 + "' name='check1'>";
								$(this).append(html);
							}
							if (check2 == 0) {
								value2 = $('#filevalue2').text();
								html = "<input type='text' value='" + value2 + "' name='check2'>";
								$(this).append(html);
							}
							if (check3 == 0) {
								value3 = $('#filevalue3').text();
								html = "<input type='text' value='" + value3 + "' name='check3'>";
								$(this).append(html);
							}
						})
		show();
		function show() {
			//파일 이름이 있는 경우 remove 이미지를 보이게 하고 없는 경우는 보이지 않게 합니다.
			if ($('#filevalue1').text() == '') {
				$('#remove1').css('display', 'none');
			} else {
				$("#remove1").css({
					'display' : 'inline-block',
					'position' : 'relative',
					'top' : '-5px'
				})
			}
			if ($('#filevalue2').text() == '') {
				$('#remove2').css('display', 'none');
			} else {
				$("#remove2").css({
					'display' : 'inline-block',
					'position' : 'relative',
					'top' : '-5px'
				})
			}
			if ($('#filevalue3').text() == '') {
				$('#remove3').css('display', 'none');
			} else {
				$("#remove3").css({
					'display' : 'inline-block',
					'position' : 'relative',
					'top' : '-5px'
				})
			}
		}

		$("#image1").change(function() {
			check1++;
			var inputfile1 = $(this).val().split('\\');
			$('#filevalue1').text(inputfile1[inputfile1.length - 1]);
			show();
		})
		$("#remove1").click(function() {
			$("#filevalue1").text('');
			$(this).css('display', 'none');
		})

		$("#image2").change(function() {
			check2++;
			var inputfile2 = $(this).val().split('\\');
			$('#filevalue2').text(inputfile2[inputfile2.length - 1]);
			show();
		})
		$("#remove2").click(function() {
			$("#filevalue2").text('');
			$(this).css('display', 'none');
		})

		$("#image3").change(function() {
			check3++;
			var inputfile3 = $(this).val().split('\\');
			$('#filevalue3').text(inputfile3[inputfile3.length - 1]);
			show();
		})
		$("#remove3").click(function() {
			$("#filevalue3").text('');
			$(this).css('display', 'none');
		})
	</script>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>