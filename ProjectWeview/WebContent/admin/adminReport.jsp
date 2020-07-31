<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="dto.ReportBoardDTO" %>
<%@ page import="java.util.*" %>
<html>
<head>
<jsp:include page="adminHeader.jsp" />
  <style>
  	table a{color:#858796;}  
  	table a:hover{text-decoration:none;color:#858796;}
  	table tr:hover{background:#eff0ff; cursor:pointer;}
  	table tr>th,td{text-align:center;}
  	.page-container {width:200px; margin:0 auto;}
  	.pagination{float:right;}
  	.table-report-collapse td:nth-child(1){width:23%; background:#f8f9f9}
  	.table-report-collapse td:nth-child(2){width:77%; text-align:left; padding-left:50px; background:#f8f9f9}
  	.table-report-h th:nth-child(1),td:nth-child(1){width:10%}
  	.table-report-h th:nth-child(2),td:nth-child(2){width:10%}
  	.table-report-h th:nth-child(3),td:nth-child(3){width:15%}
  	.table-report-h th:nth-child(4),td:nth-child(4){width:15%}
  	.table-report-h th:nth-child(5),td:nth-child(5){width:15%}
  	.table-m {width:100%; margin-bottom:0;}
  </style>
  <script>
  $(function(){
	  $('[data-toggle="tooltip"]').tooltip();
  })
  </script>	
</head>
<body>
        <div class="container-fluid">
          <br>
          <div class="card shadow mb-4">
            <div class="card-header py-3">
              <h6 class="m-0 font-weight-bold text-primary" style="display:inline; line-height:38px">Report</h6>          
            </div>
            <%-- 게시글이 있는 경우 --%>
            <c:if test="${listcount > 0 }">    
            <div class="card-body">
              <div class="table-responsive">
                <table class="table table-report-h" width="100%" cellspacing="0">
                  <thead>
                    <tr>
                      <th>No</th><th>Board No</th><th>Title</th><th>신고 수</th><th>&nbsp;</th>
                    </tr>
                  </thead>
                 </table>
                </div>
                <c:set var="no" value="${listcount-(page-1)*limit}"/>	
				<c:forEach var="b" items="${reportlist}">
							
	                <div class="table-responsive">
	                <c:if test="${b.report_count == b.max_count }">	              <!-- 신고글의 카운트 숫자와 원문글의 신고카운트 중 가장 큰 신고글(가장 최신 신고글)이 같은 경우 -->  
	                <table class="table table-report-h" width="100%" cellspacing="0">	   <!-- 신고글의 공통 컬럼 원문글 번호, 원문글 제목, 최신 신고글의 count를 출력 -->             
		                <tr style="height:75px; line-height:75px">
		                   <td>
							<c:out value="${no}"/><%-- no 출력 --%>	
							<c:set var="no" value="${no-1}"/>	<%-- no=no-1; 의미--%>	
						   </td>
						   <td>${b.board_no}</td>
						   <td>${b.title}</td>
						   <td>${b.max_count}</td>
   							
		                   <td style="height:75px">
		                      <a href="#report${b.board_no}" id="reportview${b.board_no}" class="btn btn-success btn-circle btn-sm" data-toggle="collapse" aria-expanded="false" >
		                    	<i class="fas fa-sort" style="cursor:pointer"></i>
		          	  		  </a>

		                  	  <a href="WeviewDetail.com?no=${b.board_no}" class="btn btn-info btn-circle btn-sm" style="cursor:pointer" data-toggle="tooltip" title="신고 글 보러가기">
		                    	<i class="fas fa-search" style="cursor:pointer"></i>
		                  	  </a>
		                  	  <a href="AdminReportOk.com?board_no=${b.board_no}" class="btn btn-warning btn-circle btn-sm" style="cursor:pointer" data-toggle="tooltip" title="반려하기">
		                   		<i class="fas fa-check-circle" style="cursor:pointer"></i>
		                  	  </a>
		                  	  <a href="AdminReportDelete.com?board_no=${b.board_no}" class="btn btn-danger btn-circle btn-sm" style="cursor:pointer" data-toggle="tooltip" title="해당 글 삭제">
		                   		<i class="fas fa-trash" style="cursor:pointer"></i>
		                  	  </a>
		                  	   <a href="AdminReportBlock.com?board_no=${b.board_no}" class="btn btn-secondary btn-circle btn-sm" style="cursor:pointer" data-toggle="tooltip" title="회원 차단">
		                   		<i class="fa fa-user-times" style="cursor:pointer" ></i>
		                  	  </a>
		                    </td>
		                   </tr>
	                	 </table>
	                	 <div>
	                	 <!-- 신고 사유가 출력되는 부분 max_count보다 작은 경우 신고 사유만 출력 된다. -->
	                	 <div class="collapse" id="report${b.board_no}">
		                 	<table  class="table table-report-collapse" width="100%" cellspacing="0">
	                    	<tr>
		                    	<th style="width:60%; text-align:left; padding-left:80px;">신고 사유</th>
		                    	<th style="width:40%; text-align:center;">신고 날짜</th>
	                    	</tr>
	                    	</table>   
		                 </div>
		                 </div>
	                	 </c:if>
	                	 
	                	 </div>
	                	 <div class="collapse" id="report${b.board_no}">
		                 	<table  class="table table-report-collapse" width="100%" cellspacing="0">
	                    	<tr>
		                    	<td style="width:60%; text-align:left; padding-left:80px;">${b.reason_report}</td>
		                    	<td style="width:40%; text-align:center;">${b.reportdate}</td>
	                    	</tr>
	                    	</table>   
		                 </div>
      		
	              
				  
			</c:forEach>
			<c:if test="${b.report_count == b.max_count }"></c:if>
          </div>		
          <br>
             <div class="page-container">
				<ul class="pagination">
	 			  <c:if test="${page <= 1 }">						
					<li class="page-item">
					  <a class="page-link gray" href="#">&lt;&lt;</a></li>
				  </c:if>
				  <c:if test="${page > 1 }">		  
					<li class="page-item">
					  <a class="page-link" href="./AdminReport.com?page=${page-1}">&lt;&lt;</a></li>
				  </c:if>
		  			<c:forEach var="a" begin="${startpage}" end="${endpage}">
						<c:if test="${a == page }">
							<li class="page-item">
							   <a class="page-link gray">${a}</a>
							</li>
						</c:if>
						<c:if test="${a != page }">
						    <li class="page-item">
							   <a href="./AdminReport.com?page=${a}" 
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
						<a href="./AdminReport.com?page=${page+1}" 
						   class="page-link">&gt;&gt;</a>
					  </li>	
					</c:if>	
				</ul>
		</div>
	</c:if>
			<!-- 레코드가 없으면-->
		<c:if test="${listcount == 0 }">
			<div style="width:250px; height:150px; margin:0 auto; margin-top:100px;">
				<font size=5>등록된 글이 없습니다.</font>
			</div>	
		</c:if> 					
       </div>
      
	</div><jsp:include page="adminFooter.jsp"></jsp:include>
</body>
</html>