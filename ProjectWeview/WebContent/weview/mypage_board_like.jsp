<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta charset="UTF-8">
<style>
#p_active {
	background: #269ded;
	border-color: #269ded;
	color: #ffffff;
}
</style>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<!-- Blog Details Hero Begin -->
	<section class="blog-details-hero set-bg" data-setbg="img/mypage.jpg"
		style="width: 1024px; margin: 0 auto"></section>
	<!-- Blog Details Hero End -->
	<section class="blog-details spad">
		<div class="container">
			<div class="row">
				<div class="col-lg-4 col-md-5 order-md-1 order-2">
					<div class="blog__sidebar" style="padding-top: 0px">
						<div class="blog__sidebar__item">
							<div style="border-bottom: 3px solid #269DED; width: 184px; margin-bottom: 20px;">
								<h4 style="margin-bottom: 15px;">My Page</h4>
							</div>
							<ul class="hangel">
								<li><a href="mypage_modify.jsp">회원정보 수정</a></li>
								<li><a href="WeviewMypageWritten.com">내가 작성한 글 보기</a></li>
								<li style="font-weight: 800"><a href="WeviewMypageLove.com">내가 찜한 글 보기</a></li>
							</ul>
						</div>
					</div>
				</div>
				<div class="col-lg-8 col-md-8 order-md-1 order-1">
					<div class="table-responsive">

						<%-- 게시글이 있는 경우 --%>
						<c:if test="${listcount > 0 }">
							<table id="board_table" class="table" width="100%" cellspacing="0">
								<thead>
									<tr>
										<th>No</th>
										<th style="text-align: left; width: 50%;">Title</th>
										<th>Best</th>
										<th>&nbsp;</th>
									</tr>
								</thead>
								<tbody>
									<c:set var="no" value="${listcount-(page-1)*limit}" />
									<c:forEach var="b" items="${lovelist}">
										<tr>
											<td><c:out value="${no}" /> <c:set var="no" value="${no-1}" /></td>
											<td style="text-align: left; width: 50%;"><a href="#">${b.title}</a></td>
											<td>${b.best}</td>
											<th>
												<a href="WeviewDetail.com?no=${b.board_no}" class="bttn bttn-primary bttn-circle bttn-sm"
												data-toggle="tooltip" data-placement="top" title="글 보러 가기">
													<i style="color: white" class="fa fa-search-plus" aria-hidden="true"></i>
												</a> &nbsp; 
												<a href="WeviewMypageLoveDelete.com?no=${b.no}" class="bttn bttn-heart bttn-circle bttn-sm deletebttn"
												data-toggle="tooltip" data-placement="top" title="좋아요 취소">
													<i style="color: white" class="fa fa-heart" aria-hidden="true"></i>
												</a>
											</th>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</c:if>
						<!-- 레코드가 없으면 -->
						<c:if test="${listcount == 0 }">
							<div style="width: 250px; height: 150px; margin: 0 auto; margin-top: 100px;">
								<font size=5>찜한 글이 없습니다.</font>
							</div>
						</c:if>
					</div>
					<br>
					<%-- 게시글이 있는 경우 --%>
					<c:if test="${listcount > 0 }">
						<ul class="weview__pagination">
							<c:if test="${page <= 1 }">
								<a class="gray" href="#"><i class="fa fa-long-arrow-left"></i></a>
							</c:if>
							<c:if test="${page > 1 }">
								<a href="./WeviewMypageLove.com?page=${page-1}"><i class="fa fa-long-arrow-left"></i></a>
							</c:if>
							<c:forEach var="a" begin="${startpage}" end="${endpage}">
								<c:if test="${a == page }">
									<a class="gray" id="p_active">${a}</a>
								</c:if>
								<c:if test="${a != page }">
									<a href="./WeviewMypageLove.com?page=${a}">${a}</a>
								</c:if>
							</c:forEach>
							<c:if test="${page >= maxpage }">
								<a class="gray"><i class="fa fa-long-arrow-right"></i></a>
							</c:if>
							<c:if test="${page < maxpage }">
								<a href="./WeviewMypageLove.com?page=${page+1}"><i class="fa fa-long-arrow-right"></i></a>
							</c:if>
						</ul>
					</c:if>
				</div>
			</div>
		</div>
	</section>
	<!-- Blog Details Section End -->
	<jsp:include page="footer.jsp"></jsp:include>
	<script>
		$(function(){
		  $('[data-toggle="tooltip"]').tooltip();   
		  
		  $(".deletebttn").on('click',function(){
			  if(confirm('좋아요를 취소 하시겠습니까?')==true){
				  return true;
			  }else{
				  return false;
			  }
		  })		  
		});
	</script>
</body>
</html>