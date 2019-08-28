$(document).ready(function() {

	var palModal = $("#paletteModal");
	var mapModal = $("#mapModal");

	$(document).on("click", "#paletteBtn", function(event) {
		$(palModal).show();
	});

	$(document).on("click", "#mapBtn", function(event) {
		$(mapModal).show();
	});

	$(document).on("click", ".close", function(event) {
		$(palModal).hide();
		$(mapModal).hide();
	});

	$("#submit").click(function() {
		$("div[class=itemTag]").each(function(index) {
			var eqValue = $("div[class=itemTag]:eq(" + index + ")");
		
		$.ajax({
			type : "post",
			url : "/item",
//			contentType : 'application/json',
//			dataType : 'json',
			data : { 	
				"postNo" : $(eqValue).find("#postNo").val(),
				"tagNo" : $(eqValue).find("#tagNo").val(),
				"name" : $(eqValue).find("#name").val(),
				"categoryNo" : $(eqValue).find("#categoryNo").val(),
				"brandNo" : $(eqValue).find("#brandNo").val(),
				"color" : $(eqValue).find("#color").val(),
				"store" : $(eqValue).find("#store").val(),
				"xCoordinate" : $(eqValue).find("#xCoordinate").val(),
				"yCoordinate" : $(eqValue).find("#yCoordinate").val()
			},
			success : function(result) {
				alert("전송성공");
			},
			error : function(result) {
				alert("전송실패");
			}
		});
		});
	});
});

//$("#post").serializeObject()
//jQuery.fn.serializeObject = function() { 
//	var obj = null; 
//	try { 
////		console.log($("#tag"));
//		if($("#tag")) { 
//			var arr = $("#tag").serializeArray(); 
//			if(arr){ 
//				console.log(arr);
//				obj = {}; 
//				console.log(obj);
//				jQuery.each(arr, function() {
//					obj[$("#tag").name] = $("#tag").val; 
//				});
//				console.log(arr);
//				console.log($("#tag").val);
//			} 
//		} 
//	}catch(e) { 
//		alert(e.message); 
//	}
//	return obj; 
//}

//(function ($) {
//
//	  'use strict';
//
//		$.fn.serializeObject = function () {
//
//			var obj = {};
//
//			$.each(this.serializeArray(), function () {
//				obj[this.name] = this.value;
//			});
//
//			return obj;
//
//		};
//
//	})(jQuery);