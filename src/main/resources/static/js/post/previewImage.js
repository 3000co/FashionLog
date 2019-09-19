$(document).ready(function() {

	$(document).on("change", "#selectImg", function(event) {
		
		handleImgFileSelect(event);

//		if($("#postImage").attr('src') !== 'undefined'){
//			colorPick();
//		}
		
		// 선택이미지를 바꿨을 경우 태그 삭제
		var tagDisplay = $(".itemTag").css("display");
		console.log(tagDisplay);
		
		if (tagDisplay === "block") {
			var len = $(".itemTag").length;
			console.log(len);
			
			for (var i = 0; i < len; i++) {
				console.log("들어왔어" + i);
				$(".itemTag:eq(" + i + ")").detach();
				console.log("나갈거야" + i);
			}

			count = 0;
		}

	});
});

function colorPick() {
	
	//create an form data object
	var data = new FormData($('#imgWrap')[0]);

	$.ajax({
		type : 'POST',
		crossOrigin : true,
		url : "http://127.0.0.1:5000/autoColorPick",
		contentType: false,
		processData: false,
		data : data,
		success : function(str){
			console.log(str);
			colorSend(str);
		},
		error: function(e){

		}
	});
}

function handleImgFileSelect(e) {

	var files = e.target.files;
	var filesArr = Array.prototype.slice.call(files);

	if (filesArr.length != 0) {
		filesArr.forEach(function(f) {
			if(!f.type.match("image.*")) {
				alert("이미지 확장자만 가능");
				return;
			}
			var reader = new FileReader();
			reader.onload = function(e) {
				$("#postImage").attr("src", e.target.result);
			}
			reader.readAsDataURL(f);
		});
	}else {
		$("#postImage").attr("src", null);
	}
}


function colorSend(data) {
	var colorArr = data.split(",");
	
	var auto;
	for (var i = 0; i < colorArr.length - 1 ; i++) {
		auto = $(".square.auto:eq(" + i + ")");
		auto.attr("color", colorArr[i + 1]);
		auto.css("background-color", colorArr[i + 1]);
	}
}



