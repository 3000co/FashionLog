$(document).ready( function() {
	var count = 0;
	var num = 0;
	var newTag = $("#itemTag1");
	var tagMold;
	
	var style = 0;
	$(document).on("change", "#styleNo", function(event) {
		var text = $("#styleNo option:selected").text();
		var value = $("#styleNo option:selected").val();
		$("input[class=styleNo]").each(function(index) {
			$("input[class=styleText]:eq(" + style + ")").val(text);
			$("input[class=styleNo]:eq(" + style + ")").val(value);
		});
		style++;
	});

	$(document).on("click", "#img", function(event) {
		
		if (count < 7) {
			count++;
			num++;
			var eventX = event.clientX;
			var eventY = event.clientY;
			var tagStyle = $(newTag).css("display");
			
			if (tagStyle === "block") {
				newTag = $(tagMold).clone().attr("id", "itemTag" + num);
				$("#itemTagWrap").append(newTag);
				$(newTag).find('#xCoordinate').val(eventX);
				$(newTag).find('#yCoordinate').val(eventY);
				
				$("div[class=itemTag]").each(function(index) {
					var clickIndex = $(this).index();
					$(newTag).find('#tagNo').val(clickIndex + 1);
				});
			} else {
				$(newTag).show();
				tagMold = $(newTag).clone();
				$('#xCoordinate').val(eventX);
				$('#yCoordinate').val(eventY);
			}
		}
	});
	
	
	$(document).on("click", "#exitBtn", function(event) {
		$("input[name=tagNo]").each(function(index) {
			var eqValue = $("input[name=tagNo]:eq(" + index + ")").val();
			var clickIndex = $(event.target).parents(".itemTag").index();
			
			if (clickIndex < index) {
				$("input[name=tagNo]:eq(" + index + ")").val(eqValue - 1);
			}
		});
		
		$(event.target).parents(".itemTag").detach();	
		count--;
	});
	
});
