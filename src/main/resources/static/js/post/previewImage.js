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

		if($("#postImage").attr('src') !== 'undefined'){
			colorPick();
		}
	});
});

function colorPick() {
	
	//get form
	var form = $('#imgWrap')[0];
	
	//create an form data object
	var data = new FormData(form);
	
	$.ajax({
		type : 'POST',
		crossOrigin : true,
		url : "http://127.0.0.1:5000/autoColorPick",
		contentType: false,
		processData: false,
		data : data,
		success : function(str){
			console.log(str);
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