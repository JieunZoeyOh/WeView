<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="description" content="Ogani Template">
<meta name="keywords" content="Ogani, unica, creative, html">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>우리들의 리뷰, Weview</title>

<!-- Google Font -->
<link
	href="https://fonts.googleapis.com/css2?family=Cairo:wght@200;300;400;600;900&display=swap"
	rel="stylesheet">

<!-- Css Styles -->
<link rel="stylesheet" href="css/bootstrap.min.css" type="text/css">
<link rel="stylesheet" href="css/font-awesome.min.css" type="text/css">
<link rel="stylesheet" href="css/elegant-icons.css" type="text/css">
<link rel="stylesheet" href="css/nice-select.css" type="text/css">
<link rel="stylesheet" href="css/jquery-ui.min.css" type="text/css">
<link rel="stylesheet" href="css/owl.carousel.min.css" type="text/css">
<link rel="stylesheet" href="css/slicknav.min.css" type="text/css">
<link rel="stylesheet" href="css/style.css" type="text/css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<title>WEVIEW</title>
</head>

<body>
	<!-- Page Preloder -->
	<div id="preloder">
		<div class="loader"></div>
	</div>

	<!-- Humberger Begin -->
	<div class="humberger__menu__overlay"></div>
	<div class="humberger__menu__wrapper">
		<div class="humberger__menu__logo">
			<a href="./WeviewMain.com"><img src="img/logo.png" alt=""></a>
		</div>
		<nav class="humberger__menu__nav mobile-menu">
			<ul>
				<c:if test="${empty email }">
					<li class="active"><a href="./index.jsp">SIGN IN</a></li>
					<li><a href="./shop-grid.html">SIGN UP</a></li>
				</c:if>
				<c:if test="${!empty email }">
					<li><label for="mypage"> MY PAGE</label>
						<ul id="mypage" class="header__menu__dropdown">
							<li><a href="./shop-details.html">회원정보 수정</a></li>
							<li><a href="WeviewMypageWritten.com">내가 작성한 글</a></li>
							<li><a href="WeviewMypageLove.com">내가 찜한 글</a></li>
						</ul></li>
				<li><a href="logout.com">SIGN OUT</a></li>
				</c:if>
				<li><label for="service">SERVICE</label>
					<ul id="service" class="header__menu__dropdown">
						<li><a href="WeviewContact.com">1:1 문의</a></li>
						<li><a href="WeviewNotice.com">공지사항</a></li>
					</ul></li>
			</ul>
		</nav>
		<div id="mobile-menu-wrap"></div>
	</div>
	<!-- Humberger End -->

	<!-- Header Section Begin -->
	<header class="header">
		<div class="header__top">
			<div class="container">
				<div class="row">
					<div class="col-lg-6 col-md-6">
						<div class="header__top__left">
							<ul>
								<li><a href="./WeviewMain.com"><img src="img/s_logo.png"></a></li>
							</ul>
						</div>
					</div>
					<div class="col-lg-6">
						<div class="header__top__right">
							<c:if test="${empty email }">
								<div class="header__top__right__social">
									<a href="./index.jsp"><i class="fa fa-user"></i> SIGN IN</a>
								</div>
								<div class="header__top__right__social">
									<a href="join.com"> SIGN UP</a>
								</div>
							</c:if>
							<c:if test="${!empty email && email != 'admin'}">
								<div class="header__top__right__language">
									
									<i class="fa fa-user"></i>
									<div>&nbsp;MY PAGE</div>
									<span class="arrow_carrot-down"></span>
									<ul>
										<li><a href="mypage_modify.jsp">회원 수정</a></li>
										<li><a href="WeviewMypageWritten.com">작성 글</a></li>
										<li><a href="WeviewMypageLove.com">찜한 글</a></li>
									</ul>
								</div>
								<div class="header__top__right__social">
									<a href="logout.com"> SIGN OUT</a>
								</div>
							</c:if>
							<c:if test="${!empty email && email == 'admin'}">
								<div class="header__top__right__social">
									<a href="AdminBoard.com"> ADMIN PAGE </a>
								</div>
								<div class="header__top__right__social">
									<a href="logout.com"> SIGN OUT</a>
								</div>
							</c:if>							
							<div class="header__top__right__language" style="content: none;">
								<div>&nbsp;SERVICE</div>
								<span class="arrow_carrot-down"></span>
								<ul>
									<li><a href="WeviewContact.com">1:1 문의</a></li>
									<li><a href="WeviewNotice.com">공지사항</a></li>
								</ul>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="container">
			<div class="humberger__open">
				<i class="fa fa-bars"></i>
			</div>
		</div>
	</header>
</body>
</html>