$("input#commentContents").change(function(){
	$("#insertBtn").attr('disabled', $("input#commentContents").val() == '' ? true : false );
});

$(document).ready(function(){
	//내용 입력전 비활성화 처리
	$("#insertBtn").attr("disabled","true");
});

//모달 함수들 
$(".reportable").click( function() {
	$(reportModal).show();
	targetCommentNo = $(this).attr('id');
	$("#targetCommentNo").val(targetCommentNo);
});

$(".close").click(function(event) {
	$(reportModal).hide();
	$(resultModal).hide();
});

$("#reportBtn1").on("click", function(){
	$(".reportBtn").val("유해한 내용")
	});
	
$("#reportBtn2").on("click", function(){
	$(".reportBtn").val("광고성 내용")
});

$(".reportBtn").on("click", function(){
	var targetCommentNo = $("#targetCommentNo").val();
	var type='1';
	var reason = $(".reportBtn").val();
	$.ajax({
		type: 'post',
		url: '/insertReport',
		data : {
			"targetCommentNo" : targetCommentNo,
			"type" : type,
			"reason" : reason
		},
		success : function(result) {
			if(result == 'SUCCESS'){
				console.log("신고 완료");
			}
		},
		error : function(request,status,error){
                console.log("error code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
            }
	});
	alert("신고가 접수되었습니다");
	$(reportModal).hide();
	$(resultModal).show();
});

//댓글 등록
$("#insertBtn").click(function(){ //댓글 등록 버튼 클릭시 
    var insertData = $('input#commentContents').val(); //commentInsertForm의 내용을 가져옴
    var postNo = $("#postNo").val();
    insertComment(insertData,postNo); //Insert 함수호출(아래)
});

function insertComment(insertData,postNo){
    $.ajax({
        url : '/insertComment',
        type : 'post',
        data : {
        	"postNo":postNo,
        	"memberNo":1,
        	"contents":insertData
        },
        success : function(data){
        		location.reload();
            },
            error : function(request,status,error){
                console.log("error code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
            }
        });
}

//댓글 삭제
$(".commentDeleteBtn").click(function(){
	var sessionNickName = $("#myUserNickname").text();
	var commentWriter = $(this).children(".targetMemName").val();
	var commentNo = $(this).children('.targetCommentNo').val();
	alert(commentNo);
	if(sessionNickName == commentWriter ){
		 $.ajax({
		        url : '/deleteComment/'+commentNo,
		        type : 'post',
		        data : commentNo,
		        success : function(data){
		        	location.reload()
		        },
		        error : function(request,status,error){
	                console.log("error code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
	            }
		    });
		}else{
			alert("작성자가 아닙니다.");
		}
	
});
