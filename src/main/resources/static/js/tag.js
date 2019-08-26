$(document).ready(
	function() {
		var count = 0;
		var newTag = $("#tagContents1");
		var tagMold;
		
		$(document).on("click","#img",function(event) {
			count++;
			if (count <= 7) {
				var eventX = event.clientX;
				var eventY = event.clientY;
				var tagStyle = $(newTag).css("display");

				if (tagStyle === "block") {
					newTag = $(tagMold).clone().attr("id", "tagContents" + count);
					$("#tagWrap").append(newTag);
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
		$(document).on("click","#exitBtn",function(event) {
			console.log(event.target);
			console.log($(event.target).parents(".tagContents"));
			$(event.target).parents(".tagContents").hide();
			count--;
		});

});
