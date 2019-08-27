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

});