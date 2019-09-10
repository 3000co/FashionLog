var img;

$(document).ready( function() {
	var count = 0;
	var num = 0;
	var newTag = $("#itemTag1");
	var tagMold;

	var style = 0;
	// 스타일 select를 선택했을 때
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

	// 선택한 스타일 중 event가 일어난 스타일을 선택취소
	$(document).on("click", "span[class=styleUndo]", function(event) {
		var clickIndex = $("span[class=styleUndo]").index(event.target);

		$("input[class=styleText]").each(function(index) {
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

	// image를클릭했을 때 아이템태그 추가
	$(document).on("click", "#postImage", function(event) {
		// image가 있을 경우에만 작동
		if ($("#selectImg").val() != "") {
			// 최대 7개 태그
			if (count < 7) {
				img = document.getElementById("postImage");
				var tagImg = document.createElement("img");

				// itemTag1의 display
				var tagStyle = $(newTag).css("display");

				// 클릭좌표
				var offsetX = event.offsetX;
				var offsetY = event.offsetY;
				// 클릭좌표의 hex코드
				var hex = colorHex(offsetX, offsetY);
				
				count++; // 7개 카운트용
				num++; // itemTag id분리용
				
				
				if (tagStyle === "block") {
					// 원래것은 그대로 두고 복제한 것의 아이디 변경
					newTag = $(tagMold).clone().attr("id", "itemTag" + num);
					$("#itemTagWrap").append(newTag);
			
					//newTag의 하위요소중 찾아서 값을 설정
					$(newTag).find("#xCoordinate").val(offsetX);
					$(newTag).find("#yCoordinate").val(offsetY);
					$(newTag).find("#color").text(hex);
					$(newTag).find(".colorSquare").css("background-color", hex);
					$("div[class=itemTag]").each(function(index) {
						var clickIndex = $(this).index();
						$(newTag).find('#tagNo').val(clickIndex + 1);
						tagImg.setAttribute("src", "images/tag" + (clickIndex + 1) + ".png");
					});
				} else {
					$(newTag).show(); // display: none -> block
					tagMold = $(newTag).clone(); // 복제
					$("#xCoordinate").val(offsetX);
					$("#yCoordinate").val(offsetY);
					$("#color").text(hex);
					$("#tagNo").val(count);
					$(".colorSquare").css("background-color", hex);
					tagImg.setAttribute("src", "images/tag1.png");
				}

				//태그이미지 alt Attribute를 tagNoImg로 지정
				tagImg.setAttribute("alt","tagNoImg");
				//태그이미지위치
				tagImg.style.position = "absolute";
				tagImg.style.left = (offsetX + 15) + "px";
				tagImg.style.top = (offsetY + 30) + "px";
				
				//태그이미지를 해당 아이템태그 div에 append
				$(newTag).append(tagImg);
			}
		}
	});

	// 아이템태그 삭제
	$(document).on("click", "#exitBtn", function(event) {
		$("input[name=tagNo]").each(function(index) {
			var eqValue = $("input[name=tagNo]:eq(" + index + ")").val();
			var clickIndex = $(event.target).parents(".itemTag").index();

			if (clickIndex < index) {
				$("input[name=tagNo]:eq(" + index + ")").val(eqValue - 1);
				$("img[alt=tagNoImg]:eq(" + index + ")").attr("src", "images/tag" + (eqValue - 1) + ".png");
			}
		});

		$(event.target).parents(".itemTag").detach();
		$(event.target).parents(".itemTag").detach();
		count--;
	});
});


//캔버스를 생성하고 이미지를 그림
//클릭이벤트 좌표에 의한 픽셀데이터를 가져와서 hex로 변환
function colorHex(x, y) {
	var canvas = document.createElement("CANVAS");
	var ctx = canvas.getContext("2d");
	
	canvas.width = img.width;
	canvas.height = img.height;
	ctx.drawImage(img, 0, 0, img.width, img.height);
	
	var pixelData = canvas.getContext("2d").getImageData(x, y, 1, 1).data;

	var hex0 = pad(pixelData[0].toString(16), 2);
	var hex1 = pad(pixelData[1].toString(16), 2);
	var hex2 = pad(pixelData[2].toString(16), 2);
	var hex = "#" + hex0 + hex1 + hex2;

	return hex;
}

//width길이만큼에서 빈공간을'0'으로 채워줌
function pad(n, width) {
	n = n + '';
	return n.length >= width ? n : new Array(width - n.length + 1).join('0') + n;
}