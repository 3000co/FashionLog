/**
 * 
 */
$(function() {
	$("#container").gridalicious({
		animate : true,
		animationOptions : {
			queue : true,
			speed : 200,
			duration : 100,
			effect : 'fadeInOnAppear',
			complete : function() {
			}
		}
	});
	$(".postImg").click(function() {
		var pId = $(this).parent().attr('id');
		location.href = "/post/" + pId.slice(1);
	});

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

	// 전체 업로드시간 세팅
	var uploadTime = $('.uploadTime');
	var nowTimeStamp = new Date();
	for (var i = 0; i < uploadTime.length; i++) {
		setTimeExp(uploadTime[i], nowTimeStamp);
	}
	var makeItemBoxes = function(feedData) {
		var boxes = new Array;
		for(var attr in feedData) {
//		var itemHtml = "<div class='item postSum' id='p"+attr+"'>";
		var itemHtml = "<div class='item postSum' id='p"+attr+"'>";
		itemHtml += "<img class='postImg' alt='"+attr+"번 포스트의 사진' src='"+feedData[attr].postImageNo+"'>";
		itemHtml += "<span class='uploadTime'>"+feedData[attr].uploadTime+"</span>";
		itemHtml += "<span class='uploader'>"+ feedData[attr].uploader +"</span>";
		itemHtml +=	"<span class='likesCount'>"+ feedData[attr].likesCount+"</span>";
		itemHtml += "<button class='likesBtn btn' style='display: none'> 좋아요 </button></div>";
		boxes.push(itemHtml);
		console.log(boxes);
		}
		return boxes;
	}
	
	$('#more').click(function() {
		$.ajax({
			type : 'get',
			url : '/getMoreFeed',
			dataType : 'json',
			data : {
					page : 2,
					size : 2,
					memberNo : $('#userNo').text()
			},
			success : function(newFeed) {
				$('#container').gridalicious('append', makeItemBoxes(newFeed));
//				$('#container').append(makeItem(newFeed));
				console.log()
			},
			error : function(err) {
				console.log('error : ' + err);
			}
		});

	});

});