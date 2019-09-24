/**
 * 
 */
const commentMent = '님이 회원님의 글에 댓글을 남겼습니다.';
const followMent = '님이 이제 회원님을 팔로우합니다.';
const likesMent = '님이 회원님의 포스트를 좋아합니다.';
//ajax로 알림을 불러오기
$.getNotification = function () {
	$.ajax({
		type: 'get',
		url: '/noti/unchecked',
		dataType: 'json',
		success: function (data) {
			//화면을 초기화
			$('.notiDiv').html('');
			//라인을 한줄씩 조립
			data.forEach(element => {
				//라인 껍데기 만들고 아이디 설정
				var notiLine = $("<div class='notiLine'></div>");
				notiLine.attr('id', 'notiNo' + element.notiNo);
				//알림 sender + ment를 가지는 case
				var notiCase = $("<span></span>");
				//알림 sender 만듬
				var notiSender = $("<span>" + element.senderMemNo.nickname + "</span>");
				//알림ment 만듬
				var notiMent = $("<span></span>");
				//알림 time 만듬
				var notiSendTime = $("<div class='notiSendTime'></div>")
				//case에 sender 넣음
				notiCase.append(notiSender);
				//case에 알맞는 ment 넣음
				switch (element.type) {
					case 1:
						notiCase.attr('class', 'likesNoti');
						notiMent.text(likesMent);
						break;
					case 2:
						notiCase.attr('class', 'commentNoti');
						notiMent.text(commentMent);
						break;
					case 3:
						notiCase.attr('class', 'followNoti');
						notiMent.text(followMent);
						break;
				}
				notiCase.append(notiMent);
				notiLine.append(notiCase);
				//알림 시간 넣음
				notiSendTime.text(element.sendTime);
				notiLine.append(notiSendTime);
				//화면에 넣기
				$('.notiDiv').append(notiLine);
				$('.notiDiv').append('<hr>');
			});
			$.setTimeExp();
			$('.notiLine').click(function() {
				var notiNo = $(this).attr('id').substring(6);
				var tempForm = document.getElementById('socialEventForm');
				tempForm.action = "/noti/check";
				tempForm.method = "post";
				var noContainer = document.createElement('input');
				noContainer.setAttribute('name','notiNo');
				noContainer.setAttribute('value',notiNo);
				tempForm.appendChild(noContainer);
				tempForm.submit();
			});
		},
		error: function (result) {
			console.log("알림을 불러오는 데 실패했습니다.");
		}
	});
}
//현재 시간 기준으로 알림 시간 표현
$.setTimeExp = function () {
	var sendTime = $('.notiSendTime');
	for (var i = 0; i < sendTime.length; i++) {
		var sendTimeStamp = new Date($(sendTime[i]).text());
		var nowTimeStamp = new Date();
		//초단위 시간차	
		var timeDiff = (nowTimeStamp - sendTimeStamp) / 1000;
		var timeExp = '';
		//1분 미만일 때
		if (timeDiff < 60) {
			timeExp = '방금 전'
			//1시간 미만일 때
		} else if (timeDiff < 3600) {
			timeExp = Math.floor(timeDiff / 60) + '분 전';
			//1일 미만일 때
		} else if (timeDiff < 86400) {
			timeExp = Math.floor(timeDiff / 3600) + '시간 전';
			//30일 미만일 때
		} else if (timeDiff < 2592000) {
			timeExp = Math.floor(timeDiff / 86400) + '일 전'
			//1년 미만일 때
		} else if (timeDiff < 31536000) {
			timeExp = '약 ' + Math.floor(timeDiff / 2592000) + '달 전'
			//년단위 넘을 때
		} else {
			timeExp = Math.floor(timeDiff / 31536000) + '년 전'
		}
		$(sendTime[i]).text(timeExp);
	}
}

//jquery선언
$(function () {
	$.getNotification();
});
