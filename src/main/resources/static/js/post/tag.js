$(document).ready( function() {
	var count = 0;
	var num = 0;
	var newTag = $("#itemTag1");
	var tagMold;

	var style = 0;
	$(document).on("change", "#styleNo", function(event) {
		$("input[class=styleText]:eq(" + style + ")").show();
		$("span[class=styleUndo]:eq(" + style + ")").show();
		var text = $("#styleNo option:selected").text();
		var value = $("#styleNo option:selected").val();
		if (value != 0 || style < 3) {
			$("input[class=styleNo]").each(function(index) {
				$("input[class=styleText]:eq(" + style + ")").val(text);
				$("input[class=styleNo]:eq(" + style + ")").val(value);
			});
			style++;
		}
	});

	$(document).on("click", "span[class=styleUndo]", function(event) {
		var clickIndex = $("span[class=styleUndo]").index(event.target);

		$("input[class=styleText]").each(function(index) {
//			console.log(index);
			var i = index - 1;
			console.log(i);
			if(clickIndex < index) {
				var indexText = $("input[class=styleText]:eq(" + index + ")");
				var newText = $("input[class=styleText]:eq(" + i + ")").val(indexText.val());
				indexText.val("");

				var indexVal = $("input[class=styleNo]:eq(" + index + ")");
				var newVal = $("input[class=styleNo]:eq(" + i + ")").val(indexVal.val());
				indexVal.val("");
			}

		});

		style--;
		$("input[class=styleText]:eq(" + style + ")").hide();
		$("span[class=styleUndo]:eq(" + style + ")").hide();

	});

	var canvas = document.createElement("CANVAS");
	var ctx = canvas.getContext("2d");
	
	$(document).on("click", "#postImage", function(event) {

//		document.body.appendChild(canvas);
		if (count < 7) {
			var img = document.getElementById("postImage");
			canvas.width = img.width;
			canvas.height = img.height;
			ctx.drawImage(img, 0, 0, img.width, img.height);
			
			count++;
			num++;
			var offsetX = event.offsetX;
			var offsetY = event.offsetY;
			var eventX = event.clientX;
			var eventY = event.clientY;
			var tagStyle = $(newTag).css("display");
			var pixelData = canvas.getContext("2d").getImageData(offsetX, offsetY, 1, 1).data;
			
			var hex0 = pad(pixelData[0].toString(16), 2);
			var hex1 = pad(pixelData[1].toString(16), 2);
			var hex2 = pad(pixelData[2].toString(16), 2);
			var hex = "#" + hex0 + hex1 + hex2;
			console.log(pixelData);
			console.log(offsetX);
			console.log(offsetY);
			
			if (tagStyle === "block") {
				newTag = $(tagMold).clone().attr("id", "itemTag" + num);
				$("#itemTagWrap").append(newTag);
				$(newTag).find("#xCoordinate").val(eventX);
				$(newTag).find("#yCoordinate").val(eventY);
				$(newTag).find("#color").val(hex);
				$(newTag).find(".colorSquare").css("background-color", hex);
				$("div[class=itemTag]").each(function(index) {
					var clickIndex = $(this).index();
					$(newTag).find('#tagNo').val(clickIndex + 1);
				});
			} else {
				$(newTag).show();
				tagMold = $(newTag).clone();
				$("#xCoordinate").val(eventX);
				$("#yCoordinate").val(eventY);
				$("#color").val(hex);
				$("#tagNo").val(count);
				$(".colorSquare").css("background-color", hex);
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


function pad(n, width) {
	  n = n + '';
	  return n.length >= width ? n : new Array(width - n.length + 1).join('0') + n;
}
