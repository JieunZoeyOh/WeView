$(function() {
	$("input:eq(0)").blur(function() {
		$("#name").empty();
		var pattern = /^[가-힣a-zA-Z]+$/;
		var name = $('input:eq(0)').val();
		if (!pattern.test(name)) {
			$('#name').css('color', 'red').val("이름 형식에 알맞게 써주세요.");
			checkid = false;
			return;
		} else {
			$('#name').css('color', 'blue').val(name);
		}

	});
	$("input:eq(0)").on('click', function() {
		if ($("#name").val() == "이름 형식에 알맞게 써주세요.")
			$("#name").val("").css('color', 'black');
		
	})

	$("input:eq(1)").blur(function() {
		$("#email").empty();
		var pattern = /\w+@\w+[.]\w{3}/;
		var email = $('input:eq(1)').val();
		if (!pattern.test(email)) {
			$('#email').css('color', 'red').val("이메일 형식에 알맞게 써주세요.");

			checkid = false;
			return;
		} else {
			$('#email').css('color', 'blue').val(email);
		}

	});
	
	$("input:eq(1)").on('click', function() {
		if ($("#email").val() == "이메일 형식에 알맞게 써주세요.")
			$("#email").val("").css('color','black');
		

	});
	
	
	



		
	
	
	     checkpass = false;
		$("input:eq(2)").blur(function() {
		var pattern =  /^[A-z0-9]{8,20}$/
		var pass = $('input:eq(2)').val();
		if (!pattern.test(pass)) {
			$(this).prop('type', 'text');
			$(this).css('color', 'red').val("비밀번호는 8글자 이상 적어주세요.");
			
			checkpass = false;
			return;
		
		} else {
			checkpass = true;
			$(this).css('color', 'blue').val(pass);
		}

	});
		$("input:eq(2)").focus(function() {
			if(checkpass==false)
			$(this).prop('type', 'password').css('color', 'black').val('');
			
			
		})
		
	
	/*
		$("input:eq(3)").focus(function() {
		$('input:eq(3)').prop('type', 'password');
		if(checkid==false) $('input:eq(3)').val('');
	});
	$("input:eq(3)").on('click', function() {
		if ($("#re_pass").val() == "비밀번호를 입력해주세요.")
			$("#re_pass").val("").css('color', 'black');

	})*/
	 checkre_pass = false;
	$("input:eq(3)").keyup(function() {
	  var pass=$("input:eq(2)").val();
	  if(pass == ''){
		  $(this).val('');
		  $("input:eq(2)").focus();
		  return;
	  }
	})
	
	  $("input:eq(3)").blur(function() {
	  var re_pass = $(this).val();
	  if(re_pass != $("input:eq(2)").val()){
		  $(this).prop('type', 'text');
			$(this).css('color', 'red').val("비밀번호가 일치하지 않습니다.");
			checkre_pass = false;
	  }else{
		  checkre_pass=true;
		  $(this).css('color', 'blue').val(re_pass);
	  }
	
	})
	$("input:eq(3)").focus(function() {
			if(checkre_pass==false)
			$(this).prop('type', 'password').css('color', 'black').val('');
			
			
		})
		
		
	    $("input:eq(4)").blur(function() {
		$("#jumin").empty();
		var pattern = /^(?:[0-9]{2}(?:0[1-9]|1[0-2])(?:0[1-9]|[1,2][0-9]|3[0,1]))-[1-4][0-9]{6}$/;
		var jumin = $('input:eq(4)').val();
		
		if (!pattern.test(jumin)) {
			$('#jumin').css('color', 'red').val(" '-' 포함하여 주민번호를 입력해주세요.");

			checkid = false;
			return;
		} else {
			$('#jumin').css('color', 'blue').val(jumin);
		}

	});
	
	$("input:eq(4)").on('click', function() {
		if ($("#jumin").val() == " '-' 포함하여 주민번호를 입력해주세요.")
			$("#jumin").val("").css('color', 'black');

	})
	
	
	
	
		$("input:eq(5)").blur(function() {
			$(this).empty();
		var pattern = /^\d{3}-\d{3,4}-\d{4}$/;
		var phone = $('input:eq(5)').val();
		
		if (!pattern.test(phone)) {
			$('#phone').css('color', 'red').val(" '-' 포함하여 휴대폰 번호를 입력해주세요.");

			checkid = false;
			return;
		} else {
			$('#phone').css('color', 'blue').val(phone);
		}

	});
	$("input:eq(5)").on('click', function() {
		if ($("#phone").val() == " '-' 포함하여 휴대폰 번호를 입력해주세요.")
			$("#phone").val("").css('color', 'black');

	})


	
});

	
