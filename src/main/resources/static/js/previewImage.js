$(document).ready(function() {
	$("#inputImg").on("change", handleImgFileSelect);

	$("#img").click(function (event){
		var x = event.clientX;
		var y = event.clientY;
		var tagStyle = document.getElementById("tag").style.display;
		var tagHtml = $("#tag").html();
		
		document.getElementById("xCoordinate").value = x;
		document.getElementById("yCoordinate").value = y;
	
		if (tagStyle === "block") {
			$("#tagContents").append(tagHtml);	
		} else {
			$("#tag").show();
		}
		
		console.log(tag);
	});
	
	$("#exitBtn").click(function (event){
		var tagStyle = document.getElementById("tag").style.display;
		if (tagStyle === "block") {
			$("#tag").hide();
		} 
	});
	
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
			$("#img").attr("src", e.target.result);
		}
		reader.readAsDataURL(f);
	});

}
