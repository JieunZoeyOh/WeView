$(document).ready(function(){
	// 등록 버튼 클릭할 떄 이벤트 부분
	var check1 = 0;
	var check2 = 0;
	var check3 = 0;
	html1=""; html2=""; html3="";
	
	$("form").submit(function() {
		var html = "";
		// 한번도 변경하지 않으면 $('#filevalue').text()의 파일명을 전송합니다.
		//<input type="file" id="upfile" name="BOARD_FILE">
		//<span id="filevalue">${boarddata.BOARD_FILE}</span> <기존의 값은 여기에 있지 input에 있지 않다
		if (check1 == 0) {
	    	value1 = $('#filevalue1').text();
	    	html1 = "<input type='text' value='" + value1 + "' name='check1' class='no-border'>";
	    	$(this).append(html1);
		}
		if (check2 == 0) {
	    	value2 = $('#filevalue2').text();
	    	html2 = "<input type='text' value='" + value2 + "' name='check2' class='no-border'>";
	    	$(this).append(html2);
		}		
		if (check3 == 0) {
	    	value3 = $('#filevalue3').text();
	    	html3+= "<input type='text' value='" + value3 + "' name='check3' class='no-border'>";
	    	$(this).append(html3);
		}		
		
		
	}); //submit end	

	$("#upfile1").change(function(){
		check1++;
		var inputfile1 = $(this).val().split('\\');
		$('#filevalue1').text(inputfile1[inputfile1.length - 1]);
	});
	$("#upfile2").change(function(){
		check2++;
		var inputfile2 = $(this).val().split('\\');
		$('#filevalue2').text(inputfile2[inputfile2.length - 1]);
	});
	$("#upfile3").change(function(){
		check3++;
		var inputfile3 = $(this).val().split('\\');
		$('#filevalue3').text(inputfile3[inputfile3.length - 1]);
	});
	
	$('#remove1').click(function(){
		$('#filevalue1').text('');
	});
	$('#remove2').click(function(){
		$('#filevalue2').text('');
	});
	$('#remove3').click(function(){
		$('#filevalue3').text('');
	});
});