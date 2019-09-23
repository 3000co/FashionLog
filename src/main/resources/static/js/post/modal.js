var palModal = $("#paletteModal");
var mapModal = $("#mapModal");
var picker;
var mapEvent;
var paletteEvent;
$(document).ready(function() {

	$(document).on("click", "#paletteBtn", function(event) {
		palette($(event.target));
		paletteEvent = $(event.target);
	});

	$(document).on("click", ".colorSquare", function(event) {
		palette($(event.target));
		paletteEvent = $(event.target);
	});

	$(document).on("click", "#paletteCheckBtn", function() {
		selectedColor();
		$(palModal).hide();
	});

	$(document).on("click", "#paletteCloseBtn", function() {
		$(palModal).hide();
	});

	$(document).on("click", "#mapBtn", function(event) {
		$(mapModal).show();
		mapCall();
		mapEvent = $(event.currentTarget);
	});

	$(document).on("click", "#mapCheckBtn", function() {
		var road = $("#road").text();
		var listEl = document.getElementById("placesList");
		var paginationEl = document.getElementById("pagination");
		
		$(mapEvent).prev().val(road);

		removeAllChildNods(listEl);
		removeMarker();

		$("#road").text("");
		$("#keyword").val("");

		while (paginationEl.hasChildNodes()) {
			paginationEl.removeChild (paginationEl.lastChild);
		}

		$(mapModal).hide();
	});

	$(document).on("click", "#mapCloseBtn", function() {
		//x 눌렀을 때도 값이 넘어가게 할것인지 정하기
		var road = $("#road").text();
		var listEl = document.getElementById("placesList");

		removeAllChildNods(listEl);
		removeMarker();

		$("#road").text("");
		$("#keyword").val("");

		$(mapModal).hide();
	});

});

function palette(event) {
	
	var x = $(event).siblings("#xCoordinate").val();
	var y = $(event).siblings("#yCoordinate").val();
	
	var hex = colorHex(x, y);
	
	$(".selected-box").css("background-color", hex);
	$(".square.picker").css("background-color", hex);

	$(".selected-box").attr("color", hex);
	$(".square.picker").attr("color", hex);

	$(palModal).show();
}

function selectedColor() {
	var className = $(paletteEvent).attr("class");
	var selectedBackColor = $(".selected-box").css("background-color");
	var selectedColor = $(".selected-box").attr("color");
	
	console.log("className : " + className);
	console.log("selectedBackColor : " + selectedBackColor);
	console.log("selectedColor : " + selectedColor);
	
	
	if(className === "colorSquare") {
		$(paletteEvent).css("background-color", selectedBackColor);
	}else {
		$(paletteEvent).siblings(".colorSquare").css("background-color", selectedBackColor);
	}
	$(paletteEvent).siblings("#color").text(selectedColor);

	selectedNode.removeClass('selected');

	selectedNode = $('.picker');
	selectedNode.addClass('selected');
}

