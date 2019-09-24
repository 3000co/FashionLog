/**
 * gridalicious 플러그인 세팅
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
	// 포스트 뷰로 이동 이벤트
	$(".postImg").click(function() {
		var pId = $(this).parent().attr('id');
		location.href = "/post/" + pId.slice(1);
	});
	/**
	 * 현재 시간 기준으로 업로드 시간 표현
	 * @param time을 text로 가지고있는 element
	 */ 
	function setTimeExp(e) {
		var nowTimeStamp = new Date();
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
	for (var i = 0; i < uploadTime.length; i++) {
		setTimeExp(uploadTime[i]);
	}
	/**
	 * 페이징된 피드를 불러오는 함수
	 * page 변수가 먼저 선언되어야 함
	 */
	var page = 0;
	var getMoreFeed = function() {
		//다음 page로 세팅 
		page += 1;
		//서버에 요청
		$.ajax({
			type : 'get',
			url : '/getMoreFeed',
			dataType : 'json',
			data : {
				page : page,
				size : 3,
				memberNo : $('#userNo').text()
			},
			success : function(newFeed) {
				//데이터로 item 만들기
				var newItemArray = makeItemBoxes(newFeed);
				//item 안의 요소 세팅
				for(i=0; i<newItemArray.length; i++) {
					var thisItem = newItemArray[i];
					//좋아요 상태 세팅
					var likesBtn = $(thisItem).find('.likesBtn');
					likesBtn.setLikesBtnStat($(thisItem).attr('id').slice(1));
					//좋아요 누르기 세팅
					likesBtn.click(function() {
						var element = $(this);
						var postNo = element.parents(".item").attr("id").slice(1);
						if (element.hasClass("stat-unLikes")) {
							element.doLikes(postNo);
						} else {
							element.unLikes(postNo);
						}
					});
					//날짜 형식 세팅
					var uploadTime = $(thisItem).find('.uploadTime');
					setTimeExp(uploadTime);
					//포스트 뷰로 이동 세팅
					$(".postImg").click(function() {
						var pId = $(this).parent().attr('id');
						location.href = "/post/" + pId.slice(1);
					});
				}
				//플러그인에 넣기
				$('#container').gridalicious('append', newItemArray);
			},
			error : function(err) {
				console.log('error : ' + err);
			}
		});
	}
	/**
	 * 받아온 feedVo로 item box html 만들기
	 * @return DOM Element Array
	 */ 
	var makeItemBoxes = function(feedData) {
		var boxes = new Array;
		for ( var attr in feedData) {
			var itemDiv = $('<div></div>').addClass('item pin').attr('id',
					"p" + attr);

			var itemHtml = "<div class='author'><a th:href='/profile/"+feedData[attr].uploader+"'><img class='profileImg' src='"+feedData[attr].uploaderProfile+"' /></a><div class='name'><p class='uploader nickname'>" + feedData[attr].uploader+"</p><p class='uploadTime datetime'>"+feedData[attr].uploadTime+"</p></div>"
			itemHtml +="<img class='postImg' alt='"+feedData[attr].postNo+"번 포스트의 사진' src='"+feedData[attr].postImageNo+"'><div class='description'></div><div class='author'><span class='likesCount' >"+feedData[attr].likesCount+"</span>"
			itemHtml += "<a href=''><i class='glyphicon glyphicon-heart likesBtn' style='display: none'></i></a>"
			itemHtml +="<a href=''><i class='glyphicon glyphicon-comment'></i></a>"
			itemHtml += "<a href='' class='btn btn-primary followToggleBtn' role='button' style='display: none'>팔로우</a></div>";
			itemDiv.html(itemHtml);
			boxes.push(itemDiv);
		}
		return boxes;
	}
	//page 불러오기 이벤트
	$('#more').click(function(){
		getMoreFeed()
	});
	
	$(window).scroll(function() {
		var scrollTop = $(this).scrollTop();
		var feedHeight = $(this).height();
		var contentHeight = $('#container').height();
		if(scrollTop + feedHeight + 1 >= contentHeight) {
			getMoreFeed();
		}
	});
});