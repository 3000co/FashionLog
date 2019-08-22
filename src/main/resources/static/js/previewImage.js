var selFile;
$(document).ready(function() {
	$("#inputImg").on("change", imgFileSelect);
});

function imgFileSelect(e) {
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

function tagFunction() {
	var click = document.getElementById("tag");
	if (x.style.display === "none") {
		x.style.display = "block";
	} 
}
function exitFuntion() {
	var x = document.getElementById("tag");
	if (x.style.display === "block") {
		x.style.display = "none";
	} 
}
}


//function styleSelect() {
//var styleNo = $("#styleNo");
//var styleNo1 = $("#styleNo1")
//var styleNo2 = $("#styleNo2")
//var styleNo3 = $("#styleNo3")

//}

