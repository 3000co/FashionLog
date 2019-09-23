$(document).ready(function() {

	if($("#memberNo").val() != $("#userNo").text()) {
		console.log("달라~~");
		$("#postDelete").hide();
		$("#postUpdate").hide();
	}else {
		$("#postDelete").show();
		$("#postUpdate").show();
	}
	
	$("#postDelete").click(function() {
		location.href = "/itemDelete";
		
		
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