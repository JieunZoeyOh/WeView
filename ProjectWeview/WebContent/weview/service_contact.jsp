<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@page import="mail.MyIdPass"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script src="js/jquery-3.5.0.js"></script>
<script>
$(function(){
   $('form').submit(function(){
      var pattern=/\w+@\w+[.]\w{3}/;
      var email = $("#sender").val();
      if(!pattern.test(email)){
         alert("이메일을 다시 확인해 주세요.");
         $("#sender").val("");
         $("#sender").focus();
         return false;
      }
   });
});
</script>
</head>
<body>
   <jsp:include page="header.jsp"></jsp:include>
    
    <!-- Blog Details Hero Begin -->
    <section class="blog-details-hero set-bg" data-setbg="img/blog/details/customerContact.png" style="width:1024px; margin:0 auto">
    </section>
    <!-- Blog Details Hero End -->

    <!-- Blog Details Section Begin -->
    <section class="blog-details spad" style="padding-bottom:20px">
        <div class="container">
            <div class="row">
                <div class="col-lg-4 col-md-5 order-md-1 order-2">
                    <div class="blog__sidebar">                   
                        <div class="blog__sidebar__item">
                           <div style="border-bottom:3px solid #269DED; width:184px; margin-bottom:20px;"><h4 style="margin-bottom:15px;">Customer Service</h4></div>
                            <ul class="hangel">
                                <li style="font-weight:800"><a href="WeviewContact.com">1 : 1 문의 게시판</a></li>
                                <li><a href="WeviewNotice.com">공지사항</a></li>
                            </ul>
                        </div>                        
                    </div>
                </div>
                <div class="col-lg-8 col-md-7 order-md-1 order-1">
                <br>
                   <div class="col-lg-12">
                       <div class="section-title related-blog-title">
                           <h2 style="font-family: 'Nanum Gothic'">궁금한 점이 있으신가요?</h2>
                       </div>
                   </div>
                    <div class="blog__details__text" style="margin-left:68px">
                        <img src="img/blog/details/contact.png" alt="Email us..." style="max-width: 88%;">                        
                    </div>
                </div>
            </div>
        </div>
    </section>
       <div class="contact-form spad">
           <div class="container">
               <div class="row">
                   <div class="col-lg-12">
                       <div class="contact__form__title">
                           <h2>Leave Message</h2>
                       </div>
                   </div>
               </div>
               <form action="weview/SendMail.jsp" method="post">
                   <div class="row hangel">
                      <input type="hidden" name="receiver" id="receiver" value="<%=MyIdPass.gmailid%>@gmail.com">
                       <div class="col-lg-6 col-md-6">
                           <input type="text" name="sender" id="sender" placeholder="이메일" required>
                       </div>
                       <div class="col-lg-6 col-md-6">
                           <input type="text" name="subject" id="subject" placeholder="메일 제목" required>
                       </div>                       
                       <div class="col-lg-12 text-center">
                           <textarea name="content" id="content" placeholder="어떤 것이 궁금하신가요? 내용을 입력하세요" required></textarea>
                           <button type="submit" class="site-btn">SEND MESSAGE</button>
                       </div>
                   </div>
               </form>
           </div>
       </div>
    <jsp:include page="footer.jsp"></jsp:include>
</body>
</html>