/**
 * 
 */
$(function() {
	$("#container").gridalicious({
		animate : true,
		animationOptions : {
			queue : true,
			speed : 200,
			duration : 300,
			effect : 'fadeInOnAppear',
			complete : function() {
				console.log("completed");
			}
		}
	});
//	// 현재 시간 기준으로 업로드 시간 표현
//	var uploadTime = $('.uploadTime');
//	for (var i = 0; i < uploadTime.length; i++) {
//		var uploadTimeStamp = new Date($(uploadTime[i]).text());
//		var nowTimeStamp = new Date();
//		// 초단위 시간차
//		var timeDiff = (nowTimeStamp - uploadTimeStamp) / 1000;
//		var timeExp = '';
//		// 1분 미만일 때
//		if (timeDiff < 60) {
//			timeExp = '방금 전'
//			// 1시간 미만일 때
//		} else if (timeDiff < 3600) {
//			timeExp = Math.floor(timeDiff / 60) + '분 전';
//			// 1일 미만일 때
//		} else if (timeDiff < 86400) {
//			timeExp = Math.floor(timeDiff / 3600) + '시간 전';
//			// 30일 미만일 때
//		} else if (timeDiff < 2592000) {
//			timeExp = Math.floor(timeDiff / 86400) + '일 전'
//			// 1년 미만일 때
//		} else if (timeDiff < 31536000) {
//			timeExp = '약 ' + Math.floor(timeDiff / 2592000) + '달 전'
//			// 년단위 넘을 때
//		} else {
//			timeExp = Math.floor(timeDiff / 31536000) + '년 전'
//		}
//		$(uploadTime[i]).text(timeExp);
//	}

	// 현재 시간 기준으로 업로드 시간 표현
	function setTimeExp(e, nowTimeStamp) {
		var uploadTimeStamp = new Date($(e).text());
		// 초단위 시간차
		var timeDiff = (nowTimeStamp - uploadTimeStamp) / 1000;
		var timeExp = '';
		// 1분 미만일 때
		if (timeDiff < 60) {
			timeExp = '방금 전'
			// 1시간 미만일 때
		} else if (timeDiff < 3600) {
			timeExp = Math.floor(timeDiff / 60) + '분 전';
			// 1일 미만일 때
		} else if (timeDiff < 86400) {
			timeExp = Math.floor(timeDiff / 3600) + '시간 전';
			// 30일 미만일 때
		} else if (timeDiff < 2592000) {
			timeExp = Math.floor(timeDiff / 86400) + '일 전'
			// 1년 미만일 때
		} else if (timeDiff < 31536000) {
			timeExp = '약 ' + Math.floor(timeDiff / 2592000) + '달 전'
			// 년단위 넘을 때
		} else {
			timeExp = Math.floor(timeDiff / 31536000) + '년 전'
		}
		$(e).text(timeExp);
	}
	
	//전체 업로드시간 세팅
	var uploadTime = $('.uploadTime');
	var nowTimeStamp = new Date();
	for (var i = 0; i < uploadTime.length; i++) {
		setTimeExp(uploadTime[i],nowTimeStamp);
	}
});