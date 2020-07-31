<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<meta charset="UTF-8">
    <title>WeView</title>
</head>

<body>
	<jsp:include page="header.jsp"></jsp:include>
	

	<!-- Breadcrumb Section End -->
	
    <!-- Blog Details Section Begin -->
    <section class="blog-details spad">
        <div class="container">
            <div class="row">
                <div class="col-lg-4 col-md-5 order-md-1 order-2">
                    <div class="blog__sidebar" style="padding-top:0px">                   
                        <div class="blog__sidebar__item">
                        	<div style="border-bottom:3px solid #269DED; width:184px; margin-bottom:20px;"><h4 style="margin-bottom:15px;">My Page</h4></div>
                            <ul class="hangel">
                                <li style="font-weight:800"><a href="mypage_modify.jsp">회원정보 수정</a></li>
                                <li><a href="mypage_board_written.jsp">내가 작성한 글 보기</a></li>
                                <li><a href="mypage_board_like.jsp">내가 찜한 글 보기</a></li>
                            </ul>
                        </div>                        
                    </div>
                </div>
                <div class="col-lg-8 col-md-7 order-md-1 order-1">
                <div class="an-container hangel">
				  <form>
				  	<div><label>아이디</label>
				  	<input type="text" id="id" name="id" value="weview" readOnly></div>				  
				  	<div><label for="password">비밀번호</label>
				  	<input type="password" id="password" name="password" value="password">
					<i class="fa fa-times" aria-hidden="true"></i></div>				  	
				  	<div><label>주민등록번호</label>
				  	<input type="text" id="jumin1" name="jumin1" value="123456" style="width:24%" readOnly> - 
				  	<input type="password" id="jumin2" name="jumin2" value="1234567" style="width:24%"  readOnly></div>
				  	<div>
				  	<input type="hidden" id="gender1" name="gender" value="남" style="width:7%" checked disabled>
				  	<input type="hidden" id="gender2" name="gender" value="여" style="width:7%" disabled>
				  	</div>
				  	<div><label for="phone">핸드폰 번호</label>
				  	<input type="text" id="phone" name="phone" value="010-1234-5678">
				  	<i class="fa fa-times" aria-hidden="true"></i>
				  	</div>		
				  	<div><label for="email">이메일</label>
				  	<input type="text" id="email" name="email" value="weview@weview">
				  	<i class="fa fa-times" aria-hidden="true"></i>
				  	</div>			  					  	
					<br><br><br>
					<div class="an-btn">
	              		<a href="#" class="btn btn-primary"><span>수정</span></a>
	            	</div>
					
				  </form>
				</div> 
			    </div>                                     
                </div>
            </div>
    </section>
    <!-- Blog Details Section End -->
	<!-- Product Details Section End -->
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>