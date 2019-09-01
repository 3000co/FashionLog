
/**팔로우 버튼(.followToggleBtn)이 로딩된 직후 팔로우 상태를 세팅
 * result 는, 
 * "following" : 팔로우 하고있음 
 * "follow" : 팔로우하고있지 않음
 * 결과에 따라 (.stat-following) 또는 (.stat-follow) 중의 하나가 추가됨.
 * 
 */

$.setFollowBtnStat = function(memberNo, followBtn) {
	$.ajax({
		type : 'post',
		url : '/checkFollow',
		dataType : 'text',
		data : {
			memberNo : memberNo
		},
		success : function(followStat) {
			console.log(followStat);
			if(followStat!='self'){
				followBtn.text(followStat);
				followBtn.addClass("stat-"+followStat);
				followBtn.css("display","inline");
			};
		},
		error : function(result) {
			alert("확인실패");
		}
	});
	}



	$(".followToggleBtn").ready(function() {
		$(".profileUnit").each(function(index) {
			var memNo = $("input[class=unitMemberNo]:eq(" + index + ")").val();
			var followBtn = $("button[class=followToggleBtn]:eq(" + index + ")");
			console.log(memNo);
			$.setFollowBtnStat(memNo,followBtn);
			
		});
	});
	