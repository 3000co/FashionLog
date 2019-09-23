// 이 스크립트는 follow 버튼을 가지고 있는 profile unit이 include 될 때 같이 쓰여야 합니다.


/**
 * 팔로우 버튼(.followToggleBtn)이 로딩된 직후 팔로우 상태를 세팅 
 * result 는, "following" : 팔로우 하고있음
 * "follow" : 팔로우하고있지 않음 
 * 결과에 따라 (.stat-following) 또는 (.stat-follow) 중의 하나가 추가됨.
 * @param memberNo {int} 해당 버튼의 주인 회원번호
 */
$.fn.setFollowBtnStat = function(memberNo) {
	var element = this;
	console.log(memberNo)
	$.ajax({
		type : 'post',
		url : '/checkFollow',
		dataType : 'text',
		data : {
			memberNo : memberNo
		},
		success : function(followStat) {
			console.log(followStat);
			switch (followStat) {
			case ('follow'):
				if (element.hasClass("stat-following")) {
					element.removeClass("stat-following");
				}
				element.addClass("stat-follow");
				element.css("display", "inline");
				element.text(followStat);
				break;
			case ('following'):
				if (element.hasClass("stat-follow")) {
					element.removeClass("stat-follow");
				}
				element.addClass("stat-following");
				element.css("display", "inline");
				element.text(followStat);
				break;
			default:
				element.removeClass("stat-follow");
				element.removeClass("stat-following");
				element.addClass("stat-self");
			}
		},
		error : function(result) {
			console.log("연결 상태를 확인해주세요");
		}
	});
}

/**
 * follow 하는 함수. 
 * 팔로우 버튼 객체에서 사용.
 * 팔로우에 성공하면 버튼 세팅을 다시 한다.
 * @param memberNo {int} 해당 버튼의 주인 회원번호
 */
$.fn.doFollow = function(memberNo) {
	var element = this;
	$.ajax({
		type : 'post',
		url : '/doFollow',
		data : {
			memberNo : memberNo
		},
		success : function(result) {
			element.setFollowBtnStat(memberNo);
		},
		error : function(result) {
			console.log("연결 상태를 확인해주세요");
		}
	});
}

/**
 * unfollow 하는 함수. 
 * 팔로우 버튼 객체에서 사용.
 * 언팔로우에 성공하면 버튼 세팅을 다시 한다.
 * @param memberNo {int} 해당 버튼의 주인 회원번호
 */
$.fn.unFollow = function(memberNo) {
	var element = this;
	$.ajax({
		type : 'post',
		url : '/unFollow',
		data : {
			memberNo : memberNo
		},
		success : function(result) {
			element.setFollowBtnStat(memberNo);
		},
		error : function(result) {
			console.log("연결 상태를 확인해주세요");
		}
	});
}

/**
 * 팔로우 버튼이 전부 로드되면 작동.
 * 팔로우 상태를 버튼에 반영
 */
$(function() {
	$(".profileUnit").each(function(index) {
		var memNo = $("input[class=unitMemberNo]:eq(" + index + ")").val();
		var followBtn = $("button[class=followToggleBtn]:eq(" + index + ")");
		$(followBtn).setFollowBtnStat(memNo);
	});
	
	/**
	 * 팔로우 버튼을 클릭했을 때, 현재 stat을 class를 통해 확인하고
	 * 팔로우할지 언팔로우 할지 분기되는 
	 * 클릭이벤트 설정
	 */
	$(".followToggleBtn").click(function() {
		var element = $(this);
		var memNo = element.siblings(".unitMemberNo").val();
		if (element.hasClass("stat-follow")) {
			element.doFollow(memNo);
		} else {
			element.unFollow(memNo);
		}
	});

	/**
	 * 사용자의 최근 포스트 이미지들 클릭시, 해당 포스트 보기로 이동
	 */
	$(".profileUnitPost").click(function() {
		location.href = '/post/'+$(this).children('.postNo').val();
	});
	/**
	 * 사용자닉네임/프사 클릭시, 해당 유저페이지로 이동
	 */
	$('.profileUnitNickname').click(function() {
		location.href = '/user/'+$(this).text();
	});
	$('.profileUnitMemImg').click(function() {
		location.href = '/user/'+$(this).siblings('.profileUnitNickname').text();
	});
});