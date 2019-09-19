/**
 * 
 */
$(function() {

	//Report 기능
	$(".commentRow").click(function () {
		$(reportModal).show();

		targetCommentNo = $(this).children('td.commentNo').attr('id');
		$("#targetCommentNo").val(targetCommentNo);

		targetMemName = $(this).children('td.targetMemName').attr('id');
		$("#targetMemName").val(targetMemName);
	});

	$(".close").click(function (event) {
		$(reportModal).hide();
	});

	$("#reportBtn1").on("click", function () {
		$(".reportBtn").val("유해한 내용")
	});

	$("#reportBtn2").on("click", function () {
		$(".reportBtn").val("광고성 내용")
	});

	$(".reportBtn").on("click", function () {
		var targetCommentNo = $("#targetCommentNo").val();
		var type = '1';
		var reason = $(".reportBtn").val();
		$.ajax({
			type: 'post',
			url: '/insertReport',
			data: {
				"targetCommentNo": targetCommentNo,
				"type": type,
				"reason": reason

			},
			success: function (result) {
				if (result == 'SUCCESS') {
					alert("신고가 접수되었습니다");
				}
			},
			error: function (err) {
				alert("문제가 발생했습니다 \n 신고가 접수되지 않았습니다");
			}
		});
	});

	document.getElementById("insertBtn").disabled = true;
	document.onkeyup = InputProcess;
	document.onmouseup = InputProcess;
	document.onmousedown = InputProcess;

	//댓글 입력 버튼 조절
	function InputProcess() {
		if (document.getElementById("contents").value == '') {
			document.getElementById("insertBtn").disabled = true;
		} else {
			document.getElementById("insertBtn").disabled = false;
		}
	};

	//삭제버튼 설정
	$(".commentRow").each(function () {
		var sessionNickName = $("#userNickname").text();
		var commentWriter = $(this).find("#targetMemName").attr("id");

		if (sessionNickName == commentWriter) {
			$(this).find(".commentDeleteBtn").css('display', 'inline');
		}
	});

	//댓글 쓰기 버튼 설정
	$('#insertBtn').click(function(){
		console.log($('#inputCommentContents').val());
		if($('#inputCommentContents').val().length!=0) {
			commentWrite();
		}
	});

	//댓글 지우기 버튼 설정
	$('.commentDeleteBtn').click(function(){
		commentDelete(this);
	})

	//댓글 쓰기 함수
	function commentWrite() {
		//get content
		var form = $('#commentInputForm')[0];

		//create an form data object
		var comment = new FormData(form);
		$.ajax({
			type: 'POST',
			url: "/insertComment",
			data: comment,
			success: function (result) {
				console.log('등록성공');
			},
			error: function (e) {
				console.log('등록실패');
			}
		});
	}
	/**
	 * 댓글 삭제 함수
	 * @param {Element} deleteBtn 
	 */
	function commentDelete(e) {
		var commentNo = $(e).parent('td').siblings('.commentNo').attr('id');
		//create an form data object
		$.ajax({
			type: 'POST',
			url: "/deleteComment",
			data: {commentNo:commentNo},
			success: function (result) {
				console.log(result+" : 삭제성공");
			},
			error: function (e) {
				console.log(e+" : 삭제실패");
			}
		});
	}
});