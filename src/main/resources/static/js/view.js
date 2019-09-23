$(document).ready(function() {
	console.log($("#memberNo").val());
	console.log($("#userNo").text());

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
			data : {"postNo" : $("#postNo").val()},
			success : function(){
				console.log("aaaaaaaaaaa");
			},error: function(){
				console.log("vvv");
			}
		});
		location.replace("/feed");
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