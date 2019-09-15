// 이 스크립트는 like 버튼을 가지고 있는 template과 같이 쓰여야 합니다.


/**
 * like 버튼(.likesBtn)이 로딩된 직후 팔로우 상태를 세팅 
 * result 는 text , "likes" : 좋아요 눌렀음
 * "unLikes" : 좋아요 누르지 않았음 
 * 결과에 따라 (.stat-likes) 또는 (.stat-unLikes) 중의 하나가 추가됨.
 * @param memberNo {int} 해당 버튼의 주인 포스트번호
 */
$.fn.setLikesBtnStat = function(postNo) {
	var element = this;
	$.ajax({
		type : 'post',
		url : '/checkLikes',
		dataType : 'text',
		data : {
			postNo : postNo
		},
		success : function(likesStat) {
			switch (likesStat) {
			case ('likes'):
				if (element.hasClass("stat-unLikes")) {
					element.removeClass("stat-unLikes");
				}
				element.addClass("stat-likes");
				element.css("display", "inline");
				if (element.hasClass("btn-light")) {
					element.removeClass("btn-light");
				}
				element.addClass("btn-primary");
				break;
			default:
				if (element.hasClass("stat-likes")) {
					element.removeClass("stat-likes");
				}
				element.addClass("stat-unLikes");
				element.css("display", "inline");
				if (element.hasClass("btn-primary")) {
					element.removeClass("btn-primary");
				}
				element.addClass("btn-light");
			}
		},
		error : function(result) {
			console.log("연결 상태를 확인해주세요");
		}
	});
}

/**
 * likes 하는 함수. 
 * 좋아요 버튼 객체에서 사용.
 * 좋아요에 성공하면 버튼 세팅을 다시 한다.
 * @param postNo {int} 해당 버튼의 주인 포스트번호
 */
$.fn.doLikes = function(postNo) {
	var element = this;
	$.ajax({
		type : 'post',
		url : '/doLikes',
		data : {
			postNo : postNo
		},
		success : function(result) {
			element.setLikesBtnStat(postNo);
		},
		error : function(result) {
			console.log("연결 상태를 확인해주세요");
		}
	});
}

/**
 * unlikes 하는 함수. 
 * 좋아 버튼 객체에서 사용.
 * 언팔로우에 성공하면 버튼 세팅을 다시 한다.
 * @param postNo {int} 해당 버튼의 주인 회원번호
 */
$.fn.unLikes = function(postNo) {
	var element = this;
	$.ajax({
		type : 'post',
		url : '/unLikes',
		data : {
			postNo : postNo
		},
		success : function(result) {
			element.setLikesBtnStat(postNo);
		},
		error : function(result) {
			console.log("연결 상태를 확인해주세요");
		}
	});
}

/**
 * 좋아요 버튼이 전부 로드되면 작동.
 * 좋아요 상태를 버튼에 반영
 */
$(".likesBtn").ready(function() {
	$(".item").each(function() {
		var postNo = $(this).attr("id").slice(1);
		var likesBtn = $(this).children("button.likesBtn");
		$(likesBtn).setLikesBtnStat(postNo);
	});
	
	/**
	 * 좋아요 버튼을 클릭했을 때, 현재 stat을 class를 통해 확인하고
	 * 좋아요할지 안좋아요 할지 분기되는 
	 * 클릭이벤트 설정
	 */
	$(".likesBtn").click(function() {
		var element = $(this);
		var postNo = element.parent().attr("id").slice(1);
		if (element.hasClass("stat-unLikes")) {
			element.doLikes(postNo);
		} else {
			element.unLikes(postNo);
		}
	});
});