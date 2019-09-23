$(document).ready( function() {
	for (var i = 0; i < 3; i++) {
		if ($(".styleText:eq(" + i + ")").val() != undefined) {
			$(".styleText:eq(" + i + ")").show();
			$(".styleUndo:eq(" + i + ")").show();
		}
	}
	if ($("#itemList").val() != undefined) {
		$(".itemTag").show();
		$(".itemTag").each(function(index) {
			var itemTag = $(".itemTag:eq(" + index + ")");
			itemTag.attr("id", "itemTag" + index);
			itemTag.find(".colorSquare").css("background-color", itemTag.find("#color").text());
			itemTag.find(".marker").css("left", itemTag.find("#xCoordinate").val() + "px");
			itemTag.find(".marker").css("top", itemTag.find("#yCoordinate").val() + "px");
		
		});
	}
	

		
		
	
});