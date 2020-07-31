<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>WEVIEW</title>

    <!-- Font Icon -->
    <link rel="stylesheet" href="fonts/material-icon/css/material-design-iconic-font.min.css">

    <!-- Main css -->
    <link rel="stylesheet" href="css/style3.css">
    
    <!-- js file -->
    <script src="js/jquery-3.5.0.js"></script>
    <script src="js/join.js"></script>
    
</head>
<body>

    <div class="main">

        <!-- Sign up form -->
        <section class="signup">
            <div class="container">
                <div class="signup-content">
                    <div class="signup-form">
                        <h2 class="form-title"><img src="img/big_logo.jpg" alt="sing up image" style="width:40%;"></h2>
                        <form method="POST" class="register-form" id="register-form" action="./joinProcess.com" enctype="multipart/form-data">
                            <div class="form-group">
                                <label for="name"><i class="zmdi zmdi-account material-icons-name"></i></label>
                                <input type="text" name="name" id="name" placeholder="Your Name" maxlength="12" required/>
                            </div>
                           <div class="form-group">
                                <label for="email"><i class="zmdi zmdi-email"></i></label>
                                <input type="email" name="email" id="email" placeholder="Your Email" maxlength="25" required/>
                            </div>
                            <div class="form-group">
                                <label for="pass"><i class="zmdi zmdi-lock"></i></label>
                                <input type="password" name="pwd" id="pass" placeholder="Password" maxlength="20" required/>
                            </div>
                            <div class="form-group">
                                <label for="re-pass"><i class="zmdi zmdi-lock-outline"></i></label>
                                <input type="password" name="re_pass" id="re_pass" placeholder="Repeat your password" maxlength="20" required/>
                            </div>
                            <div class="form-group">
                                <label for="jumin"><i class="zmdi zmdi-arrow-right"></i></label>
                                <input type="text" name="jumin" id="jumin" placeholder="Social Security Number" required/>
                            </div>
                            <div class="form-group">
                                <label for="phone"><i class="zmdi zmdi-phone"></i></label>
                                <input type="text" name="phone" id="phone" placeholder="Enter your phone number" required/>
                            </div>
                            <div class="form-group" style="display: none;">
                            	<label for="phone"><i class="zmdi zmdi-male-female"></i></label>
                            	<span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
								<input type="radio" name="gender" value="m" id="gender1" >male&nbsp;&nbsp;&nbsp;
								<input type="radio" name="gender" value="f" id="gender2">female
							</div>
							<div class="form-group">
								<label for="profile"><i class="zmdi zmdi-camera"></i></label>
								<input type="file" name="image" >
							</div>
                           
                          	
                            <div class="form-group form-button" style="text-align:center;">
                                <input type="submit" name="signup" id="signup" class="form-submit" value="회원가입" />
                                <input type="button" class="form-submit" style="background: #e46d6d" value="이전" onclick="javascript:history.back();"/>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </section>

  
    </div>

							                         
    <!-- JS -->
    <script src="vendor/jquery/jquery.min.js"></script>
    <script src="js/login.js"></script>
</body><!-- This templates was made by Colorlib (https://colorlib.com) -->
</html>