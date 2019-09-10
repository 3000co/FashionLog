//cache jquery objects to reduce dom traversal
var container =  $('.container');
var selectedBox = $('.selected-box');
var selectedNode = {};
$(function() {
  
  
  //per child node on click listener
  container.children().on('click', function(event) {
    
    //click remove
    selectedNode.removeClass('selected');
    
    //click target event
    selectedNode = $(event.target);
    
    //add teh selected class to the current selectedNode
    selectedNode.addClass('selected');
    
    //selectbox color print
    var color = selectedNode.attr('color');

    //set selected-box background color to selected color
    selectedBox.css('background-color', color);
    selectedBox.attr('color', color);
    
  });
  
  //select default color
  var picker = $('.picker');
  picker.addClass('selected');
  selectedNode = picker;
  var defaultcolor = picker.attr('color');
  selectedBox.css('background-color', defaultcolor);
  
});
