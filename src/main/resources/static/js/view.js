$(document).ready(function() {
	console.log($("#memberNo").val());
	console.log($("#userNo").text());
	if ($("#itemList").val() != undefined) {
		$(".itemInfo").each(function(index) {
			var itemTag = $(".itemInfo:eq(" + index + ")");
			itemTag.find(".colorSquare").css("background-color", itemTag.find("#color").text());
			itemTag.find(".marker").css("left", itemTag.find(".xCoordinate").val() - 140 + "px");
			itemTag.find(".marker").css("top", itemTag.find(".yCoordinate").val() + "px");
		});
	}
	if($("#memberNo").val() != $("#userNo").text()) {
		console.log("달라~~");
		$("#postDelete").hide();
		$("#postUpdate").hide();
	}else {
		console.log("같앗~~");
		$("#postDelete").show();
		$("#postUpdate").show();
	}
	
	$("#postDelete").click(function() {
		console.log($(".postNo").val());
		$.ajax({
			type : 'POST',
			url : "/postDelete",
			data : {"postNo" : $("#postNo").val()}
		});
		location.replace("/myFeed");
	});
	
	$("#postUpdate").click(function() {
		var pId = $(this).siblings("#postNo").val();
		console.log("pid: " + pId);
		location.href = "/postUpdate/" + pId;
	});

	$(".accordion").accordion({
		animate : 200,
		collapsible : true
	});
	$("#toggle").button().on("click", function() {
		if ($("#accordion").accordion("option", "icons")) {
			$("#accordion").accordion("option", "icons", null);
		} else {
			$("#accordion").accordion("option", "icons", icons);
		}
	});

});