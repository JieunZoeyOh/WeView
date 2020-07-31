<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항</title>
<style>
	pre{white-space: -moz-pre-wrap; white-space: -pre-wrap; white-space: -o-pre-wrap; white-space: pre-wrap;  word-wrap: break-word; }
</style>
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
	                <table id="board_table_detail" class="table" width="100%" cellspacing="0">
	                    <tr>
	                      <td>No.</td>
	                      <td>${noticedata.no}</td>
	                    </tr>
	                    <tr>
	                      <td>제목</td>
	                      <td>${noticedata.title}</td>
	                    </tr>
	                    <tr>
	                      <td>작성일자</td>		 
	                      <td>${noticedata.regidate}</td>
	                    </tr>
	                    <tr>
	                      <td>내용</td>
	                      <td><pre>${noticedata.content}</pre><br>         
	                      </td>
	                    </tr>
	                    <c:if test="${!empty noticedata.image1}">
	                    <tr>
	                      <td colspan="2" style="text-align:center"><img src="noticeupload/${noticedata.image1}"/></td>
	                   
	                    </tr>
	                    </c:if>
	                    <c:if test="${!empty noticedata.image2}">
	                    <tr>
	                      <td colspan="2" style="text-align:center"><img src="noticeupload/${noticedata.image2}"/></td>
	                    </tr>
	                    </c:if>
	                    <c:if test="${!empty noticedata.image3}">
	                    <tr>
	                      <td colspan="2" style="text-align:center"><img src="noticeupload/${noticedata.image3}"/></td>
	                    </tr>
	                    </c:if>
	                    <c:if test="${!empty noticedata.image1}">
						<tr>
							<td>이미지 첨부1</td>
							<td>
							<img src="https://img.icons8.com/material/26/000000/download--v1.png"/>
				  			<a href="AdminNoticeFileDown.com?filename=${noticedata.image1}">${noticedata.image1}</a>
							</td>
						</tr>
						</c:if>
						<c:if test="${!empty noticedata.image2}">
						<tr>
							<td>이미지 첨부2</td>
							<td>
							<img src="https://img.icons8.com/material/26/000000/download--v1.png"/>
				  			<a href="AdminNoticeFileDown.com?filename=${noticedata.image2}">${noticedata.image2}</a>
							</td>
						</tr>
						</c:if>
						<c:if test="${!empty noticedata.image3}">
						<tr>
							<td>이미지 첨부3</td>
							<td>
							<img src="https://img.icons8.com/material/26/000000/download--v1.png"/>
				  			<a href="AdminNoticeFileDown.com?filename=${noticedata.image3}">${noticedata.image3}</a>
							</td>
						</tr>
						</c:if>						
	                </table>
              	  </div>
                </div>
                
            </div>
            <button class="site-btn" style="float:right"  onClick="history.go(-1)">목록</button>
        </div>
        
    </section>
    <!-- Blog Details Section End -->
    <jsp:include page="footer.jsp"></jsp:include>
</body>
</html>