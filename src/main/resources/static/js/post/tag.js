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
			console.log(index);
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


	$(document).on("click", "#postImage", function(event) {

		var img = document.getElementById("postImage");
		var canvas = document.createElement("CANVAS");
		var ctx = canvas.getContext("2d");
		canvas.width = 600;
		canvas.height = 600;
		ctx.drawImage(img, 0, 0);
 
		if (count < 7) {
			count++;
			num++;
			var eventX = event.clientX;
			var eventY = event.clientY;
			var tagStyle = $(newTag).css("display");
			var pixelData = canvas.getContext("2d").getImageData(eventX, eventY, 1, 1).data;
			console.log(pixelData[0,1,2]);
			
//			var hex = rgbToHex(pixelData);
//			console.log(hex);
			
			
			if (tagStyle === "block") {
				newTag = $(tagMold).clone().attr("id", "itemTag" + num);
				$("#itemTagWrap").append(newTag);
				$(newTag).find("#xCoordinate").val(eventX);
				$(newTag).find("#yCoordinate").val(eventY);
				$(newTag).find("#color").val(pixelData[0] + "" + pixelData[1] + "" + pixelData[2]);


				$("div[class=itemTag]").each(function(index) {
					var clickIndex = $(this).index();
					$(newTag).find('#tagNo').val(clickIndex + 1);
				});
			} else {
				$(newTag).show();
				tagMold = $(newTag).clone();
				$("#xCoordinate").val(eventX);
				$("#yCoordinate").val(eventY);
				
				$("#color").val(pixelData[0] + "" + pixelData[1] + "" + pixelData[2]);
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

//function rgbToHex ( rgbType ){ 
//
//    // 컬러값과 쉼표만 남기고 삭제. 
//    var rgb = rgbType.replace( /[^%,.\d]/g, "" ); 
//
//    // 쉼표(,)를 기준으로 분리해서, 배열에 담기. 
//    rgb = rgb.split( "," ); 
//
//    // 컬러값이 "%"일 경우, 변환하기. 
//    for ( var x = 0; x < 3; x++ ) { 
//            if ( rgb[ x ].indexOf( "%" ) > -1 ) rgb[ x ] = Math.round( parseFloat( rgb[ x ] ) * 2.55 ); 
//    } 
//
//    // 16진수 문자로 변환. 
//    var toHex = function( string ){ 
//            string = parseInt( string, 10 ).toString( 16 ); 
//            string = ( string.length === 1 ) ? "0" + string : string; 
//
//            return string; 
//    }; 
//
//    var r = toHex( rgb[ 0 ] ); 
//    var g = toHex( rgb[ 1 ] ); 
//    var b = toHex( rgb[ 2 ] ); 
//
//    var hexType = "#" + r + g + b; 
//
//    return hexType; 
//} 
