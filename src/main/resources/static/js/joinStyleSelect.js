	//이미지 변경 위한 변수 
		var pageNumArr = new Array();
		var pageSampleDataArr = new Array();
		var degree =1;


	//샘플 이미지 가져오기
	$(document).ready(function(){
		//좋아하는 스타일 목록
		var favoriteStyleArr = new Array();
		
		
		
	  $.ajax({
	   url : "/getFileList",
	   async: true,
	   type : 'POST',    
	   dataType: "json",    
	   success : function(data){
		 
	   setImgArray(data);
	   },
	   error: function(data){console.log("오류 발생"); console.log("data: "+data);},
	   beforeSend: function() {
	   },
	   complete: function() {

	   }       
	  });  
	  
	  //이미지 html에 넣는 부분
	  function setImgArray(data){
		  
		  console.log(data);
		  var length = data.sampleImgList.length;
		  var sampleDataArray = new Array();
		 
		  //데이터 샘플을 배열에 저장
		  for(var i=0; i< length; i++){
	
			  var sampleData = data.sampleImgList[i];
			  sampleDataArray[i] = sampleData;
		  }
		  pageSampleDataArr = sampleDataArray;
		  //배열 값 중 6개를 랜덤으로 선택한 뒤 지정된 위치에 넣음
			var numArr = shuffleRandom(length);
			pageNumArr = numArr;
			var j=0
			console.log(numArr);
				for(var num of numArr){
						var objImg = document.getElementById("styleImg"+j);
						var styleName = sampleDataArray[num].name;
						objImg.src = sampleDataArray[num].path;
						$("<p>"+styleName+"</p>").prependTo($("#styleName"+j));
						j++;
						console.log(j);
						if(j==6){
							console.log("6개 모두 출력")
							console.log(numArr);
							break;
						}
				}
		  
		 }
	  
		
	  //클릭시의 이벤트
		  $(document).on('click', ".styleName", function(event){
			    var targetId = $(this).attr("id");
				var styleName = $(this).children("p").text();
			
				favoriteStyleArr.push(styleName);
				checkArr(favoriteStyleArr);
				
				if(degree < 10){
					changeImg(targetId);
				}
				
				if(degree==10){
					setFavoriteStyle(favoriteStyleArr);
				}
				degree++;
		  });
		  
		  function changeImg(targetId){
//				console.log(pageNumArr);
//				console.log(pageSampleDataArr);
			  var nextNo = pageNumArr[5+degree];
			  	console.log(nextNo);
				$("#"+targetId).children("img").attr("src",pageSampleDataArr[nextNo].path);
				$("#"+targetId).children("p").text(pageSampleDataArr[nextNo].name);
				
			}

	  
	  });
	
	//선택 스타일이 10개가 되면 결과 화면으로 이동, 진행바 채워줌
	function checkArr(arr){
		var increaseRate = degree*11;
		var progress = $(".progress-bar").css("width",increaseRate+"%");
		
		if(arr.length == 10){
			alert("스타일 선택 완료");
			$("#styleSelect1").css("display","none");
			$("#styleSelect2").css("display","block");

		}
	}
	
	//제일 많이 지목된 스타일 선정, 노출
	function setFavoriteStyle(array){
		var styles = array;
		var counts = {};
		var compare = -1;
		var keys = [];
		var mostFrequent;
		
		//최다 빈도 스타일 결정
		for(var i = 0, len = array.length; i < len; i++){
		       var word = array[i];
		       
		       if(counts[word] === undefined){
		           counts[word] = 1;
		       }else{
		           counts[word] = counts[word] + 1;
		       }
		       if(counts[word] > compare){
		             compare = counts[word];
		             mostFrequent = styles[i];
		             secondFrequent = styles[i+1];
		       }
		    }
		 // console.log(mostFrequent);
		 // console.log(secondFrequent);
		  insertFavoritesImage(mostFrequent,0);
		  insertFavoritesImage(secondFrequent,1);
		  
	}
	  
	function insertFavoritesImage(favoriteStyleName,num){
		//html에 추가
		  
		  for(var i=0; i<= pageSampleDataArr.length-1; i++){
			  if(pageSampleDataArr[i].name == favoriteStyleName){
				  $("#favoriteStyle"+num).children("img").attr("src",pageSampleDataArr[i].path);
				  $("#favoriteStyle"+num).children("p").text(pageSampleDataArr[i].name);
			  }
		  }

	}

	function shuffleRandom(length){
        var numArr = new Array(length);
        
        for(var i =0; i < length; i++){
        	numArr[i] = Math.floor(Math.random()*length);
        	
        	for(var j=0; j<i; j++){
        		if(numArr[j] == numArr[i]) {
        			i--;
//        			console.log("중복값. 뒤로 돌아갑니다.");
//        			console.log(numArr);
        			break;
        		}
        	}
        	
        }
   	 	return numArr;
	}
	
	//공용 버튼
	 $(document).on('click', "#refreshBtn", function(event){
		  location.reload();
	  });
	 
	  $(document).on('click', "#skipBtn", function(event){
		  
		    $("#styleSelect1").css("display", "none");
		    $("#styleSelect2").css("display", "none");
		    $("#styleSelect3").css("display", "block");
		    console.log($("#favoriteStyle0").children("p").text());
		    console.log($("#la3").text());
		    
		    var favoriteStyle0Name = $("#favoriteStyle0").children("p").text();
		    var favoriteStyle1Name = $("#favoriteStyle1").children("p").text();
		    cbCheck(favoriteStyle0Name);
		    cbCheck(favoriteStyle1Name);
	  });
	
	  
	  //가입 버튼 : 선호 스타일 2개가 selectStyle의 항목 중 하나와 같으면 체크시킴.
	 // $(document).on('click', ".joinBtn", function(event){
		  
		   
	 // });
	  
	  //선호 스타일로 설정된 스타일을 style3체크박스에 표시함.
	  function cbCheck(favoriteStyleName){
		  var i=0;
		  console.log("#la"+i);
		  var cbLabel = $("#la"+i).text();
		  console.log(cbLabel);
		  for(i=0; i<= pageSampleDataArr.length; i++ ){
			   if(cbLabel == favoriteStyleName){
				   console("여기에 체크!");
				   $("#cb"+i).prop('checked', true);
			   }
		  }
	  }
	

	
