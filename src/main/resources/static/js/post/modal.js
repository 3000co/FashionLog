var palModal = $("#paletteModal");
var mapModal = $("#mapModal");
var picker;
$(document).ready(function() {

	$(document).on("click", "#paletteBtn", function(event) {
		picker = $(event.target).siblings(".colorSquare").css("background-color");
		palette();
	});
	
	$(document).on("click", ".colorSquare", function(event) {
		picker = $(event.target).css("background-color");
		palette();
	});

	$(document).on("click", "#mapBtn", function(event) {
		$(mapModal).show();
		
		$(document).on("click", "#mapModalBtn", function() {
			console.log($(event.target));
			
			console.log($("#road").text());
			
			var road = $("#road").text();
			$(event.target).prev().val(road);
			console.log($(event.target).prev());
//			road = "";
			
			$(mapModal).hide();
		});
		
	});

	$(document).on("click", ".close", function() {
		$(palModal).hide();
		$(mapModal).hide();
	});
	$(document).on("click", ".modalBtn", function() {
		$(palModal).hide();
	});

});

function palette() {
	console.log(picker);
	$(".selected-box").css("background-color", picker);
	$(".square.picker").css("background-color", picker);
	
	var hex = $(event.target).siblings("#color").val();
	console.log(hex);
	$(".square.picker").attr("color", hex);
	
	$(palModal).show();
}