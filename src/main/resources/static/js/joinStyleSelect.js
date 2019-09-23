//이미지 변경 위한 변수 
var pageNumArr = new Array();
var pageSampleDataArr = new Array();
var degree = 1;


//샘플 이미지 가져오기
$(document).ready(function () {
	//좋아하는 스타일 목록
	var favoriteStyleArr = new Array();
	$.ajax({
		url: "/getFileList",
		async: true,
		type: 'POST',
		dataType: "json",
		success: function (data) {
			setImgArray(data);
		},
		error: function (data) {
			console.log("오류 발생");
			console.log("data: " + data);
		},
		beforeSend: function () {},
		complete: function () {

		}
	});

	//이미지 html에 넣는 부분
	function setImgArray(data) {
		var length = data.sampleImgList.length;
		var sampleDataArray = new Array();

		//데이터 샘플을 배열에 저장
		for (var i = 0; i < length; i++) {
			var sampleData = data.sampleImgList[i];
			sampleDataArray[i] = sampleData;
		}
		setImgHtml(sampleDataArray)
	}
	//샘플을 html에 저장
	function setImgHtml(sampleDataArray) {
		pageSampleDataArr = sampleDataArray;
		//배열 값 중 6개를 랜덤으로 선택한 뒤 지정된 위치에 넣음
		var numArr = shuffleRandom(sampleDataArray.length);
		pageNumArr = numArr;
		var j = 0
		for (var num of numArr) {
			var objImg = document.getElementById("styleImg" + j);
			var styleName = sampleDataArray[num].name;
			objImg.src = sampleDataArray[num].path;
			if($('#styleName'+j).find('.examStyleName').text().length===0) {
				$("<p class='examStyleName'>" + styleName + "</p>").prependTo($("#styleName" + j));
			}else{
				$('#styleName'+j).find('.examStyleName').text(styleName);
			}
			
			j++;
			if (j == 6) {
				break;
			}
		}
	}

	//예시 사진 클릭 이벤트
	$(document).on('click', ".styleName", function (event) {
		var targetId = $(this).attr("id");
		var styleName = $(this).children("p").text();

		favoriteStyleArr.push(styleName);
		checkArr(favoriteStyleArr);
		if (degree < 10) {
			changeImg(targetId);
		} else if (degree == 10) {
			setFavoriteStyle(favoriteStyleArr);
		}
		degree++;
	});
	//클릭 된 후 이미지 바꾸기
	function changeImg(targetId) {
		var nextNo = pageNumArr[5 + degree];
		$("#" + targetId).children("img").attr("src", pageSampleDataArr[nextNo].path);
		$("#" + targetId).children("p").text(pageSampleDataArr[nextNo].name);

	}

	//사진 새로 부르기 버튼
	$(document).on('click', ".otherPicBtn", function (event) {
		setImgHtml(pageSampleDataArr);
	});
	$(document).on('click', ".refreshBtn", function (event) {
		location.reload(true);
	});

	//체크박스 체인지 이벤트
	$(".cb").change(function () {
		checkLimitProcess(this);
	});
	
	//db 등록 용 styleNo부여 후 form data로 넘기기
	$(".finalJoinBtn").click(function() {
		var checkedStyle = $(':checked');
		checkedStyle.each(function (i) {
			checkedStyle.eq(i).prop('name', checkedStyle.eq(i).prop('name')+(i+1));
		});
		// var joinForm = $('#memberInfo')[0];
		// var formData = new FormData(joinForm);
		$('#memberInfo').submit();
		
		
	})

});

//선택 스타일이 10개가 되면 결과 화면으로 이동, 진행바 채워줌
function checkArr(arr) {
	var increaseRate = degree * 10;
	$(".progress-bar").css("width", increaseRate + "%");
	//bar가 변하는 시간 (0.5s)까지 기다리고 화면 전환
	setTimeout(() => {
		if (arr.length == 10) {
			$("#styleSelect1").css("display", "none");
			$("#styleSelect2").css("display", "block");
		}
	}, 500);
}

//제일 많이 지목된 스타일 선정, 노출
function setFavoriteStyle(array) {
	var styles = array;
	var counts = {};
	var compare = -1;
	var keys = [];
	var mostFrequent;
	//최다 빈도 스타일 결정
	for (var i = 0, len = array.length; i < len; i++) {
		var word = array[i];

		if (counts[word] === undefined) {
			counts[word] = 1;
		} else {
			counts[word] = counts[word] + 1;
		}
		if (counts[word] > compare) {
			compare = counts[word];
			mostFrequent = styles[i];
			secondFrequent = styles[i + 1];
		}
	}
	insertFavoritesImage(mostFrequent, 0);
	insertFavoritesImage(secondFrequent, 1);
}

function insertFavoritesImage(favoriteStyleName, num) {
	//page2html에 추가
	for (var i = 0; i <= pageSampleDataArr.length - 1; i++) {
		if (pageSampleDataArr[i].name == favoriteStyleName) {
			$("#favoriteStyle" + num).children("img").attr("src", pageSampleDataArr[i].path);
			$("#favoriteStyle" + num).children("p").text(pageSampleDataArr[i].name);
		}
	}
	var favoriteStyle0Name = $("#favoriteStyle0").children("p").text();
	var favoriteStyle1Name = $("#favoriteStyle1").children("p").text();
	cbCheck(favoriteStyle0Name);
	cbCheck(favoriteStyle1Name);
}

function shuffleRandom(length) {
	var numArr = new Array(length);
	for (var i = 0; i < length; i++) {
		numArr[i] = Math.floor(Math.random() * length);
		for (var j = 0; j < i; j++) {
			if (numArr[j] == numArr[i]) {
				i--;
				break;
			}
		}
	}
	return numArr;
}


//3번 페이지 표시
$(document).on('click', ".goPage3Btn", function () {
	$("#styleSelect1").css("display", "none");
	$("#styleSelect2").css("display", "none");
	$("#styleSelect3").css("display", "block");
});

$(document).on('click', ".goPage2Btn", function (event) {

	$("#styleSelect1").css("display", "none");
	$("#styleSelect2").css("display", "block");
	$("#styleSelect3").css("display", "none");
});


// });

//선호 스타일로 설정된 스타일을 style3체크박스에 표시함.
function cbCheck(favoriteStyleName) {
	for (var i = 0; i < 10; i++) {
		var cbLabel = $("#la" + i).text();
		if (cbLabel.toLowerCase() == favoriteStyleName.toLowerCase()) {
			$("#cb" + i).prop('checked', true);
			break;
		}
	}
}

//체크 1~3개까지
function checkLimitProcess(e) {
	var checkCount = $("input:checkbox[name=styleNo]:checked").length;
	if (checkCount < 3) {
		$(".finalJoinBtn").prop("disabled", true);
	} else if (checkCount > 3) {
		alert("세개 이상 선택하실 수 없습니다");
		$(e).prop('checked', false);
	} else {
			$(".finalJoinBtn").prop("disabled", false);
	}
}

