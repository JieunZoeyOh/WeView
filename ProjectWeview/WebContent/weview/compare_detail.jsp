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
	<!-- Header Section End -->

	<!-- Breadcrumb Section Begin -->

	<!-- Breadcrumb Section End -->

	<!-- Product Details Section Begin -->
	<section class="product-details spad">
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
				<c:set var="b" value="${list }" />
				<div class="col-lg-9 col-md-5">
					<div class="product__details__text">
						<div class="row">
							<div class="col-lg-6">
								<div id="carouselExampleIndicators" class="carousel slide"
									data-ride="carousel">
									<ol class="carousel-indicators">
										<li data-target="#carouselExampleIndicators" data-slide-to="0"
											class="active"></li>
										<li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
											<li data-target="#carouselExampleIndicators"
												data-slide-to="2"></li>
									</ol>
									<div class="carousel-inner">
										<div class="carousel-item active" id="image1">
											<img src="boardupload/${b.image1 }"
												class="d-block w-100" alt="...">
											<div class="carousel-caption d-none d-md-block">
												<h5 id="image1_name">${b.productName1 }</h5>
											</div>
										</div>
										<c:if test="${!empty b.image2}">
											<div class="carousel-item" id="image2">
												<img src="boardupload/${b.image2 }"
													class="d-block w-100" alt="...">
												<div class="carousel-caption d-none d-md-block">
													<h5 id="image2_name">${b.productName2 }</h5>
												</div>
											</div>
										</c:if>
										<c:if test="${!empty b.image3}">
											<div class="carousel-item" id="image3">
												<img src="boardupload/${b.image3 }"
													class="d-block w-100" alt="...">
												<div class="carousel-caption d-none d-md-block">
													<h5 id="image3_name">${b.productName3 }</h5>
												</div>
											</div>
										</c:if>
									</div>
									<a class="carousel-control-prev"
										href="#carouselExampleIndicators" role="button"
										data-slide="prev"> <span
										class="carousel-control-prev-icon" aria-hidden="true"></span>
										<span class="sr-only">Previous</span>
									</a> <a class="carousel-control-next"
										href="#carouselExampleIndicators" role="button"
										data-slide="next"> <span
										class="carousel-control-next-icon" aria-hidden="true"></span>
										<span class="sr-only">Next</span>
									</a>
								</div>
								<div class="product__details__pic">
									<div class="product__details__pic__item"></div>
									<div class="product__details__pic__slider owl-carousel"></div>
								</div>
							</div>
							<div class="col-lg-6">
								<h3>${b.title }</h3>
								<span>${b.regidate}&nbsp;&nbsp;&nbsp;</span>
								<p style="color: black">조회수: ${b.views } &nbsp;&nbsp;&nbsp;
									작성자: ${b.member_email }</p>
								<p>${b.content }</p>
								<c:if test="${lovecheck==true }">
									<a href="./WeviewLove.com?bno=${b.no }&memail=${email }"
										class="btn btn-light"
										style="width: 89px; height: 51px; background: #ff6464"> <i
										class="fa fa-heart" aria-hidden="true"
										style="line-height: 40px; color: white"></i></a>
								</c:if>
								<c:if test="${lovecheck==false }">
									<a href="./WeviewLove.com?bno=${b.no }&memail=${email }"
										class="btn btn-light" style="width: 89px; height: 51px;">
										<i class="fa fa-heart" aria-hidden="true"
										style="line-height: 40px;"></i>
									</a>
								</c:if>
								<a href="#" data-toggle="modal" data-target="#exampleModal"
									class="btn btn-light" style="width: 89px; height: 51px;"> <i
									class="fa fa-bell" aria-hidden="true"
									style="line-height: 40px;"></i></a> <br> <br> <b>투표할
									제품</b>
								<form action="./WeviewVote.com" method="post" id="voteForm">
									<input type="hidden" name="b_no" value="${b.no }"> <input
										type="hidden" name="m_email" value="${email }"> 
									<select name="voteporduct" id="voteporduct">
										<option value="" selected>제품을 선택하세요</option>
										<option value="${b.productName1 }">${b.productName1 }</option>
										<c:if test="${!empty b.productName2}">
										<option value="${b.productName2 }">${b.productName2 }</option>
										</c:if>
										<c:if test="${!empty b.productName3}">
										<option value="${b.productName3 }">${b.productName3 }</option>
										</c:if>
									</select>
									<button type="submit" class="primary-btn"
										style="border: none; height: 40px; padding-bottom: 0px; padding-top: 0px;">투표</button>
								</form>
								<c:if test="${email eq b.member_email}">
									<p></p>
									<a href="./WeviewModifyView.com?no=${b.no }"
										class="primary-btn"
										style="background: #22a941; width: 89px; height: 51px;">수정</a>
									<a href="./WeviewDelete.com?no=${b.no }" class="primary-btn"
										style="background: #ea2929; width: 89px; height: 51px;">삭제</a>
								</c:if>
							</div>
						</div>
						<c:set var="v" value="${vote }" />
						<b>투표 현황</b>
						<hr style="color:#ebebeb">
						<table style="width:100%;">
							<tr><td>${b.productName1 }</td><td>${v.like1 }표</td><td>${v.like1/(v.like1+v.like2+v.like3)*100 }&#37;</td></tr>
							<c:if test="${!empty b.productName2}">
							<tr><td>${b.productName2 }</td><td>${v.like2 }표</td><td>${v.like2/(v.like1+v.like2+v.like3)*100 }&#37;</td></tr>
							</c:if>
							<c:if test="${!empty b.productName3}">
							<tr><td>${b.productName3 }</td><td>${v.like3 }표</td><td>${v.like3/(v.like1+v.like2+v.like3)*100 }&#37;</td></tr>
							</c:if>
						</table>
					</div>
				</div>
			</div>
			<div class="col-lg-12">
				<div class="product__details__tab">
					<ul class="nav nav-tabs" role="tablist">
						<li class="nav-item"><a class="nav-link active"
							data-toggle="tab" href="#tabs-1" role="tab" aria-selected="true">댓글
								<span>(${comm_total })</span>
						</a></li>
					</ul>
					<div class="tab-content">

						<div class="tab-pane active" id="tabs-1" role="tabpanel">

							<c:forEach var="c" items="${comm }">
								<div class="row">
									<div class="col-lg-12">
										<div class="blog__details__author">
											<div class="blog__details__author__pic">
 												<%-- <img src="memberupload/${comm.image }" alt=""> --%>
											</div>
											<div class="blog__details__author__text">
												<div class="row">
													<div style="width: 15px;"></div>
													<b style="color: black">${c.member_email }</b>&nbsp; <input
														type="hidden" id="comm_no" name="comm_no" value="${c.no }">
													<div size="2px;" style="line-height: 25px;">
														${c.regidate }&nbsp; <a href="#" data-toggle="modal"
															data-target="#exampleModal${c.no }"><i
															class="fa fa-bell" aria-hidden="true"></i></a>

													</div>
												</div>
												<span>${c.content }</span>
												<c:if test="${email == c.member_email }">
													&nbsp;&nbsp;&nbsp;
													<a href="./WeviewCommDelete.com?no=${c.no }&bno=${b.no}"
														style="color: black"> <i class="fa fa-times"></i></a>
													<br>
												</c:if>
											</div>
										</div>
										<hr style="color: #ebebeb">
									</div>
								</div>
								<div class="modal fade" id="exampleModal${c.no }" tabindex="-1"
									role="dialog" aria-labelledby="exampleModalLabel"
									aria-hidden="true">
									<div class="modal-dialog">
										<div class="modal-content">
											<div class="modal-header">
												<h5 class="modal-title" id="exampleModalLabel">신고하기</h5>
												<button type="button" class="close" data-dismiss="modal"
													aria-label="Close">
													<span aria-hidden="true">&times;</span>
												</button>
											</div>
											<form action="WeviewReportComm.com" method="post">
												<div class="modal-body">
													신고 사유&nbsp;&nbsp;<input type="text" name="reason"> <input
														type="hidden" name="commno" value="${c.no }">
														<input type="hidden" name="bno" value="${b.no }"> <input
														type="hidden" name="memail" value="${email }">
												</div>
												<div class="modal-footer">
													<button type="button" class="btn btn-secondary"
														data-dismiss="modal">취소</button>
													<button type="submit" class="btn btn-primary">신고</button>
												</div>
											</form>
										</div>
									</div>
								</div>
							</c:forEach>
							<div class="container">
								<div class="col-lg-12">
									<div class="hero__search">
										<div class="hero__search__form">
											<form action="WeviewCommAction.com" method="post">
												<div class="col-lg-12 text-center">
													<input type="text" id="content" name="content"
														placeholder="Your Comment"> <input type="hidden"
														value="${email }" name="comm_email" id="comm_email">
													<input type="hidden" id="board_no" name="board_no"
														value="${b.no }">
													<button type="submit" class="btn btn-primary"
														style="height: 40px; border-radius: 0 .25rem .25rem 0">
														SEND</button>
												</div>
											</form>
										</div>
									</div>
								</div>
							</div>
						</div>

					</div>
				</div>
			</div>
		</div>
	</section>

	<!-- Modal 1-->
	<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">신고하기</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<form action="WeviewReportBoard.com" method="post">
					<div class="modal-body">
						신고 사유<input type="text" name="reason"> <input
							type="hidden" name="bno" value="${b.no }"> <input
							type="hidden" name="cno" value="${c.no }"> <input
							type="hidden" name="memail" value="${email }">
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary"
							data-dismiss="modal">취소</button>
						<button type="submit" class="btn btn-primary">신고</button>
					</div>
				</form>
			</div>
		</div>
	</div>

	<!-- Product Details Section End -->
	<jsp:include page="footer.jsp"></jsp:include>
	<script>
		$("#voteForm").submit(function(){
			if($("#voteporduct").val().length==0){
				alert("제품을 선택하세요");
				return false;
			}
		})
	</script>
</body>
</html>