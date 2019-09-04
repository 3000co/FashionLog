$(function() {

	//cache jquery objects to reduce dom traversal
	var container =  $('.colorContainer');
	var selectedBox = $('.selected-box');
	var selectedNode = {};
	var colorValue= $('#colorValue');

	//select default color

	
	//per child node on click listener
	container.children().on('click', function(event) {

		//click remove
		/*selectedNode.removeClass('selected');*/

		//click target event
		selectedNode = $(event.target);

		//add teh selected class to the current selectedNode
		selectedNode.addClass('selected');

		//selectbox color print
		var searchWord = $('#inputField-tokenfield').val();
		var color = selectedNode.attr('color');

		$('#inputField').tokenfield('createToken',searchWord+color);
		$('#inputField-tokenfield').val('');
	

	});


});








