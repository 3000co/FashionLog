$(document).ready(function() {
	$("#selectImg").on("change", handleImgFileSelect);
	$("#selectImg").on("change", function(){
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

var selFile;

function handleImgFileSelect(e) {

	var files = e.target.files;
	var filesArr = Array.prototype.slice.call(files);

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
		$("#postImage").attr("src", null);
	}
}
