	$(document).ready(function () {
		//responsive menu toggle
		$("#menutoggle").click(function () {
			$('.xs-menu').toggleClass('displaynone');

		});
		//drop down menu	
		$(".searchBoard").click(function () {
			$('.mega-menu').addClass('display-on');

		});
		//					$(".searchBoard").mouseleave(function() {
		//						$('.mega-menu').removeClass('display-on');
		//					});

		$('html').click(function (e) {
			if (!($(e.target).is(".menuBtn ") || $(e.target).is(".token-input"))) {
				$('.mega-menu').removeClass('display-on');
			}
		});

		//db에서 상세 속성 목록 불러오기
		getAttrList("styleBtnTable", "/getAttrList/style");
		getAttrList("categoryBtnTable", "/getAttrList/category");
		getAttrList("brandBtnTable", "/getAttrList/brand");


		$('#inputField').tokenfield({
			//	      autocomplete: {
			//	        source: [],
			//	        
			//	        delay: 100
			//	      },
			delimiter: '',
			showAutocompleteOnFocus: true,
			createTokensOnBlur: false
		});

		/* $("#searchButton").click(function(e) {
		   e.preventDefault();
		   var searchWord = $('#inputField').val()
		   searchWord.submit();
		   
		   $('.form-data').text( $('#inputField').val());
		 });*/

		$("#searchButton").click(function (e) {
			e.preventDefault();

			$('#searchWords').val($('#inputField').val());


			document.searchButton.submit();

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
		$('.menuBtn').click(function () {
			var searchWord = $('#inputField-tokenfield').val();
			searchWord += ($(this).val());
			$('#inputField-tokenfield').val(searchWord);
			changeSearchMod(this);
		});

		//상세버튼 누를 시 검색창에 토큰 추가

		$(document).on("click", ".attrBtn", function () {
			var attrWord = $(this).val();
			var searchWord = $('#inputField-tokenfield').val();
			$('#inputField').tokenfield('createToken', searchWord + attrWord);
			$('#inputField-tokenfield').val('');
		});
		/* $('.attrBtn').click(function(){
		   console.log("안녕?");
			var attrWord = $(this).val();
			
			var searchWord = $('#inputField-tokenfield').val();
			$('#inputField').tokenfield('createToken',searchWord + attrWord);
			$('#inputField-tokenfield').val('');
		});*/


		//검색 타입 선택 버튼(category류 버튼)
		function getAttrList(applyTargetId, url) {

			$.ajax({
				url: url,
				type: 'POST',
				dataType: "json",
				success: function (data) {

					var jsondata = JSON.stringify(data);
					var attrName;
					var html = "";

					for (i = 0; i < data.attrList.length; i++) {
						//attrName = data.styleList[i].name;
						attrName = data.attrList[i].name;
						html += "<td><input class='attrBtn'";
						html += "type='button' value='" + attrName + "'/>";
						html += "</td>"
					}

					$("#obj").append(jsondata);
					$("#" + applyTargetId).html(html);
					$('.attrBtn').click(function(){
						returnDefaultMod();
					})
				},
				error: function (data) {
					console.log("오류 발생");
					console.log("data: " + data);
				}
			});
		}

		function changeSearchMod(target) {
			var defaultSearch = document.getElementById("defaultSearch");
			switch ($(target).attr('id')) {
				case 'categoryBtn':
					defaultSearch.style.display = 'none';
					document.getElementById("categorySearch").style.display = 'block';
					break;
				case 'colorBtn':
					defaultSearch.style.display = 'none';
					document.getElementById("colorSearch").style.display = 'block';
					break;
				case 'styleBtn':
					defaultSearch.style.display = 'none';
					document.getElementById("styleSearch").style.display = 'block';
					break;
				case 'brandBtn':
					defaultSearch.style.display = 'none';
					document.getElementById("brandSearch").style.display = 'block';
					break;
				case 'userBtn':
					defaultSearch.style.display = 'none';
					document.getElementById("userSearch").style.display = 'block';
					break;
			}
		}


		function returnDefaultMod() {
			var defaultSearch = document.getElementById("defaultSearch");
			defaultSearch.style.display = 'block';
			document.getElementById("categorySearch").style.display = 'none';
			document.getElementById("colorSearch").style.display = 'none';
			document.getElementById("styleSearch").style.display = 'none';
			document.getElementById("brandSearch").style.display = 'none';
			document.getElementById("userSearch").style.display = 'none';

		}
		//프로필 버튼 누르면 해당 유저 전용 url로 들어감.
		$('#myProfile').click(function () {
			var userNickname = $("#myUserNickname").text();
			$(this).attr("href", "/user/" + userNickname)
		});
	});
