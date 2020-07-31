<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>memeber</title>
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
              <h6 class="m-0 font-weight-bold text-primary" style="line-height:38px">MemberSesrch</h6>
            </div>
            <div class="card-body">
              <div class="table-responsive">
                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                  <thead>
     <tr>
                   
                    <th>이메일</th>
                      <th>비밀번호</th>
                      <th>주민번호</th>
                      <th>핸드폰번호</th>
                      <th>성별</th>
                      <th>이름</th>
                      <th>차단</th>
                      <th>삭제</th>
                    </tr>
       
				</thead>
				<tbody>
					
				<c:forEach var="m" items="${totallist}">
					
						<tr>
						
							<td>${m.email}</td>
							<td>${m.pwd}</td>
							<td>${m.jumin}</td>
							<td>${m.phone}</td>
							<td>${m.gender}</td>
							<td>${m.name}</td>
							<c:if test="${m.block==0}"><td>정상</td></c:if>
							<c:if test="${m.block==1}"><td>차단</td></c:if>
							<td><button class="btn btn-danger btn-circle btn-sm" style="cursor:pointer" data-toggle="modal"
                                data-target="#myModal"><i class="fas fa-trash" style="cursor:pointer"></i></button></td></tr>

						
						</c:forEach>
				</tbody>
                  
                  
                </table>
                 <!-- modal 시작 -->
                <div class="modal" id=myModal>
      <div class="modal-dialog">
         <div class="modal-content">

            <!-- modal body -->
            <div class="modal-body">
            
            <form name = "deleteForm" action = "AdminMemberDelete.com" method="post">
       
                    <input type = "hidden" name = "delete" >
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

        </div>
      
        <!-- /.container-fluid -->
	<jsp:include page="adminFooter.jsp"></jsp:include>
	<script>  
	$(".btn-circle").click(function(){//버튼을 클릭하면 tr안의 첫번쨰td의 값이 hidden의 값
	     email=$(this).parent().parent().find("td:eq(0)").text();
	     
	     $("input[type=hidden]").val(email);
		
		
	})
	
	
	
	</script> 
</body>
</html>