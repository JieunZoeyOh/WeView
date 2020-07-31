$(function(){
	$('.reportview').click(function(){
		var board_no = $(this).val();
		console.log(board_no);
	})

	function reasonView(board_no){
			console.log("클릭됨");
			var data = "board_no="+board_no;
			ajax(data);

	}
})



function ajax(sdata){
 
	console.log(sdata);
	output="";
	$.ajax({
		type : "POST",
		data : sdata,
		url : "AdminReportlist.com",
		dataType : "json",
		cache : false,
		success : function(data){
			
			if (data.listcount > 0){	//총 갯수가 0보다 큰 경우				
				output = '<table  class="table table-report-collapse" width="100%" cellspacing="0">';
				output += '<tr><td>Report No</td><td>Email</td><td>신고 사유</td><td>신고 일시</td></tr>'
				$(data.reportlist).each(
					function(index, item){
						output += '<tr>';
						
						output += "<td>"+item.no+"</td>";
						output += "<td>"+item.member_email+"</td>";
						output += "<td>"+item.reason_report+"</td>";
						output += '<td>' + item.reportdate+ '</td></tr>';
					})
				output += "</table>";
				console.log('data.listcount[0].board_no'+data.listcount[0].board_no);
				$('#report'+data.listcount[0].board_no).append(output); //table 완성				
				
			}//if(data.listcount) end
		}, //success end
		error : function() {
			console.log('에러');
		}
	})//ajax end
} //function ajax end
