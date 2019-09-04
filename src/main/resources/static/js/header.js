	$(document).ready(function() {
				//responsive menu toggle
				$("#menutoggle").click(function() {
					$('.xs-menu').toggleClass('displaynone');

					});
			//drop down menu	
					$(".searchBoard").click(function() {
						$('.mega-menu').addClass('display-on');
					});
					$(".searchBoard").mouseleave(function() {
						$('.mega-menu').removeClass('display-on');
					});
			
			});
	
//button js 
	$(document).ready(function() {   
	    $('#inputField').tokenfield({
	      autocomplete: {
	        source: [],
	        
	        delay: 100
	      },
	      delimiter: '&',
	      showAutocompleteOnFocus: true,
	      createTokensOnBlur: false
	    });
	    
	    $("#searchButton").click(function(e) {
	      e.preventDefault();
	      $('.form-data').text( $('#inputField').val());

	    });
	});

	
	$('#inputField').on('tokenfield:createdtoken', function (e) {
	    console.log('createdtoken', {
	      value: e.attrs.value,
	      tokens: $('#inputField').tokenfield('getTokens'),
	      $input: $('#inputField').data('bs.tokenfield').$input.val()
	    });
	    returnDefaultMod();
	  });

	//메뉴 버튼
	$('.menuBtn').click(function(){
		var searchWord = $('#inputField-tokenfield').val();
		console.log($(this).val());
		searchWord += ($(this).val());
		console.log("searchword2 : "+ searchWord);
		$('#inputField-tokenfield').val(searchWord);
	});

	//상세 속성 버튼 
	$('.attrBtn').click(function(){
		var attrWord = $(this).val();
		var searchWord = $('#inputField-tokenfield').val();
		$('#inputField').tokenfield('createToken',searchWord + attrWord);
		$('#inputField-tokenfield').val('');
		
	});


			
			


	 