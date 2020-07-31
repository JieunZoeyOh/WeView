<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:include page="adminHeader.jsp" />
<script src="js/noticewriteform.js"></script>
  <style>
  	@import url(http://fonts.googleapis.com/earlyaccess/notosanskr.css);
	.an-form label{width:150px; text-align:left; padding-right:20px; color:black; font-weight:bold}
	.addfile {width:150px; text-align:left; padding-right:20px; color:black; font-weight:bold}
	#text-label{line-height:500px; position:relative; bottom: 250px;}
	.an-form {width:100%; margin:0 auto; text-align:center; font-family:'Noto Sans KR', sans-serif; }
	.an-form2 {width:100%; margin:0 auto; text-align:center; font-family:'Noto Sans KR', sans-serif; }
  	.an-form > input{
  		width:60%;
  		padding: 12px 20px;
  		margin: 8px 0px;
  		box-sizing: border-box; border:none; border-bottom:1px solid #dadeea;
  	}
  	.an-form textarea{width:60%; height:500px;resize:none; border:1px solid #dadeea;
  					border-radius: 5px;}
  	
  	.an-btn{
  		text-align:right;
  		margin-top:10px;
  	}
  	img{cursor:pointer;}
  	input:focus {outline:1px solid #269ded;}
  	textarea:focus {outline:1px solid #269ded;}
  	
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
              <h6 class="m-0 font-weight-bold text-primary" style="display:inline; line-height:38px">Notice</h6>
             	<button type="button" class="btn btn-secondary" style="float:right" onclick="history.back()">이전</button>
            </div>
            <div class="card-body">
			    <div class="an-container">
				  <form action="AdminNoticeAddAction.com" method="post" enctype="multipart/form-data" name="noticeform">
				  	<div class="an-form"><label for="title">제목</label>
				  	<input type="text" id="title" name="title" placeholder="Enter Title..." required></div>				  
				  	
				  	<br>
				  	
				  	<div class="an-form" style="height:500px"><label id="text-label" for="content" required>내용</label>
				  	<textarea id="content" name="content"></textarea></div>
				  	
				  	<br>
				  	<div class="an-form2">
				  		<label class="addfile" for="image1">이미지 첨부1</label>
				  		
				  		<div style="width:60%; display:inline-block; text-align:left;">		  		
				  		<label for="upfile1">
				  			<img src="https://img.icons8.com/ios/50/000000/image.png" alt="파일첨부" width="25px"/>
				  		</label>			
						<input type="file" id="upfile1" name="image1" style="display:none" accept="image/gif, image/jpeg, image/png"/>
						<span id="filevalue1" style="width:250px"></span>
						&nbsp;&nbsp;<img id="remove1" src="https://img.icons8.com/windows/32/000000/xbox-x.png" 
								alt="파일삭제" width="25px">
						</div>
					</div>
				  	<div class="an-form2">
				  		<label class="addfile" for="image2">이미지 첨부2</label>
				  		
				  		<div style="width:60%; display:inline-block; text-align:left;">		  		
				  		<label for="upfile2">
				  			<img src="https://img.icons8.com/ios/50/000000/image.png" alt="파일첨부" width="25px"/>
				  		</label>			
						<input type="file" id="upfile2" name="image2" style="display:none" accept="image/gif, image/jpeg, image/png"/>
						<span id="filevalue2" style="width:250px"></span>
						&nbsp;&nbsp;<img id="remove2" src="https://img.icons8.com/windows/32/000000/xbox-x.png" 
								alt="파일삭제" width="25px">
						</div>
					</div>					
				  	<div class="an-form2">
				  		<label class="addfile" for="image3">이미지 첨부3</label>
				  		
				  		<div style="width:60%; display:inline-block; text-align:left;">		  		
				  		<label for="upfile3">
				  			<img src="https://img.icons8.com/ios/50/000000/image.png" alt="파일첨부" width="25px"/>
				  		</label>			
						<input type="file" id="upfile3" name="image3" style="display:none" accept="image/gif, image/jpeg, image/png"/>
						<span id="filevalue3" style="width:250px"></span>
						&nbsp;&nbsp;<img id="remove3" src="https://img.icons8.com/windows/32/000000/xbox-x.png" 
								alt="파일삭제" width="25px">
						</div>
					</div>													
					
					<br><br>
					<div class="an-btn">
	              		<button type="reset" class="btn btn-danger">취소</button>
	              		<button type="submit" class="btn btn-primary" style="margin-left:10px">완료</button>
	            	</div>
					<!-- https://codepen.io/buppagi/pen/wKwPBP 이미지 파일 업로드 시 미리보기-->
				  </form>
				</div>           	            
          	</div>

        </div>
        <!-- /.container-fluid -->
	</div><jsp:include page="adminFooter.jsp"></jsp:include>
</body>
</html>