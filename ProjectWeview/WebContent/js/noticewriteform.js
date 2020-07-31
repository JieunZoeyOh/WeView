$(function(){
	
	$("#upfile1").change(function(){
		var inputfile1 = $(this).val().split('\\');
		$('#filevalue1').text(inputfile1[inputfile1.length - 1]);
	});	
	
	$("#upfile2").change(function(){
		var inputfile2 = $(this).val().split('\\');
		$('#filevalue2').text(inputfile2[inputfile2.length - 1]);
	});	
	
	$("#upfile3").change(function(){
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
