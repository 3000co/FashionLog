$(document).ready( function() {
	var count = 0;
	var newTag = $("#itemTag1");
	var tagMold;
	
	$(document).on("click", "#img", function(event) {
		count++;
		if (count = 1 && ) {
			
		}
		if (count <= 7) {
			var eventX = event.clientX;
			var eventY = event.clientY;
			var tagStyle = $(newTag).css("display");

			if (tagStyle === "block") {
				newTag = $(tagMold).clone().attr("id", "itemTag" + count);
				$("#itemTagWrap").append(newTag);
				$(newTag).find('#xCoordinate').val(eventX);
				$(newTag).find('#yCoordinate').val(eventY);
			} else {
				$(newTag).show();
				tagMold = $(newTag).clone();
				$('#xCoordinate').val(eventX);
				$('#yCoordinate').val(eventY);
			}
		}
	});
	$(document).on("click", "#exitBtn", function(event) {
		console.log(event.target);
		console.log($(event.target).parents(".itemTag"));
		if (count != 1) {
			$(event.target).parents(".itemTag").detach();			
		}else {
			$(event.target).parents(".itemTag").hide();
		}
		count--;
	});
	
});
