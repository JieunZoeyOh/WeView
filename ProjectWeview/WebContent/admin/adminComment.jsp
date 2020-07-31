<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>댓글관리</title>
<style>
table th,td{text-align:center}
</style>
</head>
<body>
	<jsp:include page="adminHeader.jsp"></jsp:include>
        <!-- Begin Page Content -->
        <div class="container-fluid">
          <!-- Page Heading -->
          <br>
          <!-- DataTales Example -->
          <div class="card shadow mb-4">
            <div class="card-header py-3" style="height:71px">
              <h6 class="m-0 font-weight-bold text-primary" style="line-height:38px">Comments</h6>
            </div>
            <div class="card-body">
              <div class="table-responsive">
                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                  <thead>
                    <tr>
                    <th>번호</th>
                    <th>원문 글 번호</th>
                    <th>원문 댓글 번호</th>
                      <th>작성자</th>
                      <th>신고사유</th>
                      <th>신고수</th>
                      <th>작성일</th>
                      
                      <th>삭제</th>
                    </tr>
                  </thead>
                 
                  <tbody>
                    
                   <c:forEach var="c" items="${totallist}">
					
						<tr>
						
							<td>${c.no}</td>
							<td><a href="WeviewDetail.com?no=${c.board_no}">${c.board_no}</a></td>
							<td>${c.comm_no}</td>
							<td>${c.member_email}</td>
							<td>${c.reason_report}</td>
							<td>${c.report_count}</td>
							<td>${c.reportdate}</td>
							
							<td><a href="AdminDeleteComm.com?no=${c.comm_no }" class="btn btn-danger btn-circle btn-sm" style="cursor:pointer"><i class="fas fa-trash" style="cursor:pointer"></i></a></td></tr>
						</c:forEach>
                   
            
                  </tbody>
                  
                </table>
                
                
                
                
                
                <!-- modal 시작 -->
                <div class="modal" id=myModal>
      <div class="modal-dialog">
         <div class="modal-content">

            <!-- modal body -->
            <div class="modal-body">
            
            <form name = "deleteForm" action = "AdminDeleteComm.com" method="post">
       
                    <input type = "hidden" name = "delete">
                   <div class="form-group">
                        <label for="pwd">비밀번호</label>
                        <input type="password" class="form-control" id="board_pass"
                           placeholder="Enter password" name="pwd">
                     </div>
                   <button type="submit" class="btn btn-primary">전송</button>
                    <button type="button" class=" btn btn-danger" data-dismiss="modal">취소</button>
               </form>
                          
            </div>
         </div>
      </div>
   </div><!-- modal 끝 -->
              </div>
          </div>
        </div>
        <!-- /.container-fluid -->
	</div><jsp:include page="adminFooter.jsp"></jsp:include>
	
	<script>  
	$(".btn-circle").click(function(){//버튼을 클릭하면 tr안의 첫번쨰td의 값이 hidden의 값
		baord_no=$(this).parent().parent().find("td:eq(1)").text();
	     
	     $("input[type=hidden]").val(baord_no);
		
		
	})
	
	
	
	</script>
     
</body>
</html>