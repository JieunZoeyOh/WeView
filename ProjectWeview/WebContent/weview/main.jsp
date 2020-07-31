<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>

	<!-- Blog Details Hero Begin -->
	<section class="blog-details-hero set-bg"
		style="height: 50px; margin: 0 auto"></section>
	<!-- Blog Details Hero End -->

	<!-- Product Section Begin -->
	<section class="product spad" style="padding-top: 0px;">
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
									<li><a href="./WeviewMain.com?category=${ca.no}">${ca.name }</a></li>
								</c:forEach>
							</ul>
						</div>
					</div>
					<div class="sidebar__item">
						<div class="hero__search">
							<div class="hero__search__form">
								<form action="./WeviewSearch.com">
									<input type="text" placeholder="Search..." name="search">
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
				<div class="col-lg-9 col-md-7" style="padding-top: 20px;">
					<div class="product__discount">
						<div class="section-title product__discount__title">
							<h2>Weekly Bset</h2>
						</div>
						<div class="row">
							<div class="product__discount__slider owl-carousel">
								<c:forEach var="best" items="${bestlist }">
									<div class="col-lg-4">
										<div id="bestmodal${best.no }" class="carousel slide"
											data-ride="carousel">
											<div class="carousel-inner">
												<div class="carousel-item active">
													<div class="product__item">
														<div class="product__item__pic set-bg"
															data-setbg="boardupload/${best.image1 }">
															<ul class="product__item__pic__hover">
																<li><a data-toggle="modal"
																	href="#portfolio${best.no }"><i
																		class="fa fa-thumbs-o-up"></i></a></li>
																<li><a href="./WeviewDetail.com?no=${best.no }"><i
																		class="fa fa-file-text-o"></i></a></li>
																<li><a
																	href="./WeviewLove.com?bno=${best.no }&memail=${email }">
																		<i class="fa fa-heart"></i>
																</a></li>
															</ul>
														</div>
														<div class="product__item__text">
															<h6>
																<a href="#">${best.productName1 }</a>
															</h6>
															<h5>${best.title }</h5>
														</div>
													</div>
												</div>
												<c:if test="${!empty b.productName2}">
													<div class="carousel-item">
														<div class="product__item">
															<div class="product__item__pic set-bg"
																data-setbg="boardupload/${best.image2 }">
																<ul class="product__item__pic__hover">
																	<li><a data-toggle="modal"
																		href="#portfolio${best.no }"><i
																			class="fa fa-thumbs-o-up"></i></a></li>
																	<li><a href="./WeviewDetail.com?no=${best.no }"><i
																			class="fa fa-file-text-o"></i></a></li>
																	<li><a
																		href="./WeviewLove.com?bno=${best.no }&memail=${email }"><i
																			class="fa fa-heart"></i></a></li>
																</ul>
															</div>
															<div class="product__item__text">
																<h6>
																	<a href="#">${best.productName2 }</a>
																</h6>
																<h5>${best.title }</h5>
															</div>
														</div>
													</div>
												</c:if>
												<c:if test="${!empty b.productName3}">
													<div class="carousel-item">
														<div class="product__item">
															<div class="product__item__pic set-bg"
																data-setbg="boardupload/${best.image3 }">
																<ul class="product__item__pic__hover">
																	<li><a data-toggle="modal"
																		href="#portfolio${best.no }"><i
																			class="fa fa-thumbs-o-up"></i></a></li>
																	<li><a href="./WeviewDetail.com?no=${best.no }"><i
																			class="fa fa-file-text-o"></i></a></li>
																	<li><a
																		href="./WeviewLove.com?bno=${best.no }&memail=${email }"><i
																			class="fa fa-heart"></i></a></li>
																</ul>
															</div>
															<div class="product__item__text">
																<h6>
																	<a href="#">${best.productName3 }</a>
																</h6>
																<h5>${best.title }</h5>
															</div>
														</div>
													</div>
												</c:if>
											</div>
											<a class="carousel-control-prev" href="#bestmodal${best.no }"
												role="button" data-slide="prev"> <span
												class="carousel-control-prev-icon" aria-hidden="true"
												style="position: relative; bottom: 50px;"></span> <span
												class="sr-only">Previous</span>
											</a> <a class="carousel-control-next"
												href="#bestmodal${best.no }" role="button" data-slide="next">
												<span class="carousel-control-next-icon" aria-hidden="true"
												style="position: relative; bottom: 50px;"></span> <span
												class="sr-only">Next</span>
											</a>
										</div>
									</div>
								</c:forEach>
							</div>
						</div>
					</div>
					<div class="filter__item">
						<div class="row">
							<div class="col-lg-4 col-md-5"></div>
							<div class="col-lg-4 col-md-4">
								<div class="filter__found">
									<h6>
										<span>${listcount }</span> Products found
									</h6>
								</div>
							</div>
							<div class="col-lg-4 col-md-3"></div>
						</div>
					</div>
					<div class="row">
						<c:set var="m_num" value="1" />
						<c:forEach var="b" items="${list }">
							<div class="col-lg-4 col-md-6 col-sm-6">
								<div id="carouselExampleControls${b.no }" class="carousel slide"
									data-ride="carousel">
									<div class="carousel-inner">
										<div class="carousel-item active">
											<div class="product__item">
												<div class="product__item__pic set-bg"
													data-setbg="boardupload/${b.image1 }">
													<ul class="product__item__pic__hover">
														<li><a data-toggle="modal"
															href="#portfolioModal${b.no }"><i
																class="fa fa-thumbs-o-up"></i></a></li>
														<li><a href="./WeviewDetail.com?no=${b.no }"><i
																class="fa fa-file-text-o"></i></a></li>
														<li><a
															href="./WeviewLove.com?bno=${b.no }&memail=${email }">
																<i class="fa fa-heart"></i>
														</a></li>
													</ul>
												</div>
												<div class="product__item__text">
													<h6>
														<a href="#">${b.productName1 }</a>
													</h6>
													<h5>${b.title }</h5>
												</div>
											</div>
										</div>
										<c:if test="${!empty b.productName2}">
											<div class="carousel-item">
												<div class="product__item">
													<div class="product__item__pic set-bg"
														data-setbg="boardupload/${b.image2 }">
														<ul class="product__item__pic__hover">
															<li><a data-toggle="modal"
																href="#portfolioModal${b.no }"><i
																	class="fa fa-thumbs-o-up"></i></a></li>
															<li><a href="./WeviewDetail.com?no=${b.no }"><i
																	class="fa fa-file-text-o"></i></a></li>
															<li><a
																href="./WeviewLove.com?bno=${b.no }&memail=${email }"><i
																	class="fa fa-heart"></i></a></li>
														</ul>
													</div>
													<div class="product__item__text">
														<h6>
															<a href="#">${b.productName2 }</a>
														</h6>
														<h5>${b.title }</h5>
													</div>
												</div>
											</div>
										</c:if>
										<c:if test="${!empty b.productName3}">
											<div class="carousel-item">
												<div class="product__item">
													<div class="product__item__pic set-bg"
														data-setbg="boardupload/${b.image3 }">
														<ul class="product__item__pic__hover">
															<li><a data-toggle="modal"
																href="#portfolioModal${b.no }"><i
																	class="fa fa-thumbs-o-up"></i></a></li>
															<li><a href="./WeviewDetail.com?no=${b.no }"><i
																	class="fa fa-file-text-o"></i></a></li>
															<li><a
																href="./WeviewLove.com?bno=${b.no }&memail=${email }"><i
																	class="fa fa-heart"></i></a></li>
														</ul>
													</div>
													<div class="product__item__text">
														<h6>
															<a href="#">${b.productName3 }</a>
														</h6>
														<h5>${b.title }</h5>
													</div>
												</div>
											</div>
										</c:if>
									</div>
									<a class="carousel-control-prev"
										href="#carouselExampleControls${b.no }" role="button"
										data-slide="prev"> <span
										class="carousel-control-prev-icon" aria-hidden="true"
										style="position: relative; bottom: 50px;"></span> <span
										class="sr-only">Previous</span>
									</a> <a class="carousel-control-next"
										href="#carouselExampleControls${b.no }" role="button"
										data-slide="next"> <span
										class="carousel-control-next-icon" aria-hidden="true"
										style="position: relative; bottom: 50px;"></span> <span
										class="sr-only">Next</span>
									</a>
								</div>
							</div>

							<div style="display: none;">${m_num + 1 }</div>
						</c:forEach>
					</div>
					<div class="product__pagination">
						<c:if test="${page <= 1 }">
							<a class="gray" href="#"><i class="fa fa-long-arrow-left"></i></a>
						</c:if>
						<c:if test="${page > 1 }">
							<a href="./WeviewMain.com?page=${page-1}"><i
								class="fa fa-long-arrow-left"></i></a>
						</c:if>

						<c:forEach var="a" begin="${startpage}" end="${endpage}">
							<c:if test="${a == page }">
								<a class="gray">${a}</a>
							</c:if>
							<c:if test="${a != page }">
								<a href="./WeviewMain.com?page=${a}">${a}</a>
							</c:if>
						</c:forEach>
						<c:if test="${page >= maxpage }">
							<a class="gray"><i class="fa fa-long-arrow-right"></i></a>
						</c:if>
						<c:if test="${page < maxpage }">
							<a href="./WeviewMain.com?page=${page+1}"><i
								class="fa fa-long-arrow-right"></i></a>
						</c:if>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!-- Product Section End -->

	<!-- Modal 1-->
	<c:forEach var="m" items="${list }">
		<div class="portfolio-modal modal fade" id="portfolioModal${m.no }"
			tabindex="-1" role="dialog" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="container">
						<div class="justify-content-center">
							<div class="col-lg-12" style="text-align: center;">
								<div class="modal-body">
									<!-- Project Details Go Here-->
									<div class="row" style="height: 40px;">
										<h2 class="text-uppercase">${m.title }</h2>
										<br>
										<p class="item-intro text-muted"
											style="line-height: 60px; margin-bottom: 0px;">${m.regidate }</p>
									</div>
									<p class="item-intro text-muted">${m.member_email }
										번호:${m.no }</p>
									<div id="carouselExampleFade${m.no }"
										class="carousel slide carousel-fade" data-ride="carousel">
										<div class="carousel-inner">
											<div class="carousel-item active" id="image1">
												<div class="modal_img">
													<img src="boardupload/${m.image1 }">
												</div>
												<div class="carousel-caption d-none d-md-block">
													<h6 id="image1_name">${m.productName1 }</h6>
												</div>
											</div>
											<c:if test="${!empty b.productName2}">
												<div class="carousel-item" id="image2">
													<div class="modal_img">
														<img src="boardupload/${m.image2 }" id="image2">
													</div>
													<div class="carousel-caption d-none d-md-block">
														<h6 id="image2_name">${m.productName2 }</h6>
													</div>
												</div>
											</c:if>
											<c:if
												test="${!empty b.productName3}">
												<div class="carousel-item" id="image3">
													<div class="modal_img">
														<img src="boardupload/${m.image3 }">
													</div>
													<div class="carousel-caption d-none d-md-block" id="image3">
														<h6 id="image3_name">${m.productName3 }</h6>
													</div>
												</div>
											</c:if>
										</div>
										<a class="carousel-control-prev"
											href="#carouselExampleFade${m.no }" role="button"
											data-slide="prev"> <span
											class="carousel-control-prev-icon" aria-hidden="true"></span>
											<span class="sr-only">Previous</span>
										</a> <a class="carousel-control-next"
											href="#carouselExampleFade${m.no }" role="button"
											data-slide="next"> <span
											class="carousel-control-next-icon" aria-hidden="true"></span>
											<span class="sr-only">Next</span>
										</a>
									</div>
									<form action="./WeviewVote.com" method="post"
										id="voteform${m.no }">
										<input type="hidden" name="b_no" value="${m.no }"> <input
											type="hidden" name="m_email" value="${email }"> <select
											name="voteporduct" id="voteproduct${m.no }">
											<option value="_" selected>제품을 선택하세요</option>
											<option value="${m.productName1 }">${m.productName1 }</option>
											<option value="${m.productName2 }">${m.productName2 }</option>
											<option value="${m.productName3 }">${m.productName3 }</option>
										</select>
										<p>${m.content }</p>
										<div style="text-align: center">
											<button class="btn btn-primary" type="submit">Vote</button>
											<button class="btn btn-danger" data-dismiss="modal"
												type="button">Close</button>
										</div>
									</form>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</c:forEach>

	<c:forEach var="m" items="${bestlist }">
		<div class="portfolio-modal modal fade" id="portfolio${m.no }"
			tabindex="-1" role="dialog" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="container">
						<div class="justify-content-center">
							<div class="col-lg-12" style="text-align: center;">
								<div class="modal-body">
									<!-- Project Details Go Here-->
									<div class="row" style="height: 40px;">
										<h2 class="text-uppercase">${m.title }</h2>
										<br>
										<p class="item-intro text-muted"
											style="line-height: 60px; margin-bottom: 0px;">${m.regidate }</p>
									</div>
									<p class="item-intro text-muted">${m.member_email }
										번호:${m.no }</p>
									<div id="bestmodal${m.no }"
										class="carousel slide carousel-fade" data-ride="carousel">
										<div class="carousel-inner">
											<div class="carousel-item active" id="image1">
												<div class="modal_img">
													<img src="boardupload/${m.image1 }">
												</div>
												<div class="carousel-caption d-none d-md-block">
													<h6 id="image1_name">${m.productName1 }</h6>
												</div>
											</div>
											<c:if test="${!empty m.productName2}">
												<div class="carousel-item" id="image2">
													<div class="modal_img">
														<img src="boardupload/${m.image2 }" id="image2">
													</div>
													<div class="carousel-caption d-none d-md-block">
														<h6 id="image2_name">${m.productName2 }</h6>
													</div>
												</div>
											</c:if>
											<c:if test="${!empty m.productName2}">
												<div class="carousel-item" id="image3">
													<div class="modal_img">
														<img src="boardupload/${m.image3 }">
													</div>
													<div class="carousel-caption d-none d-md-block" id="image3">
														<h6 id="image3_name">${m.productName3 }</h6>
													</div>
												</div>
											</c:if>
										</div>
										<a class="carousel-control-prev" href="#bestmodal${m.no }"
											role="button" data-slide="prev"> <span
											class="carousel-control-prev-icon" aria-hidden="true"></span>
											<span class="sr-only">Previous</span>
										</a> <a class="carousel-control-next" href="#bestmodal${m.no }"
											role="button" data-slide="next"> <span
											class="carousel-control-next-icon" aria-hidden="true"></span>
											<span class="sr-only">Next</span>
										</a>
									</div>
									<form action="./WeviewVote.com" method="post"
										id="votefor${m.no }" onsubmit="check();">
										<input type="hidden" name="b_no" value="${m.no }"> <input
											type="hidden" name="m_email" value="${email }"> <select
											name="voteporduct">
											<option value="_" selected>제품을 선택하세요</option>
											<option value="${m.productName1 }">${m.productName1 }</option>
											<c:if
												test="${!empty m.productName2}">
												<option value="${m.productName2 }">${m.productName2 }</option>
											</c:if>
											<c:if
												test="${!empty m.productName2}">
												<option value="${m.productName3 }">${m.productName3 }</option>
											</c:if>
										</select>
										<p>${m.content }</p>
										<div style="text-align: center">
											<button class="btn btn-primary" type="submit">Vote</button>
											<button class="btn btn-danger" data-dismiss="modal"
												type="button">Close</button>
										</div>
									</form>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</c:forEach>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>