/**
 * 
 */

//jquery선언
$(function(){

	//현재 시간 기준으로 알림 시간 표현
	var sendTime = $('.notiSendTime');
	for(var i = 0; i<sendTime.length; i++) {
		var sendTimeStamp = new Date($(sendTime[i]).text());
		var nowTimeStamp = new Date();
		//초단위 시간차	
		var timeDiff = (nowTimeStamp - sendTimeStamp)/1000;
		var timeExp = '';
		//1분 미만일 때
		if(timeDiff<60) {
			timeExp = '방금 전'
		//1시간 미만일 때
		} else if(timeDiff<3600) {
			timeExp = Math.floor(timeDiff/60)+'분 전';
		//1일 미만일 때
		} else if(timeDiff<86400) {
			timeExp = Math.floor(timeDiff/3600)+'시간 전';
		//30일 미만일 때
		} else if(timeDiff<2592000) {
			timeExp = Math.floor(timeDiff/86400)+'일 전'
		//1년 미만일 때
		} else if(timeDiff<31536000) {
			timeExp ='약 '+Math.floor(timeDiff/2592000)+'달 전'
		//년단위 넘을 때
		} else {
			timeExp =Math.floor(timeDiff/31536000)+'년 전'
		}
		$(sendTime[i]).text(timeExp);
	}
	
});