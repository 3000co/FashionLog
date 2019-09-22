	$(document).ready(function() {
		$('#memberNo').val($('#userNo').text());
		$('#id').val($('#userId').text());
		$('#nickname').val($('#userNickname').text());
		$('#phonenumber').val($('#userPhonenumber').text());
		$('#email').val($('#userEmail').text());

		cbCheck($("#userStyleNo1").text());
		cbCheck($("#userStyleNo2").text());
		cbCheck($("#userStyleNo3").text());
		$("#passwordFalse").hide();
		//$("#submit").attr("disabled", "disabled");
/*		alert($('[name="styleNo1"]').val());*/
			
	});
	


//모달 함수들 
$("#modProfile").click( function() {
	$("#modProfileModal").show();
});
$("#modProfile2").click( function() {
	$("#modProfileModal2").show();
});

$(".close").click(function(event) {
	$(this).parent().parent().hide();
});

$("#submit").click(function() {
	fileInsert();
});

function fileInsert() {
	var form = new FormData(document.getElementById('imgWrap')); 
	if ($("#postImage").attr("src") == "") {
		alert("사진을 올려주세요");
	}else {
		$.ajax({ 
			type: 'POST',
			url: "/fileInsert",
			data: form,
			processData: false, 
			contentType: false, 
			success: function (fileNo) { 
			alert(fileNo);
			memberInfoInsert(fileNo);
			}
		});
	}
}

function memberInfoInsert(fileNo) {
	alert("유저 이름:" +$("#userNo").text());
	$.ajax({
		type : "post",
		url : "/modProfile",
		data : {
			"memberNo" : $("#userNo").text(),
			"profileImageNo" : fileNo
		},
		success : function(postNo){
			alert("프로필 사진 변경에 성공하셨습니다!");
			location.reload();
		}
	});
}
//유저 정보 변경용

function password_check(password) {
	var regexP = /([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
	return (password != '' && email != 'undefined' && regexP.test(password));
}


$("#password2").keyup(function() {
	var password = $("#password").val();
	var password2 = $("#password2").val();
	if (password != "" || password2 != "") {
		if (password == password2) {
			$("#passwordFalse").hide();
			$("#submit").removeAttr("disabled");
		} else {
			$("#passwordFalse").show();
			$("#submit").attr("disabled", "disabled");
		}
	}
});


	function email_check(email) {
		var regex = /([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
		return (email != '' && email != 'undefined' && regex.test(email));
	}
	$(".form-control1").blur(function() {
		var email = $(this).val();
		if (email == '' || email == 'undefined')
			return false;

		if (!email_check(email)) {
			$("#submit").attr("disabled", "disabled");
			$("#emailFalse").text('유효하지 않은 이메일 입니다');
			$(this).focus();
			return false;
		} else {
			$("#submit").removeAttr("disabled");
			$("#emailFalse").text('유효한 이메일입니다');
		}
	});
	
	//선호 스타일 체크용
	
	
	//체크박스 체인지 이벤트
	$(".cb").change(function () {
		checkLimitProcess(this);
	});
	
	//체크 1~3개까지
	function checkLimitProcess(e) {
		var checkCount = $("input:checkbox[name=styleNo]:checked").length;
		if (checkCount == 0) {
			$("#styleFalse").text('하나 이상의 스타일을 선택해 주세요');
			$(".finalJoinBtn").prop("disabled", true);
		} else {
			$(".finalJoinBtn").prop("disabled", false);
		}
		if (checkCount > 3) {
			$("#styleFalse").text('세개 이상 선택하실 수 없습니다');
			$(".finalJoinBtn").prop("disabled", true);
			$(e).prop('checked', false);
		}
	}
	
	//db에 넣을 수 있도록 style에 번호 부여
	function endueStyleNo() {
		var checkedStyle = $(':checked');
		checkedStyle.each(function (i) {
			checkedStyle.eq(i).prop('name', checkedStyle.eq(i).prop('name')+(i+1));
		});
	}
	
	//styleNo 값 모달에 체크
	function cbCheck(favoriteStyleNo) {
		
		for (var i = 0; i < 10; i++) {
			var cbVal = $("#cb" + i).val();
			if (cbVal == favoriteStyleNo) {
				$("#cb" + i).prop('checked', true);
				break;
			}
		}
	}
	
	$("#submit2").click(function() {
		endueStyleNo();
		
		userInfoInsert();
	});
	
	function userInfoInsert() {

			var data = {
					"memberNo":$('#userNo').text(),
					"id":$('#id').val(),
		        	"nickname":$('#nickname').val(),
		        	"phonenumber":$('#phonenumber').val(),
		        	"email":$('#email').val(),
		        	"styleNo1":$('[name="styleNo1"]').val()+"",//ajax int자료형 처리 안됨
		        	"styleNo2":$('[name="styleNo2"]').val()+"",
		        	"styleNo3":$('[name="styleNo3"]').val()+""
			}
			
			$.ajax({ 
				type: 'POST',
				url: "/modProfileAll.do",
				data: data,
				success: function (data) { 
					alert("유저 정보 변경에 성공하셨습니다!");
					location.reload();
				},
				error : function(request,status,error){
	                console.log("error code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
	            }
			});
	}

	
