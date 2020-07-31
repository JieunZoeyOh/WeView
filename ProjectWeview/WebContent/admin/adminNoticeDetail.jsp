<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:include page="adminHeader.jsp" />
  <style>
  	@import url(http://fonts.googleapis.com/earlyaccess/notosanskr.css);
	.an-form label{width:150px; text-align:left; padding-right:20px; 
		color:black; font-weight:bold}
	#text-label{line-height:800px; position:relative; bottom: 400px;}
	.an-form {width:100%; margin:0 auto; text-align:center; font-family:'Noto Sans KR', sans-serif; }
  	.an-form2 {width:100%; margin:0 auto; text-align:center; font-family:'Noto Sans KR', sans-serif; }
  	.addfile {width:150px; text-align:left; padding-right:20px; color:black; font-weight:bold}
  	.an-container .an-text{
  		display:inline-block; text-align:left;
  		width:60%;
  		padding: 12px 20px;
  		margin: 8px 0px;
  		box-sizing: border-box; border:none; border-bottom:1px solid #dadeea;
  	}
  	.an-form textarea{width:60%; height:800px;resize:none; border:1px solid #dadeea;
  					border-radius: 5px;}
  	
  	.an-btn{
  		text-align:right;
  		margin-top:10px;
  	}
  	a{color:black;} a:hover{color:black}
  	textarea:disabled{background:white}
  .modal-header, .close {
    background-color: #6a6e71;
    color: #fff !important;
    text-align: center;
    font-size: 30px;
    radius
  }
  .modal-content{border:none; border-radius:1rem;}
  .modal-header, .modal-body {
    padding: 40px 50px;
  }
  </style>
</head>
<body>
        <!-- Begin Page Content -->
        <div class="container-fluid">

          <!-- Page Heading -->
          <br>
          <!-- DataTales Example -->
          <div class="card shadow mb-4">
            <div class="card-header py-3">
              <h6 class="m-0 font-weight-bold text-primary" style="display:inline; line-height:38px">notice</h6>
             
            </div>
            <div class="card-body">
			    <div class="an-container">
				  	<input type="hidden" name="no" value="${noticedata.no}">
				  	<div class="an-form"><label>제목</label>
				  	<span class="an-text">${noticedata.title}</span></div>				  
				  	
				  	<br>
				  	
				  	<div class="an-form"style="height:800px"><label id="text-label">내용</label>
				  	<textarea style="padding:15px;" disabled>${noticedata.content}</textarea></div>
				  	
				  	<br>
				  	
				  	
				<c:if test="${!empty noticedata.image1}">
					<div class="an-form2">
				  		<label class="addfile">이미지 첨부1</label>
				  		<div style="width:60%; display:inline-block; text-align:left;">
				  		<span>
				  		<img src="https://img.icons8.com/material/26/000000/download--v1.png"/>
				  		<a href="AdminNoticeFileDown.com?filename=${noticedata.image1}">${noticedata.image1}</a>
				  		</span>
				  		</div>
				  	</div>
				</c:if>  	
				<c:if test="${!empty noticedata.image2}">
					<div class="an-form2">
				  		<label class="addfile">이미지 첨부2</label>
				  		<div style="width:60%; display:inline-block; text-align:left;">
				  		<span>
				  		<img src="https://img.icons8.com/material/26/000000/download--v1.png"/>
				  		<a href="AdminNoticeFileDown.com?filename=${noticedata.image2}">${noticedata.image2}</a>
				  		</span>
				  		</div>
				  	</div>
				</c:if>  
				<c:if test="${!empty noticedata.image3}">
					<div class="an-form2">
				  		<label class="addfile">이미지 첨부3</label>
				  		<div style="width:60%; display:inline-block; text-align:left;">
				  		<span>
				  		<img src="https://img.icons8.com/material/26/000000/download--v1.png"/>
				  		<a href="AdminNoticeFileDown.com?filename=${noticedata.image3}">${noticedata.image3}</a>
				  		</span>
				  		</div>
				  	</div>
				</c:if>  								  	
				  	<!-- <div class="an-form"><label for="upfile">파일첨부</label>
						<input type="file" id="upfile" name="ad_file">
						<span id="filevalue"></span>
					</div> --><!-- https://codepen.io/buppagi/pen/wKwPBP 이미지 파일 업로드 시 미리보기-->
					<br><br>
					<div class="an-btn">
						<a href="#">
	              			<button class="btn btn-danger" data-toggle="modal" data-target="#myModal">삭제</button>
	              		</a>
	              		<a href="AdminModifyView.com?no=${noticedata.no}">
	              			<button type="submit" class="btn btn-primary" style="margin-left:10px">수정</button>
	              		</a>
	              	
	              		<button class="btn btn-secondary" style="margin-left:10px" onClick="javascript:location.href='AdminNoticeList.com';">취소</button>
	            	</div>
				</div>   
				<div class="modal fade hangel" id="myModal" role="dialog">
			  	  <div class="modal-dialog">
			  		<div class="modal-content">
			        <div class="modal-header">
			        <h5>게시글을 삭제 하시겠습니까?</h5>
			          <button type="button" class="close" data-dismiss="modal">×</button>
			        </div>			  		
			  		  <!-- Modal body -->
			  			<div class="modal-body">
							<form name="deleteForm" action="AdminNoticeDeleteAction.com" method="post">
							  	<input type="hidden" name="no" value="${param.no}">
							  	<div class="form-group">

							  	</div>
								<button type="submit" class="btn btn-primary">네, 삭제하겠습니다.</button>
								<button type="button" class="btn btn-danger"
										data-dismiss="modal" style="float:right">아니요.</button>
							  </form>	
							</div>
			  			</div>
			  		</div>
			  	 </div>
          	</div>
        </div>
        <!-- /.container-fluid -->
	</div><jsp:include page="adminFooter.jsp"></jsp:include>
</body>
</html>