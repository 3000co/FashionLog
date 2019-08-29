/**
 * 
 */

$(".followToggleBtn").ready(function() {
		$("div[class=profileUnit]").each(function(index) {
			var member = $("input[class=unitMember]:eq(" + index + ")").val();
		
		$.ajax({
			type : "Member",
			url : "/checkFollow",
			contentType : 'application/json',
			dataType : 'json',
			data : member,
			success : function(result) {
				alert("전송성공" + result);
			},
			error : function(result) {
				alert("전송실패");
			}
		});
		});
	});