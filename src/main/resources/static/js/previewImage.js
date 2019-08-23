$(document).ready(function() {
	$("#inputImg").on("change", handleImgFileSelect);
	
	var count = 0;
	var chageTagId = $("#tag");
	var tagHtml = $(chageTagId).html();
	
	$("#img").click(function (event){
		count++;
		
		if (count <= 7) {
			var x = event.clientX;
			var y = event.clientY;
			var tagStyle = $(chageTagId).css("display");
			
			document.getElementById("xCoordinate").value = x;
			document.getElementById("yCoordinate").value = y;
			
			chageTagId = $(chageTagId).attr("id","tag" + count);
		
			
			
			
			if (tagStyle === "block") {
				$(chageTagId).append(tagHtml);	
			} else {
				$(chageTagId).show();
			}
			console.log(chageTagId);
			
			console.log(tagStyle);
			console.log(count);
		}
	});
	
	$("#exitBtn").click(function (event){
		
			$(chageTagId).hide();
			
			count--;
			console.log(count);

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
