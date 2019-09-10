$(document).ready(function() {
	$("#submit").click(function() {
		fileInsert();
		location.href='/afterPostWrite';
	});
});

function fileInsert() {
	var form = new FormData(document.getElementById('imgWrap')); 
	
	if ($("#selectImg").val() == "") {
		alert("사진을 올려주세요");
	}else if ($("#styleText1").val() == "") {
		alert("스타일을 1개 이상 선택해주세요");
	}else {
		$.ajax({ 
			type: 'POST',
			url: "/fileInsert",
			data: form,
			processData: false, 
			contentType: false, 
			success: function (fileNo) { 
				postInsert(fileNo)
			}
		});
	}
}

function postInsert(fileNo) {
	$.ajax({
		type : "post",
		url : "/postInsert",
		data : {
			"memberNo" : $("#memberNo").val(),
			"postImageNo" : fileNo,
			"contents" : $("#contents").val(),
			"styleNo1" : $("#styleNo1").val(),
			"styleNo2" : $("#styleNo2").val(),
			"styleNo3" : $("#styleNo3").val()
		},
		success : function(postNo){
			itemInsert(postNo);
		}
	});
}
function itemInsert(postNo) {
	$("div[class=itemTag]").each(function(index) {
		var eqValue = $("div[class=itemTag]:eq(" + index + ")");
		$.ajax({
			type : "post",
			url : "/itemInsert",
			data : { 	
				"postNo" : postNo,
				"tagNo" : $(eqValue).find("#tagNo").val(),
				"name" : $(eqValue).find("#name").val(),
				"categoryNo" : $(eqValue).find("#categoryNo").val(),
				"brandNo" : $(eqValue).find("#brandNo").val(),
				"color" : $(eqValue).find("#color").text(),
				"store" : $(eqValue).find("#store").val(),
				"xCoordinate" : $(eqValue).find("#xCoordinate").val(),
				"yCoordinate" : $(eqValue).find("#yCoordinate").val()
			}
		});
	});
	location.replace("/feed");
}
