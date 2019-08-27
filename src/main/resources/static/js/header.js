$(document).ready(function() {   
    $('#tokenfield').tokenfield({
      autocomplete: {
        source: [],
        
        delay: 100
      },
      delimiter: ';',
      showAutocompleteOnFocus: true,
      createTokensOnBlur: false
    });
    
    $("form").submit(function(e) {
      e.preventDefault();
      $('.form-data').text( $('#tokenfield').val());

    });
});

$('#tokenfield').on('tokenfield:createdtoken', function (e) {
    console.log('createdtoken', {
      value: e.attrs.value,
      tokens: $('#tokenfield').tokenfield('getTokens'),
      $input: $('#tokenfield').data('bs.tokenfield').$input.val()
    });
  });

$('#categoryBtn').click(function(){
	var searchWord = $('#tokenfield-tokenfield').val();
	console.log($(this).val());
	searchWord += ($(this).val());
	console.log("searchword2 : "+ searchWord);
	$('#tokenfield-tokenfield').val(searchWord);
});

$('#hatBtn').click(function(){
	var searchWord = $('#tokenfield-tokenfield').val();
	console.log($(this).val());
	searchWord += ($(this).val());
	console.log("searchword2 : "+ searchWord);
	$('#tokenfield-tokenfield').val(searchWord);
	$("<span>으아아</span>").after("#tokenfield-tokenfield");
});

