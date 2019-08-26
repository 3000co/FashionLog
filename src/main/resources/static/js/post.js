$(document).ready(function() {

	var palModal = $("#paletteModal");
	var mapModal = $("#mapModal");
	
	$(document).on("click", "#paletteBtn", function(event) {
		console.log(1);
		$(palModal).show();
	});

	$(document).on("click", "#mapBtn", function(event) {
		console.log(1);
		$(mapModal).show();
	});

	$(document).on("click", ".close", function(event) {
		console.log(2);
		$(palModal).hide();
		$(mapModal).hide();
	});
	
	
//	$(document).on("click", window, function(event) {
//		if (event.target == palModal) {
//			console.log(3);
//			$(palModal).hide();
//		  }
//	});

});