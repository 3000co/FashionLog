$(document).ready(function() {
	$("#selectImg").on("change", handleImgFileSelect);
});

var selFile;

function handleImgFileSelect(e) {

	var files = e.target.files;
	var filesArr = Array.prototype.slice.call(files);

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

}
