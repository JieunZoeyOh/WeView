<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<jsp:include page="adminHeader.jsp" />
  <style>
  	table a{color:#858796;}  
  	table a:hover{text-decoration:none;color:#858796;}
  	table tr:hover{background:#eff0ff; cursor:pointer;}
  	table tr>th:nth-child(1),td:nth-child(1){text-align:center;}
  	table tr>th:nth-child(2),td:nth-child(2){padding-left:30px;}
  	table tr>th:nth-child(3),td:nth-child(3){text-align:center;}
  	.page-container {width:200px; margin:0 auto;}
  	.pagination{float:right; margin-top:30px;}
  	.gray{color:gray}
  </style>
   <title>공지사항 게시판</title>
   <script>
   $(function(){
	   	$('button').click(function(){
	   		location.href="AdminNoticeWrite.com";
	   	});
   });
   </script>
</head>
<body>
        <div class="container-fluid">
          <br>
          <div class="card shadow mb-4">
            <div class="card-header py-3">
              <h6 class="m-0 font-weight-bold text-primary" style="display:inline; line-height:38px">Notice</h6>
              <button type="button" class="btn btn-primary" style="float:right">글쓰기</button>
              
            </div>
            <%-- 게시글이 있는 경우 --%>
            <c:if test="${listcount > 0 }">
            <div class="card-body">
              <div class="table-responsive">
                <table class="table table-bordered" width="100%" cellspacing="0">
                  <thead>
                    <tr>
                      <th>No.</th><th>Title</th><th>Date</th>
                    </tr>
                  </thead>
                  <tbody>
                  	<c:set var="no" value="${listcount-(page-1)*limit}"/>	
					<c:forEach var="b" items="${noticelist}">	
                    <tr>
					  <td><%--번호 --%>
						<c:out value="${no}"/><%-- no 출력 --%>		
						<c:set var="no" value="${no-1}"/>	<%-- no=no-1; 의미--%>	
					  </td>                    
                      <td>
                      <a href="./AdminNoticeDetailAction.com?no=${b.no}">
						${b.title}
					  </a>
                      </td>
                      <td>
						${b.regidate}
					  </td>
                    </tr>
					</c:forEach>
                  </tbody>
                </table>
              </div>
              
              <div class="page-container">
						<ul class="pagination">
			 			  <c:if test="${page <= 1 }">						
							<li class="page-item">
							  <a class="page-link gray" href="#">&lt;&lt;</a></li>
						  </c:if>
						  <c:if test="${page > 1 }">		  
							<li class="page-item">
							  <a class="page-link" href="./AdminNoticeList.com?page=${page-1}">&lt;&lt;</a></li>
						  </c:if>
						  
						  <c:forEach var="a" begin="${startpage}" end="${endpage}">
							<c:if test="${a == page }">
								<li class="page-item">
								   <a class="page-link gray">${a}</a>
								</li>
							</c:if>
							<c:if test="${a != page }">
							    <li class="page-item">
								   <a href="./AdminNoticeList.com?page=${a}" 
								      class="page-link">${a}</a>
							    </li>	
							</c:if>
						  </c:forEach>
							<c:if test="${page >= maxpage }">
								<li class="page-item">
								<a class="page-link gray">&gt;&gt;</a> 
								</li>
							</c:if>
							<c:if test="${page < maxpage }">
							  <li class="page-item">
								<a href="./AdminNoticeList.com?page=${page+1}" 
								   class="page-link">&gt;&gt;</a>
							  </li>	
							</c:if>	
						</ul>
					</div>
                 </div>
            </c:if>
          
			<!-- 레코드가 없으면 -->
			<c:if test="${listcount == 0 }">
				<div style="width:250px; height:150px; margin:0 auto; margin-top:100px;">
					<font size=5>등록된 글이 없습니다.</font>
				</div>	
			</c:if>          
        </div>
        <!-- /.container-fluid -->
	</div><jsp:include page="adminFooter.jsp"></jsp:include>
</body>
</html>