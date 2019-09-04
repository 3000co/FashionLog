$(document).ready(function() {
	var palModal = $("#paletteModal");
	var mapModal = $("#mapModal");

	$(document).on("click", "#paletteBtn", function(event) {
		var src = $("#postImage").attr("src");
		
		$(palModal).show();
		$("#imgClone").attr("src", src);
	});

	$(document).on("click", "#mapBtn", function(event) {
		$(mapModal).show();
	});

	$(document).on("click", ".close", function(event) {
		$(palModal).hide();
		$(mapModal).hide();
	});

	$("#submit").click(function() {
		fileInsert();
	});
});

function fileInsert(){
	var form = new FormData(document.getElementById('imgWrap')); 
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

function postInsert(fileNo){
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
function itemInsert(postNo){
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
				"color" : $(eqValue).find("#color").val(),
				"store" : $(eqValue).find("#store").val(),
				"xCoordinate" : $(eqValue).find("#xCoordinate").val(),
				"yCoordinate" : $(eqValue).find("#yCoordinate").val()
			}
		});
	
	});
}
