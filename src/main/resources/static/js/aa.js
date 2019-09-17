$(document).ready(function() {
	$("#submit").click(function() {
		fileInsert();
		dd();
	});
});
var idVal;
function fileInsert() {
//	var form = new FormData(document.getElementById('imgWrap')); 
	idVal = $("#id").val();
	console.log(idVal);
	var c = $("#id").attr('value', idVal);
	console.log(c);
	console.log(c.defaultValue);
	
	
}
function dd() {
	$.ajax({ 
		url: "http://127.0.0.1:5000/",
		data: {
			"id" : idVal
			},
		success: function () { 
			alert("성공");
		}, 
		error : function() {
	        alert("Error!");
	    }
	});
}