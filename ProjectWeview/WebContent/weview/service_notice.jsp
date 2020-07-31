<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
   <style>
	   #board_table th:nth-child(2){ text-align:left; padding-left:30px; }
	   #board_table td:nth-child(2){ text-align:left; padding-left:30px; }
	   .gray{color:gray} 
	   #p_active{background: #269ded; border-color: #269ded; color: #ffffff;}
   </style>
<title>공지사항</title>
</head>
<body>
    <jsp:include page="header.jsp"></jsp:include>
    <section class="blog-details-hero set-bg" data-setbg="img/blog/details/announcement.png" style="margin:0 auto">
    </section>
    <section class="blog-details spad">
        <div class="container">
            <div class="row">
                <div class="col-lg-4 col-md-5 order-md-1 order-2">
                    <div class="blog__sidebar">                   
                        <div class="blog__sidebar__item">
                        	<div style="border-bottom:3px solid #269DED; width:184px; margin-bottom:20px;"><h4 style="margin-bottom:15px;">Customer Service</h4></div>
                            <ul class="hangel">
                                <li><a href="WeviewContact.com">1 : 1 문의 게시판</a></li>
                                <li style="font-weight:800"><a href="WeviewNotice.com">공지사항</a></li>
                            </ul>
                        </div>                        
                    </div>
                </div>
                <div class="col-lg-8 col-md-8 order-md-1 order-1">
                    <div class="table-responsive">
                    
	            <%-- 게시글이 있는 경우 --%>
	            <c:if test="${listcount > 0 }">                    
	                <table id="board_table" class="table table-bordered" width="100%" cellspacing="0">
	                  <thead>
	                    <tr>
	                      <th>No.</th>
	                      <th>Title</th>
	                      <th>Date</th>
	                    </tr>
	                  </thead>
	                  <tbody>
	                    <c:set var="no" value="${listcount-(page-1)*limit}"/>	
	                    <c:forEach var="b" items="${noticelist}">	
	                    <tr>
	                      <td><c:out value="${no}"/><c:set var="no" value="${no-1}"/></td>
	                      <td>
 							<a href="./WevieNoticeDetailAction.com?no=${b.no}">${b.title}</a>
						  </td>		
						  				 
	                      <td>${b.regidate}</td>
	                    </tr>
	                    </c:forEach>
	                  </tbody>
	                </table>

	                <br>
	                <!--  id="p_active" 현재 페이지 -->
	                <ul class="weview__pagination">
			 			  <c:if test="${page <= 1 }">						
							  <a class="gray" href="#"><i class="fa fa-long-arrow-left"></i></a>
						  </c:if>
						  <c:if test="${page > 1 }">		  
							  <a href="./WeviewNotice.com?page=${page-1}"><i class="fa fa-long-arrow-left"></i></a>
						  </c:if>
						  
						  <c:forEach var="a" begin="${startpage}" end="${endpage}">
							<c:if test="${a == page }">
								<a class="gray" id="p_active">${a}</a>
							</c:if>
							<c:if test="${a != page }">
								<a href="./WeviewNotice.com?page=${a}">${a}</a>
							</c:if>
						  </c:forEach>
							<c:if test="${page >= maxpage }">
								<a class="gray"><i class="fa fa-long-arrow-right"></i></a> 
							</c:if>
							<c:if test="${page < maxpage }">
								<a href="./WeviewNotice.com?page=${page+1}"><i class="fa fa-long-arrow-right"></i></a>
							</c:if>	
						</ul>
                    </c:if>
                    
					<!-- 레코드가 없으면 -->
					<c:if test="${listcount == 0 }">
						<div style="width:250px; height:150px; margin:0 auto; margin-top:100px;">
							<font size=5>등록된 글이 없습니다.</font>
						</div>	
					</c:if>                     
                    
                    </div>
                </div>        
            </div>
        </div>
    </section>
    <!-- Blog Details Section End -->
    <jsp:include page="footer.jsp"></jsp:include>
</body>
</html>