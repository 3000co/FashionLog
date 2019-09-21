	$(document).ready(function() {
		$('#memberNo').val($('#userNo').text());
		$('#id').val($('#userId').text());
		$('#nickname').val($('#userNickname').text());
		$('#phonenumber').val($('#userPhonenumber').text());
		$('#email').val($('#userEmail').text());
		$('#styleNo1').val($('#userStyleNo1').text());
		
	});
	


//모달 함수들 
$("#modProfile").click( function() {
	$("#modProfileModal").show();
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

	$(function() {
		$("#passwordFalse").hide();
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
	
