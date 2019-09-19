$(document).ready(function() {
	$(document).on("change", "#selectImg", function(event) {
		
		// 선택이미지를 바꿨을 경우 태그 삭제
		var total = $(".itemTag").length;
		if (total > 1) {
			$(".itemTag").each(function(index) {
				index = total - index -1;
				console.log(index);
					$(".itemTag:eq(" + index + ")").detach();
			});
			count = 0;
		}
		handleImgFileSelect(event);
		
		console.log($("#selectImg").val());
		
		if ($("#selectImg").val() != "") {
			dd();	
		}
		
	});
});

var selFile;
var filesArr;
function handleImgFileSelect(e) {

	var files = e.target.files;
	filesArr = Array.prototype.slice.call(files);
	
	if (filesArr.length != 0) {
		filesArr.forEach(function(f) {
			if(!f.type.match("image.*")) {
				alert("이미지 확장자만 가능");
				return;
			}

			selFile = f;

			var reader = new FileReader();
			reader.onload = function(e) {
				$("#postImage").attr("src", e.target.result);
			}
			reader.readAsDataURL(f);
			
		});
	}else {
		$("#postImage").attr("src", "undefind");
	}
	
}

function dd() {
	
	var form = new FormData(document.getElementById('imgWrap')); 
	
	$.ajax({ 
		type : "POST",
		crossOrigin : true,
		url: "http://127.0.0.1:5000/file",
		data: form,
		processData: false, 
		contentType: false, 
		success: function(data) {
			colorSend(data);
		}, 
		error : function() {
	        alert("여기Error!");
	    }
	});
}

function colorSend(data) {
	
	var colorArr = data.split(",");
	console.log(colorArr[0]);
	
	$(".square").attr('color', colorArr[0]);
	
}